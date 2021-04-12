
package com.softtech.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 概要：対象月処理機能
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/4/10
 */

public class DateUtil {

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
	 * 機能：YYYY/MMからYYYMMに変更
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

}
