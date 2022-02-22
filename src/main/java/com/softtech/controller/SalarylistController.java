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
public class SalarylistController {

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
		salarySelectJyoken.setYear(year);

		//画面へデータを渡す
		model.addAttribute("selectjyoken",salarySelectJyoken);

		//対象年度の給料リストを所得
		String employeeID = (String) session.getAttribute("userEmoplyeeID");
		List<SalaryInfoBean> sList = salaryListService.getSalaryList(year, employeeID);

		//画面へ給料リストデータを渡す
		model.addAttribute("salaryList",sList);

		return "/ems/salarylist";
	}

	@GetMapping("/salarydetail")
	public String salarydetails(Model model) {
		SalaryInfoBean salaryInfoBean = new SalaryInfoBean();
		model.addAttribute("salarydata", salaryInfoBean);
		return "/ems/salarydetail";
	}



	@PostMapping("/salarylist")
	public String salarylistSubmit(HttpServletResponse response,@Valid @ModelAttribute("selectjyoken") SalarySelectJyoken selectjyoken,BindingResult bindingResult,Model model) {

//		// NotNullの入力した年月をチェック。
//		 if (bindingResult.hasErrors()) {
//			return "/ems/salarylist";
//		 }
		 return "/ems/salarylist";
	}
//
//		// 入力した年月を持っち、DBから給料情報を取得
//	     List<SalaryInfo> sl2 = salaryListService.querySalaryList(selectjyoken.getMonth());
//
//		 // データダウンロード場合
//		 if(selectjyoken.getDownloadFlg()){
//			 FileUtil ft = new FileUtil();
//			 boolean rtn = ft.salaryDownload(response,sl2);
//			 if(!rtn) {
//				 // エラーメッセージを設定して、画面表示
//			 }else {
//				 //画面表示用データを設定する。
//				 model.addAttribute("selectjyoken", selectjyoken);
//			 }
//
//		 // 検索する場合
//		 } else {
//
//			 model.addAttribute("selectjyoken",selectjyoken);
//			 model.addAttribute("salarydate", sl2);
//		 }
//		 return "/ems/salarylist";
	}

//
//	@PostMapping("/salarylist/download")
//	public ResponseEntity<byte[]> download(@Valid @ModelAttribute("selectjyoken") SalarySelectJyoken selectjyoken,BindingResult bindingResult,Model model) throws IOException {
//	  HttpHeaders h = new HttpHeaders();
//	  h.add("Content-Type", "text/csv; charset=MS932");
//	  h.setContentDispositionFormData("filename", "hoge.csv");
//	  //画面入力値を取得
//	  String yearmonth = selectjyoken.getMonth();
//	 List<SalaryInfo> sl2= salaryListService.querySalaryList(yearmonth);
//	 StringBuffer result = new StringBuffer();
//	 for(SalaryInfo salaryInfo:sl2){
//		 result.append(salaryInfo.getEmployeeID());
//		 result.append(",");
//		 result.append(salaryInfo.getEmployeeName());
//		 result.append(",");
//		 result.append(salaryInfo.getMonth());
//		 result.append(",");
//		 result.append(salaryInfo.getPaymentDate());
//		 result.append(",");
//		 result.append(salaryInfo.getBase());
//		 result.append(",");
//		 result.append(salaryInfo.getSum());
//		 result.append(",");
//		 result.append(salaryInfo.getRemark());
//		 result.append("\r\n");
//
//	 }
//	  return new ResponseEntity<>(result.toString().getBytes("MS932"), h, HttpStatus.OK);
//	}

