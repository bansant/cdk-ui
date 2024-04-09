package com.aimachines.cdk.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yac_open_ro_status_trail")
public class ServiceStatusTrail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String roNumber;
	private String prevStatusCode;
	private String currentStatusCode;
	
	private Timestamp createdOn;
	
	/**
	 * @return the createdOn
	 */
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
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
	 * @return the prevStatusCode
	 */
	public String getPrevStatusCode() {
		return prevStatusCode;
	}
	/**
	 * @param prevStatusCode the prevStatusCode to set
	 */
	public void setPrevStatusCode(String prevStatusCode) {
		this.prevStatusCode = prevStatusCode;
	}
	/**
	 * @return the currentStatusCode
	 */
	public String getCurrentStatusCode() {
		return currentStatusCode;
	}
	/**
	 * @param currentStatusCode the currentStatusCode to set
	 */
	public void setCurrentStatusCode(String currentStatusCode) {
		this.currentStatusCode = currentStatusCode;
	}
	
	
	
	
	
}
