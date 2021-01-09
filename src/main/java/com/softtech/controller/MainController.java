package com.softtech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String emsMain(Model model) throws JsonProcessingException
	{
		List<Ofcfunction> list= mainService.queryOfcfunction("0");
		model.addAttribute("list", list);
		return "/main.html";
	}
}
