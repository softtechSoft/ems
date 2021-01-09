package com.softtech.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.entity.SalaryInfo;
import com.softtech.mapper.SalaryInfoMapper;

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
