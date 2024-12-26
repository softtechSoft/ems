package com.softtech.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.softtech.entity.AdjustmentDetail;
import com.softtech.entity.AdjustmentFile;
import com.softtech.entity.AdjustmentRequestFiles;
import com.softtech.mapper.AdjustmentDetailMapper;
import com.softtech.service.AdjustmentService;

/**
 * 年末調整コントローラー
 * 
 * 年末調整画面の表示、ファイルのアップロード・ダウンロード・削除、
 * および確定処理などの機能を提供するコントローラークラス。
 */
@Controller
public class AdjustmentController {
    private static final Logger logger = LoggerFactory.getLogger(AdjustmentController.class);

    @Autowired
    private AdjustmentService adjustmentService;
    
    @Autowired
    private AdjustmentDetailMapper adjustmentDetailMapper;

    /**
     * 年末調整ページを表示するメソッド
     *
     * @param model   モデルオブジェクト
     * @param session HTTPセッション
     * @return 年末調整ページのビュー名
     */
    @GetMapping("/adjustment")
    public String showAdjustmentPage(Model model, HttpSession session) {
        // セッションからユーザーのメールアドレスと社員IDを取得
        String employeeEmail = (String) session.getAttribute("userMailAdress");
        String employeeID = (String) session.getAttribute("userEmoplyeeID");

        // ログイン情報が存在しない場合はログインページにリダイレクト
        if (employeeEmail == null || employeeID == null) {
            logger.debug("ログイン情報がありません。ログインページへリダイレクトします。");
            return "redirect:/login";
        }

        // 現在の年度を取得
        int currentYear = java.time.LocalDate.now().getYear();
        logger.debug("調整ページ表示 - メールアドレス: {}, 従業員ID: {}, 年: {}", employeeEmail, employeeID, currentYear);
        
        AdjustmentDetail detail = adjustmentDetailMapper.findByEmployeeIdAndYear(employeeID, String.valueOf(currentYear));
        boolean isFinalized = false;
        
        if (detail == null) {
            // 該当年度のdetailが無い場合、新規で作成しINSERT
            detail = new AdjustmentDetail();
            detail.setEmployeeID(employeeID);
            detail.setEmployeeEmail(employeeEmail);
            detail.setYear(String.valueOf(currentYear));
            detail.setUploadStatus("0"); // 未確定
            detail.setAdjustmentStatus("0");
            detail.setInsertDate(new Date());
            detail.setUpdateDate(new Date());
            adjustmentDetailMapper.insert(detail);
            logger.debug("新年度の調整詳細を初期化しました - 従業員ID: {}, 年: {}", employeeID, currentYear);
        } else {
            // 既存detailあり
            isFinalized = "1".equals(detail.getUploadStatus());
        }

        // 画面に確定ステータスを渡す
        model.addAttribute("isFinalized", isFinalized);
        logger.debug("調整が確定されているか: {}", isFinalized);

        // 履歴ファイルを年度ごとに取得しモデルに追加
        Map<Integer, List<AdjustmentFile>> historiesByYear = adjustmentService.getResultFilesGroupedByYear(employeeID);
        model.addAttribute("historiesByYear", historiesByYear);
        logger.debug("履歴ファイル取得完了: {}", historiesByYear);

        // 申請書ファイルを取得しモデルに追加
        List<AdjustmentRequestFiles> requestFiles = adjustmentService.getRequestFilesForYear(currentYear);
        model.addAttribute("requestFiles", requestFiles);
        if (requestFiles != null) {
            for (AdjustmentRequestFiles rf : requestFiles) {
                logger.debug("申請書ファイル: {}, ステータス: {}", rf.getFileName(), rf.getFileULStatus());
            }
        }

        // 結果ファイルを取得しモデルに追加
        List<AdjustmentFile> resultFiles = adjustmentService.getFilesByTypeAndEmployee("resultType", employeeID, currentYear);
        model.addAttribute("resultFiles", resultFiles);
        logger.debug("現在年度のresultTypeファイル: {}", resultFiles);

        // アップ済み詳細ファイルを取得しモデルに追加
        List<AdjustmentFile> detailFiles = adjustmentService.getFilesByTypeAndEmployee("detailType", employeeID, currentYear);
        model.addAttribute("detailFiles", detailFiles);
        logger.debug("現在年度のdetailTypeファイル: {}", detailFiles);

        // 現在の年度をモデルに追加
        model.addAttribute("currentYear", currentYear);
        return "ems/adjustment"; // adjustment.htmlを返す
    }

    /**
     * ファイルと詳細情報を保存するメソッド
     *
     * @param files    アップロードされたファイルの配列
     * @param session  HTTPセッション
     * @return 保存結果のレスポンスエンティティ
     */
    @PostMapping("/saveFileAndDetail")
    @ResponseBody
    public ResponseEntity<?> handleSaveFileAndDetail(@RequestParam("files") MultipartFile[] files, HttpSession session) {
        // セッションから社員IDとメールアドレスを取得
        String employeeID = (String) session.getAttribute("userEmoplyeeID");
        String employeeEmail = (String) session.getAttribute("userMailAdress");

        // ログイン情報が存在しない場合はエラーレスポンスを返す
        if (employeeID == null || employeeEmail == null) {
            return ResponseEntity.badRequest().body("{\"error\":\"ユーザーがログインしていません。\"}");
        }

        try {
            // サービスメソッドを呼び出して保存
            adjustmentService.saveFilesAndDetails(files, employeeID, employeeEmail, "detailType");
            return ResponseEntity.ok("{\"message\":\"ファイルのアップロードに成功しました。\"}");
        } catch (IOException e) {
            // 例外が発生した場合はエラーレスポンスを返す
            return ResponseEntity.badRequest().body("{\"error\":\"ファイルのアップロード中にエラーが発生しました: " + e.getMessage() + "\"}");
        }
    }

    /**
     * 申請書ファイルをダウンロードするメソッド
     *
     * @param filename ダウンロードするファイル名
     * @return ダウンロードするファイルのレスポンスエンティティ
     */
    @GetMapping("/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadRequestFile(@PathVariable("filename") String filename) {
        try {
            // 申請書ファイルをリソースとして取得
            Resource resource = adjustmentService.loadRequestFileAsResource(filename);
            
            ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                    .filename(filename, StandardCharsets.UTF_8)
                    .build();
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            // ファイルが見つからない場合は404レスポンスを返す
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * ユーザーファイルをダウンロードするメソッド
     *
     * @param fileType   ファイルの種類
     * @param fileYear   ファイルの年度
     * @param employeeID 従業員ID
     * @param filename   ダウンロードするファイル名
     * @return ダウンロードするファイルのレスポンスエンティティ
     */
    @GetMapping("/download/{fileType}/{fileYear}/{employeeID}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadUserFile(
            @PathVariable("fileType") String fileType,
            @PathVariable("fileYear") int fileYear,
            @PathVariable("employeeID") String employeeID,
            @PathVariable("filename") String filename) {
        try {
            // ユーザーファイルをリソースとして取得
            Resource resource = adjustmentService.loadUserFileAsResource(fileType, fileYear, employeeID, filename);
            
            ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                    .filename(filename, StandardCharsets.UTF_8)
                    .build();
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            // ファイルが見つからない場合は404レスポンスを返す
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 調整を確定する処理を実行するメソッド
     *
     * @param session HTTPセッション
     * @return 確定処理の結果を含むレスポンスエンティティ
     */
    @PostMapping("/finalizeAdjustment")
    @ResponseBody
    public ResponseEntity<?> finalizeAdjustment(HttpSession session) {
        // セッションから社員IDを取得
        String employeeID = (String) session.getAttribute("userEmoplyeeID");
        if (employeeID == null) {
            // ログインしていない場合はエラーレスポンス
            return ResponseEntity.badRequest().body("{\"error\":\"ユーザーがログインしていません。\"}");
        }

        // 現在の年度
        int currentYear = java.time.LocalDate.now().getYear();
        try {
            adjustmentService.finalizeAdjustment(employeeID, currentYear);
            return ResponseEntity.ok("{\"message\":\"確定処理が完了しました。\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"確定処理中にエラーが発生しました: " + e.getMessage() + "\"}");
        }
    }
    
    /**
     * アップ済みファイルを削除するメソッド
     * 
     * @param payload リクエストボディ（JSON形式）
     * @param session HTTPセッション
     * @return 削除結果のレスポンスエンティティ
     */
    @PostMapping("/deleteFile")
    @ResponseBody
    public ResponseEntity<?> deleteFile(@RequestBody Map<String, Object> payload, HttpSession session) {
        // JSONからパラメータを取得
        String fileName   = (String) payload.get("fileName");
        Object fileYearObj = payload.get("fileYear");
        Integer fileYear  = null;
        if (fileYearObj instanceof Number) {
            fileYear = ((Number) fileYearObj).intValue();
        } else if (fileYearObj instanceof String) {
            try {
                fileYear = Integer.parseInt((String) fileYearObj);
            } catch (NumberFormatException e) {
                logger.error("Invalid fileYear format: {}", fileYearObj);
            }
        }

        String employeeID = (String) payload.get("employeeID");
        String fileType   = (String) payload.get("fileType");

        logger.debug("Delete request: fileName={}, fileYear={}, employeeID={}, fileType={}",
                     fileName, fileYear, employeeID, fileType);

        // fileYearがnullの場合のチェック
        if (fileYear == null) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("error", "fileYear が無効です。"));
        }

        try {
            adjustmentService.deleteFile(employeeID, fileYear, fileName, fileType);
            return ResponseEntity.ok(Collections.singletonMap("message", "削除が成功しました。"));
        } catch (Exception e) {
            logger.error("ファイル削除エラー: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "ファイルの削除中にエラーが発生しました。"));
        }
    }

    /**
     * レスポンスメッセージを生成するユーティリティメソッド
     */
    private Map<String, String> createResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }
}
