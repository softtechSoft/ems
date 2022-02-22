package com.softtech.service;

import java.util.List;

import com.softtech.actionForm.SalaryInfoBean;
/**
 * 概要：給料リストのインタフェース
 *
 * 作成者：テー＠ソフトテク
 * 作成日：2022/02/22
 */
public interface SalaryListService {

	/**
	 * 機能：給料リスト取得
	 *
	 * @param year 対象年度
	 * @param employeeID 対象社員ID
	 *
	 * @return 給料情報リスト
	 *
	 * @author テー@ソフトテク
	 */
	List<SalaryInfoBean> getSalaryList(String year,String employeeID);

}
