package com.softtech.service;

import java.util.List;
import java.util.Map;

import com.softtech.actionForm.YukyuDetail;

/**
 * 概要：有給情報変更サービス
 *
 * 作成者：開発@ソフトテク
 * 作成日：2024/４/12
 */
public interface YukyuDetailService{

	List<YukyuDetail> findAll();

	List<YukyuDetail> queryYukyuDetail(Map<String, String> map);

	YukyuDetail queryYukyuDetail(String employeeID);



	Map<String, String> transferUIToPara(YukyuDetail yukyuDetail);

	YukyuDetail resetToUI(YukyuDetail yukyuDetail);

	int updateYukyuDetail(Map<String, String> map);




}
