package com.softtech.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.softtech.actionForm.EmployeeEditBean;
import com.softtech.com.DepartmentInfo;
import com.softtech.com.EptypeInfo;
import com.softtech.entity.Employee;
import com.softtech.service.EmployeeEditService;

/**
 * 概要：社員情報変更機能
 *
 * 作成者：○○@ソフトテク
 * 作成日：2021/12/1
 */
@Controller
public class EmployeeEditController {

	@Autowired
	EmployeeEditService employeeEditService;

	/**
	 * 機能：初期表示
	 *
	 * @param model
	 * @return result
	 * @exception JsonMappingException
	 * @author 開発@ソフトテク
	 */
	@RequestMapping("/employeeedit")
	public String employeeEdit(Model model, HttpSession session) throws ParseException {

		// DB検索
		Employee employee = employeeEditService.queryEmployeeAll((String) session.getAttribute("userEmoplyeeID"));

		//画面表示変更
		EmployeeEditBean employeeEditBean = employeeEditService.transferDbToUI(employee);

		// 画面に渡す
		model.addAttribute("employeeEditBean", employeeEditBean);

		return "/ems/employeeedit";
	}
	/**
	 * 機能：更新
	 *
	 * @param employeeEditBean　画面データ
	 * @return result
	 * @exception ParseException
	 *
	 * @author 開発@ソフトテク
	 */
	@PostMapping("/btn-employeeEdit")
	public String employeeEditSubmit(
								@Validated @ModelAttribute("employeeEditBean") EmployeeEditBean employeeEditBean,
								BindingResult errors,
								Model model, HttpSession session) throws ParseException {

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = sdFormat.parse(employeeEditBean.getJoinedDateString());
		employeeEditBean.setJoinedDate(date);

		model.addAttribute("employeeEditBean", employeeEditBean);

		if (errors.hasErrors()) {
			return "/ems/employeeedit";
		}

		// DB更新
		Map<String, String> map = new HashMap<>();
		map.put("employeeID", employeeEditBean.getEmployeeID());
		map.put("employeeName", employeeEditBean.getEmployeeName());
		map.put("sex", employeeEditBean.getSex());
		map.put("epType", employeeEditBean.getSelectedepTypeId().toString());
		map.put("department", employeeEditBean.getSelectedDepTypeId().toString());

		Date birthday_date = sdFormat.parse(employeeEditBean.getBirthday());
		map.put("birthday", sdFormat.format(birthday_date));

		map.put("age", employeeEditBean.getAge());

		Date joinedDate_date = sdFormat.parse(employeeEditBean.getJoinedDateString());
		map.put("joinedDate", sdFormat.format(joinedDate_date));

		map.put("joinedTime", employeeEditBean.getJoinedAge());
		map.put("postCode", employeeEditBean.getPostCode());
		map.put("address", employeeEditBean.getAddress());
		map.put("phoneNumber", employeeEditBean.getPhoneNumber());
		map.put("personNumber", employeeEditBean.getPersonNumber());

		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = sdf.format(cl.getTime());
		map.put("updateDate", str);

		int num = employeeEditService.updateEmployeeAll(map);

		if (num == 1) {
			model.addAttribute("updateMsg", "社員情報を更新しました。");
		} else {
			model.addAttribute("updateMsg", "社員情報の更新に失敗しました。");
		}
		//画面再表示用属性設定
		employeeEditBean.setUpdateDate(str);
		// 社員タイプ
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
		// タイプオープションを設定
		employeeEditBean.setEpTypeInfoList(ep);

		// 部門タイプ
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
		// タイプオープションを設定
		employeeEditBean.setDepTypeInfoList(deplist);

		return "/ems/employeeedit";

	}
}