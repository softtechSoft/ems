package com.ems.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.itsofttech.pojo.Employee;

public interface EmployeeMapper
{
	//查询请求情报
	List<Employee> queryEmployee();
	Employee queryEmployee(@Param("email")String email);
	int updatePassword(Map<String,String> map);
	int updateEmployee(Map<String,String>map);
	int updateStatus(Map<String,String>map);
}
