package com.aimachines.cdk.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "HelpEmployee")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDto {

	
	@JacksonXmlProperty(localName = "HelpEmployee")
	@JacksonXmlElementWrapper(useWrapping = false)
	private EmployeeDetailsDto employeeDetailsDtos;

	@JacksonXmlProperty(localName = "ErrorCode")
	private String errorCode;

	@JacksonXmlProperty(localName = "ErrorMessage")
	private String errorMessage;
	
	private List<EmployeeDetailsDto> employeeDetailsList = null;

	public EmployeeDto() {
	}

	
	/**
	 * @return the employeeDetailsList
	 */
	public List<EmployeeDetailsDto> getEmployeeDetailsList() {
		return employeeDetailsList;
	}


	/**
	 * @param employeeDetailsList the employeeDetailsList to set
	 */
	public void setEmployeeDetailsList(List<EmployeeDetailsDto> employeeDetailsList) {
		this.employeeDetailsList = employeeDetailsList;
	}


	/**
	 * @return the employeeDetailsDtos
	 */
	public EmployeeDetailsDto getEmployeeDetailsDtos() {
		return employeeDetailsDtos;
	}

	/**
	 * @param employeeDetailsDtos the employeeDetailsDtos to set
	 */
	public void setEmployeeDetailsDtos(EmployeeDetailsDto employeeDetailsDtos) {
		if(CollectionUtils.isEmpty(employeeDetailsList)) employeeDetailsList = new ArrayList<EmployeeDetailsDto>();
		
		this.employeeDetailsDtos = employeeDetailsDtos;
		
		employeeDetailsList.add(employeeDetailsDtos);
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "EmployeeDetailsDto [employeeDetailsDtos = " + employeeDetailsDtos + "]";
	}

}
