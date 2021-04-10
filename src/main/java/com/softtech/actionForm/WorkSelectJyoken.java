package com.softtech.actionForm;

import javax.validation.constraints.Size;

public class WorkSelectJyoken {

	//month
	@Size(min=2, max=30,message="デフォルトメッセージ２と３０の間のデータを入力してください。ここでカスタマイズできます。まとめてMessageファイルもできます。")
    private String month;

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

}
