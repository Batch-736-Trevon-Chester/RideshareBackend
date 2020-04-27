package com.revature.beans;

public class StringToJSON {
	
	String str;

	public StringToJSON(String str) {
		super();
		this.str = str;
	}
	
	public StringToJSON() {}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@Override
	public String toString() {
		return "StringToJSON [str=" + str + "]";
	}
	
}
