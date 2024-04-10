package com.softtech.actionForm;

public class YukyuDetail {

	private String employeeID;
    private String nendo;
    private String totalDay;
    private String usedDay;
    private String insertDate;
    private String updateDate;
	public YukyuDetail() {

	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getNendo() {
		return nendo;
	}
	public void setNendo(String nendo) {
		this.nendo = nendo;
	}
	public String getTotalDay() {
		return totalDay;
	}
	public void setTotalDay(String totalDay) {
		this.totalDay = totalDay;
	}
	public String getUsedDay() {
		return usedDay;
	}
	public void setUsedDay(String usedDay) {
		this.usedDay = usedDay;
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
		return "YukyuDetail [employeeID=" + employeeID + ", nendo=" + nendo + ", totalDay=" + totalDay + ", usedDay="
				+ usedDay + ", insertDate=" + insertDate + ", updateDate=" + updateDate + "]";
	}

}
