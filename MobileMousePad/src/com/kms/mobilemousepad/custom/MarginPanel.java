package com.kms.mobilemousepad.custom;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MarginPanel extends JPanel {
	private float topRate, leftRate, bottomRate, rightRate;
	private boolean isMarginChanged = true;
	
	public MarginPanel() {
		this(0, 0, 0, 0);
	}
	
	public MarginPanel(float topRate, float leftRate, float bottomRate, float rightRate) {
		setMarginRate(topRate, leftRate, bottomRate, rightRate);
		setOpaque(false);
	}
	
	public void setMarginRate(float topRate, float leftRate, float bottomRate, float rightRate) {
		if(this.topRate == topRate && this.leftRate == leftRate 
				&& this.bottomRate == bottomRate && this.rightRate == rightRate)	return;
		
		isMarginChanged = true;
		this.topRate = topRate;
		this.leftRate = leftRate;
		this.bottomRate = bottomRate;
		this.rightRate = rightRate;
	}
	
	private int previousWidth, previousHeight;
	private boolean isSizeChanged() {
		return previousWidth!=getWidth() || previousHeight!=getHeight();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if(!isMarginChanged && !isSizeChanged())	return;
		
		isMarginChanged = false;
		previousWidth = getWidth();
		previousHeight = getHeight();
		setBorder(new EmptyBorder((int)(topRate*previousHeight), (int)(leftRate*previousWidth), 
				(int)(bottomRate*previousHeight), (int)(rightRate*previousWidth)));
	}
}
