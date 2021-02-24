package com.softtech.entity;

public class Ofcfunction {
	private String functionName;
	private String functionText;
	private String functionLink;

	public Ofcfunction() {

	}

	public Ofcfunction(String functionText, String functionLink, String functionName) {
		super();
		this.functionText = functionText;
		this.functionLink = functionLink;
		this.functionName = functionName;
	}

	public String getFunctionText() {
		return functionText;
	}

	public void setFunctionText(String functionText) {
		this.functionText = functionText;
	}

	public String getFunctionLink() {
		return functionLink;
	}

	public void setFunctionLink(String functionLink) {
		this.functionLink = functionLink;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
}
