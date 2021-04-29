package com.softtech.actionForm;
/**
 * 概要：勤怠情報クラス
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/4/10
 */

public class WorkDetail {
	// 社員id
	private  String employeeID;
	// 社員氏名
	private String employeeName;
	// 対象年月
	private String workMonth;
	// 稼働時間
	private float workTime;
	//定期券
	private String transportExpense;
	//他の交通費(円)
	private String transport;
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


	/**
	 * @return transportExpense
	 */
	public String getTransportExpense() {
		return transportExpense;
	}
	/**
	 * @param transportExpense セットする transportExpense
	 */
	public void setTransportExpense(String transportExpense) {
		this.transportExpense = transportExpense;
	}
	/**
	 * @return transport
	 */
	public String getTransport() {
		return transport;
	}
	/**
	 * @param transport セットする transport
	 */
	public void setTransport(String transport) {
		this.transport = transport;
	}





}
