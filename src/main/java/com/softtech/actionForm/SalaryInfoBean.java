package com.softtech.actionForm;

/**
 * 概要：給料詳細画面用Bean
 *
 * 作成者：@ソフトテク
 * 作成日：2021/7/20
 */
public class SalaryInfoBean {

	//ユーザID
	private String employeeID ;
	//ユーザ名
	private String employeeName ;
	// 住所
	private String address ;
	// 対象月
	private String month ;
	// 支払日
	private String paymentDate ;
	// 基本給
	private String base ;
	// 残業時間
	private String overTime;
	// 不足時間
	private String shortage ;
	// 残業加算
	private String overTimePlus ;
	// 稼働不足減
	private String shortageReduce ;
	// 交通費
	private String transportExpense ;
	// 手当加算
	private String allowancePlus ;
	// 手当減算
	private String allowanceReduce ;
	// 手当理由
	private String allowanceReason ;
	// 厚生年金控除個人
	private String welfarePensionSelf ;
	// 厚生年金控除会社
	private String welfarePensionComp ;
	// 厚生健康控除会社
	private String welfareHealthComp ;
	// 厚生健康控除個人
	private String welfareHealthSelf ;
	// 厚生控除子育(会社)
	private String welfareBaby ;
	// 雇用保険個人負担
	private String eplyInsSelf ;
	// 雇用保険会社負担
	private String eplyInsComp ;
	// 雇用保拠出金（会社)
	private String eplyInsWithdraw ;
	// 労災保険（会社負担のみ）
	private String wkAcccpsIns ;
	// 源泉控除
	private String withholdingTax ;
	// 住民税控除
	private String municipalTax ;
	// 社宅家賃控除
	private String rental ;
	// 社宅共益費控除
	private String rentalMgmtFee;
	// 総額
	private String sum ;
	// 総費用
	private String totalFee ;
	// 備考
	private String remark = " ";
	// 削除フラグ
	private String deleteFlg ;
	// 作成日
	private String insertDate ;
	// 更新日
	private String updateDate ;

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
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getOverTimePlus() {
		return overTimePlus;
	}
	public void setOverTimePlus(String overTimePlus) {
		this.overTimePlus = overTimePlus;
	}
	public String getShortageReduce() {
		return shortageReduce;
	}
	public void setShortageReduce(String shortageReduce) {
		this.shortageReduce = shortageReduce;
	}
	public String getTransportExpense() {
		return transportExpense;
	}
	public void setTransportExpense(String transportExpense) {
		this.transportExpense = transportExpense;
	}
	public String getAllowancePlus() {
		return allowancePlus;
	}
	public void setAllowancePlus(String allowancePlus) {
		this.allowancePlus = allowancePlus;
	}
	public String getAllowanceReduce() {
		return allowanceReduce;
	}
	public void setAllowanceReduce(String allowanceReduce) {
		this.allowanceReduce = allowanceReduce;
	}
	public String getAllowanceReason() {
		return allowanceReason;
	}
	public void setAllowanceReason(String allowanceReason) {
		this.allowanceReason = allowanceReason;
	}
	public String getWelfarePensionSelf() {
		return welfarePensionSelf;
	}
	public void setWelfarePensionSelf(String welfarePensionSelf) {
		this.welfarePensionSelf = welfarePensionSelf;
	}
	public String getWelfareHealthSelf() {
		return welfareHealthSelf;
	}
	public void setWelfareHealthSelf(String welfareHealthSelf) {
		this.welfareHealthSelf = welfareHealthSelf;
	}
	public String getEplyInsSelf() {
		return eplyInsSelf;
	}
	public void setEplyInsSelf(String eplyInsSelf) {
		this.eplyInsSelf = eplyInsSelf;
	}
	public String getWithholdingTax() {
		return withholdingTax;
	}
	public void setWithholdingTax(String withholdingTax) {
		this.withholdingTax = withholdingTax;
	}
	public String getMunicipalTax() {
		return municipalTax;
	}
	public void setMunicipalTax(String municipalTax) {
		this.municipalTax = municipalTax;
	}
	public String getRental() {
		return rental;
	}
	public void setRental(String rental) {
		this.rental = rental;
	}
	public String getRentalMgmtFee() {
		return rentalMgmtFee;
	}
	public void setRentalMgmtFee(String rentalMgmtFee) {
		this.rentalMgmtFee = rentalMgmtFee;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
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
	public String getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
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


}