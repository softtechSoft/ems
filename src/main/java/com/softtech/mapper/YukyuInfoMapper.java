package com.softtech.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.actionForm.YukyuInfoFormBean;
import com.softtech.entity.YukyuInfo;

@Mapper
public interface YukyuInfoMapper {

	YukyuInfo findIDnendo(Map<String, String> map);

	YukyuInfo findIDnendo1(YukyuInfoFormBean yukyuDetailFormBean);

	int update(Map<String, String> map);

	boolean update1(YukyuInfo yukyuInfo);

}
