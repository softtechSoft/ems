package com.ems.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.entity.SalaryInfo;
import com.ems.mapper.SalaryInfoMapper;

@Service
public class SalaryInfoServiceImpl implements SalaryInfoService
{
	@Autowired
	SalaryInfoMapper salaryInfoMapper;

	@Override
	public SalaryInfo querySalaryInfo(Map<String, String> map)
	{
		return salaryInfoMapper.querySalaryInfo(map);
	}



}
