
package com.softtech.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.softtech.com.MonthInfo;
import com.softtech.com.YearInfo;

/**
 * 概要：対象月処理機能
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/4/10
 */

public class DateUtil {

	/**
	 * 機能：現在年度を生成する
	 *
	 * @return 現在年度
	 * @exception なし
	 * @author @ソフトテク
	 */
	public static String getNowYear() {
		// YYYY/MM→yyyymmに変換
		// 現在月生成
		String b;
		Date dNow = new Date( );
		SimpleDateFormat a = new SimpleDateFormat ("yyyy");
		 b = a.format(dNow);
		//TETS COMMIT
		 //TEST
		return b;
	}
	/**
	 * 機能：前年度を生成する
	 *
	 * @param 現在年度
	 * @exception
	 * @return 前年度
	 * @author @ソフトテク
	 */
	public static String yearMinus(String year) {


		SimpleDateFormat a = new SimpleDateFormat ("yyyy");

		String s="";
		try {
			Date date = a.parse(year);
			Calendar time= Calendar.getInstance();
			time.setTime(date);
			time.add(Calendar.YEAR,-1);

			Date dt1 = time.getTime();
			SimpleDateFormat a1 = new SimpleDateFormat ("yyyy");
			s = a1.format(dt1);

		} catch (ParseException e) {
			s=year;
		}
		return s;

	}/**
	 * 機能：次年度を生成する
	 *
	 * @param 現在年度
	 * @exception
	 * @return 次年度
	 * @author @ソフトテク
	 */
	public static String yearPlus(String year) {


		SimpleDateFormat a = new SimpleDateFormat ("yyyy");

		String s="";
		try {
			Date date = a.parse(year);
			Calendar time= Calendar.getInstance();
			time.setTime(date);
			time.add(Calendar.YEAR,+1);

			Date dt1 = time.getTime();
			SimpleDateFormat a1 = new SimpleDateFormat ("yyyy");
			s = a1.format(dt1);

		} catch (ParseException e) {
			s=year;
		}
		return s;

	}

	/**
	 * 機能：現在月を生成する
	 *
	 * @return 現在月
	 * @exception なし
	 * @author 馬@ソフトテク
	 */
	public static String getNowMonth() {
		// YYYY/MM→yyyymmに変換
		// 現在月生成
		String b;
		Date dNow = new Date( );
		SimpleDateFormat a = new SimpleDateFormat ("yyyyMM");
		 b = a.format(dNow);
		return b;
	}
	/**
	 * 機能：YYYY/MMからYYYYMMに変更
	 *
	 * @param 年月
	 * @return 変更後年月
	 * @exception なし
	 * @author 馬@ソフトテク
	 */
	public static String chgMonthToYM(String month) {
		// YYYY/MM→yyyymmに変換
		return month.replace("/", "");
	}
	/**
	 * 機能：YYYY/MMからYYYMMに変更
	 *
	 * @param 年月
	 * @return 変更後年月
	 * @exception なし
	 * @author 馬@ソフトテク
	 */
	public static String chgYMToDate(String month) {
		// yyyymm→YYYY/MMに変換
		if (month.length() < 4 ) return month;
		String monthDate="";
		monthDate = month.substring(0, 4) + "/" + month.substring(4);
		return monthDate;
	}
	/**
	 * 機能：float→Stringに変換
	 *
	 * @param 年月
	 * @return 変更後年月
	 * @exception なし
	 * @author 馬@ソフトテク
	 */
	public static String formatTosepara(float getTransportExpense1) {
		DecimalFormat df = new DecimalFormat("#,###.00");
		return df.format(getTransportExpense1);
	}

	/**
	 * 機能：YYYY/MMからYYYMMに変更
	 *
	 * @param 年月
	 * @return 変更後年月
	 * @exception なし
	 */
	public static String changeYMToDate(String month) {
		// yyyymm→YYYY/MMに変換
		if (month.length() < 4 ) return month;
		String monthDate="";
		monthDate = month.substring(0, 4) + month.substring(4);
		return monthDate;
	}


	/**
	 * 機能：過去n年度分年度リスト生成
	 *
	 * @param 過去n年度
	 * @return 年度リスト
	 *
	 * @author @ソフトテク
	 */
	public static ArrayList<YearInfo> getBeforeYears(int n) {
		// 当年度生成
		ArrayList<YearInfo> years = new ArrayList<YearInfo>();

		// カレンダーオブジェクトを取得
        Calendar calendar = Calendar.getInstance();
        // 値をセット
        calendar.setTime(new Date());
        for (int i=1;i <= n;i++) {
        	//当年度ではないカレンダーを生成
        	if(i>1) {
        		calendar.add(Calendar.YEAR, -1);
        	}
        	int year = calendar.get(Calendar.YEAR);
        	YearInfo yearInfo = new YearInfo();

        	yearInfo.setId(year);
        	yearInfo.setName(Integer.toString(year));

        	years.add(yearInfo);
        }

		return years;
	}
	/**
	 * 機能：月度リストを生成する
	 *
	 * @return 月度リスト
	 *
	 * @author 開発@ソフトテク
	 */
	public static ArrayList<MonthInfo> getMonths() {

		ArrayList<MonthInfo> ml = new ArrayList<MonthInfo>();

		MonthInfo info = new MonthInfo();
		info.setId(1);
		info.setName("01");
		ml.add(info);

		MonthInfo info2 = new MonthInfo();
		info2.setId(2);
		info2.setName("02");
		ml.add(info2);

		MonthInfo info3 = new MonthInfo();
		info3.setId(3);
		info3.setName("03");
		ml.add(info3);
		MonthInfo info4 = new MonthInfo();
		info4.setId(4);
		info4.setName("04");
		ml.add(info4);

		MonthInfo info5 = new MonthInfo();
		info5.setId(5);
		info5.setName("05");
		ml.add(info5);

		MonthInfo info6= new MonthInfo();
		info6.setId(6);
		info6.setName("06");
		ml.add(info6);

		MonthInfo info7 = new MonthInfo();
		info7.setId(7);
		info7.setName("07");
		ml.add(info7);

		MonthInfo info8 = new MonthInfo();
		info8.setId(8);
		info8.setName("08");
		ml.add(info8);

		MonthInfo info9 = new MonthInfo();
		info9.setId(9);
		info9.setName("09");
		ml.add(info9);

		MonthInfo info10 = new MonthInfo();
		info10.setId(10);
		info10.setName("10");
		ml.add(info10);

		MonthInfo info11 = new MonthInfo();
		info11.setId(11);
		info11.setName("11");
		ml.add(info11);

		MonthInfo info12 = new MonthInfo();
		info12.setId(12);
		info12.setName("12");
		ml.add(info12);

		return ml;

	}


}



