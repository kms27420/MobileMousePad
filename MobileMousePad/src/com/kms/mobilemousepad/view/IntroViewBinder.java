package com.kms.mobilemousepad.view;

import com.kms.mobilemousepad.network.MConnector;
import com.kms.mobilemousepad.network.bluetooth.BluetoothConnector;
import com.kms.mobilemousepad.program_view.ProgramFrame;
import com.kms.mobilemousepad.program_view.ProgramView;

public class IntroViewBinder implements ViewBinder {
	private final ProgramView PORT_SETTING_VIEW = new PortSettingViewBinder().bindedView();
	private final MConnector BLUETOOTH_CONNECTOR = new BluetoothConnector();
	private IntroView introView;
	
	@Override
	public ProgramView bindedView() {
		if(introView!=null)	return introView;
		
		introView = new IntroView();
		introView.addWifiButtonListener(e->ProgramFrame.INSTANCE.showNextView(PORT_SETTING_VIEW));
		introView.addBluetoothButtonListener(e->BLUETOOTH_CONNECTOR.startToConnect());
		introView.addQuitButtonListener(e->System.exit(0));
		
		return introView;
	}
}
