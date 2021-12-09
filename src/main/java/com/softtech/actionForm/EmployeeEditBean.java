package com.softtech.actionForm;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 概要：給料詳細画面用Bean
 *
 * 作成者：劉@ソフトテク
 * 作成日：2021/7/20
 */
public class EmployeeEditBean {

	//ユーザID
	private String employeeID ;

	//ユーザ名
	@NotEmpty(message="氏名を入力してください。")
	@Size(max=12,message="氏名は12文字以内で入力してください。")
	private String employeeName ;

	// 住所
	@NotEmpty(message="住所を入力してください。")
	@Pattern(regexp = "^[^ -~｡-ﾟ]*$",message="住所を全角で入力してください。")
	private String address ;

	// 性別
	private String sex;
	//生年月日
	@NotEmpty(message="生年月日を入力してください。")

	private String birthday;
	//年齢
    private String age;

    //入社年月日
    private Date joinedDate;

    //入社年月日
    @NotEmpty(message="入社年月日を入力してください。")
    private String joinedDateString;

    //社齢
	public String joinedTime;

	//郵便番号
	@NotEmpty(message="郵便番号を入力してください。")
	@Pattern(regexp = "^([0-9]{3}?[0-9]{4})?$",message="郵便番号形式で入力してください。")
	public String postCode;

	//電話番号
	@Pattern(regexp = "^[0-9]*$",message="電話番号を半角で入力してください。")
	public String phoneNumber;

	//入更新日
	public String updateDate;







	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}


	public String getJoinedTime() {
		return joinedTime;
	}
	public void setJoinedTime(String joinedTime) {
		this.joinedTime = joinedTime;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	public String getJoinedDateString() {
		return joinedDateString;
	}
	public void setJoinedDateString(String joinedDateString) {
		this.joinedDateString = joinedDateString;
	}





















}