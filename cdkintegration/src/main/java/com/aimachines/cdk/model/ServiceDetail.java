package com.aimachines.cdk.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yac_service_detail")
public class ServiceDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String bocId;
	private Long bocIdFk;

	private String storeId;
	private Long storeIdFk;

	private String roNumber;
	private Long serviceIdFk;
	private Long opCodeMasterIdFk;
	
	private String lopSeqNo;
	private String actualHours;
	private String soldHours;
	private String laborCost;
	private String laborSale;
	private String laborType;
	
	private String laborTechnician;
	
	
	private String miscCost;
	private String miscSale;
	private String partsCost;
	private String partsSale;
	
	private Timestamp closed;
	
//	@Column(name="createdOn")
//	private Timestamp createdOn;
//	@Column(name="updatedOn")
//	private Timestamp updatedOn;

	private Long syncHistoryId;
	private Timestamp whenAdded;
	
	
	
	/**
	 * @return the laborTechnician
	 */
	public String getLaborTechnician() {
		return laborTechnician;
	}
	/**
	 * @param laborTechnician the laborTechnician to set
	 */
	public void setLaborTechnician(String laborTechnician) {
		this.laborTechnician = laborTechnician;
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
	 * @return the serviceIdFk
	 */
	public Long getServiceIdFk() {
		return serviceIdFk;
	}
	/**
	 * @param serviceIdFk the serviceIdFk to set
	 */
	public void setServiceIdFk(Long serviceIdFk) {
		this.serviceIdFk = serviceIdFk;
	}
	/**
	 * @return the opCodeMasterIdFk
	 */
	public Long getOpCodeMasterIdFk() {
		return opCodeMasterIdFk;
	}
	/**
	 * @param opCodeMasterIdFk the opCodeMasterIdFk to set
	 */
	public void setOpCodeMasterIdFk(Long opCodeMasterIdFk) {
		this.opCodeMasterIdFk = opCodeMasterIdFk;
	}
	/**
	 * @return the lopSeqNo
	 */
	public String getLopSeqNo() {
		return lopSeqNo;
	}
	/**
	 * @param lopSeqNo the lopSeqNo to set
	 */
	public void setLopSeqNo(String lopSeqNo) {
		this.lopSeqNo = lopSeqNo;
	}
	/**
	 * @return the actualHours
	 */
	public String getActualHours() {
		return actualHours;
	}
	/**
	 * @param actualHours the actualHours to set
	 */
	public void setActualHours(String actualHours) {
		this.actualHours = actualHours;
	}
	/**
	 * @return the soldHours
	 */
	public String getSoldHours() {
		return soldHours;
	}
	/**
	 * @param soldHours the soldHours to set
	 */
	public void setSoldHours(String soldHours) {
		this.soldHours = soldHours;
	}
	/**
	 * @return the laborCost
	 */
	public String getLaborCost() {
		return laborCost;
	}
	/**
	 * @param laborCost the laborCost to set
	 */
	public void setLaborCost(String laborCost) {
		this.laborCost = laborCost;
	}
	/**
	 * @return the laborSale
	 */
	public String getLaborSale() {
		return laborSale;
	}
	/**
	 * @param laborSale the laborSale to set
	 */
	public void setLaborSale(String laborSale) {
		this.laborSale = laborSale;
	}
	/**
	 * @return the laborType
	 */
	public String getLaborType() {
		return laborType;
	}
	/**
	 * @param laborType the laborType to set
	 */
	public void setLaborType(String laborType) {
		this.laborType = laborType;
	}
	/**
	 * @return the miscCost
	 */
	public String getMiscCost() {
		return miscCost;
	}
	/**
	 * @param miscCost the miscCost to set
	 */
	public void setMiscCost(String miscCost) {
		this.miscCost = miscCost;
	}
	/**
	 * @return the miscSale
	 */
	public String getMiscSale() {
		return miscSale;
	}
	/**
	 * @param miscSale the miscSale to set
	 */
	public void setMiscSale(String miscSale) {
		this.miscSale = miscSale;
	}
	/**
	 * @return the partsCost
	 */
	public String getPartsCost() {
		return partsCost;
	}
	/**
	 * @param partsCost the partsCost to set
	 */
	public void setPartsCost(String partsCost) {
		this.partsCost = partsCost;
	}
	/**
	 * @return the partsSale
	 */
	public String getPartsSale() {
		return partsSale;
	}
	/**
	 * @param partsSale the partsSale to set
	 */
	public void setPartsSale(String partsSale) {
		this.partsSale = partsSale;
	}
	/**
	 * @return the closed
	 */
	public Timestamp getClosed() {
		return closed;
	}
	/**
	 * @param closed the closed to set
	 */
	public void setClosed(Timestamp closed) {
		this.closed = closed;
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
