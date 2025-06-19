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

/**
 * 概要：画面遷移機能
 *
 * 作成者：○○@ソフトテク
 * 作成日：2021/1/13
 */
@Controller
public class MainController {
	@Autowired
	@Qualifier("mainServiceImpl")
	MainService mainService;

	/**
	 * 機能：画面初期化と遷移
	 *
	 * @param modelとsession
	 * @return /mainと/index
	 * @exception JsonProcessingException
	 * @author ○○@ソフトテク
	 */
	@RequestMapping("/main")
	public String emsMain(Model model, HttpSession session) throws JsonProcessingException {
		if (session.getAttribute("userMailAdress") != null) {
			List<Ofcfunction> list = mainService.queryOfcfunction((String) session.getAttribute("userAuthority"));
			
			String userAuthority = (String) session.getAttribute("userAuthority");
			if (userAuthority == null || userAuthority.isEmpty() || list == null || list.isEmpty()) {
	            list = mainService.queryOfcfunction("0");
	        }
			
			model.addAttribute("list", list);
			model.addAttribute("manager", (String) session.getAttribute("userAuthority"));
			return "/main/main";
		} else {
			return "redirect:/index";
		}
	}

	/**
	 * 機能：画面遷移
	 *
	 * @param なし
	 * @return /welcome
	 * @exception なし
	 * @author ○○@ソフトテク
	 */
	@RequestMapping("/welcome")
	public String welcome() {
		return "/main/welcome";
	}

	/**
	 * 機能：画面遷移
	 *
	 * @param session
	 * @return /welcome
	 * @exception なし
	 * @author ソフトテク教育グループ
	 */
	@RequestMapping("/exit")
	@ResponseBody
	public void exit(HttpSession session) {
		session.invalidate();
	}
}
