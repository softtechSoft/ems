package com.softtech.service;

import java.util.List;
import java.util.Map;

import com.softtech.entity.Employee;

public interface EmployeeService {
	List<Employee> queryEmployee();

	Employee queryEmployee(String email);
	//　ログイン
	Employee login(Employee em);

	int updatePassword(Map<String, String> map);

	int updateEmployee(Map<String, String> map);

	int updateStatus(Map<String, String> map);
}
