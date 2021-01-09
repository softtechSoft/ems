package com.softtech.mapper;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.SalaryInfo;

@Mapper
public interface SalaryInfoMapper

{
	SalaryInfo querySalaryInfo(Map<String,String> map);
}
