package com.softtech.entity;

import java.util.Date;

public class AdjustmentRequestFiles {
    private Integer fileID;
    private String employeeID;
    private String employeeEmail;
    private String fileName;
    private int fileYear;
    private String fileULStatus;
    private Date fileInsertDate;
    private Date fileUpdateDate;

    // Getters and Setters
    public Integer getFileID() { return fileID; }
    public void setFileID(Integer fileID) { this.fileID = fileID; }
    public String getEmployeeID() { return employeeID; }
    public void setEmployeeID(String employeeID) { this.employeeID = employeeID; }
    public String getEmployeeEmail() { return employeeEmail; }
    public void setEmployeeEmail(String employeeEmail) { this.employeeEmail = employeeEmail; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public int getFileYear() { return fileYear; }
    public void setFileYear(int fileYear) { this.fileYear = fileYear; }
    public String getFileULStatus() { return fileULStatus; }
    public void setFileULStatus(String fileULStatus) { this.fileULStatus = fileULStatus; }
    public Date getFileInsertDate() { return fileInsertDate; }
    public void setFileInsertDate(Date fileInsertDate) { this.fileInsertDate = fileInsertDate; }
    public Date getFileUpdateDate() { return fileUpdateDate; }
    public void setFileUpdateDate(Date fileUpdateDate) { this.fileUpdateDate = fileUpdateDate; }
}
