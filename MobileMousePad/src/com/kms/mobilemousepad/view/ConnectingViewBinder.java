package com.kms.mobilemousepad.view;

import com.kms.mobilemousepad.network.MConnector;
import com.kms.mobilemousepad.program_view.ProgramView;

public class ConnectingViewBinder implements ViewBinder {
	private final MConnector M_CONNECTOR;
	private ConnectingView connView;
	
	public ConnectingViewBinder(MConnector mConnector) {
		M_CONNECTOR = mConnector;
	}
	
	@Override
	public ProgramView bindedView() {
		if(connView!=null)	return connView;
		connView = new ConnectingView();
		connView.addCancelButtonListener(e->M_CONNECTOR.disconnectNomally());
		
		return connView;
	}
}
