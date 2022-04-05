package com.softtech.com;
/**
 * 概要：給料に関わるクラス
 *
 * 作成者：テー@ソフトテク
 * 作成日：2022/2/22
 */
public class SalaryCommon {
    // 開始年月
    private String fromMonth;

    // 終了年月
    private String toMonth;

    // ユーザーID
    private String employeeID;




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
	 * @return fromMonth
	 */
	public String getFromMonth() {
		return fromMonth;
	}

	/**
	 * @param fromMonth セットする fromMonth
	 */
	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}

	/**
	 * @return toMonth
	 */
	public String getToMonth() {
		return toMonth;
	}

	/**
	 * @param toMonth セットする toMonth
	 */
	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;


	}

}


