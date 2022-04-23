package com.softtech.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.com.SelectJyokenCommon;
import com.softtech.entity.Contract;
import com.softtech.entity.Transport;
import com.softtech.entity.WorkInfo;
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
	@Autowired
	private ContractService contractService;

	//画面に入力したデータ
	Map<String, String> gamenParam = new HashMap<String, String>();

	/**
	 * 機能：勤怠情報を取得
	 *
	 * @param selectJyokenCommon　取得条件：社員ID、年月
	 * @return 勤怠情報
	 * @exception DB操作例外
	 *
	 * @author 郭@ソフトテク
	 */
	private WorkInfo getWorkInfo(SelectJyokenCommon selectJyokenCommon) throws Exception {

		return null;
	}

	/**
	 * 機能：勤怠情報存在チェック。
	 *
	 * @param selectJyokenCommon　取得条件：社員ID、年月
	 * @return 勤怠情報存在状態。TRUE:存在。FALSE：存在していない
	 *
	 * @author 郭@ソフトテク
	 * @throws Exception
	 */
	public boolean hasWorkInfo(SelectJyokenCommon selectJyokenCommon) throws Exception {
		// 勤怠情報取得
		WorkInfo workInfo = getWorkInfo(selectJyokenCommon);

		if(workInfo == null) return false;

		if(workInfo.getWorkTime() != null ) return true;

		return false;
	}
	/**
	 * 機能：①勤怠テーブル、交通費テーブルに新規追加する。
	 * 　　　②画面情報を戻す
	 *      ※アドバイス：①Page資源をサービス層に持ってこない。JUNITには難しい。例org.springframework.ui.Model
	 * @param file アップロードファイル
	 * @param mapper 新規追加内容
	 * @return 交通情報
	 * @exception DB操作例外
	 *
	 * @author 楊@ソフトテク
	 */
	public Transport doTransport(MultipartFile file,Map<String, String> mapper, Model model) throws Exception {

		//画面に入力したデータ
		gamenParam.putAll(mapper);

		Transport transport = new Transport();

		//stateは非NULL状態、メッセージを表示する。
		model.addAttribute("state", "1");

		// ファイルアップロード
		if (!uploadTimeReport(file)) {
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
		transport = getTransportInf(mapper);
		return transport;
	}


	//修正機能
	public Transport updateTransport(MultipartFile file,Map<String, String> mapper, Model model) throws Exception {

		//画面に入力したデータ
		gamenParam.putAll(mapper);

		Transport transport = new Transport();

		//stateは非NULL状態、メッセージを表示する。
		model.addAttribute("state", "1");

		// ファイルアップロード
		if (!uploadTimeReport(file)) {
			//ファイルのアップロードに失敗の場合、画面に下記メッセージを表示する。
			//アップロードは失敗しました
			model.addAttribute("uploadInfo", "001");
		}

		// 勤怠テーブルに修正
		try {
			int updateWork = workinfoService.updateWorkInfo(mapper);
			if (updateWork == 1) {
				//修正成功の場合、画面に下記メッセージを表示する。
				//修正が成功しました
				model.addAttribute("updateloadInfo", "123");
			}else {
				//修正は失敗の場合、画面に下記メッセージを表示する。
				//修正は失敗しました
				model.addAttribute("updateloadInfo", "012");
			}
		} catch (Exception e) {
			//追加失敗の場合、画面に下記メッセージを表示する。
			//ファイルアプロードは失敗しました
			model.addAttribute("uploadFile", "001");
		}

		// 交通費テーブル修正
		try {
			int updatetransport = transportService.updateTransport(mapper);
			if(updatetransport == 1 ) {
				//修正成功の場合、画面に下記メッセージを表示する。
				//修正が成功しました
				model.addAttribute("updateTransportInfo", "222");
			}else {
				//修正は失敗の場合、画面に下記メッセージを表示する。
				//修正は失敗しました
				model.addAttribute("updateTransportInfo", "003");
			}
		} catch (Exception e) {
			//追加失敗の場合、画面に下記メッセージを表示する。
			//ファイルアプロードは失敗しました
			model.addAttribute("uploadFile", "001");
		}

		// 交通情報を取得し戻る
		transport = getTransportInf(mapper);
		return transport;
	}

	/**
	 * 機能：交通費情報を取得する
	 *
	 * @param mapper 必要なパラメータ
	 * @return 交通情報
	 * @exception DB操作例外
	 *
	 * @author 楊@ソフトテク
	 */
	public Transport getTransportInf(Map<String, String> mapper) {
		Transport transport = new Transport();

		// 交通情報を取得し戻る
		//query
		Map<String, String> transportMapper = new HashMap<String, String>();
		transportMapper.put("employeeID", mapper.get("employeeID"));
		transportMapper.put("workMonth", mapper.get("workMonth"));
		transport = transportService.queryTransport(transportMapper);
		if (transport == null) {
			transport = new Transport();
		}
		return transport;

	}

	/**
	 * 機能：タイムレポートをアップロードする。
	 *
	 * @param mapper 必要なパラメータ
	 * @return 交通情報
	 * @exception DB操作例外
	 *
	 * @author ソフトテク@ソフトテク
	 */
	private boolean uploadTimeReport(MultipartFile mltFile) {

		//　契約テーブルからアップロードパスを取得する。
		String emplyID = gamenParam.get("employeeID");
		Contract contract = contractService.getContract(emplyID);

		if(contract==null) {
			return false;
		}

		FileUtil fileUtil = new FileUtil();
        //アップロードパスを作成
		String uploadPath = fileUtil.mkUploadPath(contract.getTimeReportPath(),contract.getCompanyName(),contract.getEmployeeName(),gamenParam.get("workMonth"));

		// ファイルアップロード
		if (!fileUtil.uploadFile(mltFile,uploadPath)) {
			return false;
		}

		return true;
	}
}
