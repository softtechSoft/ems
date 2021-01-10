package com.softtech.service;

import java.util.List;
import java.util.Map;

import com.softtech.entity.SalaryInfo;
import com.softtech.entity.SalaryInfoComment;

public interface SalaryInfoService
{
	List<SalaryInfo> queryAllSalaryInfo();
	int updateSalaryInfo(Map<String,String> map);
	SalaryInfo uploadSalaryInfo(String slry);
	List<SalaryInfoComment> querySalaryInfoComment();
	SalaryInfo querySalaryInfo(Map<String,String> map);
}