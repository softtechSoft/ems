package com.softtech.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
        Map<Integer, List<AdjustmentFile>> historiesByYear = adjustmentService.getHistoricalFilesGroupedByYear(employeeEmail);
        model.addAttribute("historiesByYear", historiesByYear);
        return "ems/adjustment";
    }

    @PostMapping("/saveFileAndDetail")
    public ResponseEntity<?> handleSaveFileAndDetail(@RequestParam("files") MultipartFile[] files, HttpSession session) {
        String employeeEmail = (String) session.getAttribute("userMailAdress");
        if (employeeEmail == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"error\": \"User is not logged in.\"}");
        }
        int currentYear = LocalDate.now().getYear();
        try {
            adjustmentService.saveFilesAndDetails(files, employeeEmail, currentYear, "detailType"); 
            return ResponseEntity.ok("{\"message\": \"File uploaded successfully\"}");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error uploading file: " + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/download/{fileYear}/{employeeEmail}/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(
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
