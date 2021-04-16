package com.softtech.util;

import java.util.List;

import com.softtech.actionForm.WorkDetail;

/**
 * 概要：勤怠リストのDownload
 *
 * 作成者：馬＠ソフトテク
 * 作成日：2021/04/10
 */

public class HSSFWorkbook {

	public HSSFWorkbook downloadExcel(List<WorkDetail> list){
        String[] excelHeader = { "社員id", "社員氏名","対象年月","稼働時間","定期券変更","他の交通費(円)"};
        HSSFWorkbook wb = new HSSFWorkbook();
        //エラー？？？？
        HSSFSheet sheet = wb.createSheet("勤怠リスト");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(cell.getColumnIndex(), 100 * 50);
        }

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            WorkDetail workDetail = list.get(i);
            row.createCell(0).setCellValue(workDetail.getEmployeeID());
            row.createCell(1).setCellValue(workDetail.getEmployeeName());
            row.createCell(2).setCellValue(workDetail.getWorkMonth());
            row.createCell(3).setCellValue(workDetail.getWorkTime());
            row.createCell(4).setCellValue(workDetail. getTransportExpense());
            row.createCell(5).setCellValue(workDetail.getTransport());
        }
        return wb;
    }

}





