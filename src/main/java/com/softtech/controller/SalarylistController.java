package com.softtech.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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

	@GetMapping("/salarydetail")
	public String salarydetails(Model model) {
		SalaryInfoBean salaryInfoBean = new SalaryInfoBean();
		model.addAttribute("salarydata", salaryInfoBean);
		return "/ems/salarydetail";
	}



	/**
	 * 機能：画面初期表示
	 * @return  salarylist
	 * @author @ソフトテク
	 */
	@RequestMapping("/salarylist")
	public String Salarylist(Model model) {
		//現在年取得
		String year = DateUtil.getNowYear();
		//検索条件初期化
		SalarySelectJyoken salarySelectJyoken = new SalarySelectJyoken();
		salarySelectJyoken.setYear(year);

		//画面へデータを渡す
		model.addAttribute("selectjyoken",salarySelectJyoken);

		 List<SalaryInfoBean> sList = new ArrayList<SalaryInfoBean>();
		//給料データを作成
		SalaryInfoBean sb1= new SalaryInfoBean();
		sb1.setMonth("2022/1");
		sb1.setBase("210000");
		sb1.setOverTimePlus("20000");
		sb1.setTransportExpense("10000");
		sb1.setWelfarePensionComp("10000");
		sb1.setWithholdingTax("7000");
		sb1.setMunicipalTax("8000");
		sb1.setSum("225000");
		sList.add(sb1);

		SalaryInfoBean sb2= new SalaryInfoBean();
		sb2.setMonth("2022/2");
		sb2.setBase("210000");
		sb2.setOverTimePlus("0");
		sb2.setTransportExpense("10000");
		sb2.setWelfarePensionComp("10000");
		sb2.setWithholdingTax("7000");
		sb2.setMunicipalTax("8000");
		sb2.setSum("205000");


		sList.add(sb2);
		SalaryInfoBean sb3= new SalaryInfoBean();
		sb3.setMonth("2022/3");
		sb3.setBase("");
		sb3.setOverTimePlus("");
		sb3.setTransportExpense("");
		sb3.setWelfarePensionComp("");
		sb3.setWithholdingTax("");
		sb3.setMunicipalTax("");
		sb3.setSum("");


		sList.add(sb3);
		SalaryInfoBean sb4= new SalaryInfoBean();
		sb4.setMonth("2022/4");
		sb4.setBase("");
		sb4.setOverTimePlus("");
		sb4.setTransportExpense("");
		sb4.setWelfarePensionComp("");
		sb4.setWithholdingTax("");
		sb4.setMunicipalTax("");
		sb4.setSum("");

		sList.add(sb4);
		SalaryInfoBean sb5= new SalaryInfoBean();
		sb5.setMonth("2022/5");
		sb5.setBase("");
		sb5.setOverTimePlus("");
		sb5.setTransportExpense("");
		sb5.setWelfarePensionComp("");
		sb5.setWithholdingTax("");
		sb5.setMunicipalTax("");
		sb5.setSum("");

		sList.add(sb5);

		SalaryInfoBean sb6= new SalaryInfoBean();
		sb6.setMonth("2022/6");
		sb6.setBase("");
		sb6.setOverTimePlus("");
		sb6.setTransportExpense("");
		sb6.setWelfarePensionComp("");
		sb6.setWithholdingTax("");
		sb6.setMunicipalTax("");
		sb6.setSum("");

		sList.add(sb6);
		SalaryInfoBean sb7= new SalaryInfoBean();
		sb7.setMonth("2022/7");
		sb7.setBase("");
		sb7.setOverTimePlus("");
		sb7.setTransportExpense("");
		sb7.setWelfarePensionComp("");
		sb7.setWithholdingTax("");
		sb7.setMunicipalTax("");
		sb7.setSum("");

		sList.add(sb7);
		SalaryInfoBean sb8= new SalaryInfoBean();
		sb8.setMonth("2022/8");
		sb8.setBase("");
		sb8.setOverTimePlus("");
		sb8.setTransportExpense("");
		sb8.setWelfarePensionComp("");
		sb8.setWithholdingTax("");
		sb8.setMunicipalTax("");
		sb8.setSum("");

		sList.add(sb8);
		SalaryInfoBean sb9= new SalaryInfoBean();
		sb9.setMonth("2022/9");
		sb9.setBase("");
		sb9.setOverTimePlus("");
		sb9.setTransportExpense("");
		sb9.setWelfarePensionComp("");
		sb9.setWithholdingTax("");
		sb9.setMunicipalTax("");
		sb9.setSum("");

		sList.add(sb9);
		SalaryInfoBean sb10= new SalaryInfoBean();
		sb10.setMonth("2022/10");
		sb10.setBase("");
		sb10.setOverTimePlus("");
		sb10.setTransportExpense("");
		sb10.setWelfarePensionComp("");
		sb10.setWithholdingTax("");
		sb10.setMunicipalTax("");
		sb10.setSum("");

		sList.add(sb10);
		SalaryInfoBean sb11= new SalaryInfoBean();
		sb11.setMonth("2022/11");
		sb11.setBase("");
		sb11.setOverTimePlus("");
		sb11.setTransportExpense("");
		sb11.setWelfarePensionComp("");
		sb11.setWithholdingTax("");
		sb11.setMunicipalTax("");
		sb11.setSum("");

		sList.add(sb11);
		SalaryInfoBean sb12= new SalaryInfoBean();
		sb12.setMonth("2022/12");
		sb12.setBase("");
		sb12.setOverTimePlus("");
		sb12.setTransportExpense("");
		sb12.setWelfarePensionComp("");
		sb12.setWithholdingTax("");
		sb12.setMunicipalTax("");
		sb12.setSum("");

		sList.add(sb12);




		//画面へ給料リストデータを渡す
		model.addAttribute("salaryList",sList);

		return "/ems/salarylist";
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

