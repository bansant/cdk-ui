package com.aimachines.cdk.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yac_service")
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String bocId;
	private Long bocIdFk;

	private String storeId;
	private Long storeIdFk;

	private String custNo;
	private Long customerIdFk;
	
	private String advisorNo;
	
	private String roNumber;
	
	private String vin;
	private Long serviceVehicleIdFk;

	private Timestamp openedDate;
	private Timestamp closeDate;

	private String rosalecp;
	private String rosaleip;
	private String rosalewp;
	private Long mileage;
	private String recordSourceType;

//	@Column(name="createdOn")
//	private Timestamp createdOn;
//	@Column(name="updatedOn")
//	private Timestamp updatedOn;

	private Long syncHistoryId;
	private Timestamp whenAdded;

	

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
	 * @return the bocId
	 */
	public String getBocId() {
		return bocId;
	}

	/**
	 * @param bocId the bocId to set
	 */
	public void setBocId(String bocId) {
		this.bocId = bocId;
	}

	/**
	 * @return the bocIdFk
	 */
	public Long getBocIdFk() {
		return bocIdFk;
	}

	/**
	 * @param bocIdFk the bocIdFk to set
	 */
	public void setBocIdFk(Long bocIdFk) {
		this.bocIdFk = bocIdFk;
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
	 * @return the roNumber
	 */
	public String getRoNumber() {
		return roNumber;
	}

	/**
	 * @param roNumber the roNumber to set
	 */
	public void setRoNumber(String roNumber) {
		this.roNumber = roNumber;
	}

	/**
	 * @return the advisorNo
	 */
	public String getAdvisorNo() {
		return advisorNo;
	}

	/**
	 * @param advisorNo the advisorNo to set
	 */
	public void setAdvisorNo(String advisorNo) {
		this.advisorNo = advisorNo;
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
	 * @return the openedDate
	 */
	public Timestamp getOpenedDate() {
		return openedDate;
	}

	/**
	 * @param openedDate the openedDate to set
	 */
	public void setOpenedDate(Timestamp openedDate) {
		this.openedDate = openedDate;
	}

	/**
	 * @return the closeDate
	 */
	public Timestamp getCloseDate() {
		return closeDate;
	}

	/**
	 * @param closeDate the closeDate to set
	 */
	public void setCloseDate(Timestamp closeDate) {
		this.closeDate = closeDate;
	}

	/**
	 * @return the rosalecp
	 */
	public String getRosalecp() {
		return rosalecp;
	}

	/**
	 * @param rosalecp the rosalecp to set
	 */
	public void setRosalecp(String rosalecp) {
		this.rosalecp = rosalecp;
	}

	/**
	 * @return the rosaleip
	 */
	public String getRosaleip() {
		return rosaleip;
	}

	/**
	 * @param rosaleip the rosaleip to set
	 */
	public void setRosaleip(String rosaleip) {
		this.rosaleip = rosaleip;
	}

	/**
	 * @return the rosalewp
	 */
	public String getRosalewp() {
		return rosalewp;
	}

	/**
	 * @param rosalewp the rosalewp to set
	 */
	public void setRosalewp(String rosalewp) {
		this.rosalewp = rosalewp;
	}

	/**
	 * @return the mileage
	 */
	public Long getMileage() {
		return mileage;
	}

	/**
	 * @param mileage the mileage to set
	 */
	public void setMileage(Long mileage) {
		this.mileage = mileage;
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
