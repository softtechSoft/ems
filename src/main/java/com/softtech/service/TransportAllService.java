package com.softtech.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
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
public class TransportAllService {
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
	public Transport doTransport(MultipartFile file,Map<String, String> mapper, Model model) throws Exception {

		Transport transport = new Transport();

		//stateは非NULL状態、メッセージを表示する。
		model.addAttribute("state", "1");

		// ファイルアップロード
		FileUtil fileUtil = new FileUtil();
		if (!fileUtil.uploadFile(file)) {
			//ファイルのアップロードに失敗の場合、画面に下記メッセージを表示する。
			//アップロードは失敗しました
			model.addAttribute("uploadInfo", "001");
		}

		// 勤怠テーブルに新規追加
		try {
			int upWork = workinfoService.insertWorkInfo(mapper);
			if (upWork == 1) {
				//追加成功の場合、画面に下記メッセージを表示する。
				//登録が成功しました
				model.addAttribute("uploadInfo", "111");
			}else {
				//登録は失敗の場合、画面に下記メッセージを表示する。
				//登録は失敗しました
				model.addAttribute("uploadInfo", "002");
			}
		} catch (Exception e) {
			//追加失敗の場合、画面に下記メッセージを表示する。
			//対象稼働月は既に入力です
			model.addAttribute("uploadInfo", "001");
		}

		// 交通費テーブル登録
		try {
			int uptransport = transportService.insertTransport(mapper);
			if(uptransport == 1 ) {
				//追加成功の場合、画面に下記メッセージを表示する。
				//登録が成功しました
				model.addAttribute("upTransportInfo", "111");
			}else {
				//登録は失敗の場合、画面に下記メッセージを表示する。
				//登録は失敗しました
				model.addAttribute("uploadInfo", "002");
			}
		} catch (Exception e) {
			//追加失敗の場合、画面に下記メッセージを表示する。
			//対象稼働月は既に入力です。
			model.addAttribute("upTransportInfo", "001");
		}

		// 交通情報を取得し戻る
		Map<String, String> transportMapper = new HashMap<String, String>();
		transportMapper.put("employeeID", mapper.get("employeeID"));
		transport = transportService.queryTransport(transportMapper);
		if (transport == null) {
			transport = new Transport();
		}
		return transport;
	}
}
