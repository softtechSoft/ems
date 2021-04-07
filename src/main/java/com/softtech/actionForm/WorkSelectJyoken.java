package com.softtech.actionForm;

import javax.validation.constraints.NotNull;

public class WorkSelectJyoken {

	//month
	@NotNull(message="月を入力してください。")
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
