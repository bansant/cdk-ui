package com.aimachines.cdk.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yac_customer_master")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String custNo;
	private String bocId;
	private Long bocIdFk;
	private String firstName;
	private String lastName;
	private String email;
	
	private String zipCode;
	private String homePhone;
	private String cellular;
	private String businessPhone;
	private String locale;
	
//	@Column(name="createdOn",na)
//	private Timestamp createdOn;
//	@Column(name="updatedOn")
//	private Timestamp updatedOn;
	

	private boolean blockEmail;
	private boolean blockMail;
	private boolean blockPhone;
	private boolean blockSms;
	
	private String recordSource;
	private Long syncHistoryId;
	
	
	private Timestamp whenAdded;
	private String lineType;
	private String lineProvider;
	
	
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
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the homePhone
	 */
	public String getHomePhone() {
		return homePhone;
	}
	/**
	 * @param homePhone the homePhone to set
	 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
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
	 * @return the businessPhone
	 */
	public String getBusinessPhone() {
		return businessPhone;
	}
	/**
	 * @param businessPhone the businessPhone to set
	 */
	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}
	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}
	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	
	/**
	 * @return the blockEmail
	 */
	public boolean isBlockEmail() {
		return blockEmail;
	}
	/**
	 * @param blockEmail the blockEmail to set
	 */
	public void setBlockEmail(boolean blockEmail) {
		this.blockEmail = blockEmail;
	}
	/**
	 * @return the blockMail
	 */
	public boolean isBlockMail() {
		return blockMail;
	}
	/**
	 * @param blockMail the blockMail to set
	 */
	public void setBlockMail(boolean blockMail) {
		this.blockMail = blockMail;
	}
	/**
	 * @return the blockPhone
	 */
	public boolean isBlockPhone() {
		return blockPhone;
	}
	/**
	 * @param blockPhone the blockPhone to set
	 */
	public void setBlockPhone(boolean blockPhone) {
		this.blockPhone = blockPhone;
	}
	/**
	 * @return the blockSms
	 */
	public boolean isBlockSms() {
		return blockSms;
	}
	/**
	 * @param blockSms the blockSms to set
	 */
	public void setBlockSms(boolean blockSms) {
		this.blockSms = blockSms;
	}
	/**
	 * @return the recordSource
	 */
	public String getRecordSource() {
		return recordSource;
	}
	/**
	 * @param recordSource the recordSource to set
	 */
	public void setRecordSource(String recordSource) {
		this.recordSource = recordSource;
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
	/**
	 * @return the lineType
	 */
	public String getLineType() {
		return lineType;
	}
	/**
	 * @param lineType the lineType to set
	 */
	public void setLineType(String lineType) {
		this.lineType = lineType;
	}
	/**
	 * @return the lineProvider
	 */
	public String getLineProvider() {
		return lineProvider;
	}
	/**
	 * @param lineProvider the lineProvider to set
	 */
	public void setLineProvider(String lineProvider) {
		this.lineProvider = lineProvider;
	}
	
	
}
