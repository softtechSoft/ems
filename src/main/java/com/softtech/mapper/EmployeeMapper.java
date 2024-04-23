package com.softtech.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.actionForm.EmployeeEditFormBean;
import com.softtech.entity.Employee;

@Mapper
public interface EmployeeMapper {
	List<Employee> queryEmployee();

	Employee queryEmployee(@Param("email") String email);

	Employee queryEmployeeAll(@Param("employeeID") String employeeID);

	// ログイン
	Employee login(Employee em);

	int updatePassword(Map<String, String> map);

	int updateEmployee(Map<String, String> map);

	int updateEmployeeAll(Map<String, String> map);

	int updateStatus(Map<String, String> map);

	void update(EmployeeEditFormBean employeeEditBean);


}
