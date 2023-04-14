package com.softtech.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.EmployeepType;
/**
 * 概要：社員タイプリスト取得
 *
 * 作成者：ヤダナー＠ソフトテク
 * 作成日：2023/04/13
 */
@Mapper
public interface EptypeMapper {

	/**
	 * 機能：DBから社員タイプリストを取得する。
	 *
	 * @return 社員タイプリスト
	 *
	 * @author ヤダナー@ソフトテク
	 */
	ArrayList<EmployeepType> getEptypeInfoList();

}
