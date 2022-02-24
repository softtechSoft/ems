package com.softtech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.Transport;
import com.softtech.entity.WorkInfo;

@Mapper
public interface WorkDetailListMapper {

	List<WorkInfo> getWorkInfoDetail(String month);
	List<Transport> geTransportDetail(String month);
	List<Transport> getWorkTransport(String employeeID,String fromMonth,String toMonth);
}
