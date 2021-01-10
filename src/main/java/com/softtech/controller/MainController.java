package com.softtech.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.softtech.entity.Ofcfunction;
import com.softtech.service.MainService;

@Controller
public class MainController 
{
	@Autowired
	@Qualifier("mainServiceImpl")
	MainService mainService;
	
	@RequestMapping("/main")
	public String emsMain(Model model,HttpSession session) throws JsonProcessingException
	{
		List<Ofcfunction> list= mainService.queryOfcfunction((String)session.getAttribute("userAuthority"));
		model.addAttribute("list", list);
		return "/main/main";
	}
	
	@RequestMapping("/welcome")
	public String welcome()
	{
		return "/main/welcome";
	}
	
	@RequestMapping("/exit")
	@ResponseBody
	public void exit(HttpSession session)
	{
		session.invalidate();
	}
}
