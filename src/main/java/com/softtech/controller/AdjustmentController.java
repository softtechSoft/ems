package com.softtech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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

/**
 * 調整コントローラー
 */
@Controller
public class AdjustmentController {

    @Autowired
    private AdjustmentService adjustmentService;

    /**
     * 調整ページを表示する
     */
    @GetMapping("/adjustment")
    public String showAdjustmentPage(Model model, HttpSession session) {
        String employeeEmail = (String) session.getAttribute("userMailAdress");
        if (employeeEmail == null) {
            return "redirect:/login";
        }
        int currentYear = java.time.LocalDate.now().getYear();
        model.addAttribute("currentYear", currentYear);

        // 履歴ファイルを取得（resultTypeのみ）
        Map<Integer, List<AdjustmentFile>> historiesByYear = adjustmentService.getResultFilesGroupedByYear(employeeEmail);
        model.addAttribute("historiesByYear", historiesByYear);

        // 申請書を取得
        List<AdjustmentRequestFiles> requestFiles = adjustmentService.getRequestFilesForYear(currentYear);
        model.addAttribute("requestFiles", requestFiles);

        // 現在の年度のresultTypeファイルを取得
        List<AdjustmentFile> resultFiles = adjustmentService.getFilesByTypeAndEmployee("resultType", employeeEmail,
                currentYear);
        model.addAttribute("resultFiles", resultFiles);

        // 現在の年度のdetailTypeファイルを取得
        List<AdjustmentFile> detailFiles = adjustmentService.getFilesByTypeAndEmployee("detailType", employeeEmail,
                currentYear);
        model.addAttribute("detailFiles", detailFiles);

        return "ems/adjustment";
    }


    /**
     * ファイルと詳細を保存する
     */
    @PostMapping("/saveFileAndDetail")
    @ResponseBody
    public ResponseEntity<?> handleSaveFileAndDetail(@RequestParam("files") MultipartFile[] files, HttpSession session) {
        String employeeEmail = (String) session.getAttribute("userMailAdress");
        if (employeeEmail == null) {
            return ResponseEntity.badRequest()
                    .body(createResponse("ユーザーがログインしていません。"));
        }
        try {
            adjustmentService.saveFilesAndDetails(files, employeeEmail, "detailType");
            return ResponseEntity.ok(createResponse("ファイルが正常にアップロードされました。"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(createResponse("ファイルのアップロード中にエラーが発生しました: " + e.getMessage()));
        }
    }

    /**
     * ファイルをダウンロードする
     */
    @GetMapping("/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadRequestFile(@PathVariable("filename") String filename) {
        try {
            Resource resource = adjustmentService.loadRequestFileAsResource(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * ユーザーファイルをダウンロードする
     */
    @GetMapping("/download/{fileYear}/{employeeEmail}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadUserFile(@PathVariable("fileYear") int fileYear,
            @PathVariable("employeeEmail") String employeeEmail, @PathVariable("filename") String filename) {
        try {
            Resource resource = adjustmentService.loadUserFileAsResource(fileYear, employeeEmail, filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * レスポンスメッセージを作成する
     */
    private Map<String, String> createResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }
}
