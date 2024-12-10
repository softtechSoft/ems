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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.AdjustmentDetail;
import com.softtech.entity.AdjustmentFile;
import com.softtech.entity.AdjustmentRequestFiles;
import com.softtech.entity.Employee;
import com.softtech.mapper.AdjustmentDetailMapper;
import com.softtech.mapper.AdjustmentFileMapper;
import com.softtech.mapper.AdjustmentRequestFilesMapper;
import com.softtech.mapper.EmployeeMapper;

/**
 * 年末調整サービスクラス
 * 
 * このクラスは、ファイルのアップロード、ダウンロード、保存、履歴管理など
 * 年末調整に関連するビジネスロジックを提供します。
 */
@Service
public class AdjustmentService {
    private static final Logger logger = LoggerFactory.getLogger(AdjustmentService.class);

    @Autowired
    private AdjustmentFileMapper adjustmentFileMapper; // AdjustmentFileエンティティ用のマッパー
    @Autowired
    private AdjustmentDetailMapper adjustmentDetailMapper; // AdjustmentDetailエンティティ用のマッパー
    @Autowired
    private EmployeeMapper employeeMapper; // Employeeエンティティ用のマッパー
    @Autowired
    private AdjustmentRequestFilesMapper adjustmentRequestFilesMapper; // AdjustmentRequestFilesエンティティ用のマッパー

    @Value("${file.storage.location}")
    private String rootLocation; // ファイル保存のルートディレクトリパス

    @Value("${file.request.location}")
    private String requestFilesLocation; // 申請ファイル保存のディレクトリパス

    /**
     * 初期化メソッド
     * 
     * 画面起動時に呼び出され、必要なディレクトリを作成します。
     */
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(getRequestFilesLocation()); // 申請ファイル保存ディレクトリの作成
            logger.debug("ファイル保存用ディレクトリの作成が完了しました - root: {}, requestFilesLocation: {}", getRootLocation(), getRequestFilesLocation());
        } catch (IOException e) {
            logger.error("ストレージディレクトリの作成に失敗しました！", e);
            throw new RuntimeException("ストレージディレクトリの作成に失敗しました！", e);
        }
    }

    /**
     * ルートディレクトリのパスを取得するメソッド
     * 
     * @return ルートディレクトリのPathオブジェクト
     */
    private Path getRootLocation() {
        Path p = Paths.get(rootLocation).toAbsolutePath().normalize(); // ルートディレクトリの絶対パスを取得
        logger.debug("ルートロケーション取得: {}", p);
        return p;
    }

    /**
     * 年末調整申請ファイル保存ディレクトリのパスを取得するメソッド
     * 
     * @return 申請ファイル保存ディレクトリのPathオブジェクト
     */
    private Path getRequestFilesLocation() {
        Path p = getRootLocation().resolve(requestFilesLocation); // ルートディレクトリに申請ファイル保存ディレクトリを結合
        logger.debug("リクエストファイルロケーション取得: {}", p);
        return p;
    }

    /**
     * ファイルと詳細情報を保存するメソッド
     * 
     * 指定されたファイルを保存し、関連する詳細情報をデータベースに保存または更新します。
     * 
     * @param files         アップロードされたファイルの配列
     * @param employeeID    従業員ID
     * @param employeeEmail 従業員のメールアドレス
     * @param fileType      ファイルの種類（例: "detailType"）
     * @throws IOException ファイルの保存中に発生する可能性のある例外
     */
    public void saveFilesAndDetails(MultipartFile[] files, String employeeID, String employeeEmail, String fileType) throws IOException {
        // 従業員情報を取得
        Employee employee = employeeMapper.queryEmployeeAll(employeeID);
        if (employee == null) {
            throw new IllegalArgumentException("無効な従業員ID: " + employeeID);
        }
        int fileYear = java.time.LocalDate.now().getYear(); // 現在の年度を取得

        // 年末調整詳細情報を取得または新規作成
        AdjustmentDetail detail = adjustmentDetailMapper.findByEmployeeIdAndYear(employeeID, String.valueOf(fileYear));
        Date currentDate = new Date(); // 現在の日付を取得

        if (detail == null) {
            // 年末調整詳細情報が存在しない場合は新規作成
            detail = new AdjustmentDetail();
            detail.setEmployeeID(employeeID);
            detail.setEmployeeEmail(employeeEmail);
            detail.setYear(String.valueOf(fileYear));
            detail.setUploadStatus("0");
            detail.setAdjustmentStatus("0");
            detail.setInsertDate(currentDate);
            detail.setUpdateDate(currentDate);
            adjustmentDetailMapper.insert(detail); // データベースに挿入
        } else {
            // 年末調整詳細情報が存在する場合は更新
            detail.setUploadStatus("0");
            detail.setEmployeeEmail(employeeEmail); // 最新のメールアドレスを更新
            detail.setUpdateDate(currentDate);
            adjustmentDetailMapper.update(detail); // データベースを更新
        }

        // 各ファイルを保存
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                saveFile(file, employeeID, fileYear, fileType); // ファイル保存メソッドを呼び出す
            }
        }
    }

    /**
     * ファイルを保存するメソッド
     * 
     * 指定されたファイルをサーバーのファイルシステムに保存し、
     * データベースにファイル情報を保存または更新します。
     * 
     * @param file       保存するファイル
     * @param employeeID 従業員ID
     * @param year       ファイルの年度
     * @param fileType   ファイルの種類
     * @throws IOException ファイルの保存中に発生する可能性のある例外
     */
    private void saveFile(MultipartFile file, String employeeID, int year, String fileType) throws IOException {
        String originalFileName = file.getOriginalFilename(); // 元のファイル名を取得

        // ファイルを保存するディレクトリパスを取得
        Path fileTypeDirectory = getRootLocation().resolve(Paths.get(String.valueOf(year), employeeID, fileType));
        Files.createDirectories(fileTypeDirectory); // 必要なディレクトリを作成
        Path destinationFile = fileTypeDirectory.resolve(originalFileName); // 保存先のファイルパスを設定
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING); // ファイルをコピー

        // 既存のファイル情報をデータベースから取得
        AdjustmentFile existingFile = adjustmentFileMapper.findByEmployeeIDAndYearAndFileName(employeeID, year, originalFileName, fileType);
        if (existingFile != null) {
            // 既存のファイル情報が存在する場合は更新
            existingFile.setFilePath(destinationFile.toString());
            existingFile.setFileType(fileType);
            existingFile.setFileStatus("0");
            adjustmentFileMapper.updateByEmployeeIDAndYearAndFileName(existingFile); // データベースを更新
        } else {
            // 新規ファイル情報を作成してデータベースに挿入
            AdjustmentFile adjustmentFile = new AdjustmentFile();
            adjustmentFile.setEmployeeID(employeeID);
            adjustmentFile.setFileName(originalFileName);
            adjustmentFile.setFilePath(destinationFile.toString());
            adjustmentFile.setFileYear(year);
            adjustmentFile.setFileType(fileType);
            adjustmentFile.setFileStatus("0");
            adjustmentFileMapper.insert(adjustmentFile); // データベースに挿入
        }
    }

    /**
     * 年度ごとの結果ファイルを取得するメソッド
     * 
     * 指定された従業員IDに関連する結果ファイルを年度ごとにグループ化して取得します。
     * 
     * @param employeeID 従業員ID
     * @return 年度ごとの結果ファイルを格納したマップ
     */
    public Map<Integer, List<AdjustmentFile>> getResultFilesGroupedByYear(String employeeID) {
        // 結果ファイルを従業員IDで取得
        List<AdjustmentFile> files = adjustmentFileMapper.findResultFilesByEmployeeID(employeeID);
        // 年度ごとのファイルリストを格納するTreeMap（降順）
        Map<Integer, List<AdjustmentFile>> filesByYear = new TreeMap<>(Collections.reverseOrder());
        for (AdjustmentFile file : files) {
            // ファイルを年度ごとにグループ化
            filesByYear.computeIfAbsent(file.getFileYear(), k -> new ArrayList<>()).add(file);
        }
        return filesByYear;
    }

    /**
     * 指定タイプと従業員のファイルを取得するメソッド
     * 
     * 指定されたファイルタイプ、従業員ID、および年度に基づいてファイルを取得します。
     * 
     * @param fileType   ファイルの種類
     * @param employeeID 従業員ID
     * @param fileYear   ファイルの年度
     * @return 指定条件に一致するファイルのリスト
     */
    public List<AdjustmentFile> getFilesByTypeAndEmployee(String fileType, String employeeID, int fileYear) {
        return adjustmentFileMapper.findFilesByTypeAndEmployee(fileType, employeeID, fileYear);
    }

    /**
     * 年度の申請書を取得するメソッド
     * 
     * 指定された年度に関連する申請書ファイルを取得します。
     * 
     * @param year 取得対象の年度
     * @return 指定年度の申請書ファイルのリスト
     */
    public List<AdjustmentRequestFiles> getRequestFilesForYear(int year) {
        return adjustmentRequestFilesMapper.findByYearAndStatus(year, "1"); // ステータスが"1"のファイルを取得
    }

    /**
     * 申請書ファイルをリソースとしてロードするメソッド
     * 
     * 指定されたファイル名の申請書ファイルをリソースとして取得します。
     * 
     * @param filename ダウンロードするファイル名
     * @return ダウンロード可能なリソース
     */
    public Resource loadRequestFileAsResource(String filename) {
        int currentYear = java.time.LocalDate.now().getYear(); // 現在の年度を取得
        List<AdjustmentRequestFiles> files = adjustmentRequestFilesMapper.findByYearAndStatus(currentYear, "1"); // 現在年度の申請書ファイルを取得
        for (AdjustmentRequestFiles f : files) {
            if (f.getFileName().equals(filename)) {
                Path filePath = Paths.get(f.getFilePath()); // ファイルパスを取得
                if (Files.exists(filePath)) {
                    try {
                        return new UrlResource(filePath.toUri()); // リソースとしてロード
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
     * ユーザーファイルをリソースとしてロードするメソッド
     * 
     * 指定されたファイルタイプ、年度、従業員ID、およびファイル名に基づいてファイルをリソースとして取得します。
     * 
     * @param fileType   ファイルの種類
     * @param fileYear   ファイルの年度
     * @param employeeID 従業員ID
     * @param filename   ダウンロードするファイル名
     * @return ダウンロード可能なリソース
     */
    public Resource loadUserFileAsResource(String fileType, int fileYear, String employeeID, String filename) {
        // 指定された条件でファイルをデータベースから取得
        AdjustmentFile file = adjustmentFileMapper.findByEmployeeIDAndYearAndFileName(employeeID, fileYear, filename, fileType);
        if (file == null) {
            throw new RuntimeException("ファイルが見つかりません: " + filename);
        }
        Path filePath = Paths.get(file.getFilePath()); // ファイルパスを取得
        if (!Files.exists(filePath)) {
            throw new RuntimeException("ファイルが見つかりません: " + filename);
        }
        try {
            return new UrlResource(filePath.toUri()); // リソースとしてロード
        } catch (Exception e) {
            throw new RuntimeException("ファイルのロードに失敗しました: " + filename, e);
        }
    }

    /**
     * ファイルのステータスを確定するメソッド
     * 
     * 指定された従業員IDと年度に関連するファイルのステータスを確定します。
     * 
     * @param employeeID 従業員ID
     * @param year       確定対象の年度
     */
    public void finalizeAdjustment(String employeeID, int year) {
        try {
            logger.debug("確定処理開始 - 従業員ID: {}, 年: {}", employeeID, year);
            
            // 調整詳細情報のアップロードステータスを"1"に更新
            adjustmentDetailMapper.updateUploadStatusByEmployeeIdAndYear(employeeID, String.valueOf(year), "1");
            logger.debug("adjustmentDetail の uploadStatus を 1 に更新しました。");
            
            logger.debug("確定処理完了 - 従業員ID: {}, 年: {}", employeeID, year);
        } catch (Exception e) {
            logger.error("確定処理中にエラー発生: {}", e.getMessage(), e);
            throw e; // 例外を再スロー
        }
    }

}
