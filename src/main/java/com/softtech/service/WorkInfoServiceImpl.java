package com.softtech.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softtech.entity.WorkInfo;
import com.softtech.mapper.WorkInfoMapper;

@Service
public class WorkInfoServiceImpl implements WorkInfoService {
	@Autowired
	private WorkInfoMapper workInfoMapper;

	@Transactional
	@Override
    public int insertWorkInfo(Map<String, String> map) {
        return workInfoMapper.insertWorkInfo(map);
    }

    @Override
    public WorkInfo queryWorkInfo(Map<String, String> map) {
        return workInfoMapper.queryWorkInfo(map);
    }

    @Override
    public int updateWorkInfo(Map<String, String> map) {
        return workInfoMapper.updateWorkInfo(map);
    }

}
