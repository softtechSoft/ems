package com.softtech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.SalaryInfo;


@Mapper
public interface salarylistMapper {

	List<SalaryInfo> getsalaryinfolist(String month);

}
