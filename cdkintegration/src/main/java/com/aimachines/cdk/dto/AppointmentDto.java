package com.aimachines.cdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "HelpEmployee")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDto {

	 
	@JacksonXmlProperty(localName = "Appointments")
	@JacksonXmlElementWrapper(useWrapping = false)
	private AppointmentHeaderDto[] appointments;
	

	@JacksonXmlProperty(localName = "ErrorCode")
	private String errorCode;
	
	@JacksonXmlProperty(localName = "ErrorMessage")
	private String errorMessage;
	
	
	public AppointmentDto() {
	}


	/**
	 * @return the appointments
	 */
	public AppointmentHeaderDto[] getAppointments() {
		return appointments;
	}


	/**
	 * @param appointments the appointments to set
	 */
	public void setAppointments(AppointmentHeaderDto[] appointments) {
		this.appointments = appointments;
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
