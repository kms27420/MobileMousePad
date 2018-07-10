package com.kms.mobilemousepad.custom;

public class LoadingViewControler {
	private LoadingView loadingView;
	private Thread loadingThread;
	
	public LoadingViewControler(LoadingView loadingView) {
		setLoadingView(loadingView);
	}
	
	public void setLoadingView(LoadingView loadingView) {
		this.loadingView = loadingView;
	}
	
	public void startLoadingProcess() {
		if(loadingView==null)	return;
		
		loadingThread = new Thread(()->{
			try {
				while(true) {
					loadingView.animateLoading();
					Thread.sleep(80);
				}
			} catch(InterruptedException e) {}
		});
		loadingThread.start();
	}
	
	public void endLoadingProcess() {
		loadingThread.interrupt();
		loadingThread = null;
	}
}
