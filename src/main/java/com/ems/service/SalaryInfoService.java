package com.ems.service;

import java.util.Map;

import com.ems.entity.SalaryInfo;

public interface SalaryInfoService
{

	SalaryInfo querySalaryInfo(Map<String, String> map);
}