
package com.softtech.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.actionForm.WorkDetail;

/**
 * 概要：ファイル処理機能
 *
 * 作成者：楊@ソフトテク
 * 作成日：2021/2/17
 */

public class FileUtil {

	//アップロードフォルダー
	final String uploadPath = "D:/worksheet/";

	/**
	 * 機能：ファイルをアップロード
	 *
	 * @param File　対象ファイル
	 * @return TRUE:成功、FALSE失敗
	 * @exception なし
	 * @author 楊@ソフトテク
	 */
	public boolean uploadFile(MultipartFile file,String targetPath) {

		if(targetPath == null) {
			return true;
		}

		// ファイルアップロード
		if (!file.isEmpty()) {
			try {
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(new File(targetPath + File.separator + file.getOriginalFilename())));
				out.write(file.getBytes());
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/**
	 * 機能：アップロードパスを作成。
	 *
	 * @param File　対象ファイル
	 * @return TRUE:成功、FALSE失敗
	 * @exception なし
	 * @author @ソフトテク
	 */
	public String mkUploadPath(String... path) {
		String loadPath="";
		for (int i = 0; i < path.length; i++){
			if(i>0) {
				loadPath += File.separator;
			}

			loadPath += path[i];
			mkPath(loadPath);
		}
		return loadPath;
	}
	/**
	 * 機能：パス作成。
	 *
	 * @param Path　対象パス
	 * @return TRUE:成功、FALSE失敗
	 * @exception なし
	 * @author @ソフトテク
	 */
	private boolean mkPath(String path) {
		if (path == null ) return true;

		File folder = new File(path);
		if(folder.exists()) {
			return true;
		} else {
			// フォルダー作成。
			folder.mkdir();
		}
		return true;
	}
	/**
	 * 機能：勤怠リストダウンロード
	 *
	 * @param response レスポンス
	 * @param workDetailList 対象データ
	 * @return TRUE:成功、FALSE失敗
	 *
	 * @exception なし
	 * @author @ソフトテク
	 */
	public boolean workSheetDownload(HttpServletResponse  response,List<WorkDetail> workDetailList){
		//エンコーディング設定
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=Shift-JIS");
		//ダウンロードファイル名設定
		response.setHeader("Content-Disposition", "attachment; filename=\"worksheet.csv\"");

		try {
			PrintWriter pw = response.getWriter();
            for(WorkDetail wl:workDetailList) {
            	String employeeID = wl.getEmployeeID();
                String employeeName = wl.getEmployeeName();
                String workMonth = wl.getWorkMonth();
                float workTime = wl.getWorkTime();
                float transportExpense = wl.getTransportExpense();
                float transport = wl.getTransport();


                String outputString = employeeID + "," + employeeName + "," + workMonth + "," + workTime + "," + transportExpense + "," + transport
                         + "\r\n";

                pw.print(outputString);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
	 }
}
