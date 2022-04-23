package com.softtech.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.softtech.actionForm.SalaryInfoBean;
import com.softtech.actionForm.SalarySelectJyoken;
import com.softtech.com.MonthInfo;
import com.softtech.com.YearInfo;
import com.softtech.entity.SalaryInfo;
import com.softtech.service.SalaryInfoServiceImpl;
import com.softtech.service.SalaryListService;
import com.softtech.util.DateUtil;
/**
 * 概要：給料リスト機能
 *
 * 作成者：テー@ソフトテク
 * 作成日：2021/4/13
 */
@Controller
public class SalaryListController {

	// 給料リストサービス
	@Autowired
	SalaryListService salaryListService;

	// 給料サービス
	@Autowired
	SalaryInfoServiceImpl salaryInfoService;


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


	/**
	 * 機能：給料明細画面データ遷移
	 *
	 * @param model
	 * @param p1 選択された行の年月度
	 * @param p2 ログイン者ID
	 *
	 */
	@GetMapping("/toSalarydetail")
	public String SalaryDetails(Model model,@RequestParam("p1")String p1,@RequestParam("p2")String p2) {

		Map<String, String> sqlParam = new HashMap<>();
		//yyyy/mm から yyyymmに変更
		sqlParam.put("yearMonth", DateUtil.chgMonthToYM(p1));
		sqlParam.put("employeeID", p2);

		// 画面にされた行の年月、ログイン者IDの給料データを取得する
		SalaryInfo salary = salaryInfoService.querySalaryInfo(sqlParam);
		SalaryInfoBean salaryInfoBean = salaryInfoService.tranferData(salary);

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


		//給料詳細画面へデータ渡す
		model.addAttribute("salarydata", salaryInfoBean);

		return "/ems/salarydetail";
	}

	/**
	 * 機能：前年度/次年度ボタン処理
	 *
	 * @return  リスト画面
	 * @author テー@ソフトテク
	 * @throws ParseException
	 */
	@PostMapping("/salarylist")
	public String salarylistSubmit(HttpServletResponse response,
			@Valid @ModelAttribute("selectjyoken") SalarySelectJyoken selectjyoken,
			BindingResult bindingResult,Model model,HttpSession session) throws ParseException {
		if (bindingResult.hasErrors()) {
			return "/ems/salarylist";
		 }
		//画面操作フラグを取得
		String optFlg = selectjyoken.getOperationFlg();


		//画面上の年度を取得
		String year=selectjyoken.getYear();

		//画面上の年度から対象年度を生成（前年度の場合-1、次年度の場合+1)
		//前年度の場合
		if( "1".equals(optFlg)) {
			year=DateUtil.yearMinus(year);

		//次年度の場合
		}else if( "2".equals(optFlg)) {
			year=DateUtil.yearPlus(year);
		}

		//対象年度のデータをDBから取得
		String employeeID = (String) session.getAttribute("userEmoplyeeID");
		List<SalaryInfoBean> sList = salaryListService.getSalaryList(year,employeeID);

		//DBデータ、年度データ、画面操作フラグのデータを画面へ設定する。
		model.addAttribute("salaryList",sList);
		selectjyoken.setYear(year);

		//給料リスト画面に戻る。
		return "/ems/salarylist";

	}

}
