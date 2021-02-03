package com.softtech.mapper;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkInfoMapper 
{
	int uploadWorkInfo(Map<String,String>map);
}
