package com.softtech.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.Transport;
import com.softtech.util.FileUtil;

/**
 * 概要：稼働管理サービス
 *
 * 作成者：楊@ソフトテク
 * 作成日：2021/2/17
 */

@Service
public class TransportAllService
{
	@Autowired
	private TransportService transportService;
	@Autowired
	private WorkInfoService workinfoService;

	/**
	 * 機能：①勤怠テーブル、交通費テーブルに新規追加する。
	 * 　　　②画面情報を戻す
	 *
	 * @param file アップロードファイル
	 * @param mapper 新規追加内容
	 * @return 交通情報
	 * @exception DB操作例外
	 *
	 * @author 楊@ソフトテク
	 */
	public Transport doTransport(MultipartFile file, Map<String,String> mapper) throws Exception{

		Transport transport = new Transport();

		// ファイルアップロード
		FileUtil fileUtil = new FileUtil();
		if(!fileUtil.uploadFile(file)) {
			//アップロード失敗の場合、例外で処理終了。
			throw new Exception("ファイルアップロード失敗。");
		}

		// 勤怠テーブルに新規追加
		int rtn = workinfoService.uploadWorkInfo(mapper);
		if(rtn != 1) {
			//アップロード失敗の場合、例外で処理終了。
			throw new Exception("勤怠テーブルに新規追加失敗。");
		}
		// TODO:①画面上のチェックボックスの値により処理
		// 定期券変更がある場合、画面データを交通費テーブルへ新規追加。
		// 定期券変更がない場合、前月分の定期券情報を交通費テーブルへ新規追加。
		// 交通費テーブルに新規追加
		transportService.uploadTransport(mapper);

		// 交通情報を取得し戻る
		Map<String,String> transportMapper = new HashMap();
		transportMapper.put("employeeID",mapper.get("employeeID"));
		transport = transportService.queryTransport(transportMapper);
		if(transport == null ) {
			transport = new Transport();
		}
		return transport;
	}

}
