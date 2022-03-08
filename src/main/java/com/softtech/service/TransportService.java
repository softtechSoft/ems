package com.softtech.service;

import java.util.List;
import java.util.Map;

import com.softtech.entity.Transport;

public interface TransportService {
	List<Transport> queryAllTransport();
	//検索と提出
	int insertTransport(Map<String, String> map);

	Transport queryTransport(Map<String, String> map);

	//更新と修正
	int updateTransport(Map<String,String> Map);
}