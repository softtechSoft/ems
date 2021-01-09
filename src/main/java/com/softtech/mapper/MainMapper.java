package com.softtech.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.softtech.entity.Ofcfunction;

@Mapper
public interface MainMapper 
{
	List<Ofcfunction> queryOfcfunction(String EmployeeID);
}
