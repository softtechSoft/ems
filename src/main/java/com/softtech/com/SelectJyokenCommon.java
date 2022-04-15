package com.softtech.com;

/**
 * 機能：検索条件クラス
 *       (足りない項目を追加しでOK）
 *
 * @author 郭@ソフトテク
 *  * 作成日：2022/4/15
 */
public class SelectJyokenCommon {
	//社員ID
	private String emplyeeID;
	//年月-YYYYMM
	private String yearMonth;

	/**
	 * @return emplyeeID
	 */
	public String getEmplyeeID() {
		return emplyeeID;
	}
	/**
	 * @param emplyeeID セットする emplyeeID
	 */
	public void setEmplyeeID(String emplyeeID) {
		this.emplyeeID = emplyeeID;
	}
	/**
	 * @return yearMonth
	 */
	public String getYearMonth() {
		return yearMonth;
	}
	/**
	 * @param yearMonth セットする yearMonth
	 */
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

}