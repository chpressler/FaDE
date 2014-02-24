package biz.pressler.myfade;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;


public class FaDEUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

	private Logger logger = Logger.getLogger(FaDELogger.class.getName());
	
	public FaDEUncaughtExceptionHandler() {
		try {
			logger.addHandler(new FileHandler("FaDEErrorLog.xml.txt"));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void uncaughtException(final Thread t, final Throwable e) {
		if (SwingUtilities.isEventDispatchThread()) {
           logException(t, e);
		} else {
           SwingUtilities.invokeLater(new Runnable() {
               public void run() {
            	   logException(t, e);
               }
           });
		}
	}
	
	private void logException(Thread t, Throwable e) {
    	String msg = String.format("Unexpected problem on thread %s: %s", t.getName(), e.getMessage()) + ": " + e.toString();
    	logger.log(Level.FINEST, msg);
    }
	
}
