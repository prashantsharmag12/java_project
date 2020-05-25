package com.semanticsquare.thrillo.constants;

public enum UserType {
	USER("user"),
	EDITOR("editor"),
	CHIEF_EDITOR("chiefeditor");
	private UserType(String value) {
		this.value=value;
	}
	private String value;
	public String getValue() {
		return value;
	}
}
