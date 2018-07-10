package com.kms.mobilemousepad.utils.font;

public enum FontType {
	DEFAULT("ART.TTF");
	
	public final String FILE_NAME;
	
	private FontType(String fileName) {
		this.FILE_NAME = fileName;
	}
}
