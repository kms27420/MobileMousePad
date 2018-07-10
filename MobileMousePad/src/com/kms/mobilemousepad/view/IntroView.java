package com.kms.mobilemousepad.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.kms.mobilemousepad.program_view.FrameLocationType;
import com.kms.mobilemousepad.program_view.ProgramView;
import com.kms.mobilemousepad.utils.CommonComp;
import com.kms.mobilemousepad.utils.DeviceSizeCal;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.layout.linear.LinearLayout;
import com.mommoo.flat.layout.linear.Orientation;
import com.mommoo.flat.layout.linear.constraints.LinearConstraints;
import com.mommoo.flat.layout.linear.constraints.LinearSpace;

public class IntroView extends FlatPanel implements ProgramView {
	IntroView() {
		setLayout(new LinearLayout(Orientation.VERTICAL, 1));
		add(CommonComp.createCommonBt("WIFI CONNECT"), new LinearConstraints(1, LinearSpace.MATCH_PARENT));
		add(CommonComp.createCommonBt("BLUETOOTH CONNECT"), new LinearConstraints(1, LinearSpace.MATCH_PARENT));
		add(CommonComp.createCommonBt("QUIT"), new LinearConstraints(1, LinearSpace.MATCH_PARENT));
	}
	
	void addWifiButtonListener(ActionListener wifiButtonListener) {
		((JButton)getComponent(0)).addActionListener(wifiButtonListener);
	}
	
	void addBluetoothButtonListener(ActionListener bluetoothButtonListener) {
		((JButton)getComponent(1)).addActionListener(bluetoothButtonListener);
	}
	
	void addQuitButtonListener(ActionListener quitButtonListener) {
		((JButton)getComponent(2)).addActionListener(quitButtonListener);
	}

	@Override
	public Component getView() {
		return this;
	}

	@Override
	public Dimension getViewSize() {
		return DeviceSizeCal.ratioToDeviceSize(0.2f, 0.5f);
	}

	@Override
	public FrameLocationType getFrameLocation() {
		return FrameLocationType.CENTER;
	}
}
