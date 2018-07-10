package com.kms.mobilemousepad.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.text.JTextComponent;

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
import com.mommoo.flat.text.textfield.FlatTextField;

public class PortSettingView extends FlatPanel implements ProgramView {
	PortSettingView() {
		setLayout(new LinearLayout(Orientation.VERTICAL, 0));
		add(createTextFieldView(), new LinearConstraints(2, LinearSpace.MATCH_PARENT));
		add(createButtonView(), new LinearConstraints(1, LinearSpace.MATCH_PARENT));
	}
	
	void addConfirmButtonListener(ActionListener confirmButtonListener) {
		((JButton)((Container)getComponent(1)).getComponent(0)).addActionListener(confirmButtonListener);
	}
	
	void addCancelButtonListener(ActionListener cancelButtonListener) {
		((JButton)((Container)getComponent(1)).getComponent(1)).addActionListener(cancelButtonListener);
	}
	
	String getEnteredPortNum() {
		Component tf = ((Container)getComponent(0)).getComponent(1);
		if(tf instanceof JTextComponent)	return ((JTextComponent) tf).getText();
		if(tf instanceof FlatTextField)	return ((FlatTextField) tf).getText();
		
		return null;
	}
	
	private Container createTextFieldView() {
		MarginPanel textFieldView = new MarginPanel(0.3f, 0.1f, 0.3f, 0.1f);
		textFieldView.setLayout(new LinearLayout(Orientation.HORIZONTAL, 0));
		textFieldView.add(CommonComp.createCommonLb("Please enter the port number."), new LinearConstraints(1, LinearSpace.MATCH_PARENT));
		textFieldView.add(CommonComp.createCommonTf(false), new LinearConstraints(1, LinearSpace.MATCH_PARENT));
		
		return textFieldView;
	}
	
	private Container createButtonView() {
		MarginPanel buttonView = new MarginPanel(0.1f, 0.1f, 0.1f, 0.1f);
		buttonView.setLayout(new LinearLayout(Orientation.HORIZONTAL, 1));
		buttonView.add(CommonComp.createCommonBt("CONFIRM"), new LinearConstraints(1, LinearSpace.MATCH_PARENT));
		buttonView.add(CommonComp.createCommonBt("CANCEL"), new LinearConstraints(1, LinearSpace.MATCH_PARENT));
		
		return buttonView;
	}
	
	@Override
	public Component getView() {
		return this;
	}

	@Override
	public Dimension getViewSize() {
		return DeviceSizeCal.ratioToDeviceSize(0.3f, 0.25f);
	}

	@Override
	public FrameLocationType getFrameLocation() {
		return FrameLocationType.CENTER;
	}

}
