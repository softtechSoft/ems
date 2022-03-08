package com.softtech.service;

import java.util.Map;

public interface WorkInfoService {
	//検索と提出
	int insertWorkInfo(Map<String, String> map);

	String queryWorkinfo(String employeeID);

	//更新と修正
	int updateWorkInfo(Map<String,String> Map);
}
