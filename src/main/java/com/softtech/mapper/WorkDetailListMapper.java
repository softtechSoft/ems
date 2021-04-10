package com.softtech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.actionForm.WorkDetail;

@Mapper
public interface WorkDetailListMapper {

	List<WorkDetail> getWorkInfoDetail(String month);
	List<WorkDetail> geTransportDetail(String month);
}
