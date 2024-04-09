package com.aimachines.cdk.bean;

import java.util.List;

import com.aimachines.cdk.model.Customer;
import com.aimachines.cdk.model.InventoryVehicle;
import com.aimachines.cdk.model.OpenRo;
import com.aimachines.cdk.model.Service;
import com.aimachines.cdk.model.ServiceDetail;

public class ServiceDetailResponseBean {

	private Customer customer;
	private OpenRo openRo;
	private Service service;
	private InventoryVehicle vehicle;

	private List<ServiceDetailBean> lineItems;

	
	/**
	 * @return the openRo
	 */
	public OpenRo getOpenRo() {
		return openRo;
	}

	/**
	 * @param openRo the openRo to set
	 */
	public void setOpenRo(OpenRo openRo) {
		this.openRo = openRo;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the service
	 */
	public Service getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}

	/**
	 * @return the vehicle
	 */
	public InventoryVehicle getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(InventoryVehicle vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * @return the lineItems
	 */
	public List<ServiceDetailBean> getLineItems() {
		return lineItems;
	}

	/**
	 * @param lineItems the lineItems to set
	 */
	public void setLineItems(List<ServiceDetailBean> lineItems) {
		this.lineItems = lineItems;
	}

}
