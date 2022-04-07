package com.softtech.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtech.actionForm.EmployeeInfoBean;
import com.softtech.com.EptypeInfo;


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
		//画面初期data準備
		EmployeeInfoBean employeeInfoBean=new EmployeeInfoBean();
		employeeInfoBean.setEmployeeID("123");
		employeeInfoBean.setEmployeeName("りゅう");
		employeeInfoBean.setAddress("abc");

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

		employeeInfoBean.setEpTypeInfoList(ep);




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



		model.addAttribute("employeeInfo", employeeInfoBean);

		return "/ems/employee";
	}

}
