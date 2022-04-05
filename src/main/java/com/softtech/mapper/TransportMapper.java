package com.softtech.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.Transport;

@Mapper
public interface TransportMapper {

	List<Transport> queryAllTransport();
	//検索と提出
	int insertTransport(Map<String, String> map);

	Transport queryTransport(Map<String, String> map);
	//更新
	int updateTransport(Map<String,String> Map);


}

