package com.kms.mobilemousepad.utils;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import com.mommoo.flat.frame.FlatFrame;

public class DeviceSizeCal {
	private static final Dimension DEVICE_SIZE = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize();
	
	private DeviceSizeCal() {}
	
	public static Dimension ratioToDeviceSize(float widthRate, float heightRate) {
		return new Dimension((int)(DEVICE_SIZE.width * widthRate), 
				(int)(DEVICE_SIZE.height * heightRate) - FlatFrame.getTitleBarHeight());
	}
}
