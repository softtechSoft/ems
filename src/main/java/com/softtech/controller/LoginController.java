package com.softtech.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softtech.entity.Employee;
import com.softtech.entity.Ofcfunction;
import com.softtech.service.EmployeeService;

@Controller
public class LoginController 
{
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/")
	public String login(HttpSession session)
	{
		if(session.getAttribute("userMailAdress")!=null) 
		{
			return "redirect:/main";
		}
		else 
		{
			return "/index";
		}
	}
	
	@RequestMapping("/enter")
	@ResponseBody
	public String enter(@RequestParam("data") String data,HttpSession session) throws JsonMappingException, JsonProcessingException 
	{ 
		Employee employee = new Employee();
		ObjectMapper jsonMapper = new ObjectMapper();
		Map<String,String> map = jsonMapper.readValue(data, Map.class);
		employee = employeeService.queryEmployee(map.get("user"));
		if(employee!=null)
		{
			if(map.get("password").equals(employee.getPassword()))
			{
				session.setAttribute("userMailAdress", employee.getMailAdress());
				session.setAttribute("userEmoplyeeID", employee.getEmployeeID());
				session.setAttribute("userAuthority", employee.getAuthority());
				return "111";
			}
			else
			{
				return "002";
			}
		}
		else
		{
			return "001";
		}
	}
}
