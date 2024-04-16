package com.softtech.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.actionForm.YukyuDetail;

@Mapper
public interface YukyuDetailMapper {

	 List<YukyuDetail> findAll();

	 List<YukyuDetail> queryYukyuDetail(Map<String, String> map);

	 YukyuDetail queryYukyuDetail(String employeeID);

	int updateYukyuDetail(Map<String, String> map);



}
