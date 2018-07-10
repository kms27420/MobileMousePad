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

public class ExecutingView extends FlatPanel implements ProgramView {
	ExecutingView() {
		setLayout(new LinearLayout(Orientation.HORIZONTAL, 0));
		add(CommonComp.createCommonBt("Quit"), new LinearConstraints(1, LinearSpace.MATCH_PARENT));
	}
	
	void addQuitButtonListener(ActionListener quitButtonListener) {
		((JButton)getComponent(0)).addActionListener(quitButtonListener);
	}
	
	@Override
	public Component getView() {
		return this;
	}

	@Override
	public Dimension getViewSize() {
		return DeviceSizeCal.ratioToDeviceSize(0.1f, 0.1f);
	}

	@Override
	public FrameLocationType getFrameLocation() {
		return FrameLocationType.NORTH_EAST;
	}

}
