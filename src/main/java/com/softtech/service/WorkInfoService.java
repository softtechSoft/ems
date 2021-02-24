package com.softtech.service;

import java.util.Map;

public interface WorkInfoService {
	int insertWorkInfo(Map<String, String> map);

	String queryWorkinfo(String employeeID);
}
