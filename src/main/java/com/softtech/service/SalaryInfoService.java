package com.softtech.service;

import java.util.Map;

import com.softtech.entity.SalaryInfo;

public interface SalaryInfoService
{

	SalaryInfo querySalaryInfo(Map<String, String> map);
}