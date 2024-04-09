package com.aimachines.cdk.bean;

import com.aimachines.cdk.model.Customer;
import com.aimachines.cdk.model.Employee;
import com.aimachines.cdk.model.InventoryVehicle;
import com.aimachines.cdk.model.Sales;

public class SalesResponseBean {

	private Customer customer;
	private Sales sale;

	private InventoryVehicle vehicle;
	
	private Employee salesPerson;
	private Employee salesManager;
	private Employee f1Manager;

	
	/**
	 * @return the salesPerson
	 */
	public Employee getSalesPerson() {
		return salesPerson;
	}

	/**
	 * @param salesPerson the salesPerson to set
	 */
	public void setSalesPerson(Employee salesPerson) {
		this.salesPerson = salesPerson;
	}

	/**
	 * @return the salesManager
	 */
	public Employee getSalesManager() {
		return salesManager;
	}

	/**
	 * @param salesManager the salesManager to set
	 */
	public void setSalesManager(Employee salesManager) {
		this.salesManager = salesManager;
	}

	/**
	 * @return the f1Manager
	 */
	public Employee getF1Manager() {
		return f1Manager;
	}

	/**
	 * @param f1Manager the f1Manager to set
	 */
	public void setF1Manager(Employee f1Manager) {
		this.f1Manager = f1Manager;
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
	 * @return the sale
	 */
	public Sales getSale() {
		return sale;
	}

	/**
	 * @param sale the sale to set
	 */
	public void setSale(Sales sale) {
		this.sale = sale;
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
