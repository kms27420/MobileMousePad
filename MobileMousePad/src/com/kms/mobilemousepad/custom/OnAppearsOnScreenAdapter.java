package com.kms.mobilemousepad.custom;

import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

public abstract class OnAppearsOnScreenAdapter implements HierarchyListener {
	@Override
	public final void hierarchyChanged(HierarchyEvent e) {
		if(e.getChanged().isShowing() && e.getChanged().isVisible()) {
			onShowOnScreen();
			return;
		}
		if(!e.getChanged().isShowing() && !e.getChanged().isVisible()) {
			onHideFromScreen();
			return;
		}
	}
	
	protected abstract void onShowOnScreen();
	protected abstract void onHideFromScreen();
}
