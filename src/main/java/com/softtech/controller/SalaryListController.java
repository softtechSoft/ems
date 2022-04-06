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
	 * 機能：明細画面データ遷移
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

		//給料詳細画面にて選択された月を設定する
		String monthID = DateUtil.chgMonthToYM(p1).substring(4);

		switch (Integer.parseInt( monthID)){
		case 1:
			salaryInfoBean.setSelectedMonthId(1);
			break;
		case 2 :
			salaryInfoBean.setSelectedMonthId(2);
			break;
		case 3 :
			salaryInfoBean.setSelectedMonthId(3);
			break;
		case 4 :
			salaryInfoBean.setSelectedMonthId(4);
			break;
		case 5 :
			salaryInfoBean.setSelectedMonthId(5);
			break;
		case 6 :
			salaryInfoBean.setSelectedMonthId(6);
			break;
		case 7 :
			salaryInfoBean.setSelectedMonthId(7);
			break;
		case 8 :
			salaryInfoBean.setSelectedMonthId(8);
			break;
		case 9 :
			salaryInfoBean.setSelectedMonthId(9);
			break;
		case 10 :
			salaryInfoBean.setSelectedMonthId(10);
			break;
		case 11 :
			salaryInfoBean.setSelectedMonthId(11);
			break;
		case 12 :
			salaryInfoBean.setSelectedMonthId(12);
			break;
		default:
			break;
		}

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
