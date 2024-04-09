package com.aimachines.cdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentHeaderDto {


	@JacksonXmlProperty(localName = "AppointmentDate")
	private String appointmentDate;

	@JacksonXmlProperty(localName = "AppointmentTime")
	private String appointmentTime;

	@JacksonXmlProperty(localName = "CustNo")
	private String custNo;
	
	
	@JacksonXmlProperty(localName = "ApptID")
	private String apptID;

	@JacksonXmlProperty(localName = "EMail")
	private String email;

	@JacksonXmlProperty(localName = "FirstName")
	private String firstName;

	@JacksonXmlProperty(localName = "LastName")
	private String lastName;

	@JacksonXmlProperty(localName = "Cellular")
	private String cellular;

	@JacksonXmlProperty(localName = "PromiseDate")
	private String promiseDate;
	
	@JacksonXmlProperty(localName = "PromiseTime")
	private String promiseTime;
	

	@JacksonXmlProperty(localName = "VIN")
	private String vin;

	@JacksonXmlProperty(localName = "ModelYear")
	private String modelYear;

	@JacksonXmlProperty(localName = "Vehid")
	private String vehID;

	@JacksonXmlProperty(localName = "Make")
	private String make;
	
	@JacksonXmlProperty(localName = "ApptMileage")
	private String apptMileage;
	


	
	
	public AppointmentHeaderDto() {
		
	}



	/**
	 * @return the apptID
	 */
	public String getApptID() {
		return apptID;
	}



	/**
	 * @param apptID the apptID to set
	 */
	public void setApptID(String apptID) {
		this.apptID = apptID;
	}



	/**
	 * @return the appointmentDate
	 */
	public String getAppointmentDate() {
		return appointmentDate;
	}



	/**
	 * @param appointmentDate the appointmentDate to set
	 */
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}



	/**
	 * @return the appointmentTime
	 */
	public String getAppointmentTime() {
		return appointmentTime;
	}



	/**
	 * @param appointmentTime the appointmentTime to set
	 */
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}



	/**
	 * @return the custNo
	 */
	public String getCustNo() {
		return custNo;
	}



	/**
	 * @param custNo the custNo to set
	 */
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}



	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}



	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	/**
	 * @return the cellular
	 */
	public String getCellular() {
		return cellular;
	}



	/**
	 * @param cellular the cellular to set
	 */
	public void setCellular(String cellular) {
		this.cellular = cellular;
	}



	/**
	 * @return the promiseDate
	 */
	public String getPromiseDate() {
		return promiseDate;
	}



	/**
	 * @param promiseDate the promiseDate to set
	 */
	public void setPromiseDate(String promiseDate) {
		this.promiseDate = promiseDate;
	}



	/**
	 * @return the promiseTime
	 */
	public String getPromiseTime() {
		return promiseTime;
	}



	/**
	 * @param promiseTime the promiseTime to set
	 */
	public void setPromiseTime(String promiseTime) {
		this.promiseTime = promiseTime;
	}



	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}



	/**
	 * @param vin the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}



	/**
	 * @return the modelYear
	 */
	public String getModelYear() {
		return modelYear;
	}



	/**
	 * @param modelYear the modelYear to set
	 */
	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}



	/**
	 * @return the vehID
	 */
	public String getVehID() {
		return vehID;
	}



	/**
	 * @param vehID the vehID to set
	 */
	public void setVehID(String vehID) {
		this.vehID = vehID;
	}



	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}



	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}



	/**
	 * @return the apptMileage
	 */
	public String getApptMileage() {
		return apptMileage;
	}



	/**
	 * @param apptMileage the apptMileage to set
	 */
	public void setApptMileage(String apptMileage) {
		this.apptMileage = apptMileage;
	}
	
	
}
