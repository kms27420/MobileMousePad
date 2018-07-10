package com.kms.mobilemousepad.tester;

import java.net.ServerSocket;

public class Main {
	public static void main(String[] args) {
		try {
			new ServerSocket(-1).close();
		} catch(Exception e) {e.printStackTrace();}
	}
}
