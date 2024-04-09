package com.aimachines.cdk.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aimachines.cdk.bean.AppointmentResponseBean;
import com.aimachines.cdk.bean.OpenRoRequestBean;
import com.aimachines.cdk.bean.RequestBean;
import com.aimachines.cdk.bean.RequestDailyBridge;
import com.aimachines.cdk.bean.Response;
import com.aimachines.cdk.bean.SalesResponseBean;
import com.aimachines.cdk.bean.ServiceClosedResponseBean;
import com.aimachines.cdk.bean.ServiceDetailResponseBean;
import com.aimachines.cdk.bean.ServiceResponseBean;
import com.aimachines.cdk.model.Employee;
import com.aimachines.cdk.model.OpenRo;
import com.aimachines.cdk.model.Service;
import com.aimachines.cdk.model.ServiceStatusTrail;
import com.aimachines.cdk.model.Store;
import com.aimachines.cdk.service.BridgeService;
import com.aimachines.cdk.utils.ServiceConstants;
import com.aimachines.cdk.utils.ServiceException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Api(tags = "Bridge")

public class BridgeController {
	private static Logger logger = LoggerFactory.getLogger(BridgeController.class);

	@Autowired
	private BridgeService service;

	/**
	 * @param request
	 * @return
	 */

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@PostMapping(value = "/startInitialClosedRoBridge")
	public ResponseEntity<Response> startClosedRoBridge(
			@ApiParam(value = "Extract request params range date (mm/dd/yyyy)") @RequestBody RequestBean request) {

		logger.info("Start : Start the bridge");
		Response response = null;
		try {

			response = service.startInitialClosedROBridge(request);

			logger.info("End : End the bridge");

			return ResponseEntity.ok().body(response);

		} catch (ServiceException e) {
			response = new Response();
			response.setStatus("500");
			response.setMessage(ServiceConstants.FAILED);
			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@PostMapping(value = "/startDailyClosedRoBridge")
	public ResponseEntity<Response> startDailyClosedRoBridge(
			@ApiParam(value = "Extract request params daily starting from start date (mm/dd/yyyy)") @RequestBody RequestDailyBridge request) {

		logger.info("Start : Start the bridge");
		Response response = null;
		try {

			response = service.startDailyClosedROBridge(request);

			logger.info("End : End the bridge");

			return ResponseEntity.ok().body(response);

		} catch (ServiceException e) {
			response = new Response();
			response.setStatus("500");
			response.setMessage(ServiceConstants.FAILED);
			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	/**
	 * @return
	 */
	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@PostMapping(value = "/startInitialEmployeeBridge")
	public ResponseEntity<Response> startInitialEmployeeBridge() {

		logger.info("Start : Start the Appointment bridge");
		Response response = null;
		try {

			response = service.startInitialEmployeeBridge();

			logger.info("End : End the Appointment bridge");

			return ResponseEntity.ok().body(response);

		} catch (ServiceException e) {
			response = new Response();
			response.setStatus("500");
			response.setMessage(ServiceConstants.FAILED);
			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	/**
	 * @param request
	 * @return
	 */

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@PostMapping(value = "/startInitialAppointmentBridge")
	public ResponseEntity<Response> startInitialAppointmentBridge() {

		logger.info("Start : Start the Appointment bridge");
		Response response = null;
		try {

			response = service.startInitialAppointmentBridge();

			logger.info("End : End the Appointment bridge");

			return ResponseEntity.ok().body(response);

		} catch (ServiceException e) {
			response = new Response();
			response.setStatus("500");
			response.setMessage(ServiceConstants.FAILED);
			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@PostMapping(value = "/startOpenRoAndAppointmentBridge")
	public ResponseEntity<Response> startOpenRoAndAppointmentBridge() {

		logger.info("Start : Start the wip ro bridge");
		Response response = null;
		try {

			response = service.startOpenRosAndAppointmentBridge();

			logger.info("End : End the wip ro  bridge");

			return ResponseEntity.ok().body(response);

		} catch (ServiceException e) {
			response = new Response();
			response.setStatus("500");
			response.setMessage(ServiceConstants.FAILED);
			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@PostMapping(value = "/startDailyBulkAppointmentBridge")
	public ResponseEntity<Response> startDailyBulkAppointmentBridge() {

		logger.info("Start : Start the wip ro bridge");
		Response response = null;
		try {

			response = service.startDailyBulkAppointmentBridge();

			logger.info("End : End the wip ro  bridge");

			return ResponseEntity.ok().body(response);

		} catch (ServiceException e) {
			response = new Response();
			response.setStatus("500");
			response.setMessage(ServiceConstants.FAILED);
			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@GetMapping(value = "/getAllServiceOrders")
	public ResponseEntity<Iterable<Service>> getAllServiceOrders() {

		logger.info("Start : Start the wip ro bridge");
		Iterable<Service> data = null;
		try {

			data = service.getAllServiceOrder();

			logger.info("End : End the wip ro  bridge");

			return ResponseEntity.ok().body(data);

		} catch (ServiceException e) {

			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(data);
		}
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@GetMapping(value = "/getServiceOrderDetail/{roNum}")
	public ResponseEntity<ServiceDetailResponseBean> getServiceOrderDetail(
			@ApiParam(value = "Ro number for details") @PathVariable("roNum") String roNum) {

		logger.info("Start : Start the wip ro bridge");
		ServiceDetailResponseBean data = null;
		try {

			data = service.getServiceOrderDetail(roNum);

			logger.info("End : End the wip ro  bridge");

			return ResponseEntity.ok().body(data);

		} catch (ServiceException e) {

			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(data);
		}
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@GetMapping(value = "/getAuditTrial/{roNum}")
	public ResponseEntity<List<ServiceStatusTrail>> getAuditTrial(
			@ApiParam(value = "Ro number for details") @PathVariable("roNum") String roNum) {

		logger.info("Start : Start the wip ro bridge");
		List<ServiceStatusTrail> data = null;
		try {

			data = service.getAuditTrial(roNum);

			logger.info("End : End the wip ro  bridge");

			return ResponseEntity.ok().body(data);

		} catch (ServiceException e) {

			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(data);
		}
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@GetMapping(value = "/getAllOpenServiceOrders")
	public ResponseEntity<Iterable<OpenRo>> getAllOpenServiceOrders() {

		logger.info("Start : Start the wip ro bridge");
		Iterable<OpenRo> data = null;
		try {

			data = service.getAllOpenServiceOrder();

			logger.info("End : End the wip ro  bridge");

			return ResponseEntity.ok().body(data);

		} catch (ServiceException e) {

			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(data);
		}
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@GetMapping(value = "/getOpenServiceOrderDetail/{roNum}")
	public ResponseEntity<ServiceDetailResponseBean> getOpenServiceOrderDetail(
			@ApiParam(value = "Ro number for details") @PathVariable("roNum") String roNum) {

		logger.info("Start : Start the wip ro bridge");
		ServiceDetailResponseBean data = null;
		try {

			data = service.getOpenServiceOrderDetail(roNum);

			logger.info("End : End the wip ro  bridge");

			return ResponseEntity.ok().body(data);

		} catch (ServiceException e) {

			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(data);
		}
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@PostMapping(value = "/getOpenRoBtStoreId")
	public ResponseEntity<List<ServiceResponseBean>> getOpenRoBtStoreId(
			@RequestBody OpenRoRequestBean openRoRequestBean) {

		logger.info("Start : Start the wip ro bridge");
		List<ServiceResponseBean> data = null;
		try {

			data = service.getAllOpenServiceOrderByStoreId(openRoRequestBean);

			logger.info("End : End the wip ro  bridge");

			return ResponseEntity.ok().body(data);

		} catch (ServiceException e) {

			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(data);
		}
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@GetMapping(value = "/getAppointmentByStoreIdAndDate/{storeId}/{apptDate}")
	public ResponseEntity<List<AppointmentResponseBean>> getAppointmentByStoreIdAndDate(
			@ApiParam(value = "Store Id for open Ro") @PathVariable("storeId") String storeId,
			@ApiParam(value = "Appointment date YYYYY-MM-dd") @PathVariable("apptDate") String apptDate) {

		logger.info("Start : Start the wip ro bridge");
		List<AppointmentResponseBean> data = null;
		try {

			data = service.getAllAppointmentByStoreIdAndAppointmentDate(storeId, apptDate);

			logger.info("End : End the wip ro  bridge");

			return ResponseEntity.ok().body(data);

		} catch (ServiceException e) {

			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(data);
		}
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@GetMapping(value = "/getClosedRoByStoreIdAndDate/{storeId}/{closedDate}")
	public ResponseEntity<List<ServiceClosedResponseBean>> getClosedRoByStoreIdAndDate(
			@ApiParam(value = "Store Id for open Ro") @PathVariable("storeId") String storeId,
			@ApiParam(value = "Closed date YYYYY-MM-dd") @PathVariable("closedDate") String apptDate) {

		logger.info("Start : Start the wip ro bridge");
		List<ServiceClosedResponseBean> data = null;
		try {

			data = service.getClostedRoByDealerAndDate(storeId, apptDate, "ALL");

			logger.info("End : End the wip ro  bridge");

			return ResponseEntity.ok().body(data);

		} catch (ServiceException | ParseException e) {

			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(data);
		}
	}
	
	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
		@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
		@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
@GetMapping(value = "/getSalesByDealerAndDate/{storeId}/{closedDate}")
public ResponseEntity<List<SalesResponseBean>> getSalesByDealerAndDate(
		@ApiParam(value = "Store Id for open Ro") @PathVariable("storeId") String storeId,
		@ApiParam(value = "Closed date YYYYY-MM-dd") @PathVariable("closedDate") String apptDate) {

	logger.info("Start : Start the wip ro bridge");
	List<SalesResponseBean> data = null;
	try {

		data = service.getSalesByDealerAndDate(storeId, apptDate);

		logger.info("End : End the wip ro  bridge");

		return ResponseEntity.ok().body(data);

	} catch (ServiceException | ParseException e) {

		logger.info("Error occurred while triggering the bridge " + e.getMessage());
		return ResponseEntity.badRequest().body(data);
	}
}
	

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@GetMapping(value = "/getClosedRoByStoreIdAndDateAndAdvisorNo/{storeId}/{closedDate}/{advisorNo}")
	public ResponseEntity<List<ServiceClosedResponseBean>> getClosedRoByStoreIdAndDateAndAdvisorNo(
			@ApiParam(value = "Store Id for open Ro") @PathVariable("storeId") String storeId,
			@ApiParam(value = "Closed date YYYYY-MM-dd") @PathVariable("closedDate") String apptDate,
			@ApiParam(value = "Advisor No") @PathVariable("advisorNo") String advisorNo) {

		logger.info("Start : Start the wip ro bridge");
		List<ServiceClosedResponseBean> data = null;
		try {

			data = service.getClostedRoByDealerAndDate(storeId, apptDate, advisorNo);

			logger.info("End : End the wip ro  bridge");

			return ResponseEntity.ok().body(data);

		} catch (ServiceException | ParseException e) {

			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(data);
		}
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@GetMapping(value = "/getAdivsorByStore/{storeId}")
	public ResponseEntity<List<Employee>> getAdivsorByStore(
			@ApiParam(value = "Store Id for open Ro") @PathVariable("storeId") Long storeId) {

		logger.info("Start : Start the wip ro bridge");
		List<Employee> data = null;
		try {

			data = service.getAdivsorByStore(storeId);

			logger.info("End : End the wip ro  bridge");

			return ResponseEntity.ok().body(data);

		} catch (ServiceException e) {

			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(data);
		}
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@GetMapping(value = "/getAllStores")
	public ResponseEntity<Iterable<Store>> getAllStores() {

		logger.info("Start : Start the wip ro bridge");
		Iterable<Store> data = null;
		try {

			data = service.getAllStores();

			logger.info("End : End the wip ro  bridge");

			return ResponseEntity.ok().body(data);

		} catch (ServiceException e) {

			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(data);
		}
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request, The request parameters are malformed (unable to parse)."),
			@ApiResponse(code = 500, message = "Internal Server Error, Results from an unexpected runtime error.") })
	@PostMapping(value = "/startInitialSalesBridge")
	public ResponseEntity<Response> startInitialSalesBridge(
			@ApiParam(value = "Extract request params range date (mm/dd/yyyy)") @RequestBody RequestBean request) {

		logger.info("Start : Start the bridge");
		Response response = null;
		try {

			response = service.startInitialSalesBridge(request);

			logger.info("End : End the bridge");

			return ResponseEntity.ok().body(response);

		} catch (ServiceException e) {
			response = new Response();
			response.setStatus("500");
			response.setMessage(ServiceConstants.FAILED);
			logger.info("Error occurred while triggering the bridge " + e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

}
