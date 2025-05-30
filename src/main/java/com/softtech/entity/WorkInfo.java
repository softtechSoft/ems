package com.softtech.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 概要：稼働情報クラス
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/4/10
 */
public class WorkInfo {
	// 社員ID
	private String employeeID;
	private String contractID;
	// 社員氏名
	private String employeeName = "0";
	private java.time.LocalDate workStartDay;
	private java.time.LocalDate workEndDay;
	private BigDecimal workTime;
	private String workMonth = "0";
	
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public LocalDate getWorkStartDay() {
        return workStartDay;
    }
    public void setWorkStartDay(LocalDate workStartDay) {
        this.workStartDay = workStartDay;
    }
    public LocalDate getWorkEndDay() {
        return workEndDay;
    }
    public void setWorkEndDay(LocalDate workEndDay) {
        this.workEndDay = workEndDay;
    }
    public BigDecimal getWorkTime() {
        return workTime;
    }
    public void setWorkTime(BigDecimal workTime) {
        this.workTime = workTime;
    }
    public String getWorkMonth() {
        return workMonth;
    }
    public void setWorkMonth(String workMonth) {
        this.workMonth = workMonth;
    }
    public String getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
    public String getContractID() {
        return contractID;
    }
    public void setContractID(String contractID) {
        this.contractID = contractID;
    }
}
