package com.kms.mobilemousepad.utils;

public final class PortChecker {
	private PortChecker() {}
	
	public static boolean isNumeric(String port) {
		try {
			Integer.parseInt(port);
			return true;
		} catch(NumberFormatException e) {return false;}
	}
	
	public static boolean isValid(int port) {
		return port >= 49152 && port <= 65534;
	}
}
