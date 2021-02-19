package com.softtech.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.softtech.entity.Transport;
import com.softtech.service.TransportAllService;
import com.softtech.service.TransportService;
import com.softtech.service.WorkInfoService;



@Controller
public class TransportController<WorkInfoComment>
{
	@Autowired
	private TransportService transportService;
	@Autowired
	private WorkInfoService workinfoService;

	@Autowired
	private TransportAllService transportAllService;

	@RequestMapping("/transport-workinfo")
	public String insertTransport(HttpServletRequest request,HttpSession session,@RequestParam("file") MultipartFile file,Model model) throws JsonMappingException, JsonProcessingException{


		//勤怠追加処理
		Transport transport=new Transport();

		transport = transportAllService.doTransport(request,session,file,model);
		model.addAttribute("transport", transport);
		return "/ems/transpirt";
		}


	/**
	 * 機能：勤怠管理画面初期データ設定
	 *
	 * @param 画面情報
	 * @return 遷移画面
	 *
	 * @author 楊@ソフトテク
	 */
	@RequestMapping("/workdetail")
	public String Workdetail(HttpServletRequest request,HttpSession session,Model model){

		Map<String,String> sportMapper = new HashMap();
		//　交通情報取得
		sportMapper.put("employeeID",(String) session.getAttribute("userEmoplyeeID"));
		Transport transport = transportService.queryTransport(sportMapper);
		if(transport == null) {
			transport = new Transport();
		}
		//　画面へ戻す
		model.addAttribute("transport", transport);
		return "/ems/transpirt";
	}
}