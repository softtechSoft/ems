package com.softtech.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.AdjustmentFile;
import com.softtech.entity.AdjustmentRequestFiles;
import com.softtech.service.AdjustmentService;

@Controller
public class AdjustmentController {

    @Autowired
    private AdjustmentService adjustmentService;

    @GetMapping("/adjustment")
    public String showAdjustmentPage(Model model, HttpSession session) {
        String employeeEmail = (String) session.getAttribute("userMailAdress");  
        if (employeeEmail == null) {
            return "redirect:/login";
        }
        int currentYear = LocalDate.now().getYear();
        model.addAttribute("currentYear", currentYear);

        // 现有代码
        Map<Integer, List<AdjustmentFile>> historiesByYear = adjustmentService.getHistoricalFilesGroupedByYear(employeeEmail);
        model.addAttribute("historiesByYear", historiesByYear);

        List<AdjustmentRequestFiles> requestFiles = adjustmentService.getRequestFilesForYear(currentYear);
        model.addAttribute("requestFiles", requestFiles);

        // 新增代码：获取 resultType 的文件
        List<AdjustmentFile> resultFiles = adjustmentService.getFilesByTypeAndEmployee("resultType", employeeEmail, currentYear);
        model.addAttribute("resultFiles", resultFiles);

        return "ems/adjustment";
    }


    @PostMapping("/saveFileAndDetail")
    public ResponseEntity<?> handleSaveFileAndDetail(@RequestParam("files") MultipartFile[] files, HttpSession session) {
        String employeeEmail = (String) session.getAttribute("userMailAdress");
        if (employeeEmail == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Collections.singletonMap("error", "ユーザーがログインしていません。"));
        }
        int currentYear = LocalDate.now().getYear();
        try {
            adjustmentService.saveFilesAndDetails(files, employeeEmail, currentYear, "detailType"); 
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Collections.singletonMap("message", "ファイルが正常にアップロードされました。"));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Collections.singletonMap("error", "ファイルのアップロード中にエラーが発生しました: " + e.getMessage()));
        }
    }

    
    
 // 下载申请书文件
    @GetMapping("/download/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> downloadRequestFile(@PathVariable("filename") String filename) {
        try {
            Path file = adjustmentService.getRequestFilePath(filename);
            if (Files.exists(file)) {
                Resource resource = new UrlResource(file.toUri());
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/download/{fileYear}/{employeeEmail}/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> downloadUserFile(
            @PathVariable("fileYear") int fileYear,
            @PathVariable("employeeEmail") String employeeEmail,
            @PathVariable("filename") String filename) {
        try {
            Path file = adjustmentService.getFilePath(filename, employeeEmail, fileYear);
            if (Files.exists(file)) {
                Resource resource = new UrlResource(file.toUri());
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
