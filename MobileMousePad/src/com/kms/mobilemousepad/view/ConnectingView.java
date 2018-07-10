package com.kms.mobilemousepad.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.kms.mobilemousepad.custom.LoadingView;
import com.kms.mobilemousepad.custom.MarginPanel;
import com.kms.mobilemousepad.program_view.FrameLocationType;
import com.kms.mobilemousepad.program_view.ProgramView;
import com.kms.mobilemousepad.utils.CommonComp;
import com.kms.mobilemousepad.utils.DeviceSizeCal;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.layout.linear.LinearLayout;
import com.mommoo.flat.layout.linear.Orientation;
import com.mommoo.flat.layout.linear.constraints.LinearConstraints;
import com.mommoo.flat.layout.linear.constraints.LinearSpace;

public class ConnectingView extends FlatPanel implements ProgramView {
	ConnectingView() {
		setLayout(new LinearLayout(Orientation.VERTICAL, 0));
		add(new LoadingView(), new LinearConstraints(2, LinearSpace.MATCH_PARENT));
		add(createButtonView(), new LinearConstraints(1, LinearSpace.MATCH_PARENT));
	}
	
	public LoadingView getLoadingView() {
		return (LoadingView)getComponent(0);
	}
	
	void addCancelButtonListener(ActionListener cancelButtonListener) {
		((JButton)((Container)getComponent(1)).getComponent(0)).addActionListener(cancelButtonListener);
	}
	
	private Container createButtonView() {
		MarginPanel buttonView = new MarginPanel(0.2f, 0.2f, 0.2f, 0.2f);
		buttonView.setLayout(new BorderLayout());
		buttonView.add(CommonComp.createCommonBt("CANCEL"));
		return buttonView;
	}
	
	@Override
	public Component getView() {
		return this;
	}

	@Override
	public Dimension getViewSize() {
		return DeviceSizeCal.ratioToDeviceSize(0.3f, 0.3f);
	}

	@Override
	public FrameLocationType getFrameLocation() {
		return FrameLocationType.CENTER;
	}
}
