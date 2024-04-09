package com.aimachines.cdk.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.aimachines.cdk.bean.AppointmentResponseBean;
import com.aimachines.cdk.bean.HttpUtilBean;
import com.aimachines.cdk.bean.OpenRoRequestBean;
import com.aimachines.cdk.bean.RequestBean;
import com.aimachines.cdk.bean.RequestDailyBridge;
import com.aimachines.cdk.bean.Response;
import com.aimachines.cdk.bean.SalesResponseBean;
import com.aimachines.cdk.bean.ServiceClosedResponseBean;
import com.aimachines.cdk.bean.ServiceDetailBean;
import com.aimachines.cdk.bean.ServiceDetailResponseBean;
import com.aimachines.cdk.bean.ServiceResponseBean;
import com.aimachines.cdk.cron.ScheduleAppJobService;
import com.aimachines.cdk.cron.ScheduleJobService;
import com.aimachines.cdk.dto.AppointmentDto;
import com.aimachines.cdk.dto.AppointmentHeaderDto;
import com.aimachines.cdk.dto.ElementDto;
import com.aimachines.cdk.dto.EmployeeDetailsDto;
import com.aimachines.cdk.dto.EmployeeDto;
import com.aimachines.cdk.dto.ObjectDto;
import com.aimachines.cdk.dto.SalesDetailsDto;
import com.aimachines.cdk.dto.SalesDto;
import com.aimachines.cdk.dto.ServiceRepairOrderDto;
import com.aimachines.cdk.dto.ServiceRepairOrderHistoryDto;
import com.aimachines.cdk.model.Appointment;
import com.aimachines.cdk.model.Boc;
import com.aimachines.cdk.model.Customer;
import com.aimachines.cdk.model.Employee;
import com.aimachines.cdk.model.InventoryVehicle;
import com.aimachines.cdk.model.OpCodeMaster;
import com.aimachines.cdk.model.OpenRo;
import com.aimachines.cdk.model.OpenRoDetail;
import com.aimachines.cdk.model.Sales;
import com.aimachines.cdk.model.ServiceDetail;
import com.aimachines.cdk.model.ServiceStatusTrail;
import com.aimachines.cdk.model.ServiceVehicle;
import com.aimachines.cdk.model.Store;
import com.aimachines.cdk.repository.AppointmentRepo;
import com.aimachines.cdk.repository.BocRepo;
import com.aimachines.cdk.repository.CustomerRepo;
import com.aimachines.cdk.repository.EmployeeRepo;
import com.aimachines.cdk.repository.OpMasterRepo;
import com.aimachines.cdk.repository.OpenRoDetailRepo;
import com.aimachines.cdk.repository.OpenRoRepo;
import com.aimachines.cdk.repository.SaleRepo;
import com.aimachines.cdk.repository.ServiceDetailRepo;
import com.aimachines.cdk.repository.ServiceRepo;
import com.aimachines.cdk.repository.ServiceStatusTrailRepo;
import com.aimachines.cdk.repository.ServiceVehicleRepo;
import com.aimachines.cdk.repository.StoreRepo;
import com.aimachines.cdk.repository.VehicleRepo;
import com.aimachines.cdk.utils.ServiceConstants;
import com.aimachines.cdk.utils.ServiceException;

@Service
public class BridgeService {

	private static final String HCUST_BULK = "HEMPL_Bulk_Service";

	private static final String ALL = "ALL";

	private static final String END_DATE = "endDate";

	private static final String START_DATE = "startDate";

	private static final String SROD_HISTORY_DATE_RANGE = "SROD_History_DateRange";
	private static final String SROD_CLOSED_DATE_RANGE = "SROD_Closed_DateRange";

	private static final String FISH_DateRange = "FISH_DateRange";
	private static final String FISC_DateRange = "FISC_DateRange";
	
	private static final String APPT_BULK_H = "APPT_Bulk_H";
	private static final String APPT_DELTA_H = "APPT_Delta_H";

	private static final String HEMPL_ByItem = "HEMPL_ByItem";

	private static final String CDK_API_PASSWORD = "cdk-api-password";

	private static final String CDK_API_USER = "cdk-api-user";

	private static final String CDK_API_RO_HISTORY = "cdk-api-ro-history";
	private static final String CDK_API_RO_CLOSED = "cdk-api-ro-closed";
	
	private static final String CDK_API_SALES_HISTORY = "cdk-api-sales-history";
	private static final String CDK_API_SALES_CLOSED = "cdk-api-sales-closed";

	private static final String CDK_API_APPOINTMENT_HEADER_INITIAL = "cdk-api-appointment-initial-header";
	private static final String CDK_API_EMPLOYEE = "cdk-api-emp";

	private static final String CDK_API_RO_OPEN = "cdk-api-ro-open";
	private static final String SROD_OPEN_WIP = "SROD_Open_WIP";

	private static Logger logger = LoggerFactory.getLogger(BridgeService.class);

	private static final String COMMA_DELIMTER = ",";
	
	private static final String SPACE_DELIMTER = " ";


	@Autowired
	private Environment env;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private OpMasterRepo opMasterRepo;

	@Autowired
	private ServiceDetailRepo serviceDetailRepo;

	@Autowired
	private ServiceRepo serviceRepo;

	@Autowired
	private OpenRoDetailRepo openRoDetailRepo;

	@Autowired
	private OpenRoRepo openRoRepo;

	@Autowired
	private VehicleRepo vehicleRepo;

	@Autowired
	private ServiceVehicleRepo serviceVehicleRepo;

	@Autowired
	private HttpUtilService httpUtilService;

	@Autowired
	private ScheduleJobService scheduleJobService;
	
	@Autowired
	private ScheduleAppJobService scheduleAppJobService;

	@Autowired
	private BocRepo bocRepo;

	@Autowired
	private StoreRepo storeRepo;

	@Autowired
	private ServiceStatusTrailRepo serviceStatusTrailRepo;

	@Autowired
	private AppointmentRepo appointmentRepo;

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private SaleRepo saleRepo;
	

	public Response startInitialClosedROBridge(RequestBean bean) throws ServiceException {

		Response response = null;
		response = new Response();

		getDateRange(bean);

		List<Map<String, String>> extractionDateRangeList = new BridgeService().getDateRange(bean);

		// start the extraction
		for (Map<String, String> map : extractionDateRangeList) {
			executeClosedRoExtractAndLoad(map.get(START_DATE), map.get(END_DATE));
		}

		response.setStatus("200");
		response.setMessage(ServiceConstants.SUCCESS);
		return response;
	}

	public Response startDailyClosedROBridge(RequestDailyBridge bean) throws ServiceException {

		Response response = null;
		response = new Response();

		// delayed start
		LocalDateTime now = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
		LocalDateTime schduleDate = now.plusSeconds(20);

		scheduleJobService.schedule(bean.getStartDate(), schduleDate, SROD_HISTORY_DATE_RANGE, false);

		response.setStatus("200");
		response.setMessage(ServiceConstants.SUCCESS);
		return response;
	}

	public Response startInitialAppointmentBridge() throws ServiceException {

		Response response = null;
		response = new Response();

		executeAppointmentsLoad();

		response.setStatus("200");
		response.setMessage(ServiceConstants.SUCCESS);
		return response;
	}
	
	
	public Response startInitialEmployeeBridge() throws ServiceException {

		Response response = null;
		response = new Response();

		executeEmployeeLoad();

		response.setStatus("200");
		response.setMessage(ServiceConstants.SUCCESS);
		return response;
	}
	
	public Response startInitialSalesBridge(RequestBean bean) throws ServiceException {

		Response response = null;
		response = new Response();

		getDateRange(bean);

		List<Map<String, String>> extractionDateRangeList = new BridgeService().getDateRange(bean);

		// start the extraction
		for (Map<String, String> map : extractionDateRangeList) {
			executeInitialSalesExtractAndLoad(map.get(START_DATE), map.get(END_DATE));
		}

		response.setStatus("200");
		response.setMessage(ServiceConstants.SUCCESS);
		return response;
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @throws ServiceException
	 */
	public void executeClosedRoExtractAndLoad(String startDate, String endDate) throws ServiceException {
		HttpUtilBean httpUtilBean = null;
		Map<String, String> params = null;
		try {
			Iterable<Store> stores = storeRepo.findAll();
			httpUtilBean = initializeExtraction(startDate, endDate, SROD_HISTORY_DATE_RANGE,
					env.getProperty(CDK_API_RO_HISTORY));

			List<Boc> bocs = bocRepo.findByBocId("1");

			for (Store store : stores) {

				params = new HashMap<String, String>();
				params.put("dealerId", store.getStoreId());
				params.put("queryId", httpUtilBean.getQueryId());
				params.put("qparamStartDate", httpUtilBean.getStartDate());
				params.put("qparamEndDate", httpUtilBean.getEndDate());

				logger.info("Starting extract with start date " + httpUtilBean.getStartDate() + " and end date "
						+ httpUtilBean.getEndDate() + " for query Id " + SROD_HISTORY_DATE_RANGE + " for dealer Id "
						+ store.getStoreId());

				ServiceRepairOrderDto repairOrderDto = httpUtilService
						.loadExtract(httpUtilService.executeHttpExtract(httpUtilBean, params));

				loadServiceRepairOrders(repairOrderDto, repairOrderDto.getServiceRepairOrdersClosed(), bocs.get(0),
						store);

				logger.info("Finishing extract with start date " + httpUtilBean.getStartDate() + " and end date "
						+ httpUtilBean.getEndDate() + " for query Id " + SROD_HISTORY_DATE_RANGE + " for dealer Id "
						+ store.getStoreId());
			}

		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void executeInitialSalesExtractAndLoad(String startDate, String endDate) throws ServiceException {
		HttpUtilBean httpUtilBean = null;
		Map<String, String> params = null;
		try {
			Iterable<Store> stores = storeRepo.findAll();
			httpUtilBean = initializeExtraction(startDate, endDate, FISH_DateRange,env.getProperty(CDK_API_SALES_HISTORY));

			List<Boc> bocs = bocRepo.findByBocId("1");

			for (Store store : stores) {

				params = new HashMap<String, String>();
				params.put("dealerId", store.getStoreId());
				params.put("queryId", httpUtilBean.getQueryId());
				params.put("qparamStartDate", httpUtilBean.getStartDate());
				params.put("qparamEndDate", httpUtilBean.getEndDate());
				params.put("status", "\"C\"\"B\"\"I\"\"F\"\"U\"");

				logger.info("Starting extract with start date " + httpUtilBean.getStartDate() + " and end date "+ httpUtilBean.getEndDate() + " for query Id " + FISH_DateRange + " for dealer Id "+ store.getStoreId());

				SalesDto salesDto = httpUtilService.loadSalesExtract(httpUtilService.executeHttpExtract(httpUtilBean, params));

				loadSalesOrders(salesDto, salesDto.getSalesDetailsDtos(), bocs.get(0),store);

				logger.info("Finishing extract with start date " + httpUtilBean.getStartDate() + " and end date " + httpUtilBean.getEndDate() + " for query Id " + FISH_DateRange + " for dealer Id " + store.getStoreId());
			}

		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * @param startDate
	 * @param endDate
	 * @throws ServiceException
	 */
	public void executeDailySalesExtractAndLoad() throws ServiceException {
		HttpUtilBean httpUtilBean = null;
		Map<String, String> params = null;
		try {
			Iterable<Store> stores = storeRepo.findAll();
			httpUtilBean = initializeExtraction(getCurrentDate(), getCurrentDate(), FISC_DateRange,env.getProperty(CDK_API_SALES_CLOSED));

			List<Boc> bocs = bocRepo.findByBocId("1");

			for (Store store : stores) {

				params = new HashMap<String, String>();
				params.put("dealerId", store.getStoreId());
				params.put("queryId", httpUtilBean.getQueryId());
				params.put("qparamStartDate", httpUtilBean.getStartDate());
				params.put("qparamEndDate", httpUtilBean.getEndDate());
				params.put("status", "\"C\"\"B\"\"I\"\"F\"\"U\"");

				logger.info("Starting extract with start date " + httpUtilBean.getStartDate() + " and end date "+ httpUtilBean.getEndDate() + " for query Id " + FISC_DateRange + " for dealer Id "+ store.getStoreId());

				SalesDto salesDto = httpUtilService.loadSalesExtract(httpUtilService.executeHttpExtract(httpUtilBean, params));

				loadSalesOrders(salesDto, salesDto.getSalesDetailsDtos(), bocs.get(0),store);

				logger.info("Finishing extract with start date " + httpUtilBean.getStartDate() + " and end date " + httpUtilBean.getEndDate() + " for query Id " + FISC_DateRange + " for dealer Id " + store.getStoreId());
			}

		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @throws ServiceException
	 */
	public void executeDailyClosedRoExtractAndLoad() throws ServiceException {
		HttpUtilBean httpUtilBean = null;
		Map<String, String> params = null;
		try {
			Iterable<Store> stores = storeRepo.findAll();

			httpUtilBean = initializeExtraction(getCurrentDate(), getCurrentDate(), SROD_CLOSED_DATE_RANGE,
					env.getProperty(CDK_API_RO_CLOSED));

			List<Boc> bocs = bocRepo.findByBocId("1");

			for (Store store : stores) {

				params = new HashMap<String, String>();
				params.put("dealerId", store.getStoreId());
				params.put("queryId", httpUtilBean.getQueryId());
				params.put("qparamStartDate", httpUtilBean.getStartDate());
				params.put("qparamEndDate", httpUtilBean.getEndDate());

				logger.info("Starting extract with start date " + httpUtilBean.getStartDate() + " and end date "
						+ httpUtilBean.getEndDate() + " for query Id " + SROD_CLOSED_DATE_RANGE + " for dealer Id "
						+ store.getStoreId());

				ServiceRepairOrderDto repairOrderDto = httpUtilService
						.loadExtract(httpUtilService.executeHttpExtract(httpUtilBean, params));

				loadServiceRepairOrders(repairOrderDto, repairOrderDto.getServiceRepairOrdersClosed(), bocs.get(0),
						store);

				logger.info("Finishing extract with start date " + httpUtilBean.getStartDate() + " and end date "
						+ httpUtilBean.getEndDate() + " for query Id " + SROD_CLOSED_DATE_RANGE + " for dealer Id "
						+ store.getStoreId());
			}

		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	private String getCurrentDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());

		return now.format(formatter);
	}

	public void executeAppointmentsLoadDaily() throws ServiceException {
		HttpUtilBean httpUtilBean = null;
		Map<String, String> params = null;

		try {
			Iterable<Store> stores = storeRepo.findAll();
			httpUtilBean = initializeExtraction(null, null, APPT_DELTA_H,env.getProperty(CDK_API_APPOINTMENT_HEADER_INITIAL));

			List<Boc> bocs = bocRepo.findByBocId("1");
			
			

			for (Store store : stores) {
				params = new HashMap<String, String>();
				params.put("dealerId", store.getStoreId());
				params.put("queryId", httpUtilBean.getQueryId());
				params.put("deltaDate", getCurrentDate());
				
				logger.info("Starting extract for appointments "+ " for query Id " + APPT_DELTA_H + " for dealer Id "
						+ store.getStoreId());

				AppointmentDto repairOrderDto = httpUtilService.loadAppointmentExtract(httpUtilService.executeHttpExtract(httpUtilBean, params));

				loadAppointments(repairOrderDto, repairOrderDto.getAppointments(), store, bocs.get(0));

				logger.info("Finsihing extract for appointments "+ " for query Id " + APPT_DELTA_H + " for dealer Id "
						+ store.getStoreId());
			}

		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	public void executeAppointmentsLoad() throws ServiceException {
		HttpUtilBean httpUtilBean = null;
		Map<String, String> params = null;

		try {
			Iterable<Store> stores = storeRepo.findAll();
			httpUtilBean = initializeExtraction(null, null, APPT_BULK_H,env.getProperty(CDK_API_APPOINTMENT_HEADER_INITIAL));

			List<Boc> bocs = bocRepo.findByBocId("1");

			for (Store store : stores) {
				params = new HashMap<String, String>();
				params.put("dealerId", store.getStoreId());
				params.put("queryId", httpUtilBean.getQueryId());

				List<Appointment> prevAppmts =  appointmentRepo.findByStoreIdFk(store.getId());
				if(!CollectionUtils.isEmpty(prevAppmts)) {
					appointmentRepo.deleteAll(prevAppmts);
				}
				
				logger.info("Starting extract for appointments "+ " for query Id " + APPT_BULK_H + " for dealer Id "
						+ store.getStoreId());
				
				AppointmentDto repairOrderDto = httpUtilService
						.loadAppointmentExtract(httpUtilService.executeHttpExtract(httpUtilBean, params));

				loadAppointments(repairOrderDto, repairOrderDto.getAppointments(), store, bocs.get(0));

				logger.info("Finshing extract for appointments "+ " for query Id " + APPT_BULK_H + " for dealer Id "
						+ store.getStoreId());
			}

		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	public void executeEmployeeLoad() throws ServiceException {
		HttpUtilBean httpUtilBean = null;
		Map<String, String> params = null;

		try {
			Iterable<Store> stores = storeRepo.findAll();
			httpUtilBean = initializeExtraction(null, null, HCUST_BULK, env.getProperty(CDK_API_EMPLOYEE));
			List<Boc> bocs = bocRepo.findByBocId("1");
			for (Store store : stores) {
				params = new HashMap<String, String>();
				params.put("dealerId", store.getStoreId());
				params.put("queryId", httpUtilBean.getQueryId());
				

				logger.info("Starting extract for employee "+ " for query Id " + HCUST_BULK + " for dealer Id "
						+ store.getStoreId());
				

				EmployeeDto employeeDto = httpUtilService.loadEmployeeExtract(httpUtilService.executeHttpExtract(httpUtilBean, params));


				if (employeeDto != null && employeeDto.getEmployeeDetailsDtos() != null) {
					Employee employee = null;
					for(EmployeeDetailsDto employeeItem : employeeDto.getEmployeeDetailsList()){
						
						List<Employee> empList = employeeRepo.findByStoreIdFkAndEmpId(store.getId(),employeeItem.getId());
						if(CollectionUtils.isEmpty(empList)) employee = new Employee();
						else employee = empList.get(0);
						 
						employee.setStoreIdFk(store.getId());
						employee.setBocIdFk(bocs.get(0).getId());

						employee.setEmpId(employeeItem.getId());
						employee.setFirstName(employeeItem.getName());
						
						employeeRepo.save(employee);
					}
					
				}

				logger.info("Finishing extract for employee "+ " for query Id " + HCUST_BULK + " for dealer Id "+ store.getStoreId());
			}

		} catch (ServiceException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
	
	/**
	 * @throws ServiceException
	 */
	public Iterable<com.aimachines.cdk.model.Service> getAllServiceOrder() throws ServiceException {

		Iterable<com.aimachines.cdk.model.Service> data = null;

		data = serviceRepo.findAll();

		return data;
	}

	public Iterable<Store> getAllStores() throws ServiceException {

		Iterable<Store> data = null;

		data = storeRepo.findAll();

		return data;
	}

	public List<Employee> getAdivsorByStore(Long storeId) throws ServiceException {

		List<Employee> data = null;

		data = new ArrayList<Employee>();

		List<OpenRo> openRoList = openRoRepo.findByStoreIdFk(storeId);

		if (!CollectionUtils.isEmpty(openRoList)) {
			List<String> advisorList = new ArrayList<String>();
			for (OpenRo openRo : openRoList) {
				if (openRo.getAdvisorNo() != null)
					advisorList.add(openRo.getAdvisorNo());
			}

			List<String> distinctAdvisor = advisorList.stream().distinct().collect(Collectors.toList());

			for (String empId : distinctAdvisor) {

				List<Employee> empList = employeeRepo.findByEmpId(empId);
				data.add(empList.get(0));
			}
		}

		return data;
	}
	
	

	public ServiceDetailResponseBean getServiceOrderDetail(String roNum) throws ServiceException {

		List<ServiceDetail> data = null;
		ServiceDetailResponseBean bean = new ServiceDetailResponseBean();
		data = serviceDetailRepo.findByRoNumber(roNum);

		if (!CollectionUtils.isEmpty(data)) {

			List<com.aimachines.cdk.model.Service> services = serviceRepo.findByRoNumber(roNum);
			com.aimachines.cdk.model.Service service = services.get(0);
			String custNo = service.getCustNo();
			String vin = service.getVin();
			List<InventoryVehicle> vehList = vehicleRepo.findByVin(vin);
			List<Customer> customers = customerRepo.findByCustNo(custNo);

			bean.setCustomer(customers.get(0));
			bean.setService(service);
			bean.setVehicle(vehList.get(0));
			List<ServiceDetailBean> linDetails = new ArrayList<ServiceDetailBean>();
			for (ServiceDetail serviceDetail : data) {
				ServiceDetailBean serviceDetailBean = new ServiceDetailBean();
				serviceDetailBean.setService(serviceDetail);

				Optional<OpCodeMaster> codeMaster = opMasterRepo.findById(serviceDetail.getOpCodeMasterIdFk());
				if (codeMaster.isPresent()) {
					serviceDetailBean.setCodeMaster(codeMaster.get());
				}
				linDetails.add(serviceDetailBean);
			}
			bean.setLineItems(linDetails);
		}

		return bean;
	}

	/**
	 * @throws ServiceException
	 */
	public Iterable<OpenRo> getAllOpenServiceOrder() throws ServiceException {

		Iterable<OpenRo> data = null;

		data = openRoRepo.findAll();

		return data;
	}

	/**
	 * @throws ServiceException
	 */
	public List<ServiceResponseBean> getAllOpenServiceOrderByStoreId(OpenRoRequestBean openRoRequestBean)
			throws ServiceException {

		List<OpenRo> data = null;

		List<Store> stores = storeRepo.findByStoreId(openRoRequestBean.getStoreId());

		if (StringUtils.isEmpty(openRoRequestBean.getAdvisorNo())
				|| openRoRequestBean.getAdvisorNo().equalsIgnoreCase(ALL))
			data = openRoRepo.findByStoreIdFkAndStatusCodeIn(stores.get(0).getId(), openRoRequestBean.getStatusCode());
		else
			data = openRoRepo.findByStoreIdFkAndStatusCodeInAndAdvisorNo(stores.get(0).getId(),
					openRoRequestBean.getStatusCode(), openRoRequestBean.getAdvisorNo());

		List<ServiceResponseBean> serviceResponseBeans = new ArrayList<ServiceResponseBean>();
		List<OpenRoDetail> detailsList = new ArrayList<OpenRoDetail>();

		if (!CollectionUtils.isEmpty(data)) {

			for (OpenRo openRo : data) {
				ServiceResponseBean serviceResponseBean = new ServiceResponseBean();
				serviceResponseBean.setService(openRo);
				
				
				Date openDate= new Date(openRo.getOpenedDate().getTime());  
				
				long diff = new Date().getTime() - openDate.getTime();
				serviceResponseBean.setDaysDiff(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
				
				String empId = openRo.getAdvisorNo();
				if(empId!=null) {
					List<Employee> empList = employeeRepo.findByEmpId(empId);
					if(!CollectionUtils.isEmpty(empList)) {
						serviceResponseBean.setAdvisor(empList.get(0));
					}
				}

				List<Customer> customers = customerRepo.findByCustNo(openRo.getCustNo());
				serviceResponseBean.setCustomer(customers.get(0));

				List<InventoryVehicle> vehList = vehicleRepo.findByVin(openRo.getVin());
				serviceResponseBean.setVehicle(vehList.get(0));
				
				List<OpenRoDetail> openRoDetails =  openRoDetailRepo.findByRoNumber(openRo.getRoNumber());
				
				List<Employee> techNicianNos =  new ArrayList<Employee>();
				detailsList = new ArrayList<OpenRoDetail>();
				if(!CollectionUtils.isEmpty(openRoDetails)) {
					
					for(OpenRoDetail openRoDetail : openRoDetails) {
						
						if(openRoDetail.getLaborTechnician()!=null) {
							List<Employee> empList = employeeRepo.findByEmpId(openRoDetail.getLaborTechnician());
							if(!CollectionUtils.isEmpty(empList)) {
								techNicianNos.add(empList.get(0));
							}
						}
						if(openRoDetail!=null)
							detailsList.add(openRoDetail);
					}
				}
				
				serviceResponseBean.setLineItemList(detailsList);
				serviceResponseBean.setTechnicianId(techNicianNos);
				
				serviceResponseBeans.add(serviceResponseBean);
			}
		}

		return serviceResponseBeans;
	}
	
	
	
	/**
	 * @throws ServiceException
	 */
	public List<ServiceStatusTrail> getAuditTrial(String roNumber) throws ServiceException {

		List<ServiceStatusTrail> data = null;
		
		data = serviceStatusTrailRepo.findByRoNumberOrderByCreatedOnDesc(roNumber);
		
		List<com.aimachines.cdk.model.Service> serviceList = serviceRepo.findByRoNumber(roNumber);
		
		if(!CollectionUtils.isEmpty(serviceList)) {
			com.aimachines.cdk.model.Service service = serviceList.get(0);
			
			if(!CollectionUtils.isEmpty(data))  {
				ServiceStatusTrail serviceStatusTrail = data.get(0);
				String prevStatus = serviceStatusTrail != null ? serviceStatusTrail.getCurrentStatusCode(): null;
				
				ServiceStatusTrail statusTrail = new ServiceStatusTrail();
				statusTrail.setPrevStatusCode(prevStatus);
				statusTrail.setCurrentStatusCode("C98");
				statusTrail.setCreatedOn(service.getCloseDate());
				statusTrail.setRoNumber(roNumber);
			}
		}

		return data;
	}

	/**
	 * @throws ServiceException
	 */
	public List<AppointmentResponseBean> getAllAppointmentByStoreIdAndAppointmentDate(String dealerId,
			String appointmentDate) throws ServiceException {

		List<Appointment> data = null;

		List<Store> stores = storeRepo.findByStoreId(dealerId);
		data = appointmentRepo.findByStoreIdFkAndAppointmentDate(stores.get(0).getId(), appointmentDate);

		List<AppointmentResponseBean> appointmentResponseBeans = new ArrayList<AppointmentResponseBean>();

		if (!CollectionUtils.isEmpty(data)) {

			for (Appointment appointment : data) {
				AppointmentResponseBean appointmentResponseBean = new AppointmentResponseBean();
				appointmentResponseBean.setAppointment(appointment);

				List<Customer> customers = customerRepo.findByCustNo(appointment.getCustNo());
				appointmentResponseBean.setCustomer(customers.get(0));

				List<InventoryVehicle> vehList = vehicleRepo.findByVin(appointment.getVin());
				appointmentResponseBean.setVehicle(vehList.get(0));

				appointmentResponseBeans.add(appointmentResponseBean);
			}
		}
		return appointmentResponseBeans;
	}

	public List<ServiceClosedResponseBean> getClostedRoByDealerAndDate(String dealerId, String closedDate,
			String advisorNo) throws ServiceException, ParseException {

		List<com.aimachines.cdk.model.Service> data = null;

		List<Store> stores = storeRepo.findByStoreId(dealerId);

		if (advisorNo.equalsIgnoreCase(ALL))
			data = serviceRepo.findByStoreIdFkAndCloseDate(stores.get(0).getId(), closedDate);
		else
			data = serviceRepo.findByStoreIdFkAndCloseDateAndAdvisorNo(stores.get(0).getId(), closedDate, advisorNo);

		List<ServiceClosedResponseBean> closedResponseBeans = new ArrayList<ServiceClosedResponseBean>();

		if (!CollectionUtils.isEmpty(data)) {

			for (com.aimachines.cdk.model.Service service : data) {
				ServiceClosedResponseBean closedResponseBean = new ServiceClosedResponseBean();
				closedResponseBean.setService(service);

				List<Customer> customers = customerRepo.findByCustNo(service.getCustNo());
				closedResponseBean.setCustomer(customers.get(0));

				List<InventoryVehicle> vehList = vehicleRepo.findByVin(service.getVin());
				closedResponseBean.setVehicle(vehList.get(0));

				closedResponseBeans.add(closedResponseBean);
			}
		}
		return closedResponseBeans;
	}
	
	public List<SalesResponseBean> getSalesByDealerAndDate(String dealerId, String soldDate) throws ServiceException, ParseException {

		List<Sales> data = null;

		List<Store> stores = storeRepo.findByStoreId(dealerId);

		data = saleRepo.findByStoreIdFkAndSoldDate(stores.get(0).getId(), soldDate);

		List<SalesResponseBean> salesResponseBeans = new ArrayList<SalesResponseBean>();

		if (!CollectionUtils.isEmpty(data)) {

			for (Sales sale : data) {
				SalesResponseBean salesResponseBean = new SalesResponseBean();
				salesResponseBean.setSale(sale);;

				List<Customer> customers = customerRepo.findByCustNo(sale.getCustNo());
				salesResponseBean.setCustomer(customers.get(0));

				List<InventoryVehicle> vehList = vehicleRepo.findByVin(sale.getVin());
				salesResponseBean.setVehicle(vehList.get(0));
				
				
				if(sale.getSalesman()!=null) {
					List<Employee> empList = employeeRepo.findByEmpId(sale.getSalesman());
					if(!CollectionUtils.isEmpty(empList)) {
						salesResponseBean.setSalesPerson(empList.get(0));					
					}
				}
				
				if(sale.getSlsManager()!=null) {
					List<Employee> empList = employeeRepo.findByEmpId(sale.getSlsManager());
					if(!CollectionUtils.isEmpty(empList)) {
						salesResponseBean.setSalesManager(empList.get(0));			
					}
				}
				
				if(sale.getFiManager()!=null) {
					List<Employee> empList = employeeRepo.findByEmpId(sale.getFiManager());
					if(!CollectionUtils.isEmpty(empList)) {
						salesResponseBean.setF1Manager((empList.get(0)));			
					}
				}
				
				salesResponseBeans.add(salesResponseBean);
			}
		}
		return salesResponseBeans;
	}
	
	public ServiceDetailResponseBean getOpenServiceOrderDetailByRoAndStoreId(String roNum,Long storeId) throws ServiceException {

		List<OpenRoDetail> data = null;
		ServiceDetailResponseBean bean = new ServiceDetailResponseBean();
		data = openRoDetailRepo.findByRoNumber(roNum);

		if (!CollectionUtils.isEmpty(data)) {

			List<OpenRo> services = openRoRepo.findByRoNumber(roNum);
			OpenRo service = services.get(0);
			String custNo = service.getCustNo();
			String vin = service.getVin();
			List<InventoryVehicle> vehList = vehicleRepo.findByVin(vin);
			List<Customer> customers = customerRepo.findByCustNo(custNo);
			
			

			bean.setCustomer(customers.get(0));
			bean.setOpenRo(service);
			bean.setVehicle(vehList.get(0));
			List<ServiceDetailBean> linDetails = new ArrayList<ServiceDetailBean>();
			for (OpenRoDetail serviceDetail : data) {
				ServiceDetailBean serviceDetailBean = new ServiceDetailBean();
				serviceDetailBean.setOpenRoDetail(serviceDetail);

				String technicianNo = serviceDetail.getLaborTechnician();
				if(technicianNo!=null) {
					List<Employee> empList = employeeRepo.findByStoreIdFkAndEmpId(storeId, technicianNo);
					if(!CollectionUtils.isEmpty(empList)) serviceDetailBean.setTechnician(empList.get(0));
				}
				Optional<OpCodeMaster> codeMaster = opMasterRepo.findById(serviceDetail.getOpCodeMasterIdFk());
				if (codeMaster.isPresent()) {
					serviceDetailBean.setCodeMaster(codeMaster.get());
				}
				linDetails.add(serviceDetailBean);
			}
			bean.setLineItems(linDetails);
		}

		return bean;
	}

	public ServiceDetailResponseBean getOpenServiceOrderDetail(String roNum) throws ServiceException {

		List<OpenRoDetail> data = null;
		ServiceDetailResponseBean bean = new ServiceDetailResponseBean();
		data = openRoDetailRepo.findByRoNumber(roNum);

		if (!CollectionUtils.isEmpty(data)) {

			List<OpenRo> services = openRoRepo.findByRoNumber(roNum);
			OpenRo service = services.get(0);
			String custNo = service.getCustNo();
			String vin = service.getVin();
			List<InventoryVehicle> vehList = vehicleRepo.findByVin(vin);
			List<Customer> customers = customerRepo.findByCustNo(custNo);

			bean.setCustomer(customers.get(0));
			bean.setOpenRo(service);
			bean.setVehicle(vehList.get(0));
			List<ServiceDetailBean> linDetails = new ArrayList<ServiceDetailBean>();
			for (OpenRoDetail serviceDetail : data) {
				ServiceDetailBean serviceDetailBean = new ServiceDetailBean();
				serviceDetailBean.setOpenRoDetail(serviceDetail);

				Optional<OpCodeMaster> codeMaster = opMasterRepo.findById(serviceDetail.getOpCodeMasterIdFk());
				if (codeMaster.isPresent()) {
					serviceDetailBean.setCodeMaster(codeMaster.get());
				}
				linDetails.add(serviceDetailBean);
			}
			bean.setLineItems(linDetails);
		}

		return bean;
	}

	/**
	 * @throws ServiceException
	 */
	public Response startOpenRosAndAppointmentBridge() throws ServiceException {

		Response response = null;

		try {
			response = new Response();

			LocalDateTime now = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
			LocalDateTime schduleDate = now.plusSeconds(20);

			scheduleJobService.schedule(null, schduleDate, SROD_OPEN_WIP, true);

			// executeOpenRoExtractAndLoad();

			response.setStatus("200");
			response.setMessage(ServiceConstants.SUCCESS);

		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

		return response;
	}
	
	public Response startDailyBulkAppointmentBridge() throws ServiceException {

		Response response = null;
		response = new Response();
		
		LocalDateTime now = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
		LocalDateTime schduleDate = now.plusSeconds(20);

		scheduleAppJobService.schedule(schduleDate, APPT_BULK_H);

		response.setStatus("200");
		response.setMessage(ServiceConstants.SUCCESS);
		return response;
	}
	

	/**
	 * @throws ParseException
	 * @throws ServiceException
	 * @throws IOException
	 */
	public void executeOpenRoExtractAndLoad() throws ParseException, ServiceException, IOException {
		
		// execute Appointments followed by open Ros
		executeAppointmentsLoadDaily();
		
		// execute the Open RO
		HttpUtilBean httpUtilBean = null;
		Map<String, String> params = null;
		Iterable<Store> stores = storeRepo.findAll();
		List<Boc> bocs = bocRepo.findByBocId("1");
		httpUtilBean = initializeExtraction("", "", SROD_OPEN_WIP, env.getProperty(CDK_API_RO_OPEN));

		for (Store store : stores) {

			params = new HashMap<String, String>();
			params.put("dealerId", store.getStoreId());
			params.put("queryId", httpUtilBean.getQueryId());

			logger.info("Starting extract with start date " + httpUtilBean.getStartDate() + " and end date "
					+ httpUtilBean.getEndDate() + " for query Id " + SROD_OPEN_WIP + " for store Id "
					+ store.getStoreId());

			ServiceRepairOrderDto repairOrderDto = httpUtilService
					.loadExtract(httpUtilService.executeHttpExtract(httpUtilBean, params));

			// load the open ros
			loadOpenServiceRepairOrders(repairOrderDto, repairOrderDto.getServiceRepairOrdersOpen(), bocs.get(0),
					store);

			logger.info("Finishing extract with start date " + httpUtilBean.getStartDate() + " and end date "
					+ httpUtilBean.getEndDate() + " for query Id " + SROD_OPEN_WIP + " for store Id "
					+ store.getStoreId());
		}
		
		// execute Daily Sales Extract and load
		executeDailySalesExtractAndLoad();

	}
	

	/**
	 * @param repairOrderDto
	 * @param repairOrders
	 * @throws ParseException
	 */
	private void loadAppointments(AppointmentDto appointmentDto, AppointmentHeaderDto[] appointmentHeaderDtos,
			Store store, Boc boc) throws ParseException {
		if (appointmentDto != null) {

			if (appointmentHeaderDtos != null && appointmentHeaderDtos.length > 0) {

				for (AppointmentHeaderDto orderDto : appointmentHeaderDtos) {

					logger.info("Starting processing for apointment " + orderDto.getApptID() + " for cust No " + orderDto.getCustNo());
					List<OpenRo> openRos = openRoRepo.findByStoreIdFkAndCustNoAndVin(store.getId(),orderDto.getCustNo(), orderDto.getVin());
					
					if(CollectionUtils.isEmpty(openRos)) {
						String custNo = orderDto.getCustNo();
						
						if (!StringUtils.isEmpty(custNo)) {

							List<Customer> customers = customerRepo.findByCustNo(custNo);

							// handle customer logic
							Customer customer = null;
							if (CollectionUtils.isEmpty(customers)) {
								customer = new Customer();
								customer.setCustNo(custNo);
								customer.setBlockSms(false); // default to false since not null

								customer.setLastName(orderDto.getLastName());
								customer.setFirstName(orderDto.getFirstName());

								customer.setCellular(orderDto.getCellular());
								customer.setEmail(orderDto.getEmail());
								customer.setBocIdFk(boc.getId());
								// customer.setCreatedOn(new Timestamp(new Date().getTime()));

								customerRepo.save(customer);

							} else
								customer = customers.get(0);

							String vin = orderDto.getVin();
							List<InventoryVehicle> vehicles = vehicleRepo.findByVin(vin);

							// handle inventory logic
							InventoryVehicle inventoryVehicle = null;
							if (CollectionUtils.isEmpty(vehicles)) {
								inventoryVehicle = new InventoryVehicle();

								inventoryVehicle.setCustNo(custNo);
								inventoryVehicle.setCustomerIdFk(customer.getId());
								inventoryVehicle.setVin(orderDto.getVin());
								inventoryVehicle.setMake(orderDto.getMake());
								inventoryVehicle.setModel(orderDto.getMake());
								inventoryVehicle.setYear(orderDto.getModelYear());
								inventoryVehicle.setMileage(Long.valueOf(
										StringUtils.isEmpty(orderDto.getApptMileage()) ? "0" : orderDto.getApptMileage()));
								inventoryVehicle.setBocIdFk(boc.getId());
								inventoryVehicle.setStoreIdFk(store.getId());

								vehicleRepo.save(inventoryVehicle);
							} else
								inventoryVehicle = vehicles.get(0);

							// handle service vehicle logic
							List<ServiceVehicle> serviceVehicles = serviceVehicleRepo.findByVinAndCustNo(vin, custNo);
							ServiceVehicle serviceVehicle = null;
							if (CollectionUtils.isEmpty(serviceVehicles)) {
								serviceVehicle = new ServiceVehicle();

								serviceVehicle.setCustNo(custNo);
								serviceVehicle.setCustomerIdFk(customer.getId());
								serviceVehicle.setVin(orderDto.getVin());
								serviceVehicle.setMake(orderDto.getMake());
								serviceVehicle.setModel(orderDto.getMake());
								serviceVehicle.setYear(orderDto.getModelYear());
								serviceVehicle.setMileage(Long.valueOf(
										StringUtils.isEmpty(orderDto.getApptMileage()) ? "0" : orderDto.getApptMileage()));
								serviceVehicle.setBocIdFk(boc.getId());

								serviceVehicleRepo.save(serviceVehicle);
							} else
								serviceVehicle = serviceVehicles.get(0);

							// handle appointments
							List<Appointment> appointments = appointmentRepo.findByAppointmentId(Long.valueOf(orderDto.getApptID()));
							Appointment appointment = null;
							if (CollectionUtils.isEmpty(appointments))
								appointment = new Appointment();

							else
								appointment = appointments.get(0);

							// save or update
							appointment.setCustNo(custNo);
							appointment.setCustomerIdFk(customer.getId());

							appointment.setAppointmentId(Long.valueOf(orderDto.getApptID()));
							appointment.setAppointmentDate(orderDto.getAppointmentDate());
							appointment.setAppointmentTime(orderDto.getAppointmentTime());
							appointment.setPromisedDate(orderDto.getPromiseDate());
							appointment.setPromisedTime(orderDto.getPromiseTime());

							appointment.setVin(orderDto.getVin());
							appointment.setServiceVehicleIdFk(serviceVehicle.getId());

							appointment.setStoreIdFk(store.getId());
							// service.setCreatedOn(new Timestamp(new Date().getTime()));
							appointmentRepo.save(appointment);

						} else
							logger.info("Skipping the bridge since customer Id is  empty wirh Appointment Id "+ orderDto.getApptID());
					}
					

					logger.info("Finishing processing for appointment Id " + orderDto.getApptID() + " for cust No "
							+ orderDto.getCustNo());
				}
			} else
				logger.info("No Service repair order to bridge");
		}
	}

	/**
	 * @param repairOrderDto
	 * @param repairOrders
	 * @throws ParseException
	 * @throws ServiceException
	 */
	private void loadServiceRepairOrders(ServiceRepairOrderDto repairOrderDto,
			ServiceRepairOrderHistoryDto[] repairOrders, Boc boc, Store store) throws ParseException, ServiceException {
		if (repairOrderDto != null) {

			if (repairOrders != null && repairOrders.length > 0) {

				for (ServiceRepairOrderHistoryDto orderDto : repairOrders) {

					logger.info("Starting processing for ro " + orderDto.getRoNo() + " for cust No "
							+ orderDto.getCustNo());

					String custNo = orderDto.getCustNo();
					if (!StringUtils.isEmpty(custNo)) {

						List<Customer> customers = customerRepo.findByCustNo(custNo);

						// handle customer logic
						Customer customer = null;
						if (CollectionUtils.isEmpty(customers)) {
							customer = new Customer();
							customer.setCustNo(custNo);
							customer.setBlockSms(false); // default to false since not null

							if (!StringUtils.isEmpty(orderDto.getCustomerFullName())) {
								String fullName = orderDto.getCustomerFullName();

								if (fullName.contains(",")) {
									String[] nameArray = fullName.split(Pattern.quote(COMMA_DELIMTER));
									if (nameArray.length > 0) {
										customer.setLastName(nameArray[0]);
										customer.setFirstName(nameArray[1]);
									} else
										customer.setFirstName(fullName);
								}
							}

							customer.setZipCode(orderDto.getZip());
							customer.setCellular(orderDto.getContactPhoneNumber());
							customer.setEmail(orderDto.getContactEmailAddress());
							customer.setBocIdFk(boc.getId());
							// customer.setCreatedOn(new Timestamp(new Date().getTime()));

							customerRepo.save(customer);

						} else
							customer = customers.get(0);

						String vin = orderDto.getVin();
						List<InventoryVehicle> vehicles = vehicleRepo.findByVin(vin);

						// handle inventory logic
						InventoryVehicle inventoryVehicle = null;
						if (CollectionUtils.isEmpty(vehicles)) {
							inventoryVehicle = new InventoryVehicle();

							inventoryVehicle.setCustNo(custNo);
							inventoryVehicle.setCustomerIdFk(customer.getId());
							inventoryVehicle.setVin(orderDto.getVin());
							inventoryVehicle.setMake(orderDto.getMake());
							inventoryVehicle.setModel(orderDto.getModelDesc());
							inventoryVehicle.setYear(orderDto.getYear());
							inventoryVehicle.setMileage(Long.valueOf(orderDto.getMileage()));
							inventoryVehicle.setBocIdFk(boc.getId());
							inventoryVehicle.setStoreIdFk(store.getId());

							vehicleRepo.save(inventoryVehicle);
						} else
							inventoryVehicle = vehicles.get(0);

						String roNo = orderDto.getRoNo();
						
						
						// handle service vehicle logic
						List<ServiceVehicle> serviceVehicles = serviceVehicleRepo.findByVinAndCustNo(vin, custNo);
						ServiceVehicle serviceVehicle = null;
						if (CollectionUtils.isEmpty(serviceVehicles)) {
							serviceVehicle = new ServiceVehicle();

							serviceVehicle.setCustNo(custNo);
							serviceVehicle.setCustomerIdFk(customer.getId());
							serviceVehicle.setVin(orderDto.getVin());
							serviceVehicle.setMake(orderDto.getMake());
							serviceVehicle.setModel(orderDto.getModelDesc());
							serviceVehicle.setYear(orderDto.getYear());
							serviceVehicle.setMileage(Long.valueOf(orderDto.getMileage()));
							serviceVehicle.setBocIdFk(boc.getId());

							serviceVehicleRepo.save(serviceVehicle);
						} else
							serviceVehicle = serviceVehicles.get(0);

						// clear original appointment since vehicle is already checked in
						clearOriginalAppointment(store, orderDto, custNo);
						
						// delete closed ro entry from open ro and open ro details table
						List<OpenRoDetail> openRoDetails = openRoDetailRepo.findByRoNumber(roNo);
						if (!CollectionUtils.isEmpty(openRoDetails)) {
							openRoDetailRepo.deleteAll(openRoDetails);
						}
						// List<ServiceStatusTrail> roServiceStatus = serviceStatusTrailRepo.findByRoNumberOrderByCreatedOnDesc(roNo);
						// if (!CollectionUtils.isEmpty(roServiceStatus)) {
							// serviceStatusTrailRepo.deleteAll(roServiceStatus);
						// }

						List<OpenRo> openRos = openRoRepo.findByRoNumber(roNo);
						if (!CollectionUtils.isEmpty(openRos)) {
							openRoRepo.deleteAll(openRos);
						}

						String advisorNo = orderDto.getServiceAdvisor();

						// handle service header
						List<com.aimachines.cdk.model.Service> services = serviceRepo.findByRoNumber(roNo);
						com.aimachines.cdk.model.Service service = null;
						if (CollectionUtils.isEmpty(services))
							service = new com.aimachines.cdk.model.Service();

						else
							service = services.get(0);

						// save or update
						service.setCustNo(custNo);
						service.setCustomerIdFk(customer.getId());
						service.setRoNumber(roNo);
						service.setVin(orderDto.getVin());
						service.setAdvisorNo(advisorNo);
						service.setServiceVehicleIdFk(serviceVehicle.getId());
						if (!StringUtils.isEmpty(orderDto.getClosedDate()))
							service.setCloseDate(new Timestamp(
									new SimpleDateFormat("yyyy-MM-dd").parse(orderDto.getClosedDate()).getTime()));

						if (!StringUtils.isEmpty(orderDto.getOpenDate()))
							service.setOpenedDate(new Timestamp(
									new SimpleDateFormat("yyyy-MM-dd").parse(orderDto.getOpenDate()).getTime()));

						service.setBocIdFk(boc.getId());
						service.setStoreIdFk(store.getId());
						// service.setCreatedOn(new Timestamp(new Date().getTime()));
						serviceRepo.save(service);

						List<ServiceDetail> serviceDetails = serviceDetailRepo.findByRoNumber(roNo);
						// clear the previous entries
						if (!CollectionUtils.isEmpty(serviceDetails)) {
							serviceDetailRepo.deleteAll(serviceDetails);
						}

						ObjectDto lineItemCode = orderDto.getLiineItemCode();
						if (lineItemCode != null && lineItemCode.getvElements() != null
								&& lineItemCode.getvElements().length > 0) {
							int lineItemIndex = 0;
							for (ElementDto elementDto : lineItemCode.getvElements()) {
								ElementDto labourOpCode = getValue(lineItemIndex, orderDto.getLbrOpCode());
								ElementDto labourOpCodeDesc = getValue(lineItemIndex, orderDto.getLbrOpCodeDesc());

								ElementDto labourTechnician = getValue(lineItemIndex, orderDto.getLabourTechnician());
								
								ServiceDetail detail = new ServiceDetail();

								detail.setRoNumber(roNo);
								detail.setServiceIdFk(service.getId());
								detail.setLaborTechnician(labourTechnician!=null? labourTechnician.getValue(): null);

								if (!StringUtils.isEmpty(orderDto.getClosedDate()))
									detail.setClosed(new Timestamp(new SimpleDateFormat("yyyy-MM-dd")
											.parse(orderDto.getClosedDate()).getTime()));

								if (labourOpCode != null && !StringUtils.isEmpty(labourOpCode.getValue())) {
									List<OpCodeMaster> opCodeMasters = opMasterRepo
											.findByOpCode(labourOpCode.getValue());
									OpCodeMaster opCodeMaster = null;
									if (CollectionUtils.isEmpty(opCodeMasters)) {
										opCodeMaster = new OpCodeMaster();
										opCodeMaster.setOpCode(labourOpCode.getValue());
										opCodeMaster.setOpCodeDescription((labourOpCodeDesc.getValue()));
										opCodeMaster.setStoreIdFk(store.getId());
										// opCodeMaster.setCreatedOn(new Timestamp(new Date().getTime()));
										opMasterRepo.save(opCodeMaster);
									} else
										opCodeMaster = opCodeMasters.get(0);

									detail.setOpCodeMasterIdFk(opCodeMaster.getId());
								}

								if (orderDto.getLbrLineCode() != null) {
									ElementDto lbrType = getValue(lineItemIndex, orderDto.getLbrLaborType());

									ElementDto totalLabCost = getValue(lineItemIndex, orderDto.getTotLaborCost());
									ElementDto totalLabSale = getValue(lineItemIndex, orderDto.getTotLaborSale());

									detail.setLaborType(lbrType.getValue());
									detail.setLaborCost(totalLabCost != null ? totalLabCost.getValue() : "0");
									detail.setLaborSale(totalLabSale != null ? totalLabSale.getValue() : "0");

								}

								if (orderDto.getPrtLineCode() != null) {

									ElementDto totalPartsCost = getValue(lineItemIndex, orderDto.getTotPartsCost());
									ElementDto totalPartsSale = getValue(lineItemIndex, orderDto.getTotPartsSale());

									if (totalPartsCost != null)
										detail.setPartsCost(totalPartsCost.getValue());
									if (totalPartsSale != null)
										detail.setPartsSale(totalPartsSale.getValue());

								}

								ElementDto totalMiscCost = getValue(lineItemIndex, orderDto.getTotMiscCost());
								if (totalMiscCost != null) {
									detail.setMiscCost(totalMiscCost.getValue());
								}
								ElementDto totalMiscSale = getValue(lineItemIndex, orderDto.getTotMiscSale());
								if (totalMiscSale != null) {
									detail.setMiscSale(totalMiscSale.getValue());
								}

								ElementDto soldHours = getValue(lineItemIndex, orderDto.getLbrSoldHours());
								if (soldHours != null) {
									detail.setSoldHours(soldHours.getValue());
									;
								}
								ElementDto actualHours = getValue(lineItemIndex, orderDto.getHrsActualHours());
								if (actualHours != null) {
									detail.setActualHours(actualHours.getValue());
									;
								}

								detail.setBocIdFk(boc.getId());
								detail.setStoreIdFk(store.getId());
								// save ro details
								if(detail.getOpCodeMasterIdFk()!=null)
								serviceDetailRepo.save(detail);

								lineItemIndex++;
							}
						}

					} else
						logger.info(
								"Skipping the bridge since customer Id is not empty wirh ro No " + orderDto.getRoNo());

					logger.info("Finishing processing for ro " + orderDto.getRoNo() + " for cust No "
							+ orderDto.getCustNo());
				}
			} else
				logger.info("No Service repair order to bridge");
		}
	}

	
	/**
	 * @param repairOrderDto
	 * @param repairOrders
	 * @throws ParseException
	 * @throws ServiceException
	 */
	private void loadSalesOrders(SalesDto salesDto,
			SalesDetailsDto[] salesDetailsDtos, Boc boc, Store store) throws ParseException, ServiceException {
		if (salesDto != null) {

			if (salesDetailsDtos!=null && salesDetailsDtos.length>0) {

				for (SalesDetailsDto orderDto : salesDetailsDtos) {

					logger.info("Starting processing for deal No " + orderDto.getDealNo() + " for cust No "+ orderDto.getCustNo());

					String custNo = orderDto.getCustNo();
					if (!StringUtils.isEmpty(custNo)) {

						List<Customer> customers = customerRepo.findByCustNo(custNo);

						// handle customer logic
						Customer customer = null;
						if (CollectionUtils.isEmpty(customers)) {
							customer = new Customer();
							customer.setCustNo(custNo);
							customer.setBlockSms(false); // default to false since not null

							if (!StringUtils.isEmpty(orderDto.getCustomerFullName())) {
								String fullName = orderDto.getCustomerFullName();

								if (fullName.contains(" ")) {
									String[] nameArray = fullName.split(Pattern.quote(SPACE_DELIMTER));
									if (nameArray.length > 0) {
										
										if(nameArray.length == 3) {
											customer.setFirstName(nameArray[0]);
											
											String lastName =  nameArray[0] + nameArray[1];
											customer.setLastName(lastName);
										}else {
											customer.setFirstName(nameArray[0]);
											customer.setLastName(nameArray[1]);
										}
										
									} else
										customer.setFirstName(fullName);
								}
							}

							customer.setZipCode(orderDto.getZip());
							customer.setCellular(orderDto.getCustomerPhoneNo());
							customer.setEmail(orderDto.getCustomerEmailAddress());
							customer.setBocIdFk(boc.getId());

							customerRepo.save(customer);

						} else
							customer = customers.get(0);

						String vin = orderDto.getVin();
						List<InventoryVehicle> vehicles = vehicleRepo.findByVin(vin);

						// handle inventory logic
						InventoryVehicle inventoryVehicle = null;
						if (CollectionUtils.isEmpty(vehicles)) {
							inventoryVehicle = new InventoryVehicle();

							inventoryVehicle.setCustNo(custNo);
							inventoryVehicle.setCustomerIdFk(customer.getId());
							inventoryVehicle.setVin(orderDto.getVin());
							inventoryVehicle.setMake(orderDto.getMake());
							inventoryVehicle.setModel(orderDto.getModel());
							inventoryVehicle.setYear(orderDto.getYear());
							long mileage = orderDto.getVehicleMileage()!=null?Long.valueOf(orderDto.getVehicleMileage()):0L;
							inventoryVehicle.setMileage(mileage);
							inventoryVehicle.setBocIdFk(boc.getId());
							inventoryVehicle.setStoreIdFk(store.getId());

							vehicleRepo.save(inventoryVehicle);
						} else
							inventoryVehicle = vehicles.get(0);

						String dealNo = orderDto.getDealNo();
						

						List<Sales> salesDtos = saleRepo.findByDealNumber(dealNo);
						Sales sale = null;
						// clear the previous entries
						if (!CollectionUtils.isEmpty(salesDtos)) {
							sale = salesDtos.get(0);
						}
						
						sale = new Sales();
						
						sale.setCustNo(custNo);
						sale.setCustomerIdFk(customer.getId());
						sale.setBocIdFk(boc.getId());
						sale.setStoreIdFk(store.getId());
						sale.setInventory_vehicle_id_fk(inventoryVehicle.getId());
						sale.setDealNumber(orderDto.getDealNo());
						sale.setDealType(orderDto.getDealType());
						if (!StringUtils.isEmpty(orderDto.getSalesDate()))
							sale.setSoldDate(new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(orderDto.getSalesDate()).getTime()));
						sale.setVin(orderDto.getVin());
						
						sale.setSalesman(orderDto.getSalesPersonId());
						sale.setSlsManager(orderDto.getSalesManagerId());
						sale.setFiManager(orderDto.getFiManagerId());
						sale.setSaleType(orderDto.getSaleType());
						if (!StringUtils.isEmpty(orderDto.getDealEvent5()))
							sale.setDealevent5(orderDto.getDealEvent5());
						if (!StringUtils.isEmpty(orderDto.getDealEvent5Date()))
							sale.setSoldDate(new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(orderDto.getDealEvent5Date()).getTime()));
						
						saleRepo.save(sale);
					} else
						logger.info(
								"Skipping the bridge since customer Id is not empty wirh dealer No " + orderDto.getDealNo());

					logger.info("Finishing processing for dealer No " + orderDto.getDealNo() + " for cust No "
							+ orderDto.getCustNo());
				}
			} else
				logger.info("No Service repair order to bridge");
		}
	}
	
	private void clearOriginalAppointment(Store store, ServiceRepairOrderHistoryDto orderDto, String custNo) {
		// clear the previous appointments
		List<Appointment> originalAppts =  appointmentRepo.findByStoreIdFkAndCustNoAndVin(store.getId(), custNo,orderDto.getVin());	
		if(!CollectionUtils.isEmpty(originalAppts)) {
			appointmentRepo.deleteAll(originalAppts);
		}
	}

	/**
	 * @param repairOrderDto
	 * @param repairOrders
	 * @throws ParseException
	 * @throws ServiceException
	 */
	private void loadOpenServiceRepairOrders(ServiceRepairOrderDto repairOrderDto,
			ServiceRepairOrderHistoryDto[] repairOrders, Boc boc, Store store) throws ParseException, ServiceException {
		if (repairOrderDto != null) {

			if (repairOrders != null && repairOrders.length > 0) {

				for (ServiceRepairOrderHistoryDto orderDto : repairOrders) {

					logger.info("Starting processing for ro " + orderDto.getRoNo() + " for cust No "
							+ orderDto.getCustNo());

					String custNo = orderDto.getCustNo();
					if (!StringUtils.isEmpty(custNo)) {

						List<Customer> customers = customerRepo.findByCustNo(custNo);

						// handle customer logic
						Customer customer = null;
						if (CollectionUtils.isEmpty(customers)) {
							customer = new Customer();
							customer.setCustNo(custNo);
							customer.setBlockSms(false); // default to false since not null

							if (!StringUtils.isEmpty(orderDto.getCustomerFullName())) {
								String fullName = orderDto.getCustomerFullName();

								if (fullName.contains(",")) {
									String[] nameArray = fullName.split(Pattern.quote(COMMA_DELIMTER));
									if (nameArray.length > 0) {
										customer.setLastName(nameArray[0]);
										customer.setFirstName(nameArray[1]);
									} else
										customer.setFirstName(fullName);
								}
							}

							customer.setZipCode(orderDto.getZip());
							customer.setCellular(orderDto.getContactPhoneNumber());
							customer.setEmail(orderDto.getContactEmailAddress());
							customer.setBocIdFk(boc.getId());

							customerRepo.save(customer);

						} else
							customer = customers.get(0);

						String vin = orderDto.getVin();
						List<InventoryVehicle> vehicles = vehicleRepo.findByVin(vin);

						// handle inventory logic
						InventoryVehicle inventoryVehicle = null;
						if (CollectionUtils.isEmpty(vehicles)) {
							inventoryVehicle = new InventoryVehicle();

							inventoryVehicle.setCustNo(custNo);
							inventoryVehicle.setCustomerIdFk(customer.getId());
							inventoryVehicle.setVin(orderDto.getVin());
							inventoryVehicle.setMake(orderDto.getMake());
							inventoryVehicle.setModel(orderDto.getModelDesc());
							inventoryVehicle.setYear(orderDto.getYear());
							inventoryVehicle.setMileage(Long.valueOf(orderDto.getMileage()));
							inventoryVehicle.setBocIdFk(boc.getId());
							inventoryVehicle.setStoreIdFk(store.getId());

							vehicleRepo.save(inventoryVehicle);
						} else
							inventoryVehicle = vehicles.get(0);

						String roNo = orderDto.getRoNo();

						// handle service vehicle logic
						List<ServiceVehicle> serviceVehicles = serviceVehicleRepo.findByVinAndCustNo(vin, custNo);
						ServiceVehicle serviceVehicle = null;
						if (CollectionUtils.isEmpty(serviceVehicles)) {
							serviceVehicle = new ServiceVehicle();

							serviceVehicle.setCustNo(custNo);
							serviceVehicle.setCustomerIdFk(customer.getId());
							serviceVehicle.setVin(orderDto.getVin());
							serviceVehicle.setMake(orderDto.getMake());
							serviceVehicle.setModel(orderDto.getModelDesc());
							serviceVehicle.setYear(orderDto.getYear());
							serviceVehicle.setMileage(Long.valueOf(orderDto.getMileage()));
							serviceVehicle.setBocIdFk(boc.getId());

							serviceVehicleRepo.save(serviceVehicle);
						} else
							serviceVehicle = serviceVehicles.get(0);

						// clear original appointment since vehicle is already checked in
						clearOriginalAppointment(store, orderDto, custNo);
						
						String advisorNo = orderDto.getServiceAdvisor();
					
						// handle service header
						List<OpenRo> services = openRoRepo.findByRoNumber(roNo);
						OpenRo service = null;
						if (CollectionUtils.isEmpty(services))
							service = new OpenRo();

						else
							service = services.get(0);

						// save or update
						service.setStatusCode(orderDto.getStatusCode());
						service.setStatusDesc(orderDto.getStatusDesc());
						service.setCustNo(custNo);
						service.setCustomerIdFk(customer.getId());
						service.setRoNumber(roNo);
						service.setAdvisorNo(advisorNo);
						service.setVin(orderDto.getVin());
						service.setOpenRoVehicleIdFk(serviceVehicle.getId());
						if (!StringUtils.isEmpty(orderDto.getClosedDate()))
							service.setCloseDate(new Timestamp(
									new SimpleDateFormat("yyyy-MM-dd").parse(orderDto.getClosedDate()).getTime()));

						if (!StringUtils.isEmpty(orderDto.getOpenDate()))
							service.setOpenedDate(new Timestamp(
									new SimpleDateFormat("yyyy-MM-dd").parse(orderDto.getOpenDate()).getTime()));

						service.setBocIdFk(boc.getId());
						service.setStoreIdFk(store.getId());

						openRoRepo.save(service);

						// status audit log
						String statusCode = orderDto.getStatusCode();
						
						List<ServiceStatusTrail> statusTrailRepos = serviceStatusTrailRepo.findByRoNumberOrderByCreatedOnDesc(roNo);
						ServiceStatusTrail serviceStatusTrail = null;
						if (!CollectionUtils.isEmpty(statusTrailRepos)) {
							serviceStatusTrail = statusTrailRepos.get(0);
						}

						if (serviceStatusTrail == null || !serviceStatusTrail.getCurrentStatusCode().equalsIgnoreCase(statusCode)) {
							String prevStatus = serviceStatusTrail != null ? serviceStatusTrail.getCurrentStatusCode(): null;

							ServiceStatusTrail statusTrail = new ServiceStatusTrail();
							statusTrail.setPrevStatusCode(prevStatus);
							statusTrail.setCurrentStatusCode(statusCode);
							statusTrail.setCreatedOn(new Timestamp(new Date().getTime()));
							statusTrail.setRoNumber(roNo);

							serviceStatusTrailRepo.save(statusTrail);

						}

						List<OpenRoDetail> serviceDetails = openRoDetailRepo.findByRoNumber(roNo);
						// clear the previous entries
						if (!CollectionUtils.isEmpty(serviceDetails)) {
							openRoDetailRepo.deleteAll(serviceDetails);
						}

						ObjectDto lineItemCode = orderDto.getLiineItemCode();
						if (lineItemCode != null && lineItemCode.getvElements() != null
								&& lineItemCode.getvElements().length > 0) {
							int lineItemIndex = 0;
							for (ElementDto elementDto : lineItemCode.getvElements()) {
								ElementDto labourOpCode = getValue(lineItemIndex, orderDto.getLbrOpCode());
								ElementDto labourOpCodeDesc = getValue(lineItemIndex, orderDto.getLbrOpCodeDesc());
								ElementDto labourTechnician = getValue(lineItemIndex, orderDto.getLabourTechnician());
								
								ElementDto lineItemStatusCode = getValue(lineItemIndex, orderDto.getLineItemStatusCode());
								ElementDto lineItemStatusDesc = getValue(lineItemIndex, orderDto.getLineItemStatusDesc());
								
								OpenRoDetail detail = new OpenRoDetail();

								detail.setRoNumber(roNo);
								detail.setOpenRoIdFk(service.getId());
								detail.setLaborTechnician(labourTechnician!=null? labourTechnician.getValue(): null);
								detail.setStatusCode(lineItemStatusCode!=null? lineItemStatusCode.getValue(): null);
								detail.setStatusDesc(lineItemStatusDesc!=null? lineItemStatusDesc.getValue(): null);
								
								if (!StringUtils.isEmpty(orderDto.getClosedDate()))
									detail.setClosed(new Timestamp(new SimpleDateFormat("yyyy-MM-dd")
											.parse(orderDto.getClosedDate()).getTime()));

								if (labourOpCode != null && !StringUtils.isEmpty(labourOpCode.getValue())) {
									List<OpCodeMaster> opCodeMasters = opMasterRepo
											.findByOpCode(labourOpCode.getValue());
									OpCodeMaster opCodeMaster = null;
									if (CollectionUtils.isEmpty(opCodeMasters)) {
										opCodeMaster = new OpCodeMaster();
										opCodeMaster.setOpCode(labourOpCode.getValue());
										opCodeMaster.setOpCodeDescription((labourOpCodeDesc.getValue()));
										opCodeMaster.setStoreIdFk(store.getId());
										opMasterRepo.save(opCodeMaster);
									} else
										opCodeMaster = opCodeMasters.get(0);

									detail.setOpCodeMasterIdFk(opCodeMaster.getId());
								}

								if (orderDto.getLbrLineCode() != null) {
									ElementDto lbrType = getValue(lineItemIndex, orderDto.getLbrLaborType());

									ElementDto totalLabCost = getValue(lineItemIndex, orderDto.getTotLaborCost());
									ElementDto totalLabSale = getValue(lineItemIndex, orderDto.getTotLaborSale());

									detail.setLineCode(elementDto.getValue());
									detail.setLaborType(lbrType.getValue());
									detail.setLaborCost(totalLabCost != null ? totalLabCost.getValue() : "0");
									detail.setLaborSale(totalLabSale != null ? totalLabCost.getValue() : "0");

								}

								if (orderDto.getPrtLineCode() != null) {
									ElementDto partNo = getValue(lineItemIndex, orderDto.getPrtPartNo());
									ElementDto partDesc = getValue(lineItemIndex, orderDto.getPrtDesc());

									ElementDto totalPartsCost = getValue(lineItemIndex, orderDto.getTotPartsCost());
									ElementDto totalPartsSale = getValue(lineItemIndex, orderDto.getTotPartsSale());

									if (partNo != null)
										detail.setPartNo(partNo.getValue());
									if (partDesc != null)
										detail.setPartDesc(partDesc.getValue());
									if (totalPartsCost != null)
										detail.setPartsCost(totalPartsCost.getValue());
									if (totalPartsSale != null)
										detail.setPartsSale(totalPartsSale.getValue());

								}

								ElementDto totalMiscCost = getValue(lineItemIndex, orderDto.getTotMiscCost());
								if (totalMiscCost != null) {
									detail.setMiscCost(totalMiscCost.getValue());
								}
								ElementDto totalMiscSale = getValue(lineItemIndex, orderDto.getTotMiscSale());
								if (totalMiscSale != null) {
									detail.setMiscSale(totalMiscSale.getValue());
								}

								ElementDto soldHours = getValue(lineItemIndex, orderDto.getLbrSoldHours());
								if (soldHours != null) {
									detail.setSoldHours(soldHours.getValue());
									;
								}
								ElementDto actualHours = getValue(lineItemIndex, orderDto.getHrsActualHours());
								if (actualHours != null) {
									detail.setActualHours(actualHours.getValue());
									;
								}

								ElementDto lineItemEstimatedHours = getValue(lineItemIndex,
										orderDto.getLinEstDuration());
								if (lineItemEstimatedHours != null) {
									detail.setLineEstimatedHours(lineItemEstimatedHours.getValue());
								}

								detail.setBocIdFk(boc.getId());
								detail.setStoreIdFk(store.getId());

								// save ro details
								if(detail.getOpCodeMasterIdFk()!=null)
								openRoDetailRepo.save(detail);

								lineItemIndex++;
							}
						}

					} else
						logger.info(
								"Skipping the bridge since customer Id is not empty wirh ro No " + orderDto.getRoNo());

					logger.info("Finishing processing for ro " + orderDto.getRoNo() + " for cust No "
							+ orderDto.getCustNo());
				}
			} else
				logger.info("No Service repair order to bridge");
		}
	}

	/**
	 * @param bean
	 * @param queryId
	 * @return
	 * @throws ParseException
	 */
	private HttpUtilBean initializeExtraction(String execStartDate, String endDate, String queryId, String url)
			throws ParseException {
		HttpUtilBean httpUtilBean;

		httpUtilBean = new HttpUtilBean();
		httpUtilBean.setUrl(url);
		httpUtilBean.setUser(env.getProperty(CDK_API_USER));
		httpUtilBean.setPassword(env.getProperty(CDK_API_PASSWORD));
		httpUtilBean.setStartDate(execStartDate);
		httpUtilBean.setEndDate(endDate);
//		httpUtilBean.setDealerId(env.getProperty(CDK_API_DELAER_ID));
		httpUtilBean.setQueryId(queryId);

		return httpUtilBean;
	}

	/**
	 * @param lineItemIndex
	 * @param labourOpCode
	 * @return
	 */
	private ElementDto getValue(int lineItemIndex, ObjectDto labourOpCode) {
		ElementDto elementDto = null;
		if (labourOpCode != null && labourOpCode.getvElements() != null
				&& labourOpCode.getvElements().length > lineItemIndex) {
			elementDto = labourOpCode.getvElements()[lineItemIndex];
		}

		return elementDto;
	}

	private List<Map<String, String>> getDateRange(RequestBean bean) throws ServiceException {
		Date startDate = null;
		Date endDate = null;
		Map<String, String> dateMap = null;
		List<Map<String, String>> dateList = null;
		DateTimeFormatter formatters = null;

		try {

			formatters = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			dateList = new ArrayList<Map<String, String>>();

			startDate = new SimpleDateFormat("MM/dd/yyyy").parse(bean.getStartDate());
			LocalDate startLocalDate = LocalDateTime.from(startDate.toInstant().atZone(ZoneId.systemDefault()))
					.toLocalDate();

			endDate = new SimpleDateFormat("MM/dd/yyyy").parse(bean.getEndDate());
			LocalDate endLocalDate = LocalDateTime.from(endDate.toInstant().atZone(ZoneId.systemDefault()))
					.toLocalDate();

			if (startLocalDate.getMonth() == endLocalDate.getMonth()
					&& startLocalDate.getYear() == endLocalDate.getYear()) {
				dateMap = new HashMap<String, String>();
				dateMap.put(START_DATE, bean.getStartDate());
				dateMap.put(END_DATE, bean.getEndDate());

				dateList.add(dateMap);
			} else {
				while (startLocalDate.isBefore(endLocalDate)) {
					dateMap = new HashMap<String, String>();
					LocalDate monthStartDate = startLocalDate.withDayOfMonth(1);
					LocalDate monthEndDate = startLocalDate.withDayOfMonth(startLocalDate.lengthOfMonth());

					dateMap.put(START_DATE, monthStartDate.format(formatters));

					if (monthEndDate.isAfter(endLocalDate))
						dateMap.put(END_DATE, endLocalDate.format(formatters));
					else
						dateMap.put(END_DATE, monthEndDate.format(formatters));

					startLocalDate = monthEndDate.plusDays(1);

					dateList.add(dateMap);
				}
			}

		} catch (ParseException e) {
			throw new ServiceException(e.getMessage());
		}

		return dateList;
	}
}
