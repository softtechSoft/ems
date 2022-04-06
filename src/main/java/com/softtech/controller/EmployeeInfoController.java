package com.softtech.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtech.actionForm.EmployeeInfoBean;
import com.softtech.com.epTypeInfo;

/**
 * 概要：社員情報処理
 *
 * 作成者：劉@ソフトテク
 * 作成日：2021/7/30
 */
@Controller
public class EmployeeInfoController {

	/**
	 * 機能：社員情報画面の初期化
	 *
	 * @param model
	 * @return 社員情報画面
	 * @exception なし
	 * @author 劉@ソフトテク
	 */
	@RequestMapping("/employeeinfo")
	public String employeeInfoInit(Model model) {
		ArrayList<epTypeInfo> ml = new ArrayList<epTypeInfo>();
		epTypeInfo info = new epTypeInfo();
		info.setId(1);
		info.setName("正社員");
		ml.add(info);


		epTypeInfo info2 = new epTypeInfo();
		info2.setId(2);
		info2.setName("契約社員");
		ml.add(info2);


		epTypeInfo info3 = new epTypeInfo();
		info3.setId(3);
		info3.setName("個人事業");
		ml.add(info3);


	    //EmployeeInfoBean.setepTypeInfoList(ml);


		/*switch (Integer.parseInt( I)){
		case 1:
			epTypeInfoBean.setEmployeeID(1);

			break;
		case 2 :
		employeeInfoBean.setEmployeeID(2);
		break;
		case 3 :
			employeeInfoBean.setEmployeeID(3);
			break;

		default:

			break;
		`}*/

		//画面初期data準備
		EmployeeInfoBean employeeInfoBean=new EmployeeInfoBean();
		employeeInfoBean.setEmployeeID("123");
		employeeInfoBean.setEmployeeName("りゅう");
		employeeInfoBean.setAddress("abc");

		model.addAttribute("employeeInfo", employeeInfoBean);

		return "/ems/employee";
	}

}
