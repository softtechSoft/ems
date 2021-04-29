package com.softtech.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.entity.Transport;
import com.softtech.mapper.TransportMapper;

@Service
public class TransportServiceImpl implements TransportService { 
	@Autowired
	private TransportMapper transportMapper;

	public TransportMapper getTransportMapper() {
		return transportMapper;
	}

	public void setTransportMapper(TransportMapper transportMapper) {
		this.transportMapper = transportMapper;
	}

	@Override
	public List<Transport> queryAllTransport() {

		return transportMapper.queryAllTransport();
	}

	@Override
	public Transport queryTransport(Map<String, String> map) {

		return transportMapper.queryTransport(map);
	}

	@Override
	public int insertTransport(Map<String, String> map) {

		return transportMapper.insertTransport(map);
	}

}
