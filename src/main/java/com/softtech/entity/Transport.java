package com.softtech.entity;

public class Transport {

	private String workMonth = "0";
	private String startDate = "";
	private String startStation = "";
	private String endStation = "";
	private String transportFacility = "";
	private String midStation1 = "";
	private String transportFacility1 = "";
	private String midStation2 = "";
	private String midStation3 = "";
	private String transportExpense = "";
	private String businessTrip = "0";

	// 社員ID
	private String employeeID;
	// 交通費
	private int transport;

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public String getEndStation() {
		return endStation;
	}

	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}

	public String getTransportFacility() {
		return transportFacility;
	}

	public void setTransportFacility(String transportFacility) {
		this.transportFacility = transportFacility;
	}

	public String getMidStation1() {
		return midStation1;
	}

	public void setMidStation1(String midStation1) {
		this.midStation1 = midStation1;
	}

	public String getMidStation2() {
		return midStation2;
	}

	public void setMidStation2(String midStation2) {
		this.midStation2 = midStation2;
	}

	public String getMidStation3() {
		return midStation3;
	}

	public void setMidStation3(String midStation3) {
		this.midStation3 = midStation3;
	}

	public String getTransportExpense() {
		return transportExpense;
	}

	public void setTransportExpense(String transportExpense) {
		this.transportExpense = transportExpense;
	}

	public String getTransportFacility1() {
		return transportFacility1;
	}

	public void setTransportFacility1(String transportFacility1) {
		this.transportFacility1 = transportFacility1;
	}

	public String getBusinessTrip() {
		return businessTrip;
	}

	public void setBusinessTrip(String businessTrip) {
		this.businessTrip = businessTrip;
	}

	public String getWorkMonth() {
		return workMonth;
	}

	public void setWorkMonth(String workMonth) {
		this.workMonth = String.format("%s/%s", workMonth.substring(0, 4), workMonth.substring(4, 6));
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = String.format("%s/%s/%s", startDate.substring(0, 4), startDate.substring(4, 6),
				startDate.substring(6, 8));
	}

	/**
	 * @return transport
	 */
	public int getTransport() {
		return transport;
	}

	/**
	 * @param transport セットする transport
	 */
	public void setTransport(int transport) {
		this.transport = transport;
	}

	/**
	 * @return employeeID
	 */
	public String getEmployeeID() {
		return employeeID;
	}

	/**
	 * @param employeeID セットする employeeID
	 */
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
}
