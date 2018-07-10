package com.kms.mobilemousepad.program_view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import com.kms.mobilemousepad.custom.MarginPanel;
import com.kms.mobilemousepad.utils.CommonComp;
import com.kms.mobilemousepad.utils.DeviceSizeCal;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.frame.FlatFrame;
import com.mommoo.flat.layout.linear.LinearLayout;
import com.mommoo.flat.layout.linear.Orientation;
import com.mommoo.flat.layout.linear.constraints.LinearConstraints;
import com.mommoo.flat.layout.linear.constraints.LinearSpace;

public class AlertFrame {
	public static final AlertFrame INSTANCE = new AlertFrame();
	
	private final FlatFrame FRAME = new FlatFrame();
	private final Dimension SIZE = DeviceSizeCal.ratioToDeviceSize(0.2f, 0.2f);
	
	private AlertFrame() {
		FRAME.setTitle("Alert");
		FRAME.removeControlPanel();
		FRAME.setWindowExit(false);
		FRAME.setResizable(true);
		
		FRAME.getContainer().setLayout(new BorderLayout());
		FRAME.getContainer().add(new AlertView());
		
		bindWindowCloser();
	}
	
	public void alert(String alertMsg) {
		FRAME.hide();
		((AlertView)FRAME.getContainer().getComponent(0)).setAlertText(alertMsg);
		FRAME.getContainer().setPreferredSize(SIZE);
		FRAME.pack();
		FRAME.setLocationOnScreenCenter();
		SwingUtilities.invokeLater(()->{
			FRAME.show();
		});
	}
	
	private void bindWindowCloser() {
		((AlertView)FRAME.getContainer().getComponent(0)).addConfirmButtonListener(e->FRAME.hide());
	}
	
	private static class AlertView extends FlatPanel {
		private AlertView() {
			setLayout(new LinearLayout(Orientation.VERTICAL, 0));
			add(createTextView(), new LinearConstraints(2, LinearSpace.MATCH_PARENT));
			add(createButtonView(), new LinearConstraints(1, LinearSpace.MATCH_PARENT));
		}
		
		private void addConfirmButtonListener(ActionListener confirmButtonListener) {
			((JButton)((Container)getComponent(1)).getComponent(0)).addActionListener(confirmButtonListener);
		}
		
		private void setAlertText(String text) {
			((JTextPane)((Container)getComponent(0)).getComponent(0)).setText(text);
		}
		
		private Container createTextView() {
			MarginPanel textView = new MarginPanel(0.2f,0.2f,0.2f,0.2f);
			textView.setLayout(new BorderLayout());
			textView.add(CommonComp.createCommonLb());
			return textView;
		}
		
		private Container createButtonView() {
			MarginPanel buttonView = new MarginPanel(0.2f,0.2f,0.2f,0.2f);
			buttonView.setLayout(new BorderLayout());
			buttonView.add(CommonComp.createCommonBt("Confirm"));
			((JButton)buttonView.getComponent(0)).addActionListener(e->INSTANCE.FRAME.hide());
			return buttonView;
		}
	}
}
