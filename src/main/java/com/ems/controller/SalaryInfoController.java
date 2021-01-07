package com.ems.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ems.entity.SalaryInfo;
import com.ems.service.SalaryInfoServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
public class SalaryInfoController
{
	@Autowired
	SalaryInfoServiceImpl salaryInfoService;

	@RequestMapping("/salaryinfo")
	@ResponseBody
	public String salaryinfo(@RequestParam("yearMonth") String yearMonth,HttpSession session) throws JsonProcessingException
	{

		session.setAttribute("employeeID", "E001");

		ObjectMapper jsonMapper=new ObjectMapper();
		//SQLのparam作成
		Map<String,String> sqlParam = new HashMap<>();
		sqlParam.put("yearMonth",yearMonth);
		sqlParam.put("employeeID",(String) session.getAttribute("employeeID"));
		//作成のParamによって給料明細の情報を検索出す
		SalaryInfo salary = salaryInfoService.querySalaryInfo(sqlParam) ;

		if(salary == null)
		{
			salary = new SalaryInfo();
		}

		Map<String,Object> map = new HashMap<>();
		map.put("column", null);
		map.put("data", salary);
		String result = jsonMapper.writeValueAsString(map);
		return result;


	}

}