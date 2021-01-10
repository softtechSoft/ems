package com.softtech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softtech.entity.SalaryInfo;
import com.softtech.entity.SalaryInfoComment;
import com.softtech.service.SalaryInfoServiceImpl;

@Controller
public class SalaryInfoController
{
	@Autowired
	SalaryInfoServiceImpl salaryInfoService;

	@RequestMapping("/salarydetails")
	public String salarydetails()
	{
		return "/ems/salarydetails";
	}
	
	@RequestMapping("/request-salarydetails")
	@ResponseBody
	public String salaryinfo(@RequestParam("yearMonth") String yearMonth,HttpSession session) throws JsonProcessingException
	{
		ObjectMapper jsonMapper=new ObjectMapper();
		Map<String,String> sqlParam = new HashMap<>();
		sqlParam.put("yearMonth",yearMonth);
		sqlParam.put("employeeID",(String) session.getAttribute("userEmoplyeeID"));
		SalaryInfo salary = salaryInfoService.querySalaryInfo(sqlParam) ;
		if(salary == null)
		{
			salary = new SalaryInfo();
		}
		List<SalaryInfoComment> column = salaryInfoService.querySalaryInfoComment();
		Map<String,Object> map = new HashMap<>();
		map.put("column", column);
		map.put("data", salary);
		String result = jsonMapper.writeValueAsString(map);
		return result;
	}
}