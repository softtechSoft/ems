package com.softtech.controller;

import java.text.ParseException;
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
import com.softtech.entity.Employee;
import com.softtech.service.EmployeeEditService;

/**
 * 概要：社員情報変更機能
 *
 * 作成者：開発@ソフトテク
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

		return "/ems/employeeEdit";
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
		// Validationチェックエラー時、エラー情報表示
		if (errors.hasErrors()) {
			model.addAttribute("employeeEditBean", employeeEditBean);
			return "/ems/employeeEdit";
		}
		// DB更新
		Map<String, String> map = employeeEditService.transferUIToPara(employeeEditBean);
		int num = employeeEditService.updateEmployeeAll(map);

		if (num == 1) {
			model.addAttribute("updateMsg", "社員情報を更新しました。");
		} else {
			model.addAttribute("updateMsg", "社員情報の更新に失敗しました。");
		}

		// 画面再表示設定
		employeeEditBean = employeeEditService.resetToUI(employeeEditBean);
		model.addAttribute("employeeEditBean", employeeEditBean);

		return "/ems/employeeEdit";

	}

}