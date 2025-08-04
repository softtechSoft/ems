package com.softtech.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
public class WorkInfoManageController<WorkInfoComment> {
	@Autowired
	private TransportService transportService;

	@Autowired
	private TransportAllService transportAllService;


	/**
	 * 機能：勤怠管理画面初期データ設定
	 *
	 * @param 画面情報
	 * @return 遷移画面
	 *
	 * @author 楊@ソフトテク
	 */
	@RequestMapping(path="/workdetail", method= RequestMethod.GET)
	public String Workdetail(HttpServletRequest request, HttpSession session, Model model) throws Exception{

	  //現在日付
	  Calendar cal = Calendar.getInstance();
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

	  String month = sdf.format(cal.getTime());


	  Map<String, String> sportMapper = new HashMap<String, String>();
	  String employeeID = (String) session.getAttribute("userEmoplyeeID");
	  String maxMonth =transportService.queryMaxWorkMonthTransport(employeeID);

	  //　交通情報取得
	  sportMapper.put("employeeID", (String) session.getAttribute("userEmoplyeeID"));
	  sportMapper.put("workMonth", maxMonth);

	  Transport transport = transportService.queryTransport(sportMapper);
	  if (transport == null) {
	    transport = new Transport();

	    // DBデータが存在する場合、修正できるように設定する（state=1:修正、0:新規登録(提出),9:エラー)
	    transport.setState("0");

	    LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
	    transport.setWorkStartDay(firstDayOfMonth.format(DateTimeFormatter.BASIC_ISO_DATE));

	    LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
	    transport.setWorkEndDay(lastDayOfMonth.format(DateTimeFormatter.BASIC_ISO_DATE));
	  }else {
		  if(month != maxMonth) {
			  	transport.setState("0");
			}else {
				transport.setState("1");
			}
		  //transport.setState("1");

	  }
	  // 稼働月設定。
	  transport.setWorkMonth(month);

	  // 交通費を０に設定。
	  transport.setBusinessTrip("0");


	  //　画面へ戻す
	  model.addAttribute("transport", transport);
	  return "/ems/workInfoManage";
	}


	/**
	 * 機能：対象年月が変更された場合の処理
	 * 　　　対象年月のデータをDBから取得し画面へ渡す
	 *
	 *@param request　リクエスト
	 * @param model　画面モデル
	 * @return
	 * @exception
	 * @author 孫@ソフトテク
	 */
	@RequestMapping("/changeMonth")
	public String changeMonth(HttpServletRequest request,HttpSession session,@RequestParam("file") MultipartFile file,Model model) throws Exception {

	  DateFormat sdFormat1 = new SimpleDateFormat("yyyyMM");

	  //画面から、年月を取得
	  String month = "";
	  //パラメータ取得
	  Map<String, String[]> map = request.getParameterMap();
	  for (Map.Entry<String, String[]> entry : map.entrySet()) {
	    //稼働月を変換。YYYY/MM形をYYYYMM形に変換。
	    if(entry.getKey().equals("workMonth")){
	      month = entry.getValue()[0].replace("/", "");
	      break;
	    }
	  }

	  //セッションからログインIDを取得する。
	  String employeeID = (String) session.getAttribute("userEmoplyeeID");

	  //年月、ログインIDを持っち、DBを検索する。（workinfo、transport）
	  Map<String, String> sportMapper = new HashMap<String, String>();
	  //　交通情報取得
	  sportMapper.put("employeeID", employeeID);
	  sportMapper.put("workMonth", month);
	  Transport transport = transportService.queryTransport(sportMapper);
	  if (transport == null) {
	    transport = new Transport();

	    transport.setState("0");

	    //指定年月の開始日と最終日をLocalDateで設定
	    java.util.Date monthDate = sdFormat1.parse(month);
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(monthDate);
	    int year = cal.get(Calendar.YEAR);
	    int monthValue = cal.get(Calendar.MONTH) + 1;

	    LocalDate firstDayOfMonth = LocalDate.of(year, monthValue, 1);
	    LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());

	    transport.setWorkStartDay(firstDayOfMonth.format(DateTimeFormatter.BASIC_ISO_DATE));
	    transport.setWorkEndDay(lastDayOfMonth.format(DateTimeFormatter.BASIC_ISO_DATE));
	  }else {
	    transport.setState("1");
	  }

	  // 稼働月設定。
	  transport.setWorkMonth(month);

	  model.addAttribute("transport", transport);
	  return "/ems/workInfoManage";
	}


	@RequestMapping("/transport-workinfo")
	//新規追加
	public String insertTransport(HttpServletRequest request, HttpSession session,@RequestParam("file") MultipartFile file, Model model) throws Exception {

	  Map<String, String> mapper = new HashMap<String, String>();
	  mapper.put("employeeID", (String) session.getAttribute("userEmoplyeeID"));

	  boolean flg = true;
	  String workStartDay = "";

	  //パラメータ取得
	  Map<String, String[]> map = request.getParameterMap();
	  for (Map.Entry<String, String[]> entry : map.entrySet()) {

	    //稼働開始日。YYYY-MM-DD形をYYYYMMDD形に変換
	    if (entry.getKey().equals("workStartDay") ) {
	      String startDayValue = entry.getValue()[0];
	      if (startDayValue != null && !startDayValue.trim().isEmpty()) {
	        workStartDay = startDayValue.replace("-", "");
	        mapper.put(entry.getKey(), workStartDay);
	      } else {
	        throw new IllegalArgumentException("稼働開始日が入力されていません");
	      }
	      continue;
	    }

	    //終了日を変換。YYYY-MM-DD形をYYYYMMDD形に変換
	    if ( entry.getKey().equals("workEndDay")) {
	      String endDayValue = entry.getValue()[0];
	      if (endDayValue != null && !endDayValue.trim().isEmpty()) {
	        mapper.put(entry.getKey(), endDayValue.replace("-", ""));
	      } else {
	        throw new IllegalArgumentException("稼働最終日が入力されていません");
	      }
	      continue;
	    }

	    //稼働月を変換。YYYY/MM形をYYYYMM形に変換。
	    if(entry.getKey().equals("workMonth")){
	      String workMonthValue = entry.getValue()[0];
	      if (workMonthValue != null && !workMonthValue.trim().isEmpty()) {
	        mapper.put(entry.getKey(), workMonthValue.replace("/", ""));
	      } else {
	        throw new IllegalArgumentException("稼働月が入力されていません");
	      }
	      continue;
	    }

	    //定期券開始日を変換。YYYYMM形に変換。
	    if(entry.getKey().equals("startDate")){
	      String startDateValue = entry.getValue()[0];
	      if (startDateValue != null && !startDateValue.trim().isEmpty()) {
	        String startDate = startDateValue;
	        startDate = startDate.replace("/", "");
	        startDate = startDate.replace("-", "");
	        mapper.put(entry.getKey(), startDate);
	      } else {
	        mapper.put(entry.getKey(), workStartDay);
	      }
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

	    String paramValue = entry.getValue()[0];
	    if (paramValue != null) {
	      mapper.put(entry.getKey(), paramValue);
	    }
	  } // for

	  //勤怠追加処理
	  Transport transport = new Transport();


	  try{
		  transport = transportAllService.doTransport(file, mapper, model);
	  }catch(Exception e) {

	  } finally {

		  if (transport == null) {
		    transport = new Transport();

		    transport.setState("0");
		  }else {
		    transport.setState("1");
		  }

		  //現在日付
		  Calendar cal = Calendar.getInstance();
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		  String month = sdf.format(cal.getTime());
		  transport.setWorkMonth(month);

		  model.addAttribute("transport", transport);

	  }

	  return "/ems/workInfoManage";
	}


	//修正
	@RequestMapping("/transport-update")
	public String updateTransport(HttpServletRequest request, HttpSession session,@RequestParam("file") MultipartFile file, Model model) throws Exception {

	    // セッションからログインIDを取得する。
	    Map<String, String> mapper = new HashMap<String, String>();
	    mapper.put("employeeID", (String) session.getAttribute("userEmoplyeeID"));

	    String workStartDay = "";

	    //パラメータ取得
	    Map<String, String[]> map = request.getParameterMap();
	    for (Map.Entry<String, String[]> entry : map.entrySet()) {

	        //稼働開始日。YYYY-MM-DD形をYYYYMMDD形に変換
	        if (entry.getKey().equals("workStartDay") ) {
	            String startDayValue = entry.getValue()[0];
	            if (startDayValue != null && !startDayValue.trim().isEmpty()) {
	                workStartDay = startDayValue.replace("-", "");
	                mapper.put(entry.getKey(), workStartDay);
	            } else {
	                throw new IllegalArgumentException("稼働開始日が入力されていません");
	            }
	            continue;
	        }

	        //終了日を変換。YYYY-MM-DD形をYYYYMMDD形に変換
	        if ( entry.getKey().equals("workEndDay")) {
	            String endDayValue = entry.getValue()[0];
	            if (endDayValue != null && !endDayValue.trim().isEmpty()) {
	                mapper.put(entry.getKey(), endDayValue.replace("-", ""));
	            } else {
	                throw new IllegalArgumentException("稼働最終日が入力されていません");
	            }
	            continue;
	        }

	        //稼働月を変換。YYYY/MM形をYYYYMM形に変換。
	        if(entry.getKey().equals("workMonth")){
	            String workMonthValue = entry.getValue()[0];
	            if (workMonthValue != null && !workMonthValue.trim().isEmpty()) {
	                mapper.put(entry.getKey(), workMonthValue.replace("/", ""));
	            } else {
	                throw new IllegalArgumentException("稼働月が入力されていません");
	            }
	            continue;
	        }

	        //定期券開始日を変換。YYYYMM形に変換。
	        if(entry.getKey().equals("startDate")){
	            String startDateValue = entry.getValue()[0];
	            if (startDateValue != null && !startDateValue.trim().isEmpty()) {
	                String startDate = startDateValue;
	                startDate = startDate.replace("/", "");
	                startDate = startDate.replace("-", "");
	                mapper.put(entry.getKey(), startDate);
	            } else {
	                // null の場合は稼働開始日を使用
	                mapper.put(entry.getKey(), workStartDay);
	            }
	            continue;
	        }
	        String paramValue = entry.getValue()[0];
	        if (paramValue != null) {
	            mapper.put(entry.getKey(), paramValue);
	        }
	    } // forのEND

	    //勤怠修正処理

	    Transport transport = new Transport();
		try {
			transport = transportAllService.updateTransport(file, mapper, model);
		}catch(Exception e) {

	    } finally {
		    if (transport == null) {
		        transport = new Transport();
		        transport.setState("0");
		    }else {
		        transport.setState("1");
		    }
	    }
	    model.addAttribute("transport", transport);
	    return "/ems/workInfoManage";
	}

}