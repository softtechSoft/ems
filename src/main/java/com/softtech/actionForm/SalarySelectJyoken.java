package com.softtech.actionForm;
/**
 * 概要：対象月クラス
 *
 * 作成者：王@ソフトテク
 * 作成日：2021/4/13
 */

import javax.validation.constraints.Size;

public class SalarySelectJyoken {

	//対象年度
	private String year ;

	//対象年月
	@Size(min=2, max=30,message="月を入力してください。例：202104")
	private String month ;

	//ダウンロード
	private boolean downloadFlg;

	/**
	 * @return month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month セットする month
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	public boolean getDownloadFlg() {
		return downloadFlg;
	}

	public void setDownloadFlg(boolean downloadFlg) {
		this.downloadFlg = downloadFlg;
	}

	/**
	 * @return year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year セットする year
	 */
	public void setYear(String year) {
		this.year = year;
	}

}
