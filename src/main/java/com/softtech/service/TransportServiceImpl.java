package com.softtech.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softtech.mapper.TransportMapper;
import com.sun.jdi.connect.Transport;


@Service
public class TransportServiceImpl implements TransportService
{
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
	public int uploadTransport(Map<String, String> map) {

		return transportMapper.uploadTransport(map);
	}


}
