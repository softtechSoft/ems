package com.softtech.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.YukyuInfoFormBean;
import com.softtech.entity.YukyuInfo;
import com.softtech.mapper.YukyuInfoMapper;
import com.softtech.util.DateUtil;

@Service
public class YukyuInfoServiceImpl implements YukyuInfoService {
	@Autowired
	YukyuInfoMapper yukyuDetailMapper ;
	/*
	 * 機能：初期化表示
	 *
	 * @param employee　DBデータ
	 * @return 更新行数　1:成功
	 *
	 * @author 開発@ソフトテク
	 */
	@Override
	public YukyuInfoFormBean findIDnendo(Map<String, String> map) {
		YukyuInfo yukyuInfo = yukyuDetailMapper.findIDnendo(map);
		YukyuInfoFormBean yukyuDetailFormBean = new YukyuInfoFormBean();
		yukyuDetailFormBean.setEmployeeID(yukyuInfo.getEmployeeID());
		yukyuDetailFormBean.setNendo(yukyuInfo.getNendo());
		yukyuDetailFormBean.setTotalDay(yukyuInfo.getTotalDay());
		yukyuDetailFormBean.setUsedDay(yukyuInfo.getUsedDay());
		yukyuDetailFormBean.setInsertDate(DateUtil.chgYMDToDate(yukyuInfo.getInsertDate()));
		yukyuDetailFormBean.setUpdateDate(DateUtil.chgYMDToDate(yukyuInfo.getUpdateDate()));

		return yukyuDetailFormBean;
	}


	//
	@Override
	public YukyuInfoFormBean findIDnendo1(YukyuInfoFormBean DetailForm) {
		YukyuInfo yukyuInfo = yukyuDetailMapper.findIDnendo1(DetailForm);
		YukyuInfoFormBean yukyuDetailFormBean = new YukyuInfoFormBean();
		yukyuDetailFormBean.setEmployeeID(yukyuInfo.getEmployeeID());
		yukyuDetailFormBean.setNendo(yukyuInfo.getNendo());
		yukyuDetailFormBean.setTotalDay(yukyuInfo.getTotalDay());
		yukyuDetailFormBean.setUsedDay(yukyuInfo.getUsedDay());
		yukyuDetailFormBean.setInsertDate(DateUtil.chgYMDToDate(yukyuInfo.getInsertDate()));
		yukyuDetailFormBean.setUpdateDate(DateUtil.chgYMDToDate(yukyuInfo.getUpdateDate()));

		return yukyuDetailFormBean;
	}


	/*
	 * 機能：DB更新
	 *
	 * @param employee　DBデータ
	 * @return 更新行数　1:成功
	 *
	 * @author 開発@ソフトテク
	 */
	@Override
	public int update(Map<String, String> map) {

		return yukyuDetailMapper.update(map);
	}

	//
	@Override
	public boolean update1(YukyuInfoFormBean yukyuDetailFormBean) {
		YukyuInfo yukyuInfo = new YukyuInfo();
		yukyuInfo.setEmployeeID(yukyuDetailFormBean.getEmployeeID());
		yukyuInfo.setNendo(yukyuDetailFormBean.getNendo());
		yukyuInfo.setTotalDay(yukyuDetailFormBean.getTotalDay());
		yukyuInfo.setUsedDay(yukyuDetailFormBean.getUsedDay());
		yukyuInfo.setInsertDate(DateUtil.chgYMDToDateGyaku(yukyuDetailFormBean.getInsertDate()));
		yukyuInfo.setUpdateDate(DateUtil.chgYMDToDateGyaku(yukyuDetailFormBean.getUpdateDate()));

		yukyuDetailMapper.update1(yukyuInfo);
		return true;
	}



	/*
	 * 機能：画面要データをMapに設定
	 *
	 * @param yukyuDetail　画面データ
	 * @return Map
	 *
	 * @author 開発@ソフトテク
	 */
	@Override
	public Map<String, String> transferUIToMap(YukyuInfoFormBean yukyuDetail) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("employeeID", yukyuDetail.getEmployeeID());
		map.put("nendo", yukyuDetail.getNendo());
		map.put("totalDay", yukyuDetail.getTotalDay());
		map.put("usedDay", yukyuDetail.getUsedDay());
		map.put("insertDate", yukyuDetail.getInsertDate());


		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cl = Calendar.getInstance();
		String str = sdFormat.format(cl.getTime());
		map.put("updateDate", str);

		return map;
	}


	/*
	 * 機能：画面再表示の設定
	 *
	 * @param yukyuDetail　画面データ
	 * @return 画面データ
	 *
	 * @author 開発@ソフトテク
	 */
//	@Override
//	public YukyuDetailFormBean resetToUI(YukyuDetailFormBean yukyuDetail) {
//		// TODO 自動生成されたメソッド・スタブ
//
//		//最終更新日
//		Calendar cl = Calendar.getInstance();
//		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
//		String str = sdFormat.format(cl.getTime());
//		yukyuDetail.setUpdateDate(str);
//
//
//		return yukyuDetail;
//	}

	@Override
	public Map<String, String> getIdAndNendoForPara(String employeeID,String nendo) {

	    // DB検索（自分社員IDを取得と現在の年度を取得）、有給管理テーブルからデータを取得
	    Map<String, String> sqlParam = new HashMap<>();
	    sqlParam.put("nendo", nendo);
	    sqlParam.put("employeeID", employeeID);

	    return sqlParam;
	}

	@Override
	public YukyuInfoFormBean transferDbToUI(YukyuInfoFormBean yukyuDetailep) {
		YukyuInfoFormBean yukyuDetailFormBean = new YukyuInfoFormBean();
		//社員ID
		yukyuDetailFormBean.setEmployeeID(yukyuDetailep.getEmployeeID());
		//年度
		yukyuDetailFormBean.setNendo(yukyuDetailep.getNendo());
		//総日数
		yukyuDetailFormBean.setTotalDay(yukyuDetailep.getTotalDay());
		//消化日数
		yukyuDetailFormBean.setUsedDay(yukyuDetailep.getUsedDay());
		//作成日
//		String dateStr = yukyuDetailep.getInsertDate();
//		if (dateStr != null && dateStr.length() == 8) {
//			String insertDate = dateStr.substring(0, 4) + "/" + dateStr.substring(4, 6) + "/" + dateStr.substring(6);
//			yukyuDetailFormBean.setInsertDate(insertDate);
//		} else {
//        	String insertDate =dateStr;
//        	yukyuDetailFormBean.setInsertDate(insertDate);
//            // 不正な形式の場合はそのまま返す
//        }
		String insertDate = yukyuDetailep.getInsertDate().substring(0, 4) + "/" + yukyuDetailep.getInsertDate().substring(4, 6) + "/"
				+ yukyuDetailep.getInsertDate().substring(6);
		yukyuDetailFormBean.setInsertDate(insertDate);

//		yukyuDetailFormBean.setInsertDate(yukyuDetailep.getInsertDate());
		//更新日
		String upDate = yukyuDetailep.getUpdateDate().substring(0, 4) + "/" + yukyuDetailep.getUpdateDate().substring(4, 6) + "/"
				+ yukyuDetailep.getUpdateDate().substring(6);
		yukyuDetailFormBean.setUpdateDate(upDate);
//		yukyuDetailFormBean.setUpdateDate(yukyuDetailep.getUpdateDate());

		return yukyuDetailFormBean;
	}





}
