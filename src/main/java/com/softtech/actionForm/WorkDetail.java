package com.softtech.actionForm;

public class WorkDetail {
	// 社員id
	private  String workInfoID;
	// 社員氏名
	private String contractID;
	// 対象年月
	private String workMonth;
	// 稼働時間
	private int workTime;
	//定期券変更
	//private String a;
	//定期券額（円)
	private int transportExpense;
	//他の交通費(円
	private int transport;
	//プロジェクト名
	//private String c;
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
	/**
	 * @return transportExpense
	 */
	public int getTransportExpense() {
		return transportExpense;
	}
	/**
	 * @param transportExpense セットする transportExpense
	 */
	public void setTransportExpense(int transportExpense) {
		this.transportExpense = transportExpense;
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
	public int getWorkTime() {
		return workTime;
	}
	/**
	 * @param workTime セットする workTime
	 */
	public void setWorkTime(int workTime) {
		this.workTime = workTime;
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



}
