package com.softtech.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
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
	public String WorkDetailSubmit(HttpServletResponse response,@Valid @ModelAttribute("selectjyolken") WorkSelectJyoken selectjyolken, BindingResult bindingResult,Model model) {
		// NotNullの入力した年月をチェック。
		 if (bindingResult.hasErrors()) {
			return "/ems/workdetaillist";
		 }

		 // データダウンロード場合
		 if(selectjyolken.getDownloadFlg()){
			 csvDownload(response,selectjyolken.getMonth());
		 // 検索する場合
		 } else {
			 // 入力した年月を持っち、DBから勤怠情報を取得
		     List<WorkDetail> workDetailList1 = workDetailListService.queryWorkDetail(selectjyolken.getMonth());
			 model.addAttribute("selectjyolken", selectjyolken);
			 model.addAttribute("timereport", workDetailList1);
		 }
		 return "/ems/workdetaillist";
	}

	//勤怠リストをダウンロードする。
	private void csvDownload(HttpServletResponse  response,String month){
			 response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=utf-8");
			 response.setHeader("Content-Disposition", "attachment; filename=\"test.csv\"");
	        try (PrintWriter pw = response.getWriter()) {
	        	List<WorkDetail> workDetailList2 = workDetailListService.queryWorkDetail(month);
	            for (int i = 0; i < workDetailList2.size(); i++) {
	            	String employeeID = workDetailList2.get(i).getEmployeeID();
	                String employeeName = workDetailList2.get(i).getEmployeeName();
	                String workMonth = workDetailList2.get(i).getWorkMonth();
	                float workTime = workDetailList2.get(i).getWorkTime();
	                float transportExpense = workDetailList2.get(i).getTransportExpense();
	                float transport = workDetailList2.get(i).getTransport();


	                String outputString = employeeID + "," + employeeName + "," + workMonth + "," + workTime + "," + transportExpense + "," + transport
	                         + "\r\n";

	                pw.print(outputString);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 }
}