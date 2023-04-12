package com.softtech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.com.EptypeInfo;
import com.softtech.entity.Employee;
/**
 * 概要：社員タイプリスト取得
 *
 * 作成者：テー＠ソフトテク
 * 作成日：2022/02/22
 */
@Mapper
public interface EptypeMapper {

	/**
	 * 機能：DBから社員タイプリストを取得する。
	 *
	 * @param eptypeInfo 取得要パラメータ
	 * @return 社員タイプリスト
	 *
	 * @author テー@ソフトテク
	 */
	List<Employee> getEmployeeList(EptypeInfo eptypeInfo);

}
