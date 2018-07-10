package com.kms.mobilemousepad.custom;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class LoadingView extends Component {
	private final int MAX_STICKS = 12;
	private final float OUTER_RAD_RATE = 0.3f;
	private final float INNER_RAD_RATE = OUTER_RAD_RATE * 2 /3f;
	private final float STICK_WIDTH_RATE = INNER_RAD_RATE / 3f;
	private final double START_POSI = Math.PI / 2;
	
	private Color defaultStickColor = Color.LIGHT_GRAY;
	private Color[] gradColors = new Color[]{
			new Color(232, 201, 201), new Color(255, 175, 175), new Color(255, 134, 136)
			};
	
	public LoadingView() {}
	public LoadingView(Color defaultStickColor, Color[] gradationColors) {
		setAnimationColors(defaultStickColor, gradationColors);
	}
	
	public void setAnimationColors(Color defaultStickColor, Color[] gradationColors) {
		this.defaultStickColor = defaultStickColor;
		this.gradColors = gradationColors;
	}
	
	private int currentPosi;
	public void animateLoading() {
		currentPosi = currentPosi+1>MAX_STICKS ? 1 : currentPosi+1;
		revalidate();
		repaint();
	}
	
	private final DrawingMaterial MATERIAL = new DrawingMaterial();
	
	private void drawInitialSticks(Graphics g) {
		g.setColor(defaultStickColor);
		((Graphics2D)g).setStroke(MATERIAL.stickStroke);
		for(int i=0; i<MAX_STICKS; i++) {
			g.drawLine(MATERIAL.cx, MATERIAL.cy, 
					(int)(MATERIAL.cx+MATERIAL.bigRad*Math.cos(START_POSI-2*Math.PI*i/MAX_STICKS)), 
					(int)(MATERIAL.cy-MATERIAL.bigRad*Math.sin(START_POSI-2*Math.PI*i/MAX_STICKS)));
		}
	}
	
	private void drawOrderedSticks(Graphics g) {
		int[] order = new int[gradColors.length];
		
		((Graphics2D)g).setStroke(MATERIAL.stickStroke);
		for(int i=0; i<order.length; i++) {
			order[i] = currentPosi+i>MAX_STICKS ? currentPosi+i-MAX_STICKS : currentPosi+i;
			g.setColor(gradColors[i]);
			g.drawLine(MATERIAL.cx, MATERIAL.cy, 
					(int)(MATERIAL.cx+MATERIAL.bigRad*Math.cos(START_POSI-2*Math.PI*order[i]/MAX_STICKS)), 
					(int)(MATERIAL.cy-MATERIAL.bigRad*Math.sin(START_POSI-2*Math.PI*order[i]/MAX_STICKS)));
		}
	}
	
	private void drawInnerCircle(Graphics g) {
		g.setColor(MATERIAL.getParentBg());
		((Graphics2D)g).setStroke(MATERIAL.originalStroke);
		g.fillOval(MATERIAL.cx-MATERIAL.smallRad, MATERIAL.cy-MATERIAL.smallRad, 2*MATERIAL.smallRad, 2*MATERIAL.smallRad);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		MATERIAL.updateDrawingMaterial(g);
		drawInitialSticks(g);
		drawOrderedSticks(g);
		drawInnerCircle(g);
	}
	
	private class DrawingMaterial {
		private int width, height;
		private int cx, cy;
		private int bigRad, smallRad;
		private Stroke originalStroke, stickStroke;
		
		private boolean isSizeChanged() {
			return width != getWidth() || height != getHeight();
		}
		
		private void updateDrawingMaterial(Graphics g) {
			if(!isSizeChanged())	return;
			width = getWidth();
			height = getHeight();
			cx = width/2;
			cy = height/2;
			bigRad = (int)(height * OUTER_RAD_RATE);
			smallRad = (int)(height * INNER_RAD_RATE);
			originalStroke = ((Graphics2D)g).getStroke();
			stickStroke = new BasicStroke(height * STICK_WIDTH_RATE);
		}
		
		private Color getParentBg() {
			Component parent = LoadingView.this;
			Color bg = parent.getBackground();
			while(bg == null || !parent.isOpaque()) {
				try { 
					parent = parent.getParent();
					bg = parent.getBackground();
				} catch(Exception e) {
					bg = Color.WHITE;
					e.printStackTrace();
					break;
				}
			}
			return bg;
		}
	}
}
