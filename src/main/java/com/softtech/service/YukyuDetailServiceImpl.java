package com.softtech.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.YukyuDetailFormBean;
import com.softtech.mapper.YukyuDetailMapper;
import com.softtech.util.DateUtil;

@Service
public class YukyuDetailServiceImpl implements YukyuDetailService {
	@Autowired
	YukyuDetailMapper yukyuDetailMapper ;
	@Override
	public List<YukyuDetailFormBean> findAll() {
		return yukyuDetailMapper.findAll();
	}

	@Override
	public List<YukyuDetailFormBean> queryYukyuDetail(Map<String, String> map) {
		return yukyuDetailMapper.queryYukyuDetail(map);
	}


	@Override
	public YukyuDetailFormBean findYukyuDetail(String employeeID,String nendo) {
		//
		return yukyuDetailMapper.findYukyuDetail(employeeID,nendo);
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
	public int updateYukyuDetail(Map<String, String> map) {
		// TODO 自動生成されたメソッド・スタブ
		return yukyuDetailMapper.updateYukyuDetail(map);
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
	public Map<String, String> transferUIToPara(YukyuDetailFormBean yukyuDetail) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("employeeID", yukyuDetail.getEmployeeID());
		map.put("nendo", yukyuDetail.getNendo());
		map.put("totalDay", yukyuDetail.getTotalDay());
		map.put("usedDay", yukyuDetail.getUsedDay());
		map.put("insertDate", DateUtil.changeYMToDate(yukyuDetail.getInsertDate()));


		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cl = Calendar.getInstance();
		String str = sdFormat.format(cl.getTime());
		map.put("updateDate", str);

		return map;
	}

	@Override
	public YukyuDetailFormBean findIDnendo(Map<String, String> map) {
		return yukyuDetailMapper.findIDnendo(map);
	}

	@Override
	public YukyuDetailFormBean findEmployeeID(String employeeID) {
		YukyuDetailFormBean yukyu = yukyuDetailMapper.findEmployeeID(employeeID);
		return yukyu;
	}

	/*
	 * 機能：画面再表示の設定
	 *
	 * @param yukyuDetail　画面データ
	 * @return 画面データ
	 *
	 * @author 開発@ソフトテク
	 */
	@Override
	public YukyuDetailFormBean resetToUI(YukyuDetailFormBean yukyuDetail) {
		// TODO 自動生成されたメソッド・スタブ

		//最終更新日
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		String str = sdFormat.format(cl.getTime());
		yukyuDetail.setUpdateDate(str);


		return yukyuDetail;
	}

	@Override
	public Map<String, String> getIdAndNendoForPara(String employeeID,String nendo) {

	    // DB検索（自分社員IDを取得と現在の年度を取得）、有給管理テーブルからデータを取得
	    Map<String, String> sqlParam = new HashMap<>();
	    sqlParam.put("nendo", nendo);
	    sqlParam.put("employeeID", employeeID);

	    return sqlParam;
	}

	@Override
	public YukyuDetailFormBean transferDbToUI(YukyuDetailFormBean yukyuDetailep) {
		YukyuDetailFormBean yukyuDetailFormBean = new YukyuDetailFormBean();
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
