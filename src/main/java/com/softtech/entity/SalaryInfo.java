package com.softtech.entity;

public class SalaryInfo {
	private String employeeName = "0";
	private String employeeID = "0";
	private String address = "0";
	private String month = "0";
	private String paymentDate = "0";
	private String base = "0";
	private String overTimePlus = "0";
	private String shortageReduce = "0";
	private String transportExpense = "0";
	private String allowancePlus = "0";
	private String allowanceReduce = "0";
	private String allowanceReason = "0";
	private String welfarePensionSelf = "0";
	private String welfareHealthSelf = "0";
	private String eplyInsSelf = "0";
	private String withholdingTax = "0";
	private String municipalTax = "0";
	private String rental = "0";
	private String rentalMgmtFee = "0";
	private String sum = "0";
	private String remark = " ";
	private String totalFee = "0";
	private String wkAcccpsIns = "0";
	private String eplyInsWithdraw = "0";
	private String eplyInsComp = "0";
	private String welfareBaby = "0";
	private String welfarePensionComp = "0";
	private String welfareHealthComp = "0";
	private String overTime = "0";
	private String shortage = "0";

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = String.format("%s/%s", month.substring(0, 4), month.substring(4, 6));
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = String.format("%s/%s/%s", paymentDate.substring(0, 4), paymentDate.substring(4, 6),
				paymentDate.substring(6, 8));
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = String.format("%,d円", Integer.parseInt(base));
	}

	public String getOverTimePlus() {
		return overTimePlus;
	}

	public void setOverTimePlus(String overTimePlus) {
		this.overTimePlus = String.format("%,d円", Integer.parseInt(overTimePlus));
	}

	public String getShortageReduce() {
		return shortageReduce;
	}

	public void setShortageReduce(String shortageReduce) {
		this.shortageReduce = String.format("%,d円", Integer.parseInt(shortageReduce));
	}

	public String getTransportExpense() {
		return transportExpense;
	}

	public void setTransportExpense(String transportExpense) {
		this.transportExpense = String.format("%,d円", Integer.parseInt(transportExpense));
	}

	public String getAllowancePlus() {
		return allowancePlus;
	}

	public void setAllowancePlus(String allowancePlus) {
		this.allowancePlus = String.format("%,d円", Integer.parseInt(allowancePlus));
	}

	public String getAllowanceReduce() {
		return allowanceReduce;
	}

	public void setAllowanceReduce(String allowanceReduce) {
		this.allowanceReduce = String.format("%,d円", Integer.parseInt(allowanceReduce));
	}

	public String getAllowanceReason() {
		return allowanceReason;
	}

	public void setAllowanceReason(String allowanceReason) {
		this.allowanceReason = allowanceReason;
	}
	public String getWelfarePensionSelf() {
		return  welfarePensionSelf;
	}


	public void setWelfarePensionSelf(String welfarePensionSelf) {
		this.welfarePensionSelf = String.format("%,d円", Integer.parseInt(welfarePensionSelf));
	}

	public String getWelfareHealthSelf() {
		return welfareHealthSelf;
	}

	public void setWelfareHealthSelf(String welfareHealthSelf) {
		this.welfareHealthSelf = String.format("%,d円", Integer.parseInt(welfareHealthSelf));


	}

	public String getEplyInsSelf() {
		return eplyInsSelf;
	}

	public void setEplyInsSelf(String eplyInsSelf) {
		this.eplyInsSelf = String.format("%,d円", Integer.parseInt(eplyInsSelf));
	}

	public String getWithholdingTax() {
		return withholdingTax;
	}

	public void setWithholdingTax(String withholdingTax) {
		this.withholdingTax = String.format("%,d円", Integer.parseInt(withholdingTax));
	}

	public String getMunicipalTax() {
		return municipalTax;
	}

	public void setMunicipalTax(String municipalTax) {
		this.municipalTax = String.format("%,d円", Integer.parseInt(municipalTax));
	}

	public String getRental() {
		return rental;
	}

	public void setRental(String rental) {
		this.rental = String.format("%,d円", Integer.parseInt(rental));
	}

	public String getRentalMgmtFee() {
		return rentalMgmtFee;
	}

	public void setRentalMgmtFee(String rentalMgmtFee) {
		this.rentalMgmtFee = String.format("%,d円", Integer.parseInt(rentalMgmtFee));
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = String.format("%,d円", Integer.parseInt(sum));
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getWkAcccpsIns() {
		return wkAcccpsIns;
	}

	public void setWkAcccpsIns(String wkAcccpsIns) {
		this.wkAcccpsIns = wkAcccpsIns;
	}

	public String getEplyInsWithdraw() {
		return eplyInsWithdraw;
	}

	public void setEplyInsWithdraw(String eplyInsWithdraw) {
		this.eplyInsWithdraw = eplyInsWithdraw;
	}

	public String getEplyInsComp() {
		return eplyInsComp;
	}

	public void setEplyInsComp(String eplyInsComp) {
		this.eplyInsComp = eplyInsComp;
	}

	public String getWelfareBaby() {
		return welfareBaby;
	}

	public void setWelfareBaby(String welfareBaby) {
		this.welfareBaby = welfareBaby;
	}

	public String getWelfarePensionComp() {
		return welfarePensionComp;
	}

	public void setWelfarePensionComp(String welfarePensionComp) {
		this.welfarePensionComp = welfarePensionComp;
	}
	public String getWelfareHealthComp() {
		return welfareHealthComp;
	}

	public void setWelfareHealthComp(String welfareHealthComp) {
		this.welfareHealthComp = welfareHealthComp;
	}
	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public String getShortage() {
		return shortage;
	}

	public void setShortage(String shortage) {
		this.shortage = shortage;
	}
}