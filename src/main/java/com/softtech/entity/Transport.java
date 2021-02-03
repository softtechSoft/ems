package com.softtech.entity;

public class Transport 
{
	private String employeeName;
	private String workStartDay;
	private String workEndDay;
	private String workTime;
	private String startStation;
	private String endStation;
	private String transportFacility;
	private String midStation1;
	private String midStation2;
	private String midStation3;
	private String transportExpense;
	private String transportFacility1;
	private String businessTrip;
	
	public Transport()
	{
		
	}
	public Transport(String employeeName,String workStartDay,String workEndDay,String workTime,
					 String startStation,String endStation,String transportFacility,String midStation1,
					 String midStation2,String midStation3,String transportExpense,String transportFacility1,
					 String businessTrip) 
	{
		super();
		this.employeeName = employeeName;
		this.workEndDay = workStartDay;
		this.workEndDay = workEndDay;
		this.workTime = workTime;
		this.startStation = startStation;
		this.endStation = endStation;
		this.transportFacility = transportFacility;
		this.midStation1 = midStation1;
		this.midStation2 = midStation2;
		this.midStation3 = midStation3;
		this.transportExpense = transportExpense;
		this.transportFacility1 = transportFacility1;
		this.businessTrip = businessTrip;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
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
	@Override
	public String toString() {
		return "Transport [employeeName=" + employeeName + ", workStartDay=" + workStartDay + ", workEndDay="
				+ workEndDay + ", workTime=" + workTime + ", startStation=" + startStation + ", endStation="
				+ endStation + ", transportFacility=" + transportFacility + ", midStation1=" + midStation1
				+ ", midStation2=" + midStation2 + ", midStation3=" + midStation3 + ", transportExpense="
				+ transportExpense + ", transportFacility1=" + transportFacility1 + ", businessTrip=" + businessTrip
				+ "]";
	}
}
