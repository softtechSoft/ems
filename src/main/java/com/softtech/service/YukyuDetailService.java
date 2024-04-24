package com.softtech.service;

import java.util.List;
import java.util.Map;

import com.softtech.actionForm.YukyuDetailFormBean;

/**
 * 概要：有給情報変更サービス
 *
 * 作成者：開発@ソフトテク
 * 作成日：2024/４/12
 */
public interface YukyuDetailService{

	List<YukyuDetailFormBean> findAll();

	List<YukyuDetailFormBean> queryYukyuDetail(Map<String, String> map);

	YukyuDetailFormBean findYukyuDetail(String employeeID, String nendo);



	Map<String, String> transferUIToPara(YukyuDetailFormBean yukyuDetail);

//	Map<String, String> transferUIToIdNePara(YukyuDetailFormBean yukyuDetail);
	Map<String, String> getIdAndNendoForPara(String employeeID,String nendo);

	YukyuDetailFormBean resetToUI(YukyuDetailFormBean yukyuDetail);

	int updateYukyuDetail(Map<String, String> map);

	YukyuDetailFormBean findIDnendo(Map<String, String> map);

	YukyuDetailFormBean findEmployeeID(String employeeID);

	YukyuDetailFormBean transferDbToUI(YukyuDetailFormBean yukyuDetailep);




}
