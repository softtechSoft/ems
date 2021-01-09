package com.softtech.entity;

public class Ofcfunction 
{
	private String functionText;
	private String functionLink;
	
	public Ofcfunction()
	{
		
	}
	public Ofcfunction(String functionText, String functionLink) {
		super();
		this.functionText = functionText;
		this.functionLink = functionLink;
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
}
