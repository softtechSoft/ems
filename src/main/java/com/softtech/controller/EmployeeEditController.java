package com.softtech.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtech.actionForm.EmployeeEditBean;
import com.softtech.com.EptypeInfo;
import com.softtech.com.departmentInfo;
import com.softtech.entity.Employee;
import com.softtech.service.EmployeeEditService;

/**
 * 概要：社員情報変更機能
 *
 * 作成者：○○@ソフトテク 作成日：2021/12/1
 */
@Controller
public class EmployeeEditController {

	@Autowired
	EmployeeEditService employeeEditService;

	@RequestMapping("/employeeedit")
	public String employeeEdit(Model model, HttpSession session) throws ParseException {

		Employee employee = employeeEditService.queryEmployeeAll((String) session.getAttribute("userEmoplyeeID"));

		EmployeeEditBean employeeEditBean = new EmployeeEditBean();
		employeeEditBean.setEmployeeName(employee.getEmployeeName());
		employeeEditBean.setEmployeeID(employee.getEmployeeID());
		employeeEditBean.setSex(employee.getSex());

		String birthday = employee.getBirthday().substring(0, 4) + "-" + employee.getBirthday().substring(4, 6) + "-"
				+ employee.getBirthday().substring(6);
		employeeEditBean.setBirthday(birthday);
		employeeEditBean.setAge(employee.getAge());
		employeeEditBean.setEpType(employee.getEpType());

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdFormat.parse(employee.getJoinedDate());
		employeeEditBean.setJoinedDateString(sdFormat2.format(date));
		employeeEditBean.setPersonNumber(employee.getPersonNumber());
		employeeEditBean.setDepartment(employee.getDepartment());
		employeeEditBean.setJoinedTime(employee.getJoinedTime());
		employeeEditBean.setPostCode(employee.getPostCode());
		employeeEditBean.setAddress(employee.getAddress());
		employeeEditBean.setPhoneNumber(employee.getPhoneNumber());
		employeeEditBean.setUpdateDate(employee.getUpdateDate());
		employeeEditBean.setSelectedepTypeId(Integer.valueOf(employee.getEpType()));
		employeeEditBean.setSelectedDepTypeId(Integer.valueOf(employee.getDepartment()));
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
		ArrayList<departmentInfo> deplist = new ArrayList<departmentInfo>();
		departmentInfo deinfo = new departmentInfo();
		deinfo.setId(1);
		deinfo.setName("開発一部");
		deplist.add(deinfo);
		departmentInfo deinfo2 = new departmentInfo();
		deinfo2.setId(2);
		deinfo2.setName("開発二部");
		deplist.add(deinfo2);
		departmentInfo deinfo3 = new departmentInfo();
		deinfo3.setId(3);
		deinfo3.setName("管理部");
		deplist.add(deinfo3);
		// タイプオープションを設定
		employeeEditBean.setDepTypeInfoList(deplist);

		// 画面に渡す
		model.addAttribute("employeeEditBean", employeeEditBean);

		return "/ems/employeeedit";
	}

	@PostMapping("/btn-employeeEdit")
	public String employeeEditSubmit(HttpServletResponse response,
			@Validated @ModelAttribute("employeeEditBean") EmployeeEditBean employeeEditBean, BindingResult errors,
			Model model, HttpSession session) throws ParseException {

		// 氏名の必須チェック
		if (employeeEditBean.getEmployeeName() == null || employeeEditBean.getEmployeeName().isEmpty()) {
			model.addAttribute("employeeNameErr", "氏名を入力してください。");
		} else if (employeeEditBean.getEmployeeName().length() > 12) {
			model.addAttribute("employeeNameErr", "氏名は12文字以内で入力してください。");
		}

		// 入社年月日の必須チェック
		if (employeeEditBean.getJoinedDateString() == null || employeeEditBean.getJoinedDateString().isEmpty()) {
			model.addAttribute("joinedDateStringErr", "入社年月日を入力してください。");
		} else {
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
			Date date = sdFormat.parse(employeeEditBean.getJoinedDateString());
			employeeEditBean.setJoinedDate(date);
		}

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
		//画面再表示
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
		ArrayList<departmentInfo> deplist = new ArrayList<departmentInfo>();
		departmentInfo deinfo = new departmentInfo();
		deinfo.setId(1);
		deinfo.setName("開発一部");
		deplist.add(deinfo);
		departmentInfo deinfo2 = new departmentInfo();
		deinfo2.setId(2);
		deinfo2.setName("開発二部");
		deplist.add(deinfo2);
		departmentInfo deinfo3 = new departmentInfo();
		deinfo3.setId(3);
		deinfo3.setName("管理部");
		deplist.add(deinfo3);
		// タイプオープションを設定
		employeeEditBean.setDepTypeInfoList(deplist);

		return "/ems/employeeedit";

	}
}