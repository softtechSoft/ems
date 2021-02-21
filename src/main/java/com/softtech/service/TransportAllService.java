package com.softtech.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.Transport;

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
	public Transport doTransport(HttpServletRequest request,HttpSession session,MultipartFile file,Model model){

		Transport transport = new Transport();
		//メッセージ一覧
		model.addAttribute("state", "007");

		 // ファイルアップロード
		 if (!file.isEmpty()){
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
				}

		 	// セッションからログインIDを取得する。
			Map<String,String> mapper = new HashMap();
			mapper.put("employeeID",(String) session.getAttribute("userEmoplyeeID"));

			//パラメータ取得
			Map<String,String[]> map = request.getParameterMap();
			for(Map.Entry<String, String[]> entry : map.entrySet()){
				//開始日、終了日を変換。YYYY-MM-DD形をYYYYMMDD形に変換
				if(entry.getKey().equals("workStartDay")||entry.getKey().equals("workEndDay")){
					mapper.put(entry.getKey(),entry.getValue()[0].replace("-",""));
					continue;
				}
				//稼働月と定期券開始日を変換。YYYY/MM形をYYYYMM形に変換。
				if(entry.getKey().equals("workMonth")||entry.getKey().equals("startDate")){
					mapper.put(entry.getKey(),entry.getValue()[0].replace("/",""));
					continue;
				}
				mapper.put(entry.getKey(), entry.getValue()[0]);
			}

			// 勤怠テーブルに新規追加
			try{
				int upWork = workinfoService.uploadWorkInfo(mapper);
				if(upWork > 0){
					model.addAttribute("uploadInfo", "111");
				}else if(upWork != 0){
					model.addAttribute("uploadInfo", "002");
					}
				}catch(Exception e){
					model.addAttribute("uploadInfo", "001");
			}

			// ①画面上のチェックボックスの値により処理
			// 定期券変更がある場合、画面データを交通費テーブルへ新規追加。
			// 定期券変更がない場合、前月分の定期券情報を交通費テーブルへ新規追加。
			// 交通費テーブルに新規追加
			try{
				int uptransport = transportService.uploadTransport(mapper);
				if(uptransport > 0){
					model.addAttribute("upTransportInfo", "111");
				}else if(uptransport != 0){
					model.addAttribute("uptransport", "002");
					}
				}catch(Exception e){
					model.addAttribute("upTransportInfo", "001");
				}

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

