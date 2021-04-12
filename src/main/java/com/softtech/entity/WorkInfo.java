package com.softtech.entity;

/**
 * 概要：稼働情報クラス
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/4/10
 */
public class WorkInfo {
	// 社員ID
	private String employeeID;
	// 社員氏名
	private String contractID;
	// 社員workInfoid
	private String workInfoID;
	private String employeeName = "0";
	private String workStartDay = "";
	private String workEndDay = "";
	private String workTime = "";
	private String workMonth = "0";

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
		this.workMonth = workMonth;
		//String.format("%s/%s",workMonth.substring(0,4),workMonth.substring(4,6));
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
	 * @return contractID
	 */
	public String getContractID() {
		return contractID;
	}

	/**
	 * @param contractID セットする contractID
	 */
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}

	/**
	 * @return workInfoID
	 */
	public String getWorkInfoID() {
		return workInfoID;
	}

	/**
	 * @param workInfoID セットする workInfoID
	 */
	public void setWorkInfoID(String workInfoID) {
		this.workInfoID = workInfoID;
	}
}
