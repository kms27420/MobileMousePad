package com.kms.mobilemousepad.network;

import com.kms.mobilemousepad.custom.LoadingViewControler;
import com.kms.mobilemousepad.program_view.AlertFrame;
import com.kms.mobilemousepad.program_view.ProgramFrame;
import com.kms.mobilemousepad.view.ConnectingView;
import com.kms.mobilemousepad.view.ConnectingViewBinder;
import com.kms.mobilemousepad.view.ExecutingViewBinder;

public abstract class MConnector {
	private final ConnectingView CONN_VIEW = (ConnectingView)new ConnectingViewBinder(this).bindedView();
	private final LoadingViewControler LOADING_VIEW_CONTROLER = new LoadingViewControler(CONN_VIEW.getLoadingView());
	private final ExecutingViewBinder EXECUTING_VIEW_BINDER = new ExecutingViewBinder();
	private boolean isDisconnectedNormally;
	
	protected void onStartToConnect() {
		isDisconnectedNormally = false;
		ProgramFrame.INSTANCE.showNextView(CONN_VIEW);
		LOADING_VIEW_CONTROLER.startLoadingProcess();
	}
	
	protected abstract Receiver connectToMobile() throws Exception;
	
	public void disconnectNomally() {
		isDisconnectedNormally = true;
	}
	
	public final void startToConnect() {
		onStartToConnect();
		new Thread(()->{
			try {
				Receiver receiver = connectToMobile();
				EXECUTING_VIEW_BINDER.setReceiver(receiver);
				LOADING_VIEW_CONTROLER.endLoadingProcess();
				ProgramFrame.INSTANCE.showWithoutSaveCurrentView(EXECUTING_VIEW_BINDER.bindedView());
				receiver.startToReceive();
			} catch(Exception e) {
				if(isDisconnectedNormally)	onDisconnectedNormally();
				else	onDisconnectedAbnormally();
			}
		}).start();
	}
	
	private void onDisconnectedAbnormally() {
		disconnectNomally();
		onDisconnectedNormally();
		AlertFrame.INSTANCE.alert("Connection failed. Check the WIFI statement.");
	}
	
	private void onDisconnectedNormally() {
		LOADING_VIEW_CONTROLER.endLoadingProcess();
		ProgramFrame.INSTANCE.showPreviousView();
	}
}
