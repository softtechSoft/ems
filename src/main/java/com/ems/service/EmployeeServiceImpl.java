package com.ems.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.mapper.EmployeeMapper;
import com.ems.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
	@Autowired
	private EmployeeMapper employeeMapper;
	
	
	@Override
	public Employee queryEmployee(String email) 
	{
		return employeeMapper.queryEmployee(email);
	}
	
	@Override
	public int updatePassword(Map<String, String> map) 
	{
		return employeeMapper.updatePassword(map);
	}
	
	public EmployeeMapper getEmployeeMapper() {
		return employeeMapper;
	}
	public void setEmployeeMapper(EmployeeMapper employeeMapper) {
		this.employeeMapper = employeeMapper;
	}

	@Override
	public List<Employee> queryEmployee() {
		return employeeMapper.queryEmployee();
	}

	@Override
	public int updateEmployee(Map<String,String> map) {
		
		return employeeMapper.updateEmployee(map);
	}

	@Override
	public int updateStatus(Map<String,String>map) {
		return employeeMapper.updateStatus(map);
	}
}
