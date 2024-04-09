package com.aimachines.cdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "ServiceRODetailHistory")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceRepairOrderDto {

	 
	@JacksonXmlProperty(localName = "service-repair-order-history")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ServiceRepairOrderHistoryDto[] serviceRepairOrdersClosed;
	
	
	@JacksonXmlProperty(localName = "service-repair-order-open")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ServiceRepairOrderHistoryDto[] serviceRepairOrdersOpen;
	

	@JacksonXmlProperty(localName = "ErrorCode")
	private String errorCode;
	
	@JacksonXmlProperty(localName = "ErrorMessage")
	private String errorMessage;
	
	
	public ServiceRepairOrderDto() {
	}

	
	
	/**
	 * @return the serviceRepairOrdersClosed
	 */
	public ServiceRepairOrderHistoryDto[] getServiceRepairOrdersClosed() {
		return serviceRepairOrdersClosed;
	}



	/**
	 * @param serviceRepairOrdersClosed the serviceRepairOrdersClosed to set
	 */
	public void setServiceRepairOrdersClosed(ServiceRepairOrderHistoryDto[] serviceRepairOrdersClosed) {
		this.serviceRepairOrdersClosed = serviceRepairOrdersClosed;
	}



	/**
	 * @return the serviceRepairOrdersOpen
	 */
	public ServiceRepairOrderHistoryDto[] getServiceRepairOrdersOpen() {
		return serviceRepairOrdersOpen;
	}



	/**
	 * @param serviceRepairOrdersOpen the serviceRepairOrdersOpen to set
	 */
	public void setServiceRepairOrdersOpen(ServiceRepairOrderHistoryDto[] serviceRepairOrdersOpen) {
		this.serviceRepairOrdersOpen = serviceRepairOrdersOpen;
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

	
	
	
}
