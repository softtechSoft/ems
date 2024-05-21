package com.softtech.service;

import java.util.Map;

import com.softtech.actionForm.EmployeeEditFormBean;
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

	public EmployeeEditFormBean transferDbToUI(Employee employee);

	public Map<String, String> transferUIToPara(EmployeeEditFormBean employeeEditFormBean);

	public EmployeeEditFormBean resetToUI(EmployeeEditFormBean employeeEditFormBean);

	public void update(EmployeeEditFormBean employeeEditFormBean);

}
