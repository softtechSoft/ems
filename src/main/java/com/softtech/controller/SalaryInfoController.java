package com.softtech.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.softtech.actionForm.SalaryInfoBean;
import com.softtech.com.MonthInfo;
import com.softtech.com.YearInfo;
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

	/**
	 * 機能：初期表示
	 *
	 * @param yearMonthとsession
	 * @return result
	 * @exception JsonMappingException
	 * @author ○○@ソフトテク
	 */
	@RequestMapping("/salarydetail")
	public String salarydetails(Model model) {

		SalaryInfoBean salaryInfoBean = new SalaryInfoBean();

		//現在の年月度取得
		String p1 = DateUtil.getNowMonth();

		// 過去五年間の年度を生成する
		ArrayList<YearInfo> yl = DateUtil.getBeforeYears(5);
		// 年度候補を設定
		salaryInfoBean.setYearInfoList(yl);
		// 選択する年度の設定
		salaryInfoBean = salaryInfoService.setSelectedYear(salaryInfoBean,p1);

		//月度候補生成
		ArrayList<MonthInfo> ml = DateUtil.getMonths() ;
		//月度候補設定
		salaryInfoBean.setMonthInfoList(ml);
		//選択する月度の設定
		salaryInfoBean = salaryInfoService.setSelectedMonth(salaryInfoBean,p1);

		//戻る値
		model.addAttribute("salarydata", salaryInfoBean);

		return "/ems/salarydetail";
	}

	/**
	 * 機能：給料明細取得
	 *     （検索ボタン処理）
	 *
	 * @param salaryInfo 画面入力
	 * @param model
	 *
	 * @author 開発@ソフトテク
	 */
	@PostMapping("/request-salarydetail")
	public String getSalaryInfo(@ModelAttribute("salarydata") SalaryInfoBean salaryInfo,
								Model model,
								HttpSession session)
	{
		//選択された年度を取得
		String selectedYearMonth = salaryInfo.getMonth();

		//検索条件
		Map<String, String> sqlParam = new HashMap<>();
		sqlParam.put("yearMonth", salaryInfo.getMonth());
		sqlParam.put("employeeID", (String) session.getAttribute("userEmoplyeeID"));
		//DB検索
		SalaryInfo salary = salaryInfoService.querySalaryInfo(sqlParam);
		if (salary == null) {
			salary = new SalaryInfo();
		}
		//画面表示データ生成
		SalaryInfoBean salaryInfoBean = salaryInfoService.tranferData(salary);

		// 過去五年間の年度を生成する
		ArrayList<YearInfo> yl = DateUtil.getBeforeYears(5);
		// 年度候補を設定
		salaryInfoBean.setYearInfoList(yl);
		// 選択する年度の設定
		salaryInfoBean = salaryInfoService.setSelectedYear(salaryInfoBean,selectedYearMonth);

		//月度候補生成
		ArrayList<MonthInfo> ml = DateUtil.getMonths() ;
		//月度候補設定
		salaryInfoBean.setMonthInfoList(ml);
		//選択する月度の設定
		salaryInfoBean = salaryInfoService.setSelectedMonth(salaryInfoBean,selectedYearMonth);

		model.addAttribute("salarydata", salaryInfoBean);

		return "/ems/salarydetail";
	}


}
