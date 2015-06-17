package com.jensui.projects.fade.actions;

import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;

public class SubmitErrorAction extends AbstractAction {
	
	private static final long serialVersionUID = 1L;

	public void actionPerformed(ActionEvent e) {
		try {
		    URL url;
		    URLConnection urlConnection;
		    DataOutputStream outStream;
		    DataInputStream inStream;

		    // Build request body
		    String body = "test=" + "qqqq";
		    
		    // Create connection
		    url = new URL("http://picus.111mb.de/cp/postdatatest.php");
		    urlConnection = url.openConnection();
		    ((HttpURLConnection)urlConnection).setRequestMethod("POST");
		    urlConnection.setDoInput(true);
		    urlConnection.setDoOutput(true);
		    urlConnection.setUseCaches(false);
//		    urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		    urlConnection.setRequestProperty("Content-Length", ""+ body.length());

		    // Create I/O streams
		    outStream = new DataOutputStream(urlConnection.getOutputStream());
		    inStream = new DataInputStream(urlConnection.getInputStream());

		    // Send request
		    outStream.writeBytes(body);
		    outStream.flush();
		    outStream.close();

		    // Get Response
		    // - For debugging purposes only!
		    String buffer;
		    while((buffer = inStream.readLine()) != null) {
		        System.out.println(buffer);
		    }

		    // Close I/O streams
		    inStream.close();
		    outStream.close();
		}
		catch(Exception ex) {
			Logger.getAnonymousLogger().log(Level.SEVERE, ex.toString());
		}
		}

}

