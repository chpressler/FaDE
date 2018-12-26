package com.jensui.projects.fade;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FaDELogger {

	private static volatile FaDELogger instance = null;
	
	public static synchronized FaDELogger getInstance() {
		if(instance == null) {
			synchronized (FaDELogger.class) {
				if(instance == null) {
					instance = new FaDELogger();
				}
			}
		}
		return instance;
	}
	
	private FaDELogger() {
	}
	
	public void write(String classname, String msg) {
		Logger.getLogger(classname).log(Level.FINEST, msg);
	}
	
}
