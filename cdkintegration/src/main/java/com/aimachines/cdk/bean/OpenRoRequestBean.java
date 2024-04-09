package com.aimachines.cdk.bean;

import java.util.List;

public class OpenRoRequestBean {

	public List<String> statusCode;
	public String storeId;
	
	public String advisorNo;
	
	
	/**
	 * @return the advisorNo
	 */
	public String getAdvisorNo() {
		return advisorNo;
	}
	/**
	 * @param advisorNo the advisorNo to set
	 */
	public void setAdvisorNo(String advisorNo) {
		this.advisorNo = advisorNo;
	}
	/**
	 * @return the statusCode
	 */
	public List<String> getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(List<String> statusCode) {
		this.statusCode = statusCode;
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
	
	
	


	
}
