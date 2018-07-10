package com.kms.mobilemousepad.view;

import com.kms.mobilemousepad.network.wifi.WifiConnector;
import com.kms.mobilemousepad.program_view.AlertFrame;
import com.kms.mobilemousepad.program_view.ProgramFrame;
import com.kms.mobilemousepad.program_view.ProgramView;
import com.kms.mobilemousepad.utils.PortChecker;

public class PortSettingViewBinder implements ViewBinder {
	private final WifiConnector WIFI_CONNECTOR = new WifiConnector();
	private PortSettingView portSettingView;
	
	@Override
	public ProgramView bindedView() {
		if(portSettingView!=null)	return portSettingView;
		
		portSettingView = new PortSettingView();
		portSettingView.addConfirmButtonListener(e->setPortAndTryToConnect());
		portSettingView.addCancelButtonListener(e->ProgramFrame.INSTANCE.showPreviousView());
		
		return portSettingView;
	}
	
	private void setPortAndTryToConnect() {
		int port = -1;
		if(!PortChecker.isNumeric(portSettingView.getEnteredPortNum()))	
			AlertFrame.INSTANCE.alert("Enter the only number. (range : 49152~65534)");
		else if(!PortChecker.isValid(port=Integer.parseInt(portSettingView.getEnteredPortNum())))
			AlertFrame.INSTANCE.alert("Check the range of port. (range : 49152~65534)");
		else {
			WIFI_CONNECTOR.setPort(port);
			WIFI_CONNECTOR.startToConnect();
		}
	}
}
