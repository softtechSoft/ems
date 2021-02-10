package com.softtech.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.sun.jdi.connect.Transport;

@Mapper
public interface TransportMapper 
{
	List<Transport> queryAllTransport();
	int uploadTransport(Map<String,String>map);
	Map<String, String> queryTransport(Map<String,String>map);
	
}
