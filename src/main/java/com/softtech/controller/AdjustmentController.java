package com.softtech.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
 * 年末調整コントローラー
 */
@Controller
public class AdjustmentController {
    private static final Logger logger = LoggerFactory.getLogger(AdjustmentController.class);

    @Autowired
    private AdjustmentService adjustmentService;

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

        // 履歴ファイルを年度ごとに取得しモデルに追加
        Map<Integer, List<AdjustmentFile>> historiesByYear = adjustmentService.getResultFilesGroupedByYear(employeeID);
        model.addAttribute("historiesByYear", historiesByYear);
        logger.debug("履歴ファイル取得完了: {}", historiesByYear);

        // 現在の年度の申請書ファイルを取得しモデルに追加
        List<AdjustmentRequestFiles> requestFiles = adjustmentService.getRequestFilesForYear(currentYear);
        model.addAttribute("requestFiles", requestFiles);
        if (requestFiles != null) {
            for (AdjustmentRequestFiles rf : requestFiles) {
                logger.debug("申請書ファイル: {}, ステータス: {}", rf.getFileName(), rf.getFileULStatus());
            }
        }

        // 現在の年度の結果ファイルを取得しモデルに追加
        List<AdjustmentFile> resultFiles = adjustmentService.getFilesByTypeAndEmployee("resultType", employeeID, currentYear);
        model.addAttribute("resultFiles", resultFiles);
        logger.debug("現在年度のresultTypeファイル: {}", resultFiles);

        // 現在の年度の詳細ファイルを取得しモデルに追加
        List<AdjustmentFile> detailFiles = adjustmentService.getFilesByTypeAndEmployee("detailType", employeeID, currentYear);
        model.addAttribute("detailFiles", detailFiles);
        logger.debug("現在年度のdetailTypeファイル: {}", detailFiles);

        // 現在の年度をモデルに追加
        model.addAttribute("currentYear", currentYear);
        return "ems/adjustment"; // 調整ページのビュー名を返す
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
            // ファイルと詳細情報を保存するサービスメソッドを呼び出す
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
            // ファイルをダウンロード可能なレスポンスを返す
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
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
    public ResponseEntity<Resource> downloadUserFile(@PathVariable("fileType") String fileType,
            @PathVariable("fileYear") int fileYear, @PathVariable("employeeID") String employeeID,
            @PathVariable("filename") String filename) {
        try {
            // ユーザーファイルをリソースとして取得
            Resource resource = adjustmentService.loadUserFileAsResource(fileType, fileYear, employeeID, filename);
            // ファイルをダウンロード可能なレスポンスを返す
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
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
            // ログインしていない場合はエラーレスポンスを返す
            return ResponseEntity.badRequest().body("{\"error\":\"ユーザーがログインしていません。\"}");
        }

        // 現在の年度を取得
        int currentYear = java.time.LocalDate.now().getYear();
        try {
            // 調整確定処理を実行するサービスメソッドを呼び出す
            adjustmentService.finalizeAdjustment(employeeID, currentYear);
            return ResponseEntity.ok("{\"message\":\"確定処理が完了しました。\"}");
        } catch (Exception e) {
            // 例外が発生した場合はエラーレスポンスを返す
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"確定処理中にエラーが発生しました: " + e.getMessage() + "\"}");
        }
    }

    /**
     * レスポンスメッセージを生成するメソッド
     *
     * @param message メッセージ内容
     * @return メッセージを含むマップ
     */
    private Map<String, String> createResponse(String message) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }
}
