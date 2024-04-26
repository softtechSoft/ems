package com.softtech.service;

import java.util.Map;

import com.softtech.actionForm.YukyuInfoFormBean;

/**
 * 概要：有給情報変更サービス
 *
 * 作成者：開発@ソフトテク
 * 作成日：2024/４/12
 */
public interface YukyuInfoService{

	YukyuInfoFormBean findIDnendo(Map<String, String> map);

	YukyuInfoFormBean findIDnendo1(YukyuInfoFormBean yukyuDetailFormBean);

	int update(Map<String, String> map);

	boolean update1(YukyuInfoFormBean yukyuDetailFormBean);

	Map<String, String> getIdAndNendoForPara(String employeeID,String nendo);

	Map<String, String> transferUIToMap(YukyuInfoFormBean yukyuDetailFormBean);

	YukyuInfoFormBean transferDbToUI(YukyuInfoFormBean yukyuDetailFormBean);

}
