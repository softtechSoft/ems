package com.softtech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.com.SalaryCommon;
import com.softtech.entity.SalaryInfo;
/**
 * 概要：給料リスト取得
 *
 * 作成者：テー＠ソフトテク
 * 作成日：2022/02/22
 */
@Mapper
public interface SalaryListMapper {

	/**
	 * 機能：DBから給料リストを取得する。
	 *
	 * @param salaryCommon 取得要パラメータ
	 * @return 給料情報リスト
	 *
	 * @author テー@ソフトテク
	 */
	List<SalaryInfo> getSalaryInfoList(SalaryCommon salaryCommon);

}
