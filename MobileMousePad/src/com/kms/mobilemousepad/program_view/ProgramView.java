package com.kms.mobilemousepad.program_view;

import java.awt.Component;
import java.awt.Dimension;

public interface ProgramView {
	public Component getView();
	public Dimension getViewSize();
	public FrameLocationType getFrameLocation();
}
