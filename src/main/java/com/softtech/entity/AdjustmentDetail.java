package com.softtech.entity;

import java.util.Date;

public class AdjustmentDetail {
    private Integer id;
    private String employeeID;
    private String employeeEmail;
    private String year;
    private String uploadStatus;
    private String adjustmentStatus;
    private Date insertDate;
    private Date updateDate;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getEmployeeID() { return employeeID; }
    public void setEmployeeID(String employeeID) { this.employeeID = employeeID; }
    public String getEmployeeEmail() { return employeeEmail; }
    public void setEmployeeEmail(String employeeEmail) { this.employeeEmail = employeeEmail; }
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
    public String getUploadStatus() { return uploadStatus; }
    public void setUploadStatus(String uploadStatus) { this.uploadStatus = uploadStatus; }
    public String getAdjustmentStatus() { return adjustmentStatus; }
    public void setAdjustmentStatus(String adjustmentStatus) { this.adjustmentStatus = adjustmentStatus; }
    public Date getInsertDate() { return insertDate; }
    public void setInsertDate(Date insertDate) { this.insertDate = insertDate; }
    public Date getUpdateDate() { return updateDate; }
    public void setUpdateDate(Date updateDate) { this.updateDate = updateDate; }
}
