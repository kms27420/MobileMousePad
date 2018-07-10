package com.kms.mobilemousepad.program_view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.SwingUtilities;

import com.kms.mobilemousepad.utils.DeviceSizeCal;
import com.mommoo.flat.frame.FlatFrame;

public class ProgramFrame {	
	public static final ProgramFrame INSTANCE = new ProgramFrame();
	
	private final FlatFrame FRAME = new FlatFrame();
	private final List<ProgramView> PREVIOUS_VIEW_LIST = new ArrayList<>();
	private ProgramView currentView;
	
	private ProgramFrame() {
//		FRAME.setIconImage(ImageProvider.getIconImage(IconType.TITLE));
//		FRAME.setProcessIconImage(ImageProvider.getIconImage(IconType.TITLE));
//		FRAME.setTitleBarColor(ColorProvider.getTitleBarColor());
		FRAME.setTitle("Mouse Adapter");
		FRAME.removeControlPanel();
		FRAME.setWindowExit(true);
		FRAME.setResizable(true);
		
		FRAME.getContainer().setLayout(new BorderLayout());
	}
	
	public void showPreviousView() {
		ProgramView latestPreviousView = getLatestPreviousView();
		PREVIOUS_VIEW_LIST.remove(latestPreviousView);
		
		currentView = latestPreviousView;
		showCurrentView();
		
		latestPreviousView = null;
	}
	
	public void showNextView(ProgramView nextView) {
		PREVIOUS_VIEW_LIST.add(currentView);
		currentView = nextView;
		showCurrentView();
	}
	
	public void showWithoutSaveCurrentView(ProgramView nextView) {
		currentView = nextView;
		showCurrentView();
	}
	
	public void showCurrentView() {
		showOnScreen(currentView);
	}
	
	public void hide() {
		FRAME.hide();
	}
	
	private void showOnScreen(ProgramView programView) {
		if(programView == null)	System.exit(0);
		
		SwingUtilities.invokeLater(()->{
			FRAME.hide();
			
			FRAME.getContainer().removeAll();
			FRAME.getContainer().setPreferredSize(programView.getViewSize());
			FRAME.pack();
			FRAME.setLocation(convertFrameLocation(programView.getFrameLocation()));
			FRAME.getContainer().add(programView.getView());
			
			FRAME.show();
		});
	}
	
	private ProgramView getLatestPreviousView() {
		Iterator<ProgramView> iterator = PREVIOUS_VIEW_LIST.iterator();
		ProgramView latestPreviousView = null;
		while(iterator.hasNext()) {
			latestPreviousView = iterator.next();
		}
		return latestPreviousView;
	}
	
	private Point convertFrameLocation(FrameLocationType type) {
		Dimension frameSize = FRAME.getSize();
		Dimension deviceSize = DeviceSizeCal.ratioToDeviceSize(1f, 1f);
		int x = (deviceSize.width-frameSize.width)/2 + (deviceSize.width-frameSize.width)*type.x/2;
		int y = (deviceSize.height-frameSize.height)/2 + (deviceSize.height-frameSize.height)*type.y/2;
		
		return new Point(x, y);
	}
}
