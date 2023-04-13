package com.softtech.service;

import java.util.List;

import com.softtech.actionForm.EmployeeInfoBean;
/**
 * 概要：社員タイプリストのインタフェース
 *
 * 作成者：ヤダナー＠ソフトテク
 * 作成日：2023/04/13
 */
public interface EptypeService {

	/**
	 * 機能：社員タイプリスト取得
	 *
	 * @return 社員情報
	 *
	 * @author ヤダナー@ソフトテク
	 */

	List<EmployeeInfoBean> getEptype() ;

}
