package com.ems.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsofttech.pojo.Company;
import com.itsofttech.pojo.Employee;
import com.itsofttech.service.CompanyService;
import com.itsofttech.service.EmployeeService;

//@RestController 鏍囨敞绫讳笉浼氳瑙嗗浘瑙ｆ瀽鍣ㄨВ鏋�,鍙柟渚垮唴閮ㄦ墽琛�
//@RequestMapping("/hello") 鏍囨敞鍒扮被涓婁細澶氫竴灞傝矾寰�
@Controller
public class EmployeeController
{

	@Autowired
	@Qualifier("EmployeeServiceImpl")
	private EmployeeService employeeService;

	@RequestMapping("/employee")
	@ResponseBody
	public String employee() throws JsonProcessingException
	{
		List<Employee> list=employeeService.queryEmployee();

			ObjectMapper jsonMapper=new ObjectMapper();
			String result = jsonMapper.writeValueAsString(list);
			return result;
	}
	

	@RequestMapping("/update-employee")
	@ResponseBody
	public String employeeUpdate(@RequestParam("employeeData") String employeeData) throws JsonProcessingException
	{
		
		ObjectMapper jsonMapper=new ObjectMapper();
		Map<String,String> map = jsonMapper.readValue(employeeData,Map.class);
		
		int mun = employeeService.updateEmployee(map);
		List<Employee> list = employeeService.queryEmployee();
		
		String result = jsonMapper.writeValueAsString(list);
			return result;
	}

	@RequestMapping("/update-status")
	@ResponseBody
	public String statusUpdate(@RequestParam("data") String data) throws JsonProcessingException
	{
		
		ObjectMapper jsonMapper=new ObjectMapper();
		Map<String,String> map = jsonMapper.readValue(data,Map.class);
		
		int mun = employeeService.updateStatus(map);
		List<Employee> list = employeeService.queryEmployee();
		
		String result = jsonMapper.writeValueAsString(list);
			return result;
	}


}
