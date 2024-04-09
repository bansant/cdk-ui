package com.aimachines.cdk.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.aimachines.cdk.bean.HttpUtilBean;
import com.aimachines.cdk.dto.AppointmentDto;
import com.aimachines.cdk.dto.EmployeeDto;
import com.aimachines.cdk.dto.SalesDto;
import com.aimachines.cdk.dto.ServiceRepairOrderDto;
import com.aimachines.cdk.utils.ServiceException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class HttpUtilService {


	public StringBuilder executeHttpExtract(HttpUtilBean httpUtilBean,Map<String, String> params) throws IOException {
		HttpURLConnection con = null;
		StringBuilder content = null;
		String url = httpUtilBean.getUrl();

		try {

			URL myurl = new URL(url);
			con = (HttpURLConnection) myurl.openConnection();

			con.setDoInput(true);
			con.setDoOutput(true);


			byte[] postData = getQuery(params).getBytes(StandardCharsets.UTF_8);
			int postDataLength = postData.length;

			con.setRequestMethod("POST");

			String encoded = Base64.getEncoder().encodeToString(
					(httpUtilBean.getUser() + ":" + httpUtilBean.getPassword()).getBytes(StandardCharsets.UTF_8));

			con.setRequestProperty("Authorization", "Basic " + encoded);
			con.setRequestProperty("User-Agent", "Java client");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Content-Length", Integer.toString(postDataLength));

			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {

				wr.writeBytes(getQuery(params));
			}

			try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {

				String line;
				content = new StringBuilder();

				while ((line = br.readLine()) != null) {
					content.append(line);
				}
			}

		} finally {

			con.disconnect();
		}

		return content;
	}

	private String getQuery(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();
		boolean first = true;

		for (Map.Entry<String, String> entry : params.entrySet()) {

			if (first)
				first = false;
			else
				result.append("&");

			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
		}

		return result.toString();
	}

	public static void main(String[] args) {
		try {
			
			
			
			
			HttpUtilBean bean = new HttpUtilBean();
			bean.setUrl("https://uat-3pa.dmotorworks.com/pip-extract/help-employee/extract?");
			bean.setUser("aimachines");
			bean.setPassword("VIb61nXmKPGh");
			bean.setStartDate("11/01/2021");
			bean.setEndDate("12/15/2021");
			bean.setDealerId("3PADEV1");
			bean.setQueryId("HEMPL_Bulk_Service");
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("dealerId", bean.getDealerId());
			params.put("queryId", bean.getQueryId());
//			params.put("qparamStartDate", bean.getStartDate());
//			params.put("qparamEndDate", bean.getEndDate());
			
			StringBuilder builder =  new HttpUtilService().executeHttpExtract(bean, params);
			
			// ServiceRepairOrderDto repairOrderDto =  new HttpUtilService().loadExtract(builder);
			
			EmployeeDto repairOrderDto =  new HttpUtilService().loadEmployeeExtract(builder);
			
			System.out.println(repairOrderDto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ServiceRepairOrderDto loadExtract(StringBuilder  content) throws ServiceException {
		ServiceRepairOrderDto repairOrderDto = null;
		ObjectMapper objectMapper = null;

		try {
			objectMapper = new XmlMapper();
			
			repairOrderDto = objectMapper.readValue(content.toString(), ServiceRepairOrderDto.class);
		} catch (JsonParseException e) {
			throw new ServiceException(e.getMessage());
		} catch (JsonMappingException e) {
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		return repairOrderDto;
	}
	
	
	public AppointmentDto loadAppointmentExtract(StringBuilder  content) throws ServiceException {
		AppointmentDto repairOrderDto = null;
		ObjectMapper objectMapper = null;

		try {
			objectMapper = new XmlMapper();
			
			repairOrderDto = objectMapper.readValue(content.toString(), AppointmentDto.class);
		} catch (JsonParseException e) {
			throw new ServiceException(e.getMessage());
		} catch (JsonMappingException e) {
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		return repairOrderDto;
	}
	
	public EmployeeDto loadEmployeeExtract(StringBuilder  content) throws ServiceException {
		EmployeeDto employeeDto = null;


		try {
			JacksonXmlModule module = new JacksonXmlModule();
			module.setDefaultUseWrapper(false);
			XmlMapper xmlMapper = new XmlMapper(module);
			
			
			employeeDto = xmlMapper.readValue(content.toString(), EmployeeDto.class);
		} catch (JsonParseException e) {
			System.out.println(e.getStackTrace());
			throw new ServiceException(e.getMessage());
		} catch (JsonMappingException e) {
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		return employeeDto;
	}
	
	/**
	 * @param content
	 * @return
	 * @throws ServiceException
	 */
	public SalesDto loadSalesExtract(StringBuilder  content) throws ServiceException {
		SalesDto salesDto = null;


		try {
			JacksonXmlModule module = new JacksonXmlModule();
			module.setDefaultUseWrapper(false);
			XmlMapper xmlMapper = new XmlMapper(module);
			
			
			salesDto = xmlMapper.readValue(content.toString(), SalesDto.class);
		} catch (JsonParseException e) {
			System.out.println(e.getStackTrace());
			throw new ServiceException(e.getMessage());
		} catch (JsonMappingException e) {
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		return salesDto;
	}
}
