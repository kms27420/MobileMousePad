package com.kms.mobilemousepad.network.wifi;

import com.kms.mobilemousepad.network.MConnector;
import com.kms.mobilemousepad.network.Receiver;

public class WifiConnector extends MConnector {
	private final Broadcaster BROADCASTER = new Broadcaster();
	private final TCPConnector TCP_CONNECTOR = new TCPConnector(this);
	
	private boolean isPortChanged;
	
	public void setPort(int port) {
		isPortChanged = true;
		BROADCASTER.setPort(port);
		TCP_CONNECTOR.setPort(port);
	}
	
	@Override
	protected Receiver connectToMobile() throws Exception {
		if(isPortChanged)	BROADCASTER.broadcast();
		isPortChanged = false;
		return TCP_CONNECTOR.connectToMobile();
	}

	@Override
	public void disconnectNomally() {
		super.disconnectNomally();
		BROADCASTER.closeBroadcastSocket();
		TCP_CONNECTOR.closeServerSocket();
	}
}
