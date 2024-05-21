package com.softtech.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.softtech.actionForm.EmployeeEditFormBean;
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
		EmployeeEditFormBean employeeEditFormBean = employeeEditService.transferDbToUI(employee);

		// 画面に渡す
		model.addAttribute("employeeEditFormBean", employeeEditFormBean);

		return "/ems/employeeEdit";
	}
	/**
	 * 機能：更新
	 *
	 * @param employeeEditFormBean　画面データ
	 * @return result
	 * @exception ParseException
	 *
	 * @author 開発@ソフトテク
	 */

	@PostMapping("/btn-employeeEdit")
	 public String update(@Validated @ModelAttribute  EmployeeEditFormBean employeeEditFormBean, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "/ems/employeeEdit";
        }
        // ユーザー情報の更新
        employeeEditService.update(employeeEditFormBean);
        model.addAttribute("successMessage", "更新完了");
        return "/ems/employeeEdit";

}
}