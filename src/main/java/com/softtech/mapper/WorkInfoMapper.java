package com.softtech.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.WorkInfo;

@Mapper
public interface WorkInfoMapper {
	int insertWorkInfo(Map<String, String> map);

	WorkInfo queryWorkInfo(Map<String, String> map);

	int updateWorkInfo(Map<String, String> map);
}
