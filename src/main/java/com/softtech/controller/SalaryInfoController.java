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
		//salaryInfoBean.setEmployeeID("E001");
		//salaryInfoBean.setEmployeeName("liu Qi");

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
//	@RequestMapping("/request-salarydetail")
//	public String salaryinfo(@RequestParam("yearMonth") String yearMonth,
//			@ModelAttribute("salarydata") SalaryInfo salaryInfo,
//			Model model,
//			HttpSession session) throws JsonProcessingException {

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

		//TODO　DB　entityから画面Beanへ変換


//		List<SalaryInfoComment> column = salaryInfoService.querySalaryInfoComment();
//		Map<String, Object> map = new HashMap<>();
//		map.put("column", column);
//		map.put("data", salary);
//		String result = jsonMapper.writeValueAsString(map);

		SalaryInfoBean salaryInfoBean = new SalaryInfoBean();
		salaryInfoBean.setEmployeeID("aaa");
		salaryInfoBean.setEmployeeName("テスト");
		model.addAttribute("salarydata", salaryInfoBean);

		//return result;
		return "/ems/salarydetail";
	}
	@PostMapping("/abc")
	public String abc(@ModelAttribute("salarydate") SalaryInfo salaryInfo,Model model){

		salaryInfo.setEmployeeID("E001");
		salaryInfo.setEmployeeName("liu Qi");
		model.addAttribute("salarydate", salaryInfo);

		return "salarydetail";
	}
}