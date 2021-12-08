package com.softtech.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.softtech.service.EmployeeEditServicelmpl;

/**
 * 概要：社員情報変更機能
 *
 * 作成者：○○@ソフトテク
 * 作成日：2021/12/1
 */
@Controller
public class EmployeeEditController {

	@Autowired
	EmployeeEditServicelmpl employeeEditServicelmpl;

	@RequestMapping("/employeeedit")
	public String employeeEdit(Model model, HttpSession session) throws ParseException {

		Employee employee = employeeEditServicelmpl.queryEmployeeAll((String) session.getAttribute("userEmoplyeeID"));

		EmployeeEditBean employeeEditBean = new EmployeeEditBean();

		employeeEditBean.setEmployeeName(employee.getEmployeeName());
		employeeEditBean.setEmployeeID(employee.getEmployeeID());
		employeeEditBean.setSex(employee.getSex());

		String birthday = employee.getBirthday().substring(0, 4)
				+ "-" + employee.getBirthday().substring(4, 6)
				+ "-" + employee.getBirthday().substring(6);

		employeeEditBean.setBirthday(birthday);
		employeeEditBean.setAge(employee.getAge());
		/*employeeEditBean.setJoinedDate(employee.getJoinedDate());*/

		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = sdFormat.parse(employee.getJoinedDate());
		employeeEditBean.setJoinedDate(date);

		employeeEditBean.setJoinedTime(employee.getJoinedTime());
		employeeEditBean.setPostCode(employee.getPostCode());
		employeeEditBean.setAddress(employee.getAddress());
		employeeEditBean.setPhoneNumber(employee.getPhoneNumber());
		employeeEditBean.setUpdateDate(employee.getUpdateDate());

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

		model.addAttribute("employeeEditBean", employeeEditBean);

		if (errors.hasErrors()) {
			return "/ems/employeeedit";
		}




		return "/ems/employeeedit";

	}

}