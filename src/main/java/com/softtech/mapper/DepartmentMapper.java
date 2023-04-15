package com.softtech.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.Department;

/**
 * 概要：部門リスト取得
 *
 * 作成者：ヤダナー＠ソフトテク
 * 作成日：2023/04/14
 */
@Mapper
public interface DepartmentMapper {

	/*
	 * 機能：DBから部門リストを取得する。
	 *
	 * @return 部門リスト
	 *
	 * @author ヤダナー@ソフトテク
	 */
	ArrayList<Department> getDepTypeInfoList();

}
