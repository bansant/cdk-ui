package com.aimachines.cdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesDetailsDto {

	@JacksonXmlProperty(localName = "VIN")
	private String vin;

	@JacksonXmlProperty(localName = "VehicleMileage")
	private String vehicleMileage;

	@JacksonXmlProperty(localName = "Make")
	private String make;

	@JacksonXmlProperty(localName = "Model")
	private String model;
	
	@JacksonXmlProperty(localName = "Year")
	private String year;

	@JacksonXmlProperty(localName = "CRMFIMgrId")
	private String fiManagerId;

	@JacksonXmlProperty(localName = "Salesperson1")
	private String salesPersonId;

	@JacksonXmlProperty(localName = "CRMSalesMgrId")
	private String salesManagerId;

	@JacksonXmlProperty(localName = "CustNo")
	private String custNo;

	@JacksonXmlProperty(localName = "Name")
	private String customerFullName;

	@JacksonXmlProperty(localName = "Email1")
	private String customerEmailAddress;

	@JacksonXmlProperty(localName = "BusinessPhone")
	private String customerPhoneNo;

	@JacksonXmlProperty(localName = "ZipOrPostalCode")
	private String zip;

	@JacksonXmlProperty(localName = "DealType")
	private String dealType;

	@JacksonXmlProperty(localName = "CRMSaleType")
	private String saleType;

	@JacksonXmlProperty(localName = "SalesDate")
	private String salesDate;

	@JacksonXmlProperty(localName = "DealEvent5")
	private String dealEvent5;

	@JacksonXmlProperty(localName = "DealEvent5Date")
	private String dealEvent5Date;

	@JacksonXmlProperty(localName = "DealNo")
	private String dealNo;

	public SalesDetailsDto() {

	}

	
	/**
	 * @return the fiManagerId
	 */
	public String getFiManagerId() {
		return fiManagerId;
	}



	/**
	 * @param fiManagerId the fiManagerId to set
	 */
	public void setFiManagerId(String fiManagerId) {
		this.fiManagerId = fiManagerId;
	}



	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}


	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
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
	 * @return the vehicleMileage
	 */
	public String getVehicleMileage() {
		return vehicleMileage;
	}

	/**
	 * @param vehicleMileage the vehicleMileage to set
	 */
	public void setVehicleMileage(String vehicleMileage) {
		this.vehicleMileage = vehicleMileage;
	}

	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the salesPersonId
	 */
	public String getSalesPersonId() {
		return salesPersonId;
	}

	/**
	 * @param salesPersonId the salesPersonId to set
	 */
	public void setSalesPersonId(String salesPersonId) {
		this.salesPersonId = salesPersonId;
	}

	/**
	 * @return the salesManagerId
	 */
	public String getSalesManagerId() {
		return salesManagerId;
	}

	/**
	 * @param salesManagerId the salesManagerId to set
	 */
	public void setSalesManagerId(String salesManagerId) {
		this.salesManagerId = salesManagerId;
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
	 * @return the customerFullName
	 */
	public String getCustomerFullName() {
		return customerFullName;
	}

	/**
	 * @param customerFullName the customerFullName to set
	 */
	public void setCustomerFullName(String customerFullName) {
		this.customerFullName = customerFullName;
	}

	/**
	 * @return the customerEmailAddress
	 */
	public String getCustomerEmailAddress() {
		return customerEmailAddress;
	}

	/**
	 * @param customerEmailAddress the customerEmailAddress to set
	 */
	public void setCustomerEmailAddress(String customerEmailAddress) {
		this.customerEmailAddress = customerEmailAddress;
	}

	/**
	 * @return the customerPhoneNo
	 */
	public String getCustomerPhoneNo() {
		return customerPhoneNo;
	}

	/**
	 * @param customerPhoneNo the customerPhoneNo to set
	 */
	public void setCustomerPhoneNo(String customerPhoneNo) {
		this.customerPhoneNo = customerPhoneNo;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
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

	/**
	 * @return the salesDate
	 */
	public String getSalesDate() {
		return salesDate;
	}

	/**
	 * @param salesDate the salesDate to set
	 */
	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}

	/**
	 * @return the dealEvent5
	 */
	public String getDealEvent5() {
		return dealEvent5;
	}

	/**
	 * @param dealEvent5 the dealEvent5 to set
	 */
	public void setDealEvent5(String dealEvent5) {
		this.dealEvent5 = dealEvent5;
	}

	/**
	 * @return the dealEvent5Date
	 */
	public String getDealEvent5Date() {
		return dealEvent5Date;
	}

	/**
	 * @param dealEvent5Date the dealEvent5Date to set
	 */
	public void setDealEvent5Date(String dealEvent5Date) {
		this.dealEvent5Date = dealEvent5Date;
	}

	/**
	 * @return the dealNo
	 */
	public String getDealNo() {
		return dealNo;
	}

	/**
	 * @param dealNo the dealNo to set
	 */
	public void setDealNo(String dealNo) {
		this.dealNo = dealNo;
	}

}
