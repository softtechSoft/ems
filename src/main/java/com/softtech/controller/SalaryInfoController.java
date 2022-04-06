package com.softtech.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.softtech.actionForm.SalaryInfoBean;
import com.softtech.com.MonthInfo;
import com.softtech.entity.SalaryInfo;
import com.softtech.service.SalaryInfoServiceImpl;
import com.softtech.util.DateUtil;

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
		//戻る値をセッションに設定する 2022/02/27
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
									HttpSession session)
	{
		Map<String, String> sqlParam = new HashMap<>();

		sqlParam.put("yearMonth", salaryInfo.getMonth());
		sqlParam.put("employeeName", salaryInfo.getMonth());
		sqlParam.put("employeeID", (String) session.getAttribute("userEmoplyeeID"));
		SalaryInfo salary = salaryInfoService.querySalaryInfo(sqlParam);
		if (salary == null) {
			salary = new SalaryInfo();
		}


		//画面表示するため設定する
		SalaryInfoBean salaryInfoBean = salaryInfoService.tranferData(salary);
		//対象年月を画面へ戻す。
		salaryInfoBean.setMonth(DateUtil.chgYMToDate(salaryInfo.getMonth()));




		model.addAttribute("salarydataerror", salaryInfoBean);



		return "/ems/salarydetail";
	}
	/**
	 * 機能：月度オープション
	 *
	 * @param なし
	 * @return salarydetail
	 * @author ○○@ソフトテク
	 */
	@GetMapping("/salarydetail")
	public String salarydetail(Model model) {

	SalaryInfoBean salaryInfoBean = new SalaryInfoBean();

	ArrayList<MonthInfo> ml = new ArrayList<MonthInfo>();
	MonthInfo info = new MonthInfo();
	info.setId(1);
	info.setName("01");
	ml.add(info);

	MonthInfo info2 = new MonthInfo();
	info2.setId(2);
	info2.setName("02");
	ml.add(info2);

	MonthInfo info3 = new MonthInfo();
	info3.setId(3);
	info3.setName("03");
	ml.add(info3);
	MonthInfo info4 = new MonthInfo();
	info4.setId(4);
	info4.setName("04");
	ml.add(info4);

	MonthInfo info5 = new MonthInfo();
	info5.setId(5);
	info5.setName("05");
	ml.add(info5);

	MonthInfo info6= new MonthInfo();
	info6.setId(6);
	info6.setName("06");
	ml.add(info6);

	MonthInfo info7 = new MonthInfo();
	info7.setId(7);
	info7.setName("07");
	ml.add(info7);

	MonthInfo info8 = new MonthInfo();
	info8.setId(8);
	info8.setName("08");
	ml.add(info8);

	MonthInfo info9 = new MonthInfo();
	info9.setId(9);
	info9.setName("09");
	ml.add(info9);

	MonthInfo info10 = new MonthInfo();
	info10.setId(10);
	info10.setName("10");
	ml.add(info10);

	MonthInfo info11 = new MonthInfo();
	info11.setId(11);
	info11.setName("11");
	ml.add(info11);

	MonthInfo info12 = new MonthInfo();
	info12.setId(12);
	info12.setName("12");
	ml.add(info12);

	// 月度オープションを設定
	salaryInfoBean.setMonthInfoList(ml);
	// 画面に渡す
	model.addAttribute("salarydata", salaryInfoBean);
	return "/ems/salarydetail";
	}
}
