package com.softtech.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtech.actionForm.SalaryInfoBean;
import com.softtech.actionForm.SalarySelectJyoken;
import com.softtech.service.SalaryListService;
import com.softtech.util.DateUtil;
/**
 * 概要：給料リスト機能
 *
 * 作成者：王@ソフトテク
 * 作成日：2021/4/13
 */
@Controller
public class SalaryListController {

	@Autowired
	SalaryListService salaryListService;

	/**
	 * 機能：画面初期表示
	 * @return  salarylist
	 * @author テー@ソフトテク
	 */
	@RequestMapping("/salarylist")
	public String Salarylist(Model model,HttpSession session) {
		//現在年取得
		String year = DateUtil.getNowYear();
		//検索条件初期化
		SalarySelectJyoken salarySelectJyoken = new SalarySelectJyoken();
		// 当年度
		salarySelectJyoken.setYear(year);
		// 画面操作の初期値は前年度に設定する。
		salarySelectJyoken.setOperationFlg("1");

		//画面へデータを渡す
		model.addAttribute("selectjyoken",salarySelectJyoken);

		//対象年度の給料リストを所得
		String employeeID = (String) session.getAttribute("userEmoplyeeID");
		List<SalaryInfoBean> sList = salaryListService.getSalaryList(year, employeeID);

		//画面へ給料リストデータを渡す
		model.addAttribute("salaryList",sList);

		return "/ems/salarylist";
	}
   /*
    * 明細画面表示
    */
	@GetMapping("/salarydetail")
	public String SalaryDetails(Model model) {
		SalaryInfoBean salaryInfoBean = new SalaryInfoBean();
		model.addAttribute("salarydata", salaryInfoBean);
		return "/ems/salarydetail";
	}
	/**
	 * 機能：前年度/次年度ボタン処理
	 *
	 *
	 * @return  salarylist
	 * @author テー@ソフトテク
	 */
	@PostMapping("/salarylist")
	public String salarylistSubmit(HttpServletResponse response,
			@Valid @ModelAttribute("selectjyoken") SalarySelectJyoken selectjyoken,
			BindingResult bindingResult,Model model) {

		//画面操作フラグを取得

		//画面上の年度を取得

		//画面上の年度から対象年度を生成（前年度の場合-1、次年度の場合+1)

		//対象年度のデータをDBから取得

		//DBデータ、年度データ、画面操作フラグのデータを画面へ設定する。

		//給料リスト画面に戻る。
		return "/ems/salarylist";
	}

	}


