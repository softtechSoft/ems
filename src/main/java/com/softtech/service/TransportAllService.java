package com.softtech.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public Transport doTransport(HttpServletRequest request, HttpSession session, MultipartFile file,Map<String, String> mapper, Model model) throws Exception {

		Transport transport = new Transport();

		//メッセージ一覧
		model.addAttribute("state", "007");

		// ファイルアップロード
		FileUtil fileUtil = new FileUtil();
		if (!fileUtil.uploadFile(file)) {
			throw new Exception("ファイルアップロード失敗。");
		}

		// 勤怠テーブルに新規追加
		try {
			int upWork = workinfoService.insertWorkInfo(mapper);
			if (upWork != 1 || upWork > 0) {
				//アプロードが成功しました
				model.addAttribute("uploadInfo", "111");
			}
		} catch (Exception e) {
			//対象稼働月は既に入力です
			model.addAttribute("uploadInfo", "001");
		}

		// ①画面上のチェックボックスの値により処理
		// 定期券変更がある場合、画面データを交通費テーブルへ新規追加。
		// 定期券変更がない場合、前月分の定期券情報を交通費テーブルへ新規追加。
		// 交通費テーブルに新規追加
		String box = mapper.get("teiki");
		try {
//		if ("1".equals(box)) {
//			int uptransport = transportService.insertTransport(mapper);
//			}else if(!"on".equals(box)){
			int uptransport = transportService.insertTransport(mapper);
			if((uptransport != 1 || uptransport > 0)) {
				//アプロードが成功しました
				model.addAttribute("upTransportInfo", "111");
				}
//			}
		}	catch (Exception e) {
			//対象稼働月は既に入力です
			model.addAttribute("upTransportInfo", "001");
			}

		// 交通情報を取得し戻る
		Map<String, String> transportMapper = new HashMap();
		transportMapper.put("employeeID", mapper.get("employeeID"));
		transport = transportService.queryTransport(transportMapper);
		if (transport == null) {
			transport = new Transport();
		}
		return transport;
	}
}
