package com.aimachines.cdk.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceRepairOrderHistoryDto {

	public ServiceRepairOrderHistoryDto(String index) {

	}

	@JacksonXmlProperty(localName = "payPaymentAmount")
	private ObjectDto payPaymentAmount;

	
	
	@JacksonXmlProperty(localName = "RONumber")
	private String roNo;

	@JacksonXmlProperty(localName = "prtLineCode")
	private ObjectDto prtLineCode;
	
	@JacksonXmlProperty(localName = "ServiceAdvisor")
	private String serviceAdvisor;

	@JacksonXmlProperty(localName = "lbrLineCode")
	private ObjectDto lbrLineCode;

	@JacksonXmlProperty(localName = "hrsActualHours")
	private ObjectDto hrsActualHours;
	
	@JacksonXmlProperty(localName = "lbrSoldHours")
	private ObjectDto lbrSoldHours;
	
	@JacksonXmlProperty(localName = "totLaborSale")
	private ObjectDto totLaborSale;
	
	@JacksonXmlProperty(localName = "lbrTechNo")
	private ObjectDto labourTechnician;
	
	@JacksonXmlProperty(localName = "totLaborCost")
	private ObjectDto totLaborCost;
	
	@JacksonXmlProperty(localName = "totPartsSale")
	private ObjectDto totPartsSale;
	
	@JacksonXmlProperty(localName = "totPartsCost")
	private ObjectDto totPartsCost;
	
	@JacksonXmlProperty(localName = "totMiscCost")
	private ObjectDto totMiscCost;
	
	@JacksonXmlProperty(localName = "totMiscSale")
	private ObjectDto totMiscSale;
	
	
	@JacksonXmlProperty(localName = "lbrOpCode")
	private ObjectDto lbrOpCode;
	
	@JacksonXmlProperty(localName = "linStatusCode")
	private ObjectDto lineItemStatusCode;
	
	@JacksonXmlProperty(localName = "linStatusDesc")
	private ObjectDto lineItemStatusDesc;

	@JacksonXmlProperty(localName = "lbrOpCodeDesc")
	private ObjectDto lbrOpCodeDesc;

	@JacksonXmlProperty(localName = "prtPartNo")
	private ObjectDto prtPartNo;

	@JacksonXmlProperty(localName = "prtDesc")
	private ObjectDto prtDesc;

	@JacksonXmlProperty(localName = "linEstDuration")
	private ObjectDto linEstDuration;

	@JacksonXmlProperty(localName = "linComplaintCode")
	private ObjectDto liineItemCode;

	@JacksonXmlProperty(localName = "BookedDate")
	private String bookedDate;

	@JacksonXmlProperty(localName = "OpenDate")
	private String openDate;

	@JacksonXmlProperty(localName = "ClosedDate")
	private String closedDate;

	@JacksonXmlProperty(localName = "BookedTime")
	private String bookedTime;

	@JacksonXmlProperty(localName = "StatusDesc")
	private String statusDesc;

	@JacksonXmlProperty(localName = "StatusCode")
	private String statusCode;

	@JacksonXmlProperty(localName = "LastServiceDate")
	private String lastServiceDate;

	@JacksonXmlProperty(localName = "ModelDesc")
	private String modelDesc;

	@JacksonXmlProperty(localName = "VIN")
	private String vin;

	@JacksonXmlProperty(localName = "Year")
	private String year;

	@JacksonXmlProperty(localName = "VehID")
	private String vehID;

	@JacksonXmlProperty(localName = "Make")
	private String make;

	@JacksonXmlProperty(localName = "MakeDesc")
	private String makeDesc;

	@JacksonXmlProperty(localName = "lbrLaborType")
	private ObjectDto lbrLaborType;

	@JacksonXmlProperty(localName = "prtLaborType")
	private ObjectDto prtLaborType;

	@JacksonXmlProperty(localName = "MileageOut")
	private String mileageOut;

	@JacksonXmlProperty(localName = "Mileage")
	private String mileage;

	@JacksonXmlProperty(localName = "CustNo")
	private String custNo;

	@JacksonXmlProperty(localName = "Name1")
	private String customerFullName;

	@JacksonXmlProperty(localName = "ContactEmailAddress")
	private String contactEmailAddress;

	@JacksonXmlProperty(localName = "ContactPhoneNumber")
	private String contactPhoneNumber;

	@JacksonXmlProperty(localName = "Zip")
	private String zip;

	public ServiceRepairOrderHistoryDto() {
	}

	
	/**
	 * @return the lineItemStatusCode
	 */
	public ObjectDto getLineItemStatusCode() {
		return lineItemStatusCode;
	}




	/**
	 * @param lineItemStatusCode the lineItemStatusCode to set
	 */
	public void setLineItemStatusCode(ObjectDto lineItemStatusCode) {
		this.lineItemStatusCode = lineItemStatusCode;
	}




	/**
	 * @return the lineItemStatusDesc
	 */
	public ObjectDto getLineItemStatusDesc() {
		return lineItemStatusDesc;
	}




	/**
	 * @param lineItemStatusDesc the lineItemStatusDesc to set
	 */
	public void setLineItemStatusDesc(ObjectDto lineItemStatusDesc) {
		this.lineItemStatusDesc = lineItemStatusDesc;
	}




	/**
	 * @return the labourTechnician
	 */
	public ObjectDto getLabourTechnician() {
		return labourTechnician;
	}




	/**
	 * @param labourTechnician the labourTechnician to set
	 */
	public void setLabourTechnician(ObjectDto labourTechnician) {
		this.labourTechnician = labourTechnician;
	}




	/**
	 * @return the serviceAdvisor
	 */
	public String getServiceAdvisor() {
		return serviceAdvisor;
	}


	/**
	 * @param serviceAdvisor the serviceAdvisor to set
	 */
	public void setServiceAdvisor(String serviceAdvisor) {
		this.serviceAdvisor = serviceAdvisor;
	}


	/**
	 * @return the totLaborSale
	 */
	public ObjectDto getTotLaborSale() {
		return totLaborSale;
	}


	/**
	 * @param totLaborSale the totLaborSale to set
	 */
	public void setTotLaborSale(ObjectDto totLaborSale) {
		this.totLaborSale = totLaborSale;
	}


	/**
	 * @return the hrsActualHours
	 */
	public ObjectDto getHrsActualHours() {
		return hrsActualHours;
	}


	/**
	 * @param hrsActualHours the hrsActualHours to set
	 */
	public void setHrsActualHours(ObjectDto hrsActualHours) {
		this.hrsActualHours = hrsActualHours;
	}


	/**
	 * @return the lbrSoldHours
	 */
	public ObjectDto getLbrSoldHours() {
		return lbrSoldHours;
	}


	/**
	 * @param lbrSoldHours the lbrSoldHours to set
	 */
	public void setLbrSoldHours(ObjectDto lbrSoldHours) {
		this.lbrSoldHours = lbrSoldHours;
	}


	/**
	 * @return the totLaborCost
	 */
	public ObjectDto getTotLaborCost() {
		return totLaborCost;
	}


	/**
	 * @param totLaborCost the totLaborCost to set
	 */
	public void setTotLaborCost(ObjectDto totLaborCost) {
		this.totLaborCost = totLaborCost;
	}


	/**
	 * @return the totPartsSale
	 */
	public ObjectDto getTotPartsSale() {
		return totPartsSale;
	}


	/**
	 * @param totPartsSale the totPartsSale to set
	 */
	public void setTotPartsSale(ObjectDto totPartsSale) {
		this.totPartsSale = totPartsSale;
	}


	/**
	 * @return the totPartsCost
	 */
	public ObjectDto getTotPartsCost() {
		return totPartsCost;
	}


	/**
	 * @param totPartsCost the totPartsCost to set
	 */
	public void setTotPartsCost(ObjectDto totPartsCost) {
		this.totPartsCost = totPartsCost;
	}


	/**
	 * @return the totMiscCost
	 */
	public ObjectDto getTotMiscCost() {
		return totMiscCost;
	}


	/**
	 * @param totMiscCost the totMiscCost to set
	 */
	public void setTotMiscCost(ObjectDto totMiscCost) {
		this.totMiscCost = totMiscCost;
	}


	/**
	 * @return the totMiscSale
	 */
	public ObjectDto getTotMiscSale() {
		return totMiscSale;
	}


	/**
	 * @param totMiscSale the totMiscSale to set
	 */
	public void setTotMiscSale(ObjectDto totMiscSale) {
		this.totMiscSale = totMiscSale;
	}


	/**
	 * @return the liineItemCode
	 */
	public ObjectDto getLiineItemCode() {
		return liineItemCode;
	}

	/**
	 * @param liineItemCode the liineItemCode to set
	 */
	public void setLiineItemCode(ObjectDto liineItemCode) {
		this.liineItemCode = liineItemCode;
	}

	/**
	 * @return the linEstDuration
	 */
	public ObjectDto getLinEstDuration() {
		return linEstDuration;
	}

	/**
	 * @param linEstDuration the linEstDuration to set
	 */
	public void setLinEstDuration(ObjectDto linEstDuration) {
		this.linEstDuration = linEstDuration;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the lbrLaborType
	 */
	public ObjectDto getLbrLaborType() {
		return lbrLaborType;
	}

	/**
	 * @param lbrLaborType the lbrLaborType to set
	 */
	public void setLbrLaborType(ObjectDto lbrLaborType) {
		this.lbrLaborType = lbrLaborType;
	}

	/**
	 * @return the prtLaborType
	 */
	public ObjectDto getPrtLaborType() {
		return prtLaborType;
	}

	/**
	 * @param prtLaborType the prtLaborType to set
	 */
	public void setPrtLaborType(ObjectDto prtLaborType) {
		this.prtLaborType = prtLaborType;
	}

	/**
	 * @return the openDate
	 */
	public String getOpenDate() {
		return openDate;
	}

	/**
	 * @param openDate the openDate to set
	 */
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	/**
	 * @return the closedDate
	 */
	public String getClosedDate() {
		return closedDate;
	}

	/**
	 * @param closedDate the closedDate to set
	 */
	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}

	/**
	 * @return the payPaymentAmount
	 */
	public ObjectDto getPayPaymentAmount() {
		return payPaymentAmount;
	}

	/**
	 * @param payPaymentAmount the payPaymentAmount to set
	 */
	public void setPayPaymentAmount(ObjectDto payPaymentAmount) {
		this.payPaymentAmount = payPaymentAmount;
	}

	/**
	 * @return the roNo
	 */
	public String getRoNo() {
		return roNo;
	}

	/**
	 * @param roNo the roNo to set
	 */
	public void setRoNo(String roNo) {
		this.roNo = roNo;
	}

	/**
	 * @return the prtLineCode
	 */
	public ObjectDto getPrtLineCode() {
		return prtLineCode;
	}

	/**
	 * @param prtLineCode the prtLineCode to set
	 */
	public void setPrtLineCode(ObjectDto prtLineCode) {
		this.prtLineCode = prtLineCode;
	}

	/**
	 * @return the lbrLineCode
	 */
	public ObjectDto getLbrLineCode() {
		return lbrLineCode;
	}

	/**
	 * @param lbrLineCode the lbrLineCode to set
	 */
	public void setLbrLineCode(ObjectDto lbrLineCode) {
		this.lbrLineCode = lbrLineCode;
	}

	/**
	 * @return the lbrOpCode
	 */
	public ObjectDto getLbrOpCode() {
		return lbrOpCode;
	}

	/**
	 * @param lbrOpCode the lbrOpCode to set
	 */
	public void setLbrOpCode(ObjectDto lbrOpCode) {
		this.lbrOpCode = lbrOpCode;
	}

	/**
	 * @return the lbrOpCodeDesc
	 */
	public ObjectDto getLbrOpCodeDesc() {
		return lbrOpCodeDesc;
	}

	/**
	 * @param lbrOpCodeDesc the lbrOpCodeDesc to set
	 */
	public void setLbrOpCodeDesc(ObjectDto lbrOpCodeDesc) {
		this.lbrOpCodeDesc = lbrOpCodeDesc;
	}

	/**
	 * @return the prtPartNo
	 */
	public ObjectDto getPrtPartNo() {
		return prtPartNo;
	}

	/**
	 * @param prtPartNo the prtPartNo to set
	 */
	public void setPrtPartNo(ObjectDto prtPartNo) {
		this.prtPartNo = prtPartNo;
	}

	/**
	 * @return the prtDesc
	 */
	public ObjectDto getPrtDesc() {
		return prtDesc;
	}

	/**
	 * @param prtDesc the prtDesc to set
	 */
	public void setPrtDesc(ObjectDto prtDesc) {
		this.prtDesc = prtDesc;
	}

	/**
	 * @return the bookedDate
	 */
	public String getBookedDate() {
		return bookedDate;
	}

	/**
	 * @param bookedDate the bookedDate to set
	 */
	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}

	/**
	 * @return the bookedTime
	 */
	public String getBookedTime() {
		return bookedTime;
	}

	/**
	 * @param bookedTime the bookedTime to set
	 */
	public void setBookedTime(String bookedTime) {
		this.bookedTime = bookedTime;
	}

	/**
	 * @return the statusDesc
	 */
	public String getStatusDesc() {
		return statusDesc;
	}

	/**
	 * @param statusDesc the statusDesc to set
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	/**
	 * @return the lastServiceDate
	 */
	public String getLastServiceDate() {
		return lastServiceDate;
	}

	/**
	 * @param lastServiceDate the lastServiceDate to set
	 */
	public void setLastServiceDate(String lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}

	/**
	 * @return the modelDesc
	 */
	public String getModelDesc() {
		return modelDesc;
	}

	/**
	 * @param modelDesc the modelDesc to set
	 */
	public void setModelDesc(String modelDesc) {
		this.modelDesc = modelDesc;
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
	 * @return the vehID
	 */
	public String getVehID() {
		return vehID;
	}

	/**
	 * @param vehID the vehID to set
	 */
	public void setVehID(String vehID) {
		this.vehID = vehID;
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
	 * @return the makeDesc
	 */
	public String getMakeDesc() {
		return makeDesc;
	}

	/**
	 * @param makeDesc the makeDesc to set
	 */
	public void setMakeDesc(String makeDesc) {
		this.makeDesc = makeDesc;
	}

	/**
	 * @return the mileageOut
	 */
	public String getMileageOut() {
		return mileageOut;
	}

	/**
	 * @param mileageOut the mileageOut to set
	 */
	public void setMileageOut(String mileageOut) {
		this.mileageOut = mileageOut;
	}

	/**
	 * @return the mileage
	 */
	public String getMileage() {
		return mileage;
	}

	/**
	 * @param mileage the mileage to set
	 */
	public void setMileage(String mileage) {
		this.mileage = mileage;
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
	 * @return the contactEmailAddress
	 */
	public String getContactEmailAddress() {
		return contactEmailAddress;
	}

	/**
	 * @param contactEmailAddress the contactEmailAddress to set
	 */
	public void setContactEmailAddress(String contactEmailAddress) {
		this.contactEmailAddress = contactEmailAddress;
	}

	/**
	 * @return the contactPhoneNumber
	 */
	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	/**
	 * @param contactPhoneNumber the contactPhoneNumber to set
	 */
	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
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

}
