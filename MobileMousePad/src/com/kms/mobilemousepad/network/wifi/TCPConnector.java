package com.kms.mobilemousepad.network.wifi;

import java.io.IOException;
import java.net.ServerSocket;

import com.kms.mobilemousepad.network.Receiver;

public class TCPConnector {
	private final WifiReceiver WIFI_RECEIVER;
	private ServerSocket server;
	private int port;
	
	public TCPConnector(WifiConnector reconnector) {
		WIFI_RECEIVER = new WifiReceiver(reconnector);
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public Receiver connectToMobile() throws IOException {
		server = new ServerSocket(port);
		server.setSoTimeout(10 * 1000);
		WIFI_RECEIVER.setMSocket(server.accept());
		closeServerSocket();
		
		return WIFI_RECEIVER;
	}
	
	public void closeServerSocket() {
		if(server!=null && !server.isClosed())	try{server.close();} catch(IOException e) {}
		server = null;
	}
}
