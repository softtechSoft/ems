package com.softtech.service;

import java.util.List;

import com.softtech.entity.SalaryInfo;

public interface SalaryListService {

	List<SalaryInfo> querySalaryList(String month);

}
