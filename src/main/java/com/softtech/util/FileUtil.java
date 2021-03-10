
package com.softtech.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

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

}
