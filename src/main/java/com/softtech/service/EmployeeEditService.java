package com.softtech.service;

import java.util.Map;

import com.softtech.entity.Employee;

public interface EmployeeEditService {

	Employee queryEmployeeAll(String employeeID);

	int updateEmployeeAll(Map<String, String> map);

}
