package com.softtech.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.mapper.WorkInfoMapper;

@Service
public class WorkInfoServiceImpl implements WorkInfoService {
	@Autowired
	private WorkInfoMapper workinfoMapper;

	@Override
	public int insertWorkInfo(Map<String, String> map) {
		return workinfoMapper.insertWorkInfo(map);
	}

	public WorkInfoMapper getWorkinfoMapper() {
		return workinfoMapper;
	}

	public void setWorkinfoMapper(WorkInfoMapper workinfoMapper) {
		this.workinfoMapper = workinfoMapper;
	}

	@Override
	public String queryWorkinfo(String employeeID) {

		return workinfoMapper.queryWorkinfo(employeeID);
	}

	@Override
	public int updateWorkInfo(Map<String, String> Map) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

}
