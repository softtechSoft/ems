package com.softtech.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtech.actionForm.WorkDetail;

@Controller
public class WorkDetailListController {

	@RequestMapping("/workdetaillist")
	public String toWorkDetailList(Model model) {

		WorkDetail workDetail1 = new WorkDetail();
		workDetail1.setId(0);
		workDetail1.setName("開発一郎");
		workDetail1.setTime("180");
		workDetail1.setMoon("202103");
		workDetail1.setA("あり");
		workDetail1.setMoney(7777);
		workDetail1.setB(3456);
		workDetail1.setC("SBT開発支援");


		WorkDetail workDetail2 = new WorkDetail();
		workDetail2.setId(1);
		workDetail2.setName("開発二郎");
		workDetail2.setTime("190");
		workDetail2.setMoon("202103");
		workDetail2.setA("なし");
		workDetail2.setMoney(6666);
		workDetail2.setB(1234);
		workDetail2.setC("SBT開発支援");


		WorkDetail workDetail3 = new WorkDetail();
		workDetail3.setId(2);
		workDetail3.setName("開発三郎");
		workDetail3.setTime("200");
		workDetail3.setMoon("202103");
		workDetail3.setA("あり");
		workDetail3.setMoney(8910);
		workDetail3.setB(54321);
		workDetail3.setC("SBT開発支援");
		List<WorkDetail> workDetailList = new ArrayList<WorkDetail>();
		workDetailList.add(workDetail1);
		workDetailList.add(workDetail2);
		workDetailList.add(workDetail3);

		model.addAttribute("timereport", workDetailList);

		return "/ems/workdetaillist";
	}
	 @PostMapping("/WorkDetail")
	  public String WorkDetailSubmit( WorkDetail workDetail, BindingResult bindingResult,Model model) {
		  String a =  workDetail.getMonth();
		   a =   a + "月です。";
		  workDetail.setMonth(a);
		  model.addAttribute("WorkDetail", workDetail);
		  return "workdetaillist";
	  }

}