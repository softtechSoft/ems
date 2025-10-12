package com.softtech.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.AdjustmentDetail;
import com.softtech.entity.AdjustmentFile;
import com.softtech.entity.AdjustmentRequestFiles;
import com.softtech.entity.Employee;
import com.softtech.entity.SaveFolder;
import com.softtech.mapper.AdjustmentDetailMapper;
import com.softtech.mapper.AdjustmentFileMapper;
import com.softtech.mapper.AdjustmentRequestFilesMapper;
import com.softtech.mapper.EmployeeMapper;

/**
 * 年末調整サービスクラス
 *
 * ファイルのアップロード、ダウンロード、保存、履歴管理など
 * 年末調整に関連するビジネスロジックを提供するクラス。
 *
 *
 * 年末調整  opt/emsm/adjust  (m_file)
 *    （システム-宮野）     /YYYY    #年度
 *                                  /調整票１
 *                                  /調整票2
 *                                  /調整票3
 *     (ems-社員）                  /社員１
 *                                       /記載済み調整票１
 *                                       /記載済み調整票２
 *                                       /証跡ファイル１
 *                                       /証跡ファイル１
 *    （emsmー宮野さん）                 /源泉帳票票
 *
 *                                 /社員2
 */
@Service
public class AdjustmentService {
    private static final Logger logger = LoggerFactory.getLogger(AdjustmentService.class);

    @Autowired
    private AdjustmentFileMapper adjustmentFileMapper;
    @Autowired
    private AdjustmentDetailMapper adjustmentDetailMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private AdjustmentRequestFilesMapper adjustmentRequestFilesMapper;
    @Autowired
    SaveFolderService saveFolderService;

    //@Value("${file.storage.location}")
    private String rootLocation; // ルートディレクトリパス

    //@Value("${file.request.location}")
    private String requestFilesLocation; // 申請書ファイル保存用ディレクトリパス

    /**
     * 初期化メソッド
     */
    @PostConstruct
    public void init() {
        try {
        	// 年度更新ルートフォルダーを取得
        	SaveFolder saveFolder=saveFolderService .findFileTypeName("年度更新");
        	rootLocation=saveFolder.getSaveFolder();
        	requestFilesLocation=rootLocation;

            // 申請書ファイル保存用ディレクトリを作成
            Files.createDirectories(getRequestFilesLocation());
            logger.debug("ファイル保存用ディレクトリの作成が完了: root={}, requestFilesLocation={}",
                         getRootLocation(), getRequestFilesLocation());
        } catch (IOException e) {
            logger.error("ストレージディレクトリの作成に失敗しました！", e);
            throw new RuntimeException("ストレージディレクトリの作成に失敗しました！", e);
        }
    }

    /**
     * ルートディレクトリのパスを取得
     */
    private Path getRootLocation() {
        Path p = Paths.get(rootLocation).toAbsolutePath().normalize();
        logger.debug("ルートロケーション取得: {}", p);
        return p;
    }

    /**
     * 申請書ファイル保存ディレクトリのパスを取得
     */
    private Path getRequestFilesLocation() {
        Path p = getRootLocation().resolve(requestFilesLocation);
        logger.debug("リクエストファイルロケーション取得: {}", p);
        return p;
    }

    /**
     * ファイルと詳細情報を保存するメソッド
     */
    public void saveFilesAndDetails(MultipartFile[] files, String employeeID, String employeeEmail, String fileType)
            throws IOException {
        // 従業員情報チェック
        Employee employee = employeeMapper.queryEmployeeAll(employeeID);
        if (employee == null) {
            throw new IllegalArgumentException("無効な従業員ID: " + employeeID);
        }
        int fileYear = java.time.LocalDate.now().getYear(); // 現在の年

        // 年末調整詳細情報を取得または新規作成
        AdjustmentDetail detail = adjustmentDetailMapper.findByEmployeeIdAndYear(employeeID, String.valueOf(fileYear));
        Date currentDate = new Date();

        if (detail == null) {
            detail = new AdjustmentDetail();
            detail.setEmployeeID(employeeID);
            detail.setEmployeeEmail(employeeEmail);
            detail.setYear(String.valueOf(fileYear));
            detail.setUploadStatus("0");
            detail.setAdjustmentStatus("0");
            detail.setInsertDate(currentDate);
            detail.setUpdateDate(currentDate);
            adjustmentDetailMapper.insert(detail);
        } else {
            detail.setUploadStatus("0");
            detail.setEmployeeEmail(employeeEmail);
            detail.setUpdateDate(currentDate);
            adjustmentDetailMapper.update(detail);
        }

        // ファイルを一つずつ保存
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                saveFile(file, employeeID, fileYear, fileType);
            }
        }
    }

    /**
     * 単一ファイルを保存する内部メソッド
     */
    private void saveFile(MultipartFile file, String employeeID, int year, String fileType) throws IOException {
        String originalFileName = file.getOriginalFilename();
        // 保存先ディレクトリ
        Path fileTypeDirectory = getRootLocation().resolve(Paths.get(String.valueOf(year), employeeID, fileType));
        Files.createDirectories(fileTypeDirectory);
        Path destinationFile = fileTypeDirectory.resolve(originalFileName);
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

        // DBに存在チェック
        AdjustmentFile existingFile = adjustmentFileMapper
                .findByEmployeeIDAndYearAndFileName(employeeID, year, originalFileName, fileType);
        if (existingFile != null) {
            // 既存ファイル更新
            existingFile.setFilePath(destinationFile.toString());
            existingFile.setFileType(fileType);
            existingFile.setFileStatus("0");
            adjustmentFileMapper.updateByEmployeeIDAndYearAndFileName(existingFile);
        } else {
            // 新規ファイルINSERT
            AdjustmentFile adjustmentFile = new AdjustmentFile();
            adjustmentFile.setEmployeeID(employeeID);
            adjustmentFile.setFileName(originalFileName);
            adjustmentFile.setFilePath(destinationFile.toString());
            adjustmentFile.setFileYear(year);
            adjustmentFile.setFileType(fileType);
            adjustmentFile.setFileStatus("0");
            adjustmentFileMapper.insert(adjustmentFile);
        }
    }

    /**
     * 年度ごとの結果ファイルを取得し、マップにまとめる
     */
    public Map<Integer, List<AdjustmentFile>> getResultFilesGroupedByYear(String employeeID) {
        List<AdjustmentFile> files = adjustmentFileMapper.findResultFilesByEmployeeID(employeeID);
        Map<Integer, List<AdjustmentFile>> filesByYear = new TreeMap<>(Collections.reverseOrder());
        for (AdjustmentFile file : files) {
            filesByYear.computeIfAbsent(file.getFileYear(), k -> new ArrayList<>()).add(file);
        }
        return filesByYear;
    }

    /**
     * タイプと従業員ID、年度でファイルを検索
     */
    public List<AdjustmentFile> getFilesByTypeAndEmployee(String fileType, String employeeID, int fileYear) {
        return adjustmentFileMapper.findFilesByTypeAndEmployee(fileType, employeeID, fileYear);
    }

    /**
     * 指定年度の申請ファイルを取得
     */
    public List<AdjustmentRequestFiles> getRequestFilesForYear(int year) {
        return adjustmentRequestFilesMapper.findByYearAndStatus(year, "1");
    }

    /**
     * 申請書ファイルをリソースとしてロード
     */
    public Resource loadRequestFileAsResource(String filename) {
        int currentYear = java.time.LocalDate.now().getYear();
        List<AdjustmentRequestFiles> files = adjustmentRequestFilesMapper.findByYearAndStatus(currentYear, "1");
        for (AdjustmentRequestFiles f : files) {
            if (f.getFileName().equals(filename)) {
                Path filePath = Paths.get(f.getFilePath());
                if (Files.exists(filePath)) {
                    try {
                        return new UrlResource(filePath.toUri());
                    } catch (Exception e) {
                        throw new RuntimeException("ファイルのロードに失敗しました: " + filename, e);
                    }
                } else {
                    throw new RuntimeException("ファイルが見つかりません: " + filename);
                }
            }
        }
        throw new RuntimeException("ファイルが見つかりません: " + filename);
    }

    /**
     * ユーザーファイルをリソースとしてロード
     */
    public Resource loadUserFileAsResource(String fileType, int fileYear, String employeeID, String filename) {
        AdjustmentFile file = adjustmentFileMapper
                .findByEmployeeIDAndYearAndFileName(employeeID, fileYear, filename, fileType);
        if (file == null) {
            throw new RuntimeException("ファイルが見つかりません: " + filename);
        }
        Path filePath = Paths.get(file.getFilePath());
        if (!Files.exists(filePath)) {
            throw new RuntimeException("ファイルが見つかりません: " + filename);
        }
        try {
            return new UrlResource(filePath.toUri());
        } catch (Exception e) {
            throw new RuntimeException("ファイルのロードに失敗しました: " + filename, e);
        }
    }

    /**
     * ファイルを削除するメソッド
     */
    public void deleteFile(String employeeID, int fileYear, String fileName, String fileType) {
        logger.debug("ファイル削除開始 - employeeID: {}, fileYear: {}, fileName: {}, fileType: {}",
                     employeeID, fileYear, fileName, fileType);

        // DBにファイルレコードを検索
        AdjustmentFile file = adjustmentFileMapper.findByEmployeeIDAndYearAndFileName(employeeID, fileYear, fileName, fileType);
        if (file == null) {
            throw new RuntimeException("削除対象のファイルが見つかりません: " + fileName);
        }

        // 物理ファイルを削除
        Path filePath = Paths.get(file.getFilePath());
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                logger.debug("物理ファイルを削除しました: {}", filePath.toString());
            } else {
                logger.warn("物理ファイルが見つかりません: {}", filePath.toString());
            }
        } catch (IOException e) {
            logger.error("ファイルの削除に失敗しました: {}", filePath.toString(), e);
            throw new RuntimeException("ファイルの削除に失敗しました: " + fileName, e);
        }

        // DBからレコードを削除
        int affectedRows = adjustmentFileMapper.deletefile(employeeID, fileYear, fileName, fileType);
        if (affectedRows == 0) {
            throw new RuntimeException("DBからファイルレコードを削除できませんでした: " + fileName);
        }

        logger.debug("ファイル削除完了: {}", file.getFilePath());
    }

    /**
     * 調整を確定するメソッド
     */
    public void finalizeAdjustment(String employeeID, int year) {
        try {
            logger.debug("確定処理開始 - 従業員ID: {}, 年: {}", employeeID, year);
            // adjustmentDetailテーブルのuploadStatusを"1"に更新
            adjustmentDetailMapper.updateUploadStatusByEmployeeIdAndYear(employeeID, String.valueOf(year), "1");
            logger.debug("adjustmentDetailのuploadStatusを1に更新しました。");
            logger.debug("確定処理完了 - 従業員ID: {}, 年: {}", employeeID, year);
        } catch (Exception e) {
            logger.error("確定処理中にエラー: {}", e.getMessage(), e);
            throw e;
        }
    }
    /*
      年末調整  opt/emsm/adjust  (m_file)
     （システム-宮野さん）     /YYYY    #年度
                                    /調整票１
                                   /調整票2
                                   /調整票3
      (ems-社員）                  /社員１
                                        /記載済み調整票１
                                        /記載済み調整票２
                                        /証跡ファイル１
                                        /証跡ファイル１
     （emsmー宮野さん）                 /源泉帳票票

                                  /社員2
     */
}
