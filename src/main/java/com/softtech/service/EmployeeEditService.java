package com.softtech.service;

import java.util.List;
import java.util.Map;

import com.softtech.actionForm.EmployeeEditBean;
import com.softtech.com.EptypeInfo;
import com.softtech.entity.Employee;
/**
 * 概要：社員情報変更サービス
 *
 * 作成者：開発@ソフトテク
 * 作成日：2022/４/23
 */
public interface EmployeeEditService {

	Employee queryEmployeeAll(String employeeID);

	int updateEmployeeAll(Map<String, String> map);

	public EmployeeEditBean transferDbToUI(Employee employee);

	public Map<String, String> transferUIToPara(EmployeeEditBean employeeEditBean);

	public EmployeeEditBean resetToUI(EmployeeEditBean employeeEditBean);

	/**
	 * 機能：DBから取得したデータを取得する。
	 *
	 * @return 社員タイプリスト
	 *
	 * @author ヤダナー@ソフトテク
	 */
	List<EptypeInfo> getEptypeInfoList();

}
