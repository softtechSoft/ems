package com.softtech.service;

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

}
