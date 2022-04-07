package com.softtech.actionForm;

import java.util.ArrayList;

import com.softtech.com.EptypeInfo;


/**
 * 概要：給料詳細画面用Bean
 *
 * 作成者：劉@ソフトテク
 * 作成日：2021/7/20
 */
public class EmployeeInfoBean {

	//ユーザID
	private String employeeID ;
	//ユーザ名
	private String employeeName ;
	// 住所
	private String address ;
	//社員タイプ
	public ArrayList<EptypeInfo> epTypeInfoList;
	public ArrayList<EptypeInfo> getEpTypeInfoList() {
		return epTypeInfoList;
	}
	public void setEpTypeInfoList(ArrayList<EptypeInfo> epTypeInfoList) {
		this.epTypeInfoList = epTypeInfoList;
	}
	private Integer selectedepTypeId;

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
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address
	 * @param address セットする address
	 */
	public void setAddress(String address) {
		this.address = address;

	}
	public Integer getSelectedepTypeId() {
		return selectedepTypeId;
	}
	public void setSelectedepTypeId(Integer selectedepTypeId) {
		this.selectedepTypeId = selectedepTypeId;
	}

	}







