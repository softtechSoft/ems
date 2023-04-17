package com.softtech.entity;

public class Department {
	//部門ID
	private Integer id;    //フィールド(メンバ変数)
	//部門名称
	private String name;	//フィールド(メンバ変数)

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
