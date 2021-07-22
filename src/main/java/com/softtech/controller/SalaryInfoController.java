package com.softtech.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.softtech.actionForm.SalaryInfoBean;
import com.softtech.entity.SalaryInfo;
import com.softtech.service.SalaryInfoServiceImpl;

/**
 * 概要：給料明細機能
 *
 * 作成者：○○@ソフトテク
 * 作成日：2021/1/13
 */
@Controller
public class SalaryInfoController {
	@Autowired
	SalaryInfoServiceImpl salaryInfoService;

	@RequestMapping("/salarydetail")
	public String salarydetails(Model model) {

		SalaryInfoBean salaryInfoBean = new SalaryInfoBean();

		model.addAttribute("salarydata", salaryInfoBean);
		return "/ems/salarydetail";
	}

	/**
	 * 機能：給料明細
	 *
	 * @param yearMonthとsession
	 * @return result
	 * @exception JsonMappingException
	 * @author ○○@ソフトテク
	 */
	@PostMapping("/request-salarydetail")
	public String salarylistSubmit(HttpServletResponse response,
			@ModelAttribute("salarydata") SalaryInfoBean salaryInfo,
			Model model,
			HttpSession session) {

		//ObjectMapper jsonMapper = new ObjectMapper();
		Map<String, String> sqlParam = new HashMap<>();

		sqlParam.put("yearMonth", salaryInfo.getMonth());
		sqlParam.put("employeeID", (String) session.getAttribute("userEmoplyeeID"));
		SalaryInfo salary = salaryInfoService.querySalaryInfo(sqlParam);
		if (salary == null) {
			salary = new SalaryInfo();
		}

		//画面表示するため設定する
		SalaryInfoBean salaryInfoBean = salaryInfoService.tranferData(salary);

	    model.addAttribute("salarydata", salaryInfoBean);

		return "/ems/salarydetail";
	}

}