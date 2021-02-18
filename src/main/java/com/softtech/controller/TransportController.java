
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

		// セッションからログインIDを取得する。
		Map<String,String> mapper = new HashMap();
		mapper.put("employeeID",(String) session.getAttribute("userEmoplyeeID"));

		//パラメータ取得
		Map<String,String[]> map = request.getParameterMap();
		for(Map.Entry<String, String[]> entry : map.entrySet()){
			//開始日、終了日を変換。YYYY-MM-DD形をYYYYMMDD形に変換。
			if(entry.getKey().equals("workStartDay")||entry.getKey().equals("workEndDay")){
				mapper.put(entry.getKey(),entry.getValue()[0].replace("-",""));
				continue;
			}
			//稼働月を変換。YYYY/MM形をYYYYMM形に変換。
			if(entry.getKey().equals("workMonth")){
				mapper.put(entry.getKey(),entry.getValue()[0].replace("/",""));
				continue;
			}
			//定期券開始日を変換。YYYY/MM形をYYYYMM形に変換。
			if(entry.getKey().equals("startDate")) {
				mapper.put(entry.getKey(),entry.getValue()[0].replace("/",""));
				continue;
			}
			mapper.put(entry.getKey(), entry.getValue()[0]);
		}

		//勤怠追加処理
		Transport transport=new Transport();
		try {
			transport = transportAllService.doTransport(file, mapper, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		sportMapper.put("employeeID",(String) session.getAttribute("userEmoplyeeID"));
		//　交通情報取得
		Transport transport = transportService.queryTransport(sportMapper);
		if(transport == null) {
			transport = new Transport();
		}
		//　画面へ戻す
		model.addAttribute("transport", transport);
		return "/ems/transpirt";
	}
}