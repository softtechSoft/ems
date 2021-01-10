package com.softtech.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.SalaryInfo;
import com.softtech.entity.SalaryInfoComment;

@Mapper
public interface SalaryInfoMapper

{
	List<SalaryInfo> queryAllSalaryInfo();
	int updateSalaryInfo(Map<String,String> map);
	SalaryInfo uploadSalaryInfo(String slry);
	List<SalaryInfoComment> querySalaryInfoComment();
	SalaryInfo querySalaryInfo(Map<String,String> map);
}
