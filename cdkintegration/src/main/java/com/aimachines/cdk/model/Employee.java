package com.aimachines.cdk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yac_employee_master")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String empId;
	
	private String bocId;
	private Long bocIdFk;
	
	private String storeId;
	private Long storeIdFk;
	
	private String firstName;
	private String lastName;
	private String email;
	
	
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
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
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
	
	
	
}
