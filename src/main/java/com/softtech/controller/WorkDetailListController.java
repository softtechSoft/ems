package com.softtech.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

//		WorkDetail workDetail1 = new WorkDetail();
//		workDetail1.setId(0);
//		workDetail1.setName("開発一郎");
//		workDetail1.setTime("180");
//		workDetail1.setMoon("202103");
//		workDetail1.setA("あり");
//		workDetail1.setMoney(7777);
//		workDetail1.setB(3456);
//		workDetail1.setC("SBT開発支援");
//
//
//		WorkDetail workDetail2 = new WorkDetail();
//		workDetail2.setId(1);
//		workDetail2.setName("開発二郎");
//		workDetail2.setTime("190");
//		workDetail2.setMoon("202103");
//		workDetail2.setA("なし");
//		workDetail2.setMoney(6666);
//		workDetail2.setB(1234);
//		workDetail2.setC("SBT開発支援");
//
//
//		WorkDetail workDetail3 = new WorkDetail();
//		workDetail3.setId(2);
//		workDetail3.setName("開発三郎");
//		workDetail3.setTime("200");
//		workDetail3.setMoon("202103");
//		workDetail3.setA("あり");
//		workDetail3.setMoney(8910);
//		workDetail3.setB(54321);
//		workDetail3.setC("SBT開発支援");
//		List<WorkDetail> workDetailList = new ArrayList<WorkDetail>();
//		workDetailList.add(workDetail1);
//		workDetailList.add(workDetail2);
//		workDetailList.add(workDetail3);

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
	  public String WorkDetailSubmit(WorkSelectJyoken selectjyolken,  BindingResult bindingResult,Model model) {

		 String a =  selectjyolken.getMonth();
		   a =   a + "月です。";
		   selectjyolken.setMonth(a);

		  model.addAttribute("selectjyolken", selectjyolken);
		  model.addAttribute("timereport",new ArrayList<WorkDetail>());
		  return "/ems/workdetaillist";
	  }

}