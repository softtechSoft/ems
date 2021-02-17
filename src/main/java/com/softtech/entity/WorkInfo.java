package com.softtech.entity;

public class WorkInfo
{
	private String employeeName="0";
	private String workStartDay="";
	private String workEndDay="";
	private String workTime="";
	private String workMonth="0";

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

	@Override
	public String toString() {
		return "Transport [employeeName=" + employeeName + ", workStartDay=" + workStartDay + ", workEndDay="
				+ workEndDay + ", workTime=" + workTime + "]";
	}
	public String getWorkMonth() {
		return workMonth;
	}
	public void setWorkMonth(String workMonth) {
		this.workMonth =workMonth;
				//String.format("%s/%s",workMonth.substring(0,4),workMonth.substring(4,6));
	}
}

