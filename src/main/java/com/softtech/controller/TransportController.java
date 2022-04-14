package com.softtech.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.Transport;
import com.softtech.service.TransportAllService;
import com.softtech.service.TransportService;

/**
 * 機能：勤怠管理コントロール
 *
 * @author 楊@ソフトテク
 *  * 作成日：2021/2/17
 */

@Controller
public class TransportController<WorkInfoComment> {
	@Autowired
	private TransportService transportService;

	@Autowired
	private TransportAllService transportAllService;


	/**
	 * 機能：対象年月が変更された場合の処理
	 * 　　　対象年月のデータをDBから取得し画面へ渡す
	 *
	 *@param request　リクエスト
	 * @param model　画面モデル
	 * @return
	 * @exception なし
	 * @author 孫@ソフトテク
	 */
	@RequestMapping("/changeMonth")
	public String changeMonth(HttpServletRequest request,HttpSession session,@RequestParam("file") MultipartFile file,Model model) throws Exception {

		// ②セッションからログインIDを取得する。
		Map<String, String> mapper = new HashMap<String, String>();
		mapper.put("employeeID", (String) session.getAttribute("userEmoplyeeID"));

		boolean flg = false;
		String workStartDay = "";
		// ①画面から、年月を取得

		//②年月、ログインIDを持っち、DBを検索する。（workinfo、transport）
		Map<String, String[]> map = request.getParameterMap();
		//③DBデータが存在する場合、修正できるように設定する（state=1:修正、0:新規登録(提出),9:エラー)

		Transport transport = new Transport();
		model.addAttribute("transport", transport);
		return "/ems/transport";


	}

	@RequestMapping("/transport-workinfo")
	//新規追加
	public String insertTransport(HttpServletRequest request, HttpSession session,@RequestParam("file") MultipartFile file, Model model) throws Exception {

		// セッションからログインIDを取得する。
		Map<String, String> mapper = new HashMap<String, String>();
		mapper.put("employeeID", (String) session.getAttribute("userEmoplyeeID"));

		boolean flg = false;
		String workStartDay = "";

		//パラメータ取得
		Map<String, String[]> map = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {

			//稼働開始日。YYYY-MM-DD形をYYYYMMDD形に変換
			if (entry.getKey().equals("workStartDay") ) {
				workStartDay = entry.getValue()[0].replace("-", "");
				mapper.put(entry.getKey(), workStartDay);
				continue;
			}

			//終了日を変換。YYYY-MM-DD形をYYYYMMDD形に変換
			if ( entry.getKey().equals("workEndDay")) {
				mapper.put(entry.getKey(), entry.getValue()[0].replace("-", ""));
				continue;
			}

			//稼働月を変換。YYYY/MM形をYYYYMM形に変換。
			if(entry.getKey().equals("workMonth")){
				mapper.put(entry.getKey(), entry.getValue()[0].replace("/", ""));
				continue;
			}

			//定期券開始日を変換。YYYYMM形に変換。
			if(entry.getKey().equals("startDate")){
				String startDate = entry.getValue()[0];
				startDate = startDate.replace("/", "");
				startDate = startDate.replace("-", "");
				mapper.put(entry.getKey(), startDate);
				continue;
			}

			//定期券チェックボックスがチェックされた場合
			if(entry.getKey().equals("teiki")) {
				String en = entry.getValue()[0];
				if( en != null && en.equals("1")) {
					flg = true;
					continue;
				}
			}
			mapper.put(entry.getKey(), entry.getValue()[0]);
		} // for

		// 定期券チェックボックスがチェックされない場合、テーブルから交通費を取得
		if(!flg) {
			Map<String, String> sportMapper = new HashMap<String, String>();
			//　交通情報取得
			sportMapper.put("employeeID", (String) session.getAttribute("userEmoplyeeID"));
			Transport transport = transportService.queryTransport(sportMapper);
			// 定期券開始日
			mapper.put("startDate", workStartDay);
			if(transport != null ) {
				//起点駅
				mapper.put("startStation", transport.getStartStation());
				//終点駅
				mapper.put("endStation", transport.getEndStation());
				//交通機関
				mapper.put("transportFacility", transport.getTransportFacility());
				//中間駅1
				mapper.put("midStation1", transport.getMidStation1());
				//交通機関1
				mapper.put("transportFacility1", transport.getTransportFacility1());
				//中間駅2
				mapper.put("midStation2", transport.getMidStation2());
				//中間駅3
				mapper.put("midStation3", transport.getMidStation3());
				//定期券金額(1ヶ月)
				mapper.put("transportExpense1", transport.getTransportExpense());
			} else {
				//起点駅
				mapper.put("startStation", "無し");
				//終点駅
				mapper.put("endStation", "無し");
				//交通機関
				mapper.put("transportFacility", "無し");
				//中間駅1
				mapper.put("midStation1", "無し");
				//交通機関1
				mapper.put("transportFacility1", "無し");
				//中間駅2
				mapper.put("midStation2", "無し");
				//中間駅3
				mapper.put("midStation3", "無し");
				//定期券金額(1ヶ月)
				mapper.put("transportExpense1", "0");
			}
			//出張交通費
			mapper.put("businessTrip", "0");
		}

		//勤怠追加処理
		Transport transport = new Transport();

		transport = transportAllService.doTransport(file, mapper, model);
		model.addAttribute("transport", transport);
		return "/ems/transport";
	}


	//修正
	@RequestMapping("/transport-update")
		public String updateTransport(HttpServletRequest request, HttpSession session,@RequestParam("file") MultipartFile file, Model model) throws Exception {

			// セッションからログインIDを取得する。
			Map<String, String> mapper = new HashMap<String, String>();
			mapper.put("employeeID", (String) session.getAttribute("userEmoplyeeID"));

			boolean flg = false;
			String workStartDay = "";

			//パラメータ取得
			Map<String, String[]> map = request.getParameterMap();
			for (Map.Entry<String, String[]> entry : map.entrySet()) {

				//稼働開始日。YYYY-MM-DD形をYYYYMMDD形に変換
				if (entry.getKey().equals("workStartDay") ) {
					workStartDay = entry.getValue()[0].replace("-", "");
					mapper.put(entry.getKey(), workStartDay);
					continue;
				}

				//終了日を変換。YYYY-MM-DD形をYYYYMMDD形に変換
				if ( entry.getKey().equals("workEndDay")) {
					mapper.put(entry.getKey(), entry.getValue()[0].replace("-", ""));
					continue;
				}

				//稼働月を変換。YYYY/MM形をYYYYMM形に変換。
				if(entry.getKey().equals("workMonth")){
					mapper.put(entry.getKey(), entry.getValue()[0].replace("/", ""));
					continue;
				}

				//定期券開始日を変換。YYYYMM形に変換。
				if(entry.getKey().equals("startDate")){
					String startDate = entry.getValue()[0];
					startDate = startDate.replace("/", "");
					startDate = startDate.replace("-", "");
					mapper.put(entry.getKey(), startDate);
					continue;
				}

				//定期券チェックボックスがチェックされた場合
				if(entry.getKey().equals("teiki")) {
					String en = entry.getValue()[0];
					if( en != null && en.equals("1")) {
						flg = true;
						continue;
					}
				}
				mapper.put(entry.getKey(), entry.getValue()[0]);
			} // forのEND

			// 定期券チェックボックスがチェックされない場合、テーブルから交通費を取得
			if(!flg) {
				Map<String, String> sportMapper = new HashMap<String, String>();
				//　交通情報取得
				sportMapper.put("employeeID", (String) session.getAttribute("userEmoplyeeID"));
				Transport transport = transportService.queryTransport(sportMapper);
				// 定期券開始日
				mapper.put("startDate", workStartDay);
				if(transport != null ) {
					//起点駅
					mapper.put("startStation", transport.getStartStation());
					//終点駅
					mapper.put("endStation", transport.getEndStation());
					//交通機関
					mapper.put("transportFacility", transport.getTransportFacility());
					//中間駅1
					mapper.put("midStation1", transport.getMidStation1());
					//交通機関1
					mapper.put("transportFacility1", transport.getTransportFacility1());
					//中間駅2
					mapper.put("midStation2", transport.getMidStation2());
					//中間駅3
					mapper.put("midStation3", transport.getMidStation3());
					//定期券金額(1ヶ月)
					mapper.put("transportExpense1", transport.getTransportExpense());
				} else {
					//起点駅
					mapper.put("startStation", "無し");
					//終点駅
					mapper.put("endStation", "無し");
					//交通機関
					mapper.put("transportFacility", "無し");
					//中間駅1
					mapper.put("midStation1", "無し");
					//交通機関1
					mapper.put("transportFacility1", "無し");
					//中間駅2
					mapper.put("midStation2", "無し");
					//中間駅3
					mapper.put("midStation3", "無し");
					//定期券金額(1ヶ月)
					mapper.put("transportExpense1", "0");
				}
				//出張交通費
				mapper.put("businessTrip", "0");
			}

			//勤怠修正処理
			Transport transport = new Transport();

			transport = transportAllService.updateTransport(file, mapper, model);
			model.addAttribute("transport", transport);
			return "/ems/transport";
		}

	/**
	 * 機能：勤怠管理画面初期データ設定
	 *
	 * @param 画面情報
	 * @return 遷移画面
	 *
	 * @author 楊@ソフトテク
	 */
	@RequestMapping(path="/workdetail", method= RequestMethod.GET)
	public String Workdetail(HttpServletRequest request, HttpSession session, Model model) {

		Map<String, String> sportMapper = new HashMap<String, String>();
		//　交通情報取得
		sportMapper.put("employeeID", (String) session.getAttribute("userEmoplyeeID"));
		Transport transport = transportService.queryTransport(sportMapper);
		if (transport == null) {
			transport = new Transport();
		}
		// 交通費を０に設定。
		transport.setBusinessTrip("0");
		//　画面へ戻す
		model.addAttribute("transport", transport);
		return "/ems/transport";
	}
}