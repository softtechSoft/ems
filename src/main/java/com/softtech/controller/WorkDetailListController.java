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

import com.softtech.actionForm.WorkDetail;
import com.softtech.actionForm.WorkSelectJyoken;
import com.softtech.service.WorkDetailListService;
import com.softtech.util.DateUtil;
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

	@RequestMapping("/workdetaillist")
	public String toWorkDetailList(Model model) {

        //現在年月取得
		String month=DateUtil.getNowMonth();
		// DBから勤怠情報を取得
		List<WorkDetail> workDetailList = workDetailListService.queryWorkDetail(month);

		model.addAttribute("timereport", workDetailList);

		//検索条件初期化
		WorkSelectJyoken workSelectJyoken= new WorkSelectJyoken();
		workSelectJyoken.setMonth(month);
		model.addAttribute("selectjyolken", workSelectJyoken);

		return "/ems/workdetaillist";
	}
	/**
	 * 機能：入力した年月に基づいて勤怠リストを表示する。
	 * @return  workdetaillist
	 * @author 馬@ソフトテク
	 */
	@PostMapping("/WorkDetail")
	public String WorkDetailSubmit(@Valid @ModelAttribute("selectjyolken") WorkSelectJyoken selectjyolken,BindingResult bindingResult,Model model) {
         // NotNullの入力した年月をチェック。
		 if (bindingResult.hasErrors()) {
			 //FieldError errMsg = bindingResult.getFieldError("month");

			 //model.addAttribute("selectjyolken", selectjyolken);
			return "/ems/workdetaillist";
		 }
		 // 入力した年月を持っち、DBから勤怠情報を取得
	     List<WorkDetail> workDetailList1 = workDetailListService.queryWorkDetail(selectjyolken.getMonth());
		 model.addAttribute("selectjyolken", selectjyolken);
		 model.addAttribute("timereport", workDetailList1);
		 return "/ems/workdetaillist";
	 }

}