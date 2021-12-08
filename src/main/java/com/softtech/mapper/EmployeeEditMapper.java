package com.softtech.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.Employee;

@Mapper
public interface EmployeeEditMapper{

	Employee queryEmployee(@Param("email") String email);
	Employee queryEmployeeAll(@Param("employeeName") String employeeName);

}

