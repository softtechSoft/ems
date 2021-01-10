package com.softtech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PasswdController 
{
	@RequestMapping("/passwd")
	public String passwd()
	{
		return "/ems/passwd";
	}
}
