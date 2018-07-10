package com.kms.mobilemousepad.network;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MouseControler {
	public static final char LEFT_DOWN_PREFIX = 'l';
    public static final char LEFT_UP_PREFIX = 'L';
    public static final char CLICK_PREFIX = 'c';
    public static final char RIGHT_CLICK_PREFIX = 'r';
    public static final char MOVE_PREFIX = 'm';
    public static final char SCROLL_PREFIX = 's';
    public static final char KEYBOARD_PREFIX = 'k';
    
	private Robot robot;
	
	MouseControler() {
		try {
			robot = new Robot();
		} catch(AWTException e) {}
	}
	
	void controlMouse(String utf) {
		try {	
			switch(utf.charAt(0)) {
			case MOVE_PREFIX :
				Point mousePointer = MouseInfo.getPointerInfo().getLocation();
				robot.mouseMove(mousePointer.x + Integer.parseInt(utf.substring(1, 6)), mousePointer.y + Integer.parseInt(utf.substring(6, 11)));
				break;
			case SCROLL_PREFIX :
				robot.mouseWheel(Integer.parseInt(utf.substring(1, 6)));
			case CLICK_PREFIX :
				robot.mousePress(MouseEvent.BUTTON1_MASK);
				robot.mouseRelease(MouseEvent.BUTTON1_MASK);
				break;
			case RIGHT_CLICK_PREFIX :
				robot.mousePress(MouseEvent.BUTTON3_MASK);
				robot.mouseRelease(MouseEvent.BUTTON3_MASK);
				break;
			case LEFT_DOWN_PREFIX :
				robot.mousePress(MouseEvent.BUTTON1_MASK);
				break;
			case LEFT_UP_PREFIX :
				robot.mouseRelease(MouseEvent.BUTTON1_MASK);
				break;
			case KEYBOARD_PREFIX :
				List<Integer> shortcut = KeyConverter.convertToKeyCode(utf.substring(1));
				for(int key : shortcut)	robot.keyPress(key);
				for(int i=shortcut.size()-1;i>=0;i--)	robot.keyRelease(shortcut.get(i));
				break;
				default : break;
			}
		} catch(Exception e) {e.printStackTrace();}
	}
	
	private static class KeyConverter {
		private static List<Integer> convertToKeyCode(String data) {
			List<Integer> returnValue = new ArrayList<>();
			List<Integer> plusIdxes = new ArrayList<>();
			for(int i=0; i<data.length(); i++)	if(data.charAt(i)=='+')	plusIdxes.add(i);
			
			int startIdx = 0;
			for(int plusIdx : plusIdxes) {
				String sub = data.substring(startIdx, plusIdx);
				if(sub.length()==1 &&sub.charAt(0)>='a' && sub.charAt(0)<='z')	returnValue.add(KeyEvent.VK_A + sub.charAt(0) - 'a');
				else if(sub.length()==2 && sub.charAt(0)=='f')	returnValue.add(KeyEvent.VK_F1 + sub.charAt(1) - '1');
				else {
					switch(sub) {
					case "ctrl" :
						returnValue.add(KeyEvent.VK_CONTROL);
						break;
					case "alt" :
						returnValue.add(KeyEvent.VK_ALT);
						break;
					case "windows" :
						returnValue.add(KeyEvent.VK_WINDOWS);
						break;
					case "tab" :
						returnValue.add(KeyEvent.VK_TAB);
						break;
					case "shift" :
						returnValue.add(KeyEvent.VK_SHIFT);
						break;
					case "esc" :
						returnValue.add(KeyEvent.VK_ESCAPE);
						break;
					case "→" :
						returnValue.add(KeyEvent.VK_RIGHT);
						break;
					case "←" :
						returnValue.add(KeyEvent.VK_LEFT);
						break;
					default : break;
					}
				}
				startIdx = plusIdx+1;
			}
			return returnValue;
		}
	}
}
