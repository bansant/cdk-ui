package com.aimachines.cdk.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yac_op_code_master")
public class OpCodeMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String opCode;
	private String opCodeDescription;
	private String category;

//	@Column(name="createdOn")
//	private Timestamp createdOn;
//	@Column(name="updatedOn")
//	private Timestamp updatedOn;

	private String storeId;
	private Long storeIdFk;
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
	 * @return the opCode
	 */
	public String getOpCode() {
		return opCode;
	}
	/**
	 * @param opCode the opCode to set
	 */
	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}
	/**
	 * @return the opCodeDescription
	 */
	public String getOpCodeDescription() {
		return opCodeDescription;
	}
	/**
	 * @param opCodeDescription the opCodeDescription to set
	 */
	public void setOpCodeDescription(String opCodeDescription) {
		this.opCodeDescription = opCodeDescription;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
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
