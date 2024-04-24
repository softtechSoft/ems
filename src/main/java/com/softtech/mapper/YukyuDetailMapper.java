package com.softtech.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.actionForm.YukyuDetailFormBean;

@Mapper
public interface YukyuDetailMapper {

	 List<YukyuDetailFormBean> findAll();

	 List<YukyuDetailFormBean> queryYukyuDetail(Map<String, String> map);

	// YukyuDetailFormBean queryYukyuDetail(String employeeID,String nendo);

	int updateYukyuDetail(Map<String, String> map);

	YukyuDetailFormBean findYukyuDetail(@Param("employeeID")String employeeID,@Param("nendo")String nendo);

	YukyuDetailFormBean findIDnendo(Map<String, String> map);

	YukyuDetailFormBean findEmployeeID(String employeeID);



}
