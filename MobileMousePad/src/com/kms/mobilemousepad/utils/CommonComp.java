package com.kms.mobilemousepad.utils;

import java.awt.Color;

import javax.swing.JTextField;

import com.kms.mobilemousepad.utils.font.FontProvider;
import com.kms.mobilemousepad.utils.font.FontType;
import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.text.textarea.alignment.FlatHorizontalAlignment;
import com.mommoo.flat.text.textarea.alignment.FlatVerticalAlignment;
import com.mommoo.flat.text.textfield.FlatTextField;

public class CommonComp {
	private CommonComp() {}
	
	public static FlatButton createCommonBt() {
		return createCommonBt("");
	}
	
	public static FlatButton createCommonBt(String buttonName) {
		FlatButton commonBt = new FlatButton(buttonName);
		commonBt.setFont(FontProvider.getFont(FontType.DEFAULT));
		return commonBt;
	}
	
	public static FlatLabel createCommonLb() {
		return createCommonLb("");
	}
	
	public static FlatLabel createCommonLb(String labelText) {
		FlatLabel commonLb = new FlatLabel(labelText);
		commonLb.setFont(FontProvider.getFont(FontType.DEFAULT));
		commonLb.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		commonLb.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		commonLb.setOpaque(false);
		return commonLb;
	}
	
	public static FlatTextField createCommonTf(boolean isPwMode) {
		FlatTextField commonTf = new FlatTextField(isPwMode);
		commonTf.setHorizontalTextFieldAlignment(JTextField.CENTER);
		commonTf.setFont(FontProvider.getFont(FontType.DEFAULT));
		commonTf.setBackground(Color.WHITE);
		return commonTf;
	}
}
