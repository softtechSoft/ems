package com.softtech.service;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.mapper.WorkInfoMapper;

@Service
public class WorkInfoServiceImpl implements WorkInfoService
{
	@Autowired
	private WorkInfoMapper workinfoMapper;
	
	@Override
	public int uploadWorkInfo(Map<String, String> map) 
	{
		return workinfoMapper.uploadWorkInfo(map);
	}

	public WorkInfoMapper getWorkinfoMapper() 
	{
		return workinfoMapper;
	}

	public void setWorkinfoMapper(WorkInfoMapper workinfoMapper) 
	{
		this.workinfoMapper = workinfoMapper;
	}

}
