package com.aimachines.cdk.bean;

import com.aimachines.cdk.model.Appointment;
import com.aimachines.cdk.model.Customer;
import com.aimachines.cdk.model.InventoryVehicle;

public class AppointmentResponseBean {

	private Customer customer;
	private Appointment appointment;

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
	 * @return the appointment
	 */
	public Appointment getAppointment() {
		return appointment;
	}

	/**
	 * @param appointment the appointment to set
	 */
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
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
