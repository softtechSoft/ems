package com.softtech.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import com.softtech.util.FileUtil;
/**
 * 概要：勤怠リスト機能
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/4/10
 */
@Controller
public class WorkDetailListController {

	@Autowired
	WorkDetailListService workDetailListService;
	@RequestMapping("/workinfolist")
	public String toWorkDetailList(Model model) {

//        //現在年月取得
//		String month=DateUtil.getNowMonth();
//		// DBから勤怠情報を取得
//		List<WorkDetail> workDetailList = workDetailListService.queryWorkDetail(month);
//
//		model.addAttribute("timereport", workDetailList);

		//検索条件初期化
		WorkSelectJyoken workSelectJyoken= new WorkSelectJyoken();
		workSelectJyoken.setFromMonth("2022/01");
		workSelectJyoken.setToMonth("2022/02");
		workSelectJyoken.setDownloadFlg(false);
		model.addAttribute("selectjyolken", workSelectJyoken);

		List<WorkDetail> workDetailList1 = new ArrayList<WorkDetail>();
		 model.addAttribute("timereport", workDetailList1);

		return "/ems/workdetaillist";
	}
	/**
	 * 機能：入力した年月に基づいて勤怠リストを表示する。
	 * @return  workdetaillist
	 * @author 馬@ソフトテク
	 */
	@PostMapping("/WorkDetail")
	public String WorkDetailSubmit(HttpServletResponse response,@Valid @ModelAttribute("selectjyolken") WorkSelectJyoken selectjyolken, BindingResult bindingResult,Model model) {
		// NotNullの入力した年月をチェック。
		 if (bindingResult.hasErrors()) {
			return "/ems/workdetaillist";
		 }

		// 入力した年月を持っち、DBから勤怠情報を取得
	     List<WorkDetail> workDetailList1 = workDetailListService.queryWorkDetail(selectjyolken.getMonth());

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