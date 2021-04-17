package com.softtech.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.entity.SalaryInfo;
import com.softtech.mapper.salarylistMapper;
import com.softtech.util.DateUtil;
/**
 * 概要：給料リストのservice
 *
 * 作成者：王＠ソフトテク
 * 作成日：2021/04/13
 */
@Service
public class SalaryListServiceImpl implements SalaryListService{
	@Autowired
	salarylistMapper salarylistMapper;

	/**
	 * 機能：DBから取得したデータを取得する。
	 *
	 * @param lst DBから取得したデータ
	 * @return 給料情報リスト
	 *
	 * @author 王@ソフトテク
	 */

	@Override
	public List<SalaryInfo> querySalaryList(String month) {

        // YYYY/MM→yyyymmに変換
		String monthP = DateUtil.chgMonthToYM(month);

		// DBからデータを取得する
		// 給料情報を取得する
		List<SalaryInfo> salaryinfolist =  salarylistMapper.getsalaryinfolist(monthP);

		return  salaryinfolist;
	}

}
