package com.softtech.mapper;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.softtech.entity.WorkInfo;


@Mapper
public interface WorkInfoMapper 
{
	int uploadWorkInfo(Map<String,String>map);
	String queryWorkinfo(String employeeID);
}
