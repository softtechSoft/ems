package com.softtech.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.YukyuDetail;
import com.softtech.mapper.YukyuDetailMapper;

@Service
public class YukyuDetailServiceImpl implements YukyuDetailService {
	@Autowired
	YukyuDetailMapper yukyuDetailMapper ;
	@Override
	public List<YukyuDetail> findAll() {
		return yukyuDetailMapper.findAll();
	}

	@Override
	public List<YukyuDetail> queryYukyuDetail(Map<String, String> map) {
		return yukyuDetailMapper.queryYukyuDetail(map);
	}


	@Override
	public YukyuDetail queryYukyuDetail(String employeeID) {
		//
		return yukyuDetailMapper.queryYukyuDetail(employeeID);
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
	public Map<String, String> transferUIToPara(YukyuDetail yukyuDetail) {
		// TODO 自動生成されたメソッド・スタブ
		Map<String, String> map = new HashMap<String, String>();

		map.put("employeeID", yukyuDetail.getEmployeeID());
		map.put("nendo", yukyuDetail.getNendo());
		map.put("totalDay", yukyuDetail.getTotalDay());
		map.put("usedDay", yukyuDetail.getUsedDay());
		map.put("insertDate", yukyuDetail.getInsertDate());
		map.put("updateDate", yukyuDetail.getUpdateDate());


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
	@Override
	public YukyuDetail resetToUI(YukyuDetail yukyuDetail) {
		// TODO 自動生成されたメソッド・スタブ

		//最終更新日
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd");
		String str = sdFormat.format(cl.getTime());
		yukyuDetail.setUpdateDate(str);


		return yukyuDetail;
	}

}
