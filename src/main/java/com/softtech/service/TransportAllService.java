package com.softtech.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
	public Transport doTransport(MultipartFile file, Map<String,String> mapper,Model model) throws Exception{

		Transport transport = new Transport();

		// ファイルアップロード
		FileUtil fileUtil = new FileUtil();
		//画面メッセージ一覧
		model.addAttribute("state", "007");
		if(!fileUtil.uploadFile(file)) {
			try {
	            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("D:/"+file.getOriginalFilename())));
	            out.write(file.getBytes());
	            out.flush();
	            out.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            model.addAttribute("uploadFile", "001");
	        } catch (IOException e) {
	            e.printStackTrace();
	            model.addAttribute("uploadFile", "001");
	        }
			model.addAttribute("uploadFile", "111");
			//アップロード失敗の場合、例外で処理終了。
			//throw new Exception("ファイルアップロード失敗。");
		}

		// 勤怠テーブルに新規追加
		try {
			int rtn = workinfoService.uploadWorkInfo(mapper);
			if(rtn > 0){
				//アップロード失敗の場合、例外で処理終了。
				model.addAttribute("uploadInfo", "111");
				}
			}catch(Exception e){
				model.addAttribute("uploadInfo", "001");
			}//throw new Exception("勤怠テーブルに新規追加失敗。");

		try {
		// TODO:①画面上のチェックボックスの値により処理
		// 定期券変更がある場合、画面データを交通費テーブルへ新規追加。
		// 定期券変更がない場合、前月分の定期券情報を交通費テーブルへ新規追加。
		// 交通費テーブルに新規追加
			int trn = transportService.uploadTransport(mapper);
			if(trn > 0){
				model.addAttribute("upTransportInfo", "111");
				}
			}catch(Exception e){
				model.addAttribute("upTransportInfo", "001");
			}
			//throw new Exception("交通費テーブルに新規追加失敗。");



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
