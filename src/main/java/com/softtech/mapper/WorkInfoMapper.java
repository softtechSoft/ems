package com.softtech.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkInfoMapper {
	int insertWorkInfo(Map<String, String> map);

	String queryWorkinfo(String employeeID);
}
