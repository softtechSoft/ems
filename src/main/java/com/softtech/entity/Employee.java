package com.softtech.entity;

public class Employee
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String employeeID;
	private String employeeName;
	private String password;
	private String status;
	private String sex;
	private String birthday;
	private String age;
	private String joinedDate;
	private String joinedTime;
	private String postCode;
	private String address;
	private String phoneNumber;
	private String authority;
	private String mailAdress;
	private String remark;
	private String insertDate;
	private String updateDate;
	
	public Employee()
	{
	}
	public Employee(String employeeID, String employeeName, String password, String status, String sex, String birthday,
			String age, String joinedDate, String joinedTime, String postCode, String address, String phoneNumber,
			String authority, String mailAdress, String insertDate, String updateDate) {
		super();
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.password = password;
		this.status = status;
		this.sex = sex;
		this.birthday = birthday;
		this.age = age;
		this.joinedDate = joinedDate;
		this.joinedTime = joinedTime;
		this.postCode = postCode;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.authority = authority;
		this.mailAdress = mailAdress;
		this.insertDate = insertDate;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getMailAdress() {
		return mailAdress;
	}
	public void setMailAdress(String mailAdress) {
		this.mailAdress = mailAdress;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", employeeName=" + employeeName + ", password=" + password
				+ ", status=" + status + ", sex=" + sex + ", birthday=" + birthday + ", age=" + age + ", joinedDate="
				+ joinedDate + ", joinedTime=" + joinedTime + ", postCode=" + postCode + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", authority=" + authority + ", mailAdress=" + mailAdress
				+ ", insertDate=" + insertDate + ", updateDate=" + updateDate + "]";
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
