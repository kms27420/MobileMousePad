package com.kms.mobilemousepad.execute;

import com.kms.mobilemousepad.program_view.ProgramFrame;
import com.kms.mobilemousepad.view.IntroViewBinder;

public class Executor {
	public static void main(String[] args) {
		ProgramFrame.INSTANCE.showNextView(new IntroViewBinder().bindedView());
	}
}