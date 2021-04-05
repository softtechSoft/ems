package com.softtech.actionForm;

public class WorkDetail {
	// 社員id
	private long id;
	// 社員氏名
	private String name;
	// 対象年月
	private String moon;
	// 稼働時間
	private String time;
	//定期券変更
	private String a;
	//定期券額（円)
	private long money;
	//他の交通費(円
	private long b;
	//プロジェクト名
	private String c;

	/**
	 * @return id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id セットする id
	 */
	public void setId(long id) {
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
	/**
	 * @return moon
	 */
	public String getMoon() {
		return moon;
	}
	/**
	 * @param moon セットする moon
	 */
	public void setMoon(String moon) {
		this.moon = moon;
	}
	/**
	 * @return time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time セットする time
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return a
	 */
	public String getA() {
		return a;
	}
	/**
	 * @param a セットする a
	 */
	public void setA(String a) {
		this.a = a;
	}
	/**
	 * @return money
	 */
	public long getMoney() {
		return money;
	}
	/**
	 * @param money セットする money
	 */
	public void setMoney(long money) {
		this.money = money;
	}
	/**
	 * @return b
	 */
	public long getB() {
		return b;
	}
	/**
	 * @param b セットする b
	 */
	public void setB(long b) {
		this.b = b;
	}
	/**
	 * @return c
	 */
	public String getC() {
		return c;
	}
	/**
	 * @param c セットする c
	 */
	public void setC(String c) {
		this.c = c;
	}


}
