package com.softtech.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.Ofcfunction;

@Mapper
public interface MainMapper 
{
	List<Ofcfunction> queryOfcfunction(@Param("authority") String authority);
}
