package com.ems.service;


import java.util.List;
import java.util.Map;

import com.itsofttech.pojo.Employee;

public interface EmployeeService 
{
	List<Employee> queryEmployee();
	Employee queryEmployee(String email);
	int updatePassword(Map<String,String> map);
	int updateEmployee(Map<String,String>map);
	int updateStatus(Map<String,String>map);
}
