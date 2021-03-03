package com.softtech.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softtech.entity.Employee;
import com.softtech.service.EmployeeService;

/**
 * 概要：パスワード変更機能
 *
 * 作成者：○○@ソフトテク
 * 作成日：2021/1/13
 */
@Controller
public class PasswdController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/passwd")
	public String passwd() {
		return "/ems/passwd";
	}

	/**
	 * 機能：パスワード変更
	 *
	 * @param dataとsession
	 * @return "001"
	 * @exception JsonMappingException
	 * @author ○○@ソフトテク
	 */
	@RequestMapping("/update-passwd")
	@ResponseBody
	public String updatePasswd(@Param("data") String data, HttpSession session)
			throws JsonMappingException, JsonProcessingException {
		Employee employee = employeeService.queryEmployee((String) session.getAttribute("userMailAdress"));
		ObjectMapper jsonMapper = new ObjectMapper();
		Map<String, String> dataMap = jsonMapper.readValue(data, Map.class);
		if (!employee.getPassword().equals(dataMap.get("oldPsw"))) {
			return "001";
		} else {
			Map<String, String> map = new HashMap<>();
			map.put("email", employee.getMailAdress());
			map.put("password", dataMap.get("firstPsw"));
			int num = employeeService.updatePassword(map);
			if (num == 0) {
				return "001";
			}
			session.removeAttribute("userUpdatePsw");
			return "111";
		}
	}
}

