package com.softtech.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.EmployeeEditBean;
import com.softtech.com.DepartmentInfo;
import com.softtech.com.EptypeInfo;
import com.softtech.entity.Department;
import com.softtech.entity.Employee;
import com.softtech.entity.EmployeepType;
import com.softtech.mapper.DepartmentMapper;
import com.softtech.mapper.EmployeeMapper;
import com.softtech.mapper.EptypeMapper;
import com.softtech.util.DateUtil;
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

	@Autowired
	EptypeMapper eptypeMapper;

    @Autowired
    DepartmentMapper departmentMapper;

	@Override
	public Employee queryEmployeeAll(String employeeID) {
		return employeeMapper.queryEmployeeAll(employeeID);
	}

	/*
	 * 機能：DB更新
	 *
	 * @param employee　DBデータ
	 * @return 更新行数　1:成功
	 *
	 * @author 開発@ソフトテク
	 */
	@Override
	public int updateEmployeeAll(Map<String, String> map) {
		return employeeMapper.updateEmployeeAll(map);
	}
	/*
	 * 機能：DBデータを画面要データへ変更
	 *
	 * @param employee　DBデータ
	 * @return 画面Form
	 *
	 * @author 開発@ソフトテク
	 */
	@Override
	public EmployeeEditBean transferDbToUI(Employee employee) {
		EmployeeEditBean employeeEditBean = new EmployeeEditBean();
		//社員名前
		employeeEditBean.setEmployeeName(employee.getEmployeeName());
		//社員ID
		employeeEditBean.setEmployeeID(employee.getEmployeeID());
		//社員性別
		employeeEditBean.setSex(employee.getSex());
		//生年月日
		if(employee.getBirthday()!=null && !"".equals(employee.getBirthday()) ){
			String birthday = employee.getBirthday().substring(0, 4) + "-" + employee.getBirthday().substring(4, 6) + "-"
					+ employee.getBirthday().substring(6);

			employeeEditBean.setBirthday(birthday);
		}
		//年齢
		employeeEditBean.setAge(employee.getAge());
		//社員タイプ
		employeeEditBean.setEpType(employee.getEpType());
		//入社日
		if(employee.getJoinedDate()!=null  && !"".equals(employee.getJoinedDate()) ){
		String joinDate = employee.getJoinedDate().substring(0, 4) + "-" + employee.getJoinedDate().substring(4, 6) + "-"
				+ employee.getJoinedDate().substring(6);
		employeeEditBean.setJoinedDateString(joinDate);
		}

		employeeEditBean.setJoinedTime(employee.getJoinedTime());
		//個人番号
		employeeEditBean.setPersonNumber(employee.getPersonNumber());

		employeeEditBean.setDepartment(employee.getDepartment());

		employeeEditBean.setPostCode(employee.getPostCode());
		employeeEditBean.setAddress(employee.getAddress());
		employeeEditBean.setPhoneNumber(employee.getPhoneNumber());
		if (employee.getUpdateDate()!=null && !"".equals(employee.getUpdateDate()) ){
			employeeEditBean.setUpdateDate(DateUtil.chgYMDToDate(employee.getUpdateDate()));
		}

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
		if (employee.getDepartment()!=null && !"".equals(employee.getDepartment()) ){
			employeeEditBean.setSelectedDepTypeId(Integer.parseInt(employee.getDepartment()));
		}

		return employeeEditBean;
	}
	/*
	 * 機能：画面要データをMapに設定
	 *
	 * @param employeeEditBean　画面データ
	 * @return Map
	 *
	 * @author 開発@ソフトテク
	 */
	@Override
	public Map<String, String> transferUIToPara(EmployeeEditBean employeeEditBean)
	{
		Map<String, String> map = new HashMap<>();

		map.put("employeeID", employeeEditBean.getEmployeeID());
		map.put("employeeName", employeeEditBean.getEmployeeName());
		map.put("sex", employeeEditBean.getSex());
		map.put("epType", employeeEditBean.getSelectedepTypeId().toString());
		map.put("department", employeeEditBean.getSelectedDepTypeId().toString());

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		Date birthday_date;
		try {
			birthday_date = sdFormat.parse(employeeEditBean.getBirthday());
			map.put("birthday", sdFormat.format(birthday_date));
		} catch (ParseException e) {
			map.put("birthday", "00000000");
		}

		map.put("age", employeeEditBean.getAge());

		Date joinedDate_date;
		try {
			joinedDate_date = sdFormat.parse(employeeEditBean.getJoinedDateString());
			map.put("joinedDate", sdFormat.format(joinedDate_date));
		} catch (ParseException e) {
			map.put("joinedDate", "00000000");
		}

		map.put("joinedTime", employeeEditBean.getJoinedTime());
		map.put("postCode", employeeEditBean.getPostCode());
		map.put("address", employeeEditBean.getAddress());
		map.put("phoneNumber", employeeEditBean.getPhoneNumber());
		map.put("personNumber", employeeEditBean.getPersonNumber());

		Calendar cl = Calendar.getInstance();
		String str = sdFormat.format(cl.getTime());
		map.put("updateDate", str);

		return map;
	}
	/*
	 * 機能：画面再表示の設定
	 *
	 * @param employeeEditBean　画面データ
	 * @return 画面データ
	 *
	 * @author 開発@ソフトテク
	 */
	@Override
	public EmployeeEditBean resetToUI(EmployeeEditBean employeeEditBean) {

		//最終更新日
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		String str = sdFormat.format(cl.getTime());
		employeeEditBean.setUpdateDate(str);

		// 社員タイプ候補
		ArrayList<EptypeInfo> ep = mkEmployeeType();
		// タイプオープションを設定
		employeeEditBean.setEpTypeInfoList(ep);

		// 部門タイプ候補
		ArrayList<DepartmentInfo> deplist = mkDepartment();
		// タイプオープションを設定
		employeeEditBean.setDepTypeInfoList(deplist);

		return employeeEditBean;
	}
	/*
	 * 機能：社員タイプ取得
	 *
	 * @param なし
	 * @return 社員タイプリスト
	 *
	 * @author 開発@ソフトテク
	 */
    private ArrayList<EptypeInfo> mkEmployeeType(){
    	ArrayList<EmployeepType> eptypeInfoList = eptypeMapper.getEptypeInfoList();
    	return changeData(eptypeInfoList) ;

    }

    /*
	 * 機能：社員タイプ変換。エンティティ⇒アクションフォマード
	 *
	 * @param 社員タイプエンティティ
	 * @return 社員タイプリスト
	 * @author ヤダナー@ソフトテク
	 */
    private ArrayList<EptypeInfo> changeData(ArrayList<EmployeepType> employeepTypes)
    {
    	ArrayList<EptypeInfo> eptypeInfos = new ArrayList<EptypeInfo>();
    	if(employeepTypes == null) return eptypeInfos;

    	for(EmployeepType eType:employeepTypes ) {
    		EptypeInfo eptypeInfo = new EptypeInfo();
    		eptypeInfo.setId(eType.getId());
    		eptypeInfo.setName(eType.getName());
    		//リストに追加
    		eptypeInfos.add(eptypeInfo);
    	}
    	return eptypeInfos;
    }
	/*
	 * 機能：部門リスト作成
	 *
	 * @param なし
	 * @return 部門リスト
	 * @author 開発@ソフトテク
	 */
	private ArrayList<DepartmentInfo> mkDepartment(){

		ArrayList<Department> departmentInfoList = departmentMapper.getDepTypeInfoList();
	    return chgData(departmentInfoList) ;
	}
	/*
	 * 機能：機能：部門リスト変換。エンティティ⇒アクションフォマード
	 *
	 * @param 部門リストエンティティ
	 * @return社員タイプのリスト
	 * @author ヤダナー@ソフトテク
	 */
   private ArrayList<DepartmentInfo> chgData(ArrayList<Department> departments)
   {
   	 ArrayList<DepartmentInfo> departmentInfos = new ArrayList<DepartmentInfo>();
   	 if(departments == null) return departmentInfos;

   	 for(Department dm:departments ) {
   		DepartmentInfo departmentInfo = new DepartmentInfo();
   		departmentInfo.setId(dm.getId());
   		departmentInfo.setName(dm.getName());

   		//リストに追加
   		departmentInfos.add(departmentInfo);
   	 }

   	 return departmentInfos;
   }
}
