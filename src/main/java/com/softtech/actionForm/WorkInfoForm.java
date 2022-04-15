package com.softtech.actionForm;

/**
 * 概要：稼働情報Formクラス
 *
 * 作成者：郭@ソフトテク
 * 作成日：2022/4/15
 */
public class WorkInfoForm {
	// 社員ID
	private String employeeID;
	// 社員氏名
	private String employeeName;
	//対象月
	private String workMonth;
	// 契約ID
	private String contractID;
	//開始日
	private String workStartDay ;
	//終了日
	private String workEndDay ;
	//稼働時間
	private String workTime ;
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
	 * @return workMonth
	 */
	public String getWorkMonth() {
		return workMonth;
	}
	/**
	 * @param workMonth セットする workMonth
	 */
	public void setWorkMonth(String workMonth) {
		this.workMonth = workMonth;
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
	 * @return workStartDay
	 */
	public String getWorkStartDay() {
		return workStartDay;
	}
	/**
	 * @param workStartDay セットする workStartDay
	 */
	public void setWorkStartDay(String workStartDay) {
		this.workStartDay = workStartDay;
	}
	/**
	 * @return workEndDay
	 */
	public String getWorkEndDay() {
		return workEndDay;
	}
	/**
	 * @param workEndDay セットする workEndDay
	 */
	public void setWorkEndDay(String workEndDay) {
		this.workEndDay = workEndDay;
	}
	/**
	 * @return workTime
	 */
	public String getWorkTime() {
		return workTime;
	}
	/**
	 * @param workTime セットする workTime
	 */
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}


}
