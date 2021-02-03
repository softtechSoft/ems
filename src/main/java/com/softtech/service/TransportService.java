package com.softtech.service;

import java.util.List;
import java.util.Map;
import com.sun.jdi.connect.Transport;


public interface TransportService
{
	List<Transport> queryAllTransport();
	int uploadTransport(Map<String,String>map);
}