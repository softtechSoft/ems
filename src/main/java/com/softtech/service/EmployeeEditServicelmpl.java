package com.softtech.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.softtech.actionForm.EmployeeEditBean;
import com.softtech.com.DepartmentInfo;
import com.softtech.com.EptypeInfo;
import com.softtech.entity.Employee;
import com.softtech.mapper.EmployeeMapper;
/**
 * 概要：社員情報変更サービス
 *
 * 作成者：開発@ソフトテク
 * 作成日：2022/４/23
 */
@Service
public class EmployeeEditServicelmpl implements EmployeeEditService {
	@Autowired
	EmployeeMapper employeeMapper;

	@Override
	public Employee queryEmployeeAll(String employeeID) {
		return employeeMapper.queryEmployeeAll(employeeID);
	}

	@Override
	public int updateEmployeeAll(Map<String, String> map) {
		return employeeMapper.updateEmployeeAll(map);
	}
	/**
	 * 機能：DBデータを画面要データへ変更
	 *
	 * @param yearMonthとsession
	 * @return result
	 * @exception JsonMappingException
	 * @author ○○@ソフトテク
	 */
	public EmployeeEditBean transferDbToUI(Employee employee) {
		EmployeeEditBean employeeEditBean = new EmployeeEditBean();
		//社員名前
		employeeEditBean.setEmployeeName(employee.getEmployeeName());
		//社員ID
		employeeEditBean.setEmployeeID(employee.getEmployeeID());
		//社員性別
		employeeEditBean.setSex(employee.getSex());
		//生年月日
		String birthday = employee.getBirthday().substring(0, 4) + "-" + employee.getBirthday().substring(4, 6) + "-"
				+ employee.getBirthday().substring(6);
		employeeEditBean.setBirthday(birthday);
		//年齢
		employeeEditBean.setAge(employee.getAge());
		//社員タイプ
		employeeEditBean.setEpType(employee.getEpType());
		//入社日
		String joinDate = employee.getJoinedDate().substring(0, 4) + "-" + employee.getJoinedDate().substring(4, 6) + "-"
				+ employee.getJoinedDate().substring(6);
		employeeEditBean.setJoinedDateString(joinDate);

		employeeEditBean.setJoinedTime(employee.getJoinedTime());
		//個人番号
		employeeEditBean.setPersonNumber(employee.getPersonNumber());

		employeeEditBean.setDepartment(employee.getDepartment());

		employeeEditBean.setPostCode(employee.getPostCode());
		employeeEditBean.setAddress(employee.getAddress());
		employeeEditBean.setPhoneNumber(employee.getPhoneNumber());
		employeeEditBean.setUpdateDate(employee.getUpdateDate());

		// 社員タイプ
		ArrayList<EptypeInfo> ep = mkEmployeeType();
		// 社員タイプオープションを設定
		employeeEditBean.setEpTypeInfoList(ep);
		// 社員タイプ設定
		employeeEditBean.setSelectedepTypeId(Integer.parseInt(employee.getEpType()));

		// 部門タイプ
		ArrayList<DepartmentInfo> deplist = mkDepartment();
		// 部門タイプオープションを設定
		employeeEditBean.setDepTypeInfoList(deplist);
		// 部門タイプ設定
		employeeEditBean.setSelectedepTypeId(Integer.parseInt(employee.getDepartment()));

		return employeeEditBean;
	}

	/**
	 * 機能：社員タイプリスト作成
	 *
	 * @return 社員タイプリスト
	 * @author 開発@ソフトテク
	 *
	 */
	private ArrayList<EptypeInfo> mkEmployeeType(){
		ArrayList<EptypeInfo> ep = new ArrayList<EptypeInfo>();
		EptypeInfo info = new EptypeInfo();
		info.setId(1);
		info.setName("正社員");
		ep.add(info);
		EptypeInfo info2 = new EptypeInfo();
		info2.setId(2);
		info2.setName("契約社員");
		ep.add(info2);
		EptypeInfo info3 = new EptypeInfo();
		info3.setId(3);
		info3.setName("個人事業");
		ep.add(info3);

		return ep;
	}

	/**
	 * 機能：部門リスト作成
	 *
	 * @return 社員タイプリスト
	 * @author 開発@ソフトテク
	 */
	private ArrayList<DepartmentInfo> mkDepartment(){

		ArrayList<DepartmentInfo> deplist = new ArrayList<DepartmentInfo>();

		DepartmentInfo deinfo = new DepartmentInfo();
		deinfo.setId(1);
		deinfo.setName("開発一部");
		deplist.add(deinfo);
		DepartmentInfo deinfo2 = new DepartmentInfo();
		deinfo2.setId(2);
		deinfo2.setName("開発二部");
		deplist.add(deinfo2);
		DepartmentInfo deinfo3 = new DepartmentInfo();
		deinfo3.setId(3);
		deinfo3.setName("管理部");
		deplist.add(deinfo3);

		return deplist;
	}
}
