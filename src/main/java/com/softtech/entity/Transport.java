package com.softtech.entity;
/**
 * 概要：交通情報クラス
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/4/10
 */

public class Transport {

	private String flag ="2";
	private String workMonth = "0";
	private String startDate = "";
	private String startStation = "";
	private String endStation = "";
	private String transportFacility = "";
	private String midStation1 = "";
	private String transportFacility1 = "";
	private String midStation2 = "";
	private String midStation3 = "";
	private String state = "";
	private String workStartDay;
	private String workEndDay;
	//定期券額（円)
	private String transportExpense = "0";
	private String businessTrip = "0";

	// 社員ID
	private String employeeID;
	//社員氏名
	private String employeeName;
	// 交通費
	private int transport;
	// 1ヶ月分定期券
	private float transportExpense1;
	// 稼働時間
	private float workTime;

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
		if(startDate != null && startDate.length()>4) {
			this.startDate = String.format("%s/%s/%s", startDate.substring(0, 4), startDate.substring(4, 6),
				startDate.substring(6, 8));
		}
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

	/**
	 * @return employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName セットする employeeName
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return transportExpense1
	 */
	public float getTransportExpense1() {
		return transportExpense1;
	}

	/**
	 * @param transportExpense1 セットする transportExpense1
	 */
	public void setTransportExpense1(float transportExpense1) {
		this.transportExpense1 = transportExpense1;
	}

	/**
	 * @return workTime
	 */
	public float getWorkTime() {
		return workTime;
	}

	/**
	 * @param workTime セットする workTime
	 */
	public void setWorkTime(float workTime) {
		this.workTime = workTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWorkStartDay() {
		return workStartDay;
	}

	public void setWorkStartDay(String workStartDay) {
		this.workStartDay = workStartDay;
	}

	public String getWorkEndDay() {
		return workEndDay;
	}

	public void setWorkEndDay(String workEndDay) {
		this.workEndDay = workEndDay;
	}

}
