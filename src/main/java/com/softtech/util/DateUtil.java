
package com.softtech.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 概要：ファイル処理機能
 *
 * 作成者：@ソフトテク
 * 作成日：
 */

public class DateUtil {

	public static String getNowMonth() {
		// YYYY/MM→yyyymmに変換
		// 現在月生成
		String b;
		Date dNow = new Date( );
		SimpleDateFormat a = new SimpleDateFormat (" yyyyMM");
		 b = a.format(dNow);
		return b;
	}


}
