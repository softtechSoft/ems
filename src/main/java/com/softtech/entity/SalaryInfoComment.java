package com.softtech.entity;

public class SalaryInfoComment {

	private String columnName;
	private String comment;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "SalaryInfoComment [columnName=" + columnName + ", comment=" + comment + "]";
	}
}
