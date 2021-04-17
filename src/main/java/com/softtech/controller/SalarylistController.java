package com.softtech.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtech.actionForm.SalarySelectJyoken;
import com.softtech.entity.SalaryInfo;
import com.softtech.service.SalaryListService;
import com.softtech.util.DateUtil;
/**
 * 概要：給料リスト機能
 *
 * 作成者：王@ソフトテク
 * 作成日：2021/4/13
 */
@Controller
public class SalarylistController {

	@Autowired
	SalaryListService salaryListService;

	/**
	 * 機能：画面初期表示
	 * @return  salarylist
	 * @author 王@ソフトテク
	 */
	@RequestMapping("/salarylist")
	public String Salarylist(Model model) {

		//現在年月取得
		String month = DateUtil.getNowMonth();
		// DBから給料情報を取得
		List<SalaryInfo> sl= salaryListService.querySalaryList(month);
		model.addAttribute("salarydate",sl);
		//検索条件初期化
		SalarySelectJyoken salarySelectJyoken = new SalarySelectJyoken();
		salarySelectJyoken.setMonth(month);
		model.addAttribute("selectjyoken",salarySelectJyoken);

		return "/ems/salarylist";
	}
	/**
	 * 機能：入力した年月に基づいて給料リストを表示する。
	 * @return  salarylist
	 * @author 王@ソフトテク
	 */
	@PostMapping("/salarylist")
	public String salarylistSubmit(@Valid @ModelAttribute("selectjyoken") SalarySelectJyoken selectjyoken,BindingResult bindingResult,Model model) {
         // NotNullの入力した年月をチェック。
		 if (bindingResult.hasErrors()) {
			return "/ems/salarylist";
		 }

		 //画面入力値を取得
		 String yearmonth = selectjyoken.getMonth();
		 List<SalaryInfo> sl2= salaryListService.querySalaryList(yearmonth);

		 model.addAttribute("salarydate", sl2);

		 // 入力した年月を持っち、DBから給料情報を取得
		 selectjyoken.setMonth(yearmonth);
		 model.addAttribute("selectjyoken",selectjyoken);

		 return "/ems/salarylist";
	 }

}