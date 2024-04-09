package com.aimachines.cdk.bean;

import com.aimachines.cdk.model.Customer;
import com.aimachines.cdk.model.InventoryVehicle;
import com.aimachines.cdk.model.Service;

public class ServiceClosedResponseBean {

	private Customer customer;
	private Service service;

	private InventoryVehicle vehicle;

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

	
	
}
