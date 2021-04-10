package com.softtech.controller;

import java.util.ArrayList;
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
	@PostMapping("/WorkDetail")
	public String WorkDetailSubmit(@Valid @ModelAttribute("selectjyolken") WorkSelectJyoken selectjyolken,BindingResult bindingResult,Model model) {

		 if (bindingResult.hasErrors()) {
			 //FieldError errMsg = bindingResult.getFieldError("month");

			 //model.addAttribute("selectjyolken", selectjyolken);
			return "/ems/workdetaillist";
		 }

		 String a =  selectjyolken.getMonth();
		   a =   a + "月です。";
		   selectjyolken.setMonth(a);

		  model.addAttribute("selectjyolken", selectjyolken);
		  model.addAttribute("timereport",new ArrayList<WorkDetail>());
		  return "/ems/workdetaillist";
	 }

}