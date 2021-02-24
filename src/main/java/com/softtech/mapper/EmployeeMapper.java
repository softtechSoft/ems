package com.softtech.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.Employee;

@Mapper
public interface EmployeeMapper {
	List<Employee> queryEmployee();

	Employee queryEmployee(@Param("email") String email);

	int updatePassword(Map<String, String> map);

	int updateEmployee(Map<String, String> map);

	int updateStatus(Map<String, String> map);
}
