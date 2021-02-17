package com.softtech.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.Transport;

@Mapper
public interface TransportMapper
{
	List<Transport> queryAllTransport();
	int uploadTransport(Map<String,String>map);
	Transport queryTransport(Map<String,String>map);

}
