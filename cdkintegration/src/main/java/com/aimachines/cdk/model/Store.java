package com.aimachines.cdk.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yac_store_master")
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String bocId;
	private Long bocIdFk;

	private String storeId;
	private String storeName;

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
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
