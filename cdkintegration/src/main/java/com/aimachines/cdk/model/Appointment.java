package com.aimachines.cdk.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yac_appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String storeId;
	private Long storeIdFk;

	private String custNo;
	private Long customerIdFk;
	private Long appointmentId;
	
	
	private String appointmentDate;
	private String appointmentTime;
	private String promisedDate;
	private String promisedTime;
	
	
	private String vin;
	private Long serviceVehicleIdFk;



	private String recordSourceType;
	private Long syncHistoryId;
	private Timestamp whenAdded;
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the storeId
	 */
	public String getStoreId() {
		return storeId;
	}
	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	/**
	 * @return the storeIdFk
	 */
	public Long getStoreIdFk() {
		return storeIdFk;
	}
	/**
	 * @param storeIdFk the storeIdFk to set
	 */
	public void setStoreIdFk(Long storeIdFk) {
		this.storeIdFk = storeIdFk;
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
	 * @return the customerIdFk
	 */
	public Long getCustomerIdFk() {
		return customerIdFk;
	}
	/**
	 * @param customerIdFk the customerIdFk to set
	 */
	public void setCustomerIdFk(Long customerIdFk) {
		this.customerIdFk = customerIdFk;
	}
	/**
	 * @return the appointmentId
	 */
	public Long getAppointmentId() {
		return appointmentId;
	}
	/**
	 * @param appointmentId the appointmentId to set
	 */
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
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
	 * @return the promisedDate
	 */
	public String getPromisedDate() {
		return promisedDate;
	}
	/**
	 * @param promisedDate the promisedDate to set
	 */
	public void setPromisedDate(String promisedDate) {
		this.promisedDate = promisedDate;
	}
	/**
	 * @return the promisedTime
	 */
	public String getPromisedTime() {
		return promisedTime;
	}
	/**
	 * @param promisedTime the promisedTime to set
	 */
	public void setPromisedTime(String promisedTime) {
		this.promisedTime = promisedTime;
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
	 * @return the serviceVehicleIdFk
	 */
	public Long getServiceVehicleIdFk() {
		return serviceVehicleIdFk;
	}
	/**
	 * @param serviceVehicleIdFk the serviceVehicleIdFk to set
	 */
	public void setServiceVehicleIdFk(Long serviceVehicleIdFk) {
		this.serviceVehicleIdFk = serviceVehicleIdFk;
	}

	/**
	 * @return the recordSourceType
	 */
	public String getRecordSourceType() {
		return recordSourceType;
	}
	/**
	 * @param recordSourceType the recordSourceType to set
	 */
	public void setRecordSourceType(String recordSourceType) {
		this.recordSourceType = recordSourceType;
	}
	/**
	 * @return the syncHistoryId
	 */
	public Long getSyncHistoryId() {
		return syncHistoryId;
	}
	/**
	 * @param syncHistoryId the syncHistoryId to set
	 */
	public void setSyncHistoryId(Long syncHistoryId) {
		this.syncHistoryId = syncHistoryId;
	}
	/**
	 * @return the whenAdded
	 */
	public Timestamp getWhenAdded() {
		return whenAdded;
	}
	/**
	 * @param whenAdded the whenAdded to set
	 */
	public void setWhenAdded(Timestamp whenAdded) {
		this.whenAdded = whenAdded;
	}


	

}
