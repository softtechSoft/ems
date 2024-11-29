package com.softtech.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
 * 調整サービス
 */
@Service
public class AdjustmentService {

    @Autowired
    private AdjustmentFileMapper adjustmentFileMapper;
    @Autowired
    private AdjustmentDetailMapper adjustmentDetailMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private AdjustmentRequestFilesMapper adjustmentRequestFilesMapper;

    @Value("${file.storage.location}")
    private String rootLocation;

    @Value("${file.request.location}")
    private String requestFilesLocation;

    private Path getRootLocation() {
        return Paths.get(rootLocation);
    }

    private Path getRequestFilesLocation() {
        return Paths.get(requestFilesLocation);
    }

    /**
     * ファイルと詳細を保存する
     */
    public void saveFilesAndDetails(MultipartFile[] files, String employeeEmail, String fileType) throws IOException {
        // 社員情報を取得
        Employee employee = employeeMapper.queryEmployeeByEmail(employeeEmail);
        if (employee == null || employee.getEmployeeID() == null) {
            throw new IllegalArgumentException("無効なメールアドレス、または従業員IDが存在しません");
        }
        String employeeID = employee.getEmployeeID();
        int fileYear = java.time.LocalDate.now().getYear();

        // AdjustmentDetailレコードを検索または作成
        AdjustmentDetail detail = adjustmentDetailMapper.findByEmployeeIdAndYear(employeeID, String.valueOf(fileYear));
        if (detail == null) {
            detail = new AdjustmentDetail();
            detail.setEmployeeID(employeeID);
            detail.setEmployeeEmail(employeeEmail);
            detail.setYear(String.valueOf(fileYear));
            detail.setUploadStatus("1:アップロード完了");
            detail.setAdjustmentStatus("0:調整中");

            Date currentDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            detail.setInsertDate(currentDate);
            detail.setUpdateDate(currentDate);

            adjustmentDetailMapper.insert(detail);
        } else {
            detail.setUploadStatus("1:アップロード完了");

            Date currentDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            detail.setUpdateDate(currentDate);

            adjustmentDetailMapper.update(detail);
        }

        // 各ファイルと対応するレコードを保存
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                saveFile(file, employeeEmail, employeeID, fileYear, fileType);
            }
        }
    }

    /**
     * ファイルを保存する
     */
    private void saveFile(MultipartFile file, String employeeEmail, String employeeID, int year, String fileType)
            throws IOException {
        String originalFileName = file.getOriginalFilename();
        Path destinationFile = saveFileToDisk(file, year, employeeEmail);

        // 同名のファイルが存在するかチェック
        AdjustmentFile existingFile = adjustmentFileMapper.findByEmployeeIDAndYearAndFileName(employeeID, year,
                originalFileName);

        if (existingFile != null) {
            // 既存のレコードを更新
            existingFile.setFilePath(destinationFile.toString());
            existingFile.setFileType(fileType);
            adjustmentFileMapper.updateByEmployeeIDAndYearAndFileName(existingFile);
        } else {
            // 新しいレコードを挿入
            AdjustmentFile adjustmentFile = new AdjustmentFile();
            adjustmentFile.setEmployeeID(employeeID);
            adjustmentFile.setEmployeeEmail(employeeEmail);
            adjustmentFile.setFileName(originalFileName);
            adjustmentFile.setFilePath(destinationFile.toString());
            adjustmentFile.setFileYear(year);
            adjustmentFile.setFileType(fileType);
            adjustmentFileMapper.insert(adjustmentFile);
        }
    }

    /**
     * ファイルをディスクに保存する
     */
    private Path saveFileToDisk(MultipartFile file, int year, String employeeEmail) throws IOException {
        Path yearDirectory = getRootLocation().resolve(Paths.get(String.valueOf(year), employeeEmail));
        Files.createDirectories(yearDirectory);
        Path destinationFile = yearDirectory.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
        return destinationFile;
    }

    /**
     * 履歴ファイルを年度ごとに取得する（resultTypeのみ）
     */
    public Map<Integer, List<AdjustmentFile>> getResultFilesGroupedByYear(String employeeEmail) {
        List<AdjustmentFile> files = adjustmentFileMapper.findResultFilesByEmployeeEmail(employeeEmail);
        Map<Integer, List<AdjustmentFile>> filesByYear = new TreeMap<>(Collections.reverseOrder());
        for (AdjustmentFile file : files) {
            filesByYear.computeIfAbsent(file.getFileYear(), k -> new ArrayList<>()).add(file);
        }
        return filesByYear;
    }

    /**
     * 指定したタイプと社員のファイルを取得する
     */
    public List<AdjustmentFile> getFilesByTypeAndEmployee(String fileType, String employeeEmail, int fileYear) {
        return adjustmentFileMapper.findFilesByTypeAndEmployee(fileType, employeeEmail, fileYear);
    }

    /**
     * 申請書を取得する
     */
    public List<AdjustmentRequestFiles> getRequestFilesForYear(int year) {
        return adjustmentRequestFilesMapper.findByYearAndStatus(year, "1");
    }

    /**
     * 申請書ファイルをリソースとしてロードする
     */
    public Resource loadRequestFileAsResource(String filename) {
        try {
            Path file = getRequestFilesLocation().resolve(filename).normalize();
            if (Files.exists(file)) {
                Resource resource = new UrlResource(file.toUri());
                return resource;
            } else {
                throw new RuntimeException("ファイルが見つかりません: " + filename);
            }
        } catch (Exception e) {
            throw new RuntimeException("ファイルのロードに失敗しました: " + filename, e);
        }
    }

    /**
     * ユーザーファイルをリソースとしてロードする
     */
    public Resource loadUserFileAsResource(int fileYear, String employeeEmail, String filename) {
        try {
            Path file = getRootLocation().resolve(Paths.get(String.valueOf(fileYear), employeeEmail, filename));
            if (Files.exists(file)) {
                Resource resource = new UrlResource(file.toUri());
                return resource;
            } else {
                throw new RuntimeException("ファイルが見つかりません: " + filename);
            }
        } catch (Exception e) {
            throw new RuntimeException("ファイルのロードに失敗しました: " + filename, e);
        }
    }
}
