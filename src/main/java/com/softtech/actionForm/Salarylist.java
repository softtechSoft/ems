package com.softtech.actionForm;
/**
 * 概要：給料情報クラス
 *
 * 作成者：王@ソフトテク
 * 作成日：2021/4/13
 */

public class Salarylist {

	    //社員id
		private String employeeID ;
		//社員氏名
		private String employeeName ;
		//対象年月
		private String month ;
		//支払日
		private String paymentDate ;
		//基本給
		private String base ;
		//総額
		private String sum ;
		//備考
		private String remark;
		/**
		 * @return employeeID
		 */
		public String getEmployeeID() {
			return employeeID;
		}
		/**
		 * @param employeeID セットする employeeID
		 */
		public void setEmployeeID(String employeeID) {
			this.employeeID = employeeID;
		}

		/**
		 * @return employeeName
		 */
		public String getEmployeeName() {
			return employeeName;
		}
		/**
		 * @param employeeName セットする employeeName
		 */
		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}
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
		/**
		 * @return paymentDate
		 */
		public String getPaymentDate() {
			return paymentDate;
		}
		/**
		 * @param paymentDate セットする paymentDate
		 */
		public void setPaymentDate(String paymentDate) {
			this.paymentDate = paymentDate;
		}
		/**
		 * @return base
		 */
		public String getBase() {
			return base;
		}
		/**
		 * @param base セットする base
		 */
		public void setBase(String base) {
			this.base = base;
		}
		/**
		 * @return sum
		 */
		public String getSum() {
			return sum;
		}
		/**
		 * @param sum セットする sum
		 */
		public void setSum(String sum) {
			this.sum = sum;
		}
		/**
		 * @return remark
		 */
		public String getRemark() {
			return remark;
		}
		/**
		 * @param remark セットする remark
		 */
		public void setRemark(String remark) {
			this.remark = remark;
		}

}
