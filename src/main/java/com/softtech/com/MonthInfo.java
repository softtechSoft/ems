package com.softtech.com;
/**
 * 概要：年度情報クラス
 *
 * 作成者：開発@ソフトテク
 * 作成日：2021/4/23
 */
public class MonthInfo {
	//月度ID
	private Integer id;
	//月度
	private String name;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id セットする id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}


}