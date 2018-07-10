package com.kms.mobilemousepad.view;

import com.kms.mobilemousepad.network.Receiver;
import com.kms.mobilemousepad.program_view.ProgramView;

public class ExecutingViewBinder implements ViewBinder {
	private Receiver receiver;
	private ExecutingView executingView;
	
	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}
	
	@Override
	public ProgramView bindedView() {
		if(executingView!=null)	return executingView;
		executingView = new ExecutingView();
		executingView.addQuitButtonListener(e->receiver.disconnectNormally());
		
		return executingView;
	}
}
