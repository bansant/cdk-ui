package com.aimachines.cdk.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "FiSalesHistory")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesDto {

	@JacksonXmlProperty(localName = "FISalesHistory")
	@JacksonXmlElementWrapper(useWrapping = false)
	private SalesDetailsDto[] salesDetailsDtos;

	@JacksonXmlProperty(localName = "ErrorCode")
	private String errorCode;

	@JacksonXmlProperty(localName = "ErrorMessage")
	private String errorMessage;

	

	public SalesDto() {
	}

	/**
	 * @return the salesDetailsDtos
	 */
	public SalesDetailsDto[] getSalesDetailsDtos() {
		return salesDetailsDtos;
	}



	/**
	 * @param salesDetailsDtos the salesDetailsDtos to set
	 */
	public void setSalesDetailsDtos(SalesDetailsDto[] salesDetailsDtos) {
		this.salesDetailsDtos = salesDetailsDtos;
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
		return "EmployeeDetailsDto [employeeDetailsDtos = " + salesDetailsDtos + "]";
	}

}
