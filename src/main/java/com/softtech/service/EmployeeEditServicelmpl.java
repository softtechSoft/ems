package com.softtech.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.entity.Employee;
import com.softtech.mapper.EmployeeMapper;

@Service
public class EmployeeEditServicelmpl implements EmployeeEditService {
	@Autowired
	EmployeeMapper employeeMapper;

	@Override
	public Employee queryEmployeeAll(String employeeID) {
		return employeeMapper.queryEmployeeAll(employeeID);
	}

	@Override
	public int updateEmployeeAll(Map<String, String> map) {
		return employeeMapper.updateEmployeeAll(map);
	}

}
