package com.softtech.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtech.actionForm.WorkDetail;
import com.softtech.actionForm.WorkSelectJyoken;
import com.softtech.service.WorkDetailListService;
import com.softtech.util.DateUtil;
import com.softtech.util.FileUtil;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
/**
 * 概要：勤怠リスト機能
 *
 * 作成者：孫@ソフトテク
 * 作成日：2022/2/20
 */
@Controller
public class WorkDetailListController {

	@Autowired
	WorkDetailListService workDetailListService;
	@RequestMapping("/workinfolist")
	public String toWorkDetailList(Model model,HttpSession session) {

        //現在年月取得
		String month=DateUtil.getNowMonth();


		//半年前時間
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -6);
//		GregorianCalendar gr=new GregorianCalendar();
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//		 gr.set(GregorianCalendar.YEAR,GregorianCalendar.MONTH-6,GregorianCalendar.DATE);

		    String di=null;
		           di=sdf.format(cal.getTime());



		//検索条件初期化
		WorkSelectJyoken workSelectJyoken= new WorkSelectJyoken();
		workSelectJyoken.setFromMonth(di);
		workSelectJyoken.setToMonth(month);
		workSelectJyoken.setDownloadFlg(false);
		model.addAttribute("selectjyolken", workSelectJyoken);

		// セッションからログインIDを取得する。
		Map<String, String> mapper = new HashMap<String, String>();
		mapper.put("employeeID", (String) session.getAttribute("userEmoplyeeID"));
		mapper.put("fromMonth", di);
		mapper.put("toMonth", month);
		// DBから勤怠情報を取得
		List<WorkDetail> workDetailList = workDetailListService.queryWorkDetail(mapper);
		model.addAttribute("timereport", workDetailList);
		return "/ems/workdetaillist";
	}



	/**
	 * 機能：入力した年月に基づいて勤怠リストを表示する。
	 * @return  workdetaillist
	 * @author 馬@ソフトテク
	 */
	@PostMapping("/WorkDetail")
	public String WorkDetailSubmit(HttpServletResponse response,@Valid @ModelAttribute("selectjyolken") WorkSelectJyoken selectjyolken, BindingResult bindingResult,Model model,HttpSession session) {
		// NotNullの入力した年月をチェック。
		 if (bindingResult.hasErrors()) {
			return "/ems/workdetaillist";
		 }

		// 入力した年月を持っち、DBから勤怠情報を取得
			// セッションからログインIDを取得する。
			Map<String, String> mapper = new HashMap<String, String>();
			mapper.put("employeeID", (String) session.getAttribute("userEmoplyeeID"));
			mapper.put("fromMonth", selectjyolken.getFromMonth());
			mapper.put("toMonth", selectjyolken.getToMonth());

	     List<WorkDetail> workDetailList1 = workDetailListService.queryWorkDetail(mapper);

		 // データダウンロード場合
		 if(selectjyolken.getDownloadFlg()){
			 FileUtil ft = new FileUtil();
			 boolean rtn = ft.workSheetDownload(response,workDetailList1);
			 if(!rtn) {
				 // エラーメッセージを設定して、画面表示
			 }else {
				 //画面表示用データを設定する。
				 model.addAttribute("selectjyolken", selectjyolken);
			 }

		 // 検索する場合
		 } else {


			 model.addAttribute("selectjyolken", selectjyolken);
			 model.addAttribute("timereport", workDetailList1);
		 }
		 return "/ems/workdetaillist";
	}

}



