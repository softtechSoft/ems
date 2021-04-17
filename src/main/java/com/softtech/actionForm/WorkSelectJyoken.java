package com.softtech.actionForm;
/**
 * 概要：対象月クラス
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/4/10
 */

import javax.validation.constraints.Size;

public class WorkSelectJyoken {

	//対象年月
	@Size(min=2, max=30,message="月を入力してください。例：202104")
    private String month;
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

}
