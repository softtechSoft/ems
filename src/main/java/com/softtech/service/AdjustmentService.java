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
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

    private final Path requestFilesLocation = Paths.get("/Users/yangxiwen/Documents/work/templates");

    public void saveFilesAndDetails(MultipartFile[] files, String employeeEmail, int fileYear, String fileType) throws IOException {
        // 获取员工信息
        Employee employee = employeeMapper.queryEmployeeByEmail(employeeEmail);
        if (employee == null || employee.getEmployeeID() == null) {
            throw new IllegalArgumentException("無効なメールアドレス、または従業員IDが存在しません");
        }
        String employeeID = employee.getEmployeeID();

        // 查找或创建 AdjustmentDetail 记录
        List<AdjustmentDetail> details = adjustmentDetailMapper.findByEmployeeEmailAndYear(employeeEmail, fileYear);
        AdjustmentDetail detail;
        if (details.isEmpty()) {
            detail = new AdjustmentDetail();
            detail.setEmployeeID(employeeID);
            detail.setEmployeeEmail(employeeEmail);
            detail.setYear(String.valueOf(fileYear));
            detail.setUploadStatus("1:アップロード完了");  // 设置为上传完了
            detail.setAdjustmentStatus("0:調整中");  // 保持默认状态

            // 将 LocalDateTime 转换为 Date
            Date currentDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            detail.setInsertDate(currentDate);
            detail.setUpdateDate(currentDate);

            adjustmentDetailMapper.insert(detail);
        } else {
            detail = details.get(0);
            detail.setUploadStatus("1:アップロード完了");  // 更新为上传完了

            
            Date currentDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
            detail.setUpdateDate(currentDate);

            adjustmentDetailMapper.update(detail);
        }

        // 保存每个文件及其对应的记录
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                saveFile(file, employeeEmail, employeeID, fileYear, fileType);
            }
        }
    }

    private void saveFile(MultipartFile file, String employeeEmail, String employeeID, int year, String fileType) throws IOException {
        String originalFileName = file.getOriginalFilename();
        Path destinationFile = saveFileToDisk(file, year, employeeEmail);

        // 检查是否存在同名文件
        AdjustmentFile existingFile = adjustmentFileMapper.findByEmployeeIDAndYearAndFileName(employeeID, year, originalFileName);

        if (existingFile != null) {
            // 更新已有的记录
            existingFile.setFilePath(destinationFile.toString());
            existingFile.setFileType(fileType);
            adjustmentFileMapper.updateByEmployeeIDAndYearAndFileName(existingFile);
        } else {
            // 插入新记录
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


    private Path saveFileToDisk(MultipartFile file, int year, String employeeEmail) throws IOException {
        Path yearDirectory = Paths.get("/Users/yangxiwen/Documents/work", String.valueOf(year), employeeEmail);
        Files.createDirectories(yearDirectory);
        Path destinationFile = yearDirectory.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
        return destinationFile;
    }

    public Map<Integer, List<AdjustmentFile>> getHistoricalFilesGroupedByYear(String employeeEmail) {
        List<AdjustmentFile> files = adjustmentFileMapper.findFilesByEmployeeEmail(employeeEmail);
        return files.stream().collect(Collectors.groupingBy(AdjustmentFile::getFileYear));
    }

    public byte[] downloadFile(String filename, String employeeEmail, int fileYear) throws IOException {
        Path file = Paths.get("/Users/yangxiwen/Documents/work", String.valueOf(fileYear), employeeEmail, filename);
        if (!Files.exists(file)) {
            throw new IOException("ファイルが見つかりません: " + file.toString());
        }
        return Files.readAllBytes(file);
    }

    public Map<Integer, List<AdjustmentFile>> getHistoricalFilesGroupedByYear(String employeeEmail, int excludeYear) {
        List<AdjustmentFile> files = adjustmentFileMapper.findFilesByEmployeeEmail(employeeEmail);
        return files.stream()
            .filter(file -> file.getFileYear() != excludeYear)
            .collect(Collectors.groupingBy(AdjustmentFile::getFileYear));
    }

    public Path getFilePath(String filename, String employeeEmail, int fileYear) {
        return Paths.get("/Users/yangxiwen/Documents/work", String.valueOf(fileYear), employeeEmail, filename);
    }

    // 新增方法：获取申请书列表
    public List<AdjustmentRequestFiles> getRequestFilesForYear(int year) {
        return adjustmentRequestFilesMapper.findByYearAndStatus(year, "1");
    }

    // 新增方法：获取申请书文件路径
    public Path getRequestFilePath(String filename) {
        return requestFilesLocation.resolve(filename).normalize();
    }
    
    public List<AdjustmentFile> getFilesByTypeAndEmployee(String fileType, String employeeEmail, int fileYear) {
        return adjustmentFileMapper.findFilesByTypeAndEmployee(fileType, employeeEmail, fileYear);
    }
    
    public Map<Integer, List<AdjustmentFile>> getResultFilesGroupedByYear(String employeeEmail) {
        List<AdjustmentFile> files = adjustmentFileMapper.findResultFilesByEmployeeEmail(employeeEmail);
        Map<Integer, List<AdjustmentFile>> filesByYear = new TreeMap<>(Collections.reverseOrder());
        for (AdjustmentFile file : files) {
            filesByYear.computeIfAbsent(file.getFileYear(), k -> new ArrayList<>()).add(file);
        }
        return filesByYear;
    }


    
}
