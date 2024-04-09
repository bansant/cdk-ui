package com.aimachines.cdk.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "yac_sales")
public class Sales {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String bocId;
	private Long bocIdFk;
	
	private String storeId;
	private Long storeIdFk;

	private String custNo;
	private Long customerIdFk;
	
	
	private String dealNumber;
	private String dealType;
	
	private Timestamp soldDate;
	
	private String vin;
	private Long inventory_vehicle_id_fk;
	
	private String salesman;
	private String fiManager;
	
	private String slsManager;
	
	private String saleType;
	/**
	 * @return the saleType
	 */
	public String getSaleType() {
		return saleType;
	}
	/**
	 * @param saleType the saleType to set
	 */
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	private String tradeInFlag;
	
	private Timestamp dealevent5date;
	private String dealevent5;
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
	 * @return the dealNumber
	 */
	public String getDealNumber() {
		return dealNumber;
	}
	/**
	 * @param dealNumber the dealNumber to set
	 */
	public void setDealNumber(String dealNumber) {
		this.dealNumber = dealNumber;
	}
	/**
	 * @return the dealType
	 */
	public String getDealType() {
		return dealType;
	}
	/**
	 * @param dealType the dealType to set
	 */
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	/**
	 * @return the soldDate
	 */
	public Timestamp getSoldDate() {
		return soldDate;
	}
	/**
	 * @param soldDate the soldDate to set
	 */
	public void setSoldDate(Timestamp soldDate) {
		this.soldDate = soldDate;
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
	 * @return the inventory_vehicle_id_fk
	 */
	public Long getInventory_vehicle_id_fk() {
		return inventory_vehicle_id_fk;
	}
	/**
	 * @param inventory_vehicle_id_fk the inventory_vehicle_id_fk to set
	 */
	public void setInventory_vehicle_id_fk(Long inventory_vehicle_id_fk) {
		this.inventory_vehicle_id_fk = inventory_vehicle_id_fk;
	}
	/**
	 * @return the salesman
	 */
	public String getSalesman() {
		return salesman;
	}
	/**
	 * @param salesman the salesman to set
	 */
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	/**
	 * @return the fiManager
	 */
	public String getFiManager() {
		return fiManager;
	}
	/**
	 * @param fiManager the fiManager to set
	 */
	public void setFiManager(String fiManager) {
		this.fiManager = fiManager;
	}
	/**
	 * @return the slsManager
	 */
	public String getSlsManager() {
		return slsManager;
	}
	/**
	 * @param slsManager the slsManager to set
	 */
	public void setSlsManager(String slsManager) {
		this.slsManager = slsManager;
	}
	
	/**
	 * @return the tradeInFlag
	 */
	public String getTradeInFlag() {
		return tradeInFlag;
	}
	/**
	 * @param tradeInFlag the tradeInFlag to set
	 */
	public void setTradeInFlag(String tradeInFlag) {
		this.tradeInFlag = tradeInFlag;
	}
	/**
	 * @return the dealevent5date
	 */
	public Timestamp getDealevent5date() {
		return dealevent5date;
	}
	/**
	 * @param dealevent5date the dealevent5date to set
	 */
	public void setDealevent5date(Timestamp dealevent5date) {
		this.dealevent5date = dealevent5date;
	}
	/**
	 * @return the dealevent5
	 */
	public String getDealevent5() {
		return dealevent5;
	}
	/**
	 * @param dealevent5 the dealevent5 to set
	 */
	public void setDealevent5(String dealevent5) {
		this.dealevent5 = dealevent5;
	}
	
	
	

}
