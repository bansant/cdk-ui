package com.aimachines.cdk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yac_boc_master")
public class Boc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String bocId;
	private String bocName;
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
	 * @return the bocName
	 */
	public String getBocName() {
		return bocName;
	}
	/**
	 * @param bocName the bocName to set
	 */
	public void setBocName(String bocName) {
		this.bocName = bocName;
	}
	
	
}
