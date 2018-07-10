package com.kms.mobilemousepad.network;

import com.kms.mobilemousepad.program_view.AlertFrame;
import com.kms.mobilemousepad.program_view.ProgramFrame;

public abstract class Receiver {
	private final MConnector RECONNECTOR;
	private final MouseControler MOUSE_CONTROLER = new MouseControler();
	
	private boolean isDisconnectedNormally;
	
	public Receiver(MConnector reconnector) {
		RECONNECTOR = reconnector;
	}
	
	protected abstract String receive() throws Exception;
	
	public void disconnectNormally() {
		isDisconnectedNormally = true;
	}
	
	public final void startToReceive() {
		onStartToReceive();
		new Thread(()->{
			try {
				while(true)	{
					MOUSE_CONTROLER.controlMouse(receive());
				}
			} catch(Exception e) {
				if(isDisconnectedNormally)	onDisconnectedNormally();
				else onDisconnectedAbnormally();
			}
		}).start();
	}
	
	private void onStartToReceive() {
		isDisconnectedNormally = false;
	}
	
	private void onDisconnectedAbnormally() {
		onDisconnectedNormally();
		RECONNECTOR.startToConnect();
		AlertFrame.INSTANCE.alert("Try to reconnect...");
	}
	
	private void onDisconnectedNormally() {
		ProgramFrame.INSTANCE.showPreviousView();
	}
}
