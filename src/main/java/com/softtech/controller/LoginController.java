package com.softtech.controller;

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
import com.softtech.service.EmployeeService;

/**
 * 概要：ログイン機能
 *
 * 作成者：○○@ソフトテク
 * 作成日：2021/1/13
 */
@Controller
public class LoginController {
	@Autowired
	private EmployeeService employeeService;

	/**
	 * 機能：ログイン画面初期化と遷移
	 *
	 * @param session
	 * @return /indexと/main
	 * @exception なし
	 * @author ○○@ソフトテク
	 */
	@RequestMapping({ "/", "/ems" })
	public String login(HttpSession session) {
		if (session.getAttribute("userMailAdress") != null) {
			return "redirect:/main";
		} else {
			return "/index";
		}
	}

	/**
	 * 機能：ログイン判断
	 *
	 * @param dataとsession
	 * @return "111"と"002"と"001"
	 * @exception JsonMappingException
	 * @author ○○@ソフトテク
	 */
	@RequestMapping("/enter")
	@ResponseBody
	public String enter(@RequestParam("data") String data, HttpSession session)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper jsonMapper = new ObjectMapper();
		Map<String, String> map = jsonMapper.readValue(data, Map.class);

		// ログインSQLのパラメータ作成
		Employee employeep = new Employee();
		
		// ユーザー入力値を取得
	    String userInput = map.get("user");
	    
	    // メールアドレス形式チェックと補完
	    String userMail = userInput.contains("@") ? userInput : userInput + "@it-softtech.com"; 
		// 画面IDを設定する。
		employeep.setMailAdress(userMail);
		// 画面パスワードを設定する
		employeep.setPassword(map.get("password"));

		//String userMail = map.get("user");

		//ユーザ登録チェック
		Employee employeeUser = employeeService.queryEmployee(userMail);
		if(employeeUser==null || employeeUser.getMailAdress()==null || employeeUser.getMailAdress().isEmpty()) {
			//失敗：メールアドレス（ユーザID）が存在していません
			return "001";
		}

		Employee employee = employeeService.login(employeep);
		if (employee != null) {

			session.setAttribute("userMailAdress", employee.getMailAdress());
			session.setAttribute("userEmoplyeeID", employee.getEmployeeID());
			session.setAttribute("userAuthority", employee.getAuthority());
			session.setAttribute("userEmployeeName", employee.getEmployeeName());
			if (employee.getUpdateDate() == null) {
				session.setAttribute("userUpdatePsw", "false");
			}
			//成功：001、002ではないならなんでもOK。
			return "111";

		} else {
			//失敗：パスワード不正。
			return "002";
		}
	}
}
