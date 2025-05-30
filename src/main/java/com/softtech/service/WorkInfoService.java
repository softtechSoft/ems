package com.softtech.service;

import java.util.Map;

import com.softtech.entity.WorkInfo;

public interface WorkInfoService {
	//検索と提出
	int insertWorkInfo(Map<String, String> map);

	WorkInfo queryWorkInfo(Map<String, String> map);

	//更新と修正
	int updateWorkInfo(Map<String, String> map);
}
