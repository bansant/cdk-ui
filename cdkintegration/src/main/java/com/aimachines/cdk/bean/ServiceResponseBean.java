package com.aimachines.cdk.bean;

import java.util.List;

import com.aimachines.cdk.model.Customer;
import com.aimachines.cdk.model.Employee;
import com.aimachines.cdk.model.InventoryVehicle;
import com.aimachines.cdk.model.OpenRo;
import com.aimachines.cdk.model.OpenRoDetail;

public class ServiceResponseBean {

	private Customer customer;
	private OpenRo service;
	private Employee advisor;
	private long daysDiff;

	private List<Employee> technicianId;

	private InventoryVehicle vehicle;

	private List<OpenRoDetail> lineItemList;

	/**
	 * @return the lineItemList
	 */
	public List<OpenRoDetail> getLineItemList() {
		return lineItemList;
	}

	/**
	 * @param lineItemList the lineItemList to set
	 */
	public void setLineItemList(List<OpenRoDetail> lineItemList) {
		this.lineItemList = lineItemList;
	}

	/**
	 * @return the technicianId
	 */
	public List<Employee> getTechnicianId() {
		return technicianId;
	}

	/**
	 * @param technicianId the technicianId to set
	 */
	public void setTechnicianId(List<Employee> technicianId) {
		this.technicianId = technicianId;
	}

	/**
	 * @return the daysDiff
	 */
	public long getDaysDiff() {
		return daysDiff;
	}

	/**
	 * @param daysDiff the daysDiff to set
	 */
	public void setDaysDiff(long daysDiff) {
		this.daysDiff = daysDiff;
	}

	/**
	 * @return the advisor
	 */
	public Employee getAdvisor() {
		return advisor;
	}

	/**
	 * @param advisor the advisor to set
	 */
	public void setAdvisor(Employee advisor) {
		this.advisor = advisor;
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
	public OpenRo getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(OpenRo service) {
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
