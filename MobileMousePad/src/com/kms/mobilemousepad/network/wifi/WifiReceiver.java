package com.kms.mobilemousepad.network.wifi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.kms.mobilemousepad.network.Receiver;

public class WifiReceiver extends Receiver {
	private Socket mSocket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	
	public WifiReceiver(WifiConnector reconnector) {
		super(reconnector);
	}
	
	public void setMSocket(Socket mSocket) {
		this.mSocket = mSocket;
		try {
			inputStream = new DataInputStream(mSocket.getInputStream());
			outputStream = new DataOutputStream(mSocket.getOutputStream());
		} catch (IOException e) {e.printStackTrace();}
	}
	
	private String buf;
	@Override
	protected String receive() throws Exception {
		buf = inputStream.readUTF();
		if(buf.charAt(0)=='q')	{
			disconnectNormally();
			throw new Exception();
		}
		return buf;
	}
	@Override
	public void disconnectNormally() {
		super.disconnectNormally();
		try {
			outputStream.writeUTF("q");
			outputStream.flush();
		} catch(IOException e) {}
		
		if(mSocket!=null && !mSocket.isClosed())	try{mSocket.close();} catch(IOException e) {}
		if(inputStream!=null)	try{inputStream.close();} catch(IOException e) {}
		if(outputStream!=null)	try{outputStream.close();} catch(IOException e) {}
		mSocket = null;
		inputStream = null;
		outputStream = null;
	}
}
