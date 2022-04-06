package com.softtech.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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



	@RequestMapping("/employeeedit")
	public String employeeEdit(Model model, HttpSession session) throws ParseException {

		Employee employee = employeeEditService.queryEmployeeAll((String) session.getAttribute("userEmoplyeeID"));

		EmployeeEditBean employeeEditBean = new EmployeeEditBean();
		employeeEditBean.setEmployeeName(employee.getEmployeeName());
		employeeEditBean.setEmployeeID(employee.getEmployeeID());
		employeeEditBean.setSex(employee.getSex());

		String birthday = employee.getBirthday().substring(0, 4)
				+ "-" + employee.getBirthday().substring(4, 6)
				+ "-" + employee.getBirthday().substring(6);
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
		/*employeeEditBean.setEmployeeID("2222");
		employeeEditBean.setEmployeeName("yuzana");
		employeeEditBean.setJoinedDateString("2022,4,3");
		employeeEditBean.setJoinedTime("2022,4,1");
		employeeEditBean.setPostCode("1710014");
		employeeEditBean.setAddress("Ikebukuro");
		employeeEditBean.setPhoneNumber("07048029428");
		employeeEditBean.setUpdateDate("2022,3,1");*/


		model.addAttribute("employeeEditBean", employeeEditBean);

		return "/ems/employeeedit";
	}

	@PostMapping("/btn-employeeEdit")
	public String employeeEditSubmit(HttpServletResponse response,
			@Validated @ModelAttribute("employeeEditBean") EmployeeEditBean employeeEditBean,
			BindingResult errors,
			Model model,
			HttpSession session) throws ParseException {

		/*		// 	氏名の必須チェック
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
				}*/

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
		map.put("epType", employeeEditBean.getEpType());
		map.put("department" ,employeeEditBean.getDepartment());

		Date birthday_date = sdFormat.parse(employeeEditBean.getBirthday());
		map.put("birthday", sdFormat.format(birthday_date));

		map.put("age", employeeEditBean.getAge());

		Date joinedDate_date = sdFormat.parse(employeeEditBean.getJoinedDateString());
		map.put("joinedDate", sdFormat.format(joinedDate_date));

		map.put("joinedTime", employeeEditBean.getJoinedTime());
		map.put("postCode", employeeEditBean.getPostCode());
		map.put("address", employeeEditBean.getAddress());
		map.put("phoneNumber", employeeEditBean.getPhoneNumber());


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

		return "/ems/employeeedit";

	}

}