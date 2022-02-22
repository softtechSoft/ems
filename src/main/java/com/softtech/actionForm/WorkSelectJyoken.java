package com.softtech.actionForm;
/**
 * 概要：対象月クラス
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/4/10
 */

import javax.validation.constraints.Size;

//検索条件
public class WorkSelectJyoken {

	//対象年月
	@Size(min=2, max=30,message="月を入力してください。例：202104")
    private String month;

	//対象開始年月
	 private String fromMonth;

	//対象終了年月
	private String toMonth;

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

	/**
	 * @return downloadFlg
	 */
	public boolean getDownloadFlg() {
		return downloadFlg;
	}

	/**
	 * @param downloadFlg セットする downloadFlg
	 */
	public void setDownloadFlg(boolean downloadFlg) {
		this.downloadFlg = downloadFlg;
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
