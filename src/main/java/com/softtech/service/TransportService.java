package com.softtech.service;

import java.util.List;
import java.util.Map;

import com.softtech.entity.Transport;

public interface TransportService {
	List<Transport> queryAllTransport();

	int insertTransport(Map<String, String> map);

	Transport queryTransport(Map<String, String> map);
}