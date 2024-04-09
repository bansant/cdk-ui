package com.aimachines.cdk.bean;

import com.aimachines.cdk.model.Employee;
import com.aimachines.cdk.model.OpCodeMaster;
import com.aimachines.cdk.model.OpenRoDetail;
import com.aimachines.cdk.model.ServiceDetail;

public class ServiceDetailBean {

	private ServiceDetail service;
	
	private OpenRoDetail openRoDetail;

	private OpCodeMaster codeMaster;
	
	private Employee technician;

	
	
	/**
	 * @return the technician
	 */
	public Employee getTechnician() {
		return technician;
	}

	/**
	 * @param technician the technician to set
	 */
	public void setTechnician(Employee technician) {
		this.technician = technician;
	}

	/**
	 * @return the openRoDetail
	 */
	public OpenRoDetail getOpenRoDetail() {
		return openRoDetail;
	}

	/**
	 * @param openRoDetail the openRoDetail to set
	 */
	public void setOpenRoDetail(OpenRoDetail openRoDetail) {
		this.openRoDetail = openRoDetail;
	}

	/**
	 * @return the service
	 */
	public ServiceDetail getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(ServiceDetail service) {
		this.service = service;
	}

	/**
	 * @return the codeMaster
	 */
	public OpCodeMaster getCodeMaster() {
		return codeMaster;
	}

	/**
	 * @param codeMaster the codeMaster to set
	 */
	public void setCodeMaster(OpCodeMaster codeMaster) {
		this.codeMaster = codeMaster;
	}

}
