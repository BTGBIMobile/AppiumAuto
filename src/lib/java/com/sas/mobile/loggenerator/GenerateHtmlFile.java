package com.sas.mobile.loggenerator;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


import com.sas.mobile.android.bimobile.common.FunctionHub;
//import com.sas.mobile.android.bimobile.common.CommonFunctions;
import com.sas.mobile.main.Main;

public class GenerateHtmlFile extends Formatter{
	
	
	private static FileHandler filehandler;
	private static Formatter formatter;
	static Logger Logger = java.util.logging.Logger.getLogger("autotest.logger");
	
	
	static public void createLogFile(){
	
		Logger.setLevel(Level.FINEST);
		
		//create log file with the name of Logging.html in the folder of LogLocation
		try {
			filehandler = new FileHandler(Main.LogLocation + "/" + "Logging.html");
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Cannot find the location to create the log files");
		}
		// Create HTML file with the Formatter below
		formatter = new GenerateHtmlFile();
		filehandler.setFormatter(formatter);
		Logger.addHandler(filehandler);	
	}
	
	public String format(LogRecord rec){
	    StringBuffer buf = new StringBuffer(1000);
	    // Bold any levels >= WARNING
	    buf.append("<tr>");
	    
	    //Time column
	    buf.append("<td>");
		if(rec.getLevel().intValue() != Level.CONFIG.intValue()){
			buf.append(calcDate(rec.getMillis()));
		}    
	    buf.append("</td>");
	    
	    //Status column
	    buf.append("<td>");
		if(rec.getLevel().intValue() == Level.FINER.intValue()){
			buf.append("INFO");
		}
		if(rec.getLevel().intValue() == Level.FINE.intValue()){
			buf.append("SCREENSHOT");
		}
		if(rec.getLevel().intValue() == Level.INFO.intValue()){
			buf.append("START");
		}
		if(rec.getLevel().intValue() == Level.WARNING.intValue()){
			buf.append("WARNING");
		}
		if(rec.getLevel().intValue() == Level.SEVERE.intValue()){
			buf.append("FAILED");
		}
		if(rec.getLevel().intValue() == Level.CONFIG.intValue()){
			buf.append("");
		}
		if(rec.getLevel().intValue() == Level.FINEST.intValue()){
			buf.append("PASS");
		}
		buf.append("</td>");
		
		//Log Message column
	    buf.append("<td>");
	    buf.append(formatMessage(rec));
	    buf.append('\n');
	    buf.append("</td>");
	    
//	    //Screenshot column
//	    buf.append("<td>");
//	    if(rec.getLevel().intValue() != Level.CONFIG.intValue()){
//			buf.append(FunctionHub.screenshot.screenshotInserted2Htmlfile);
//		}
//	    buf.append('\n');
//	    buf.append("</td>");
	    
	    buf.append("</tr>\n");
	    return buf.toString();
	  }

	  private String calcDate(long millisecs)
	  {
//	    SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
		SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date resultdate = new Date(millisecs);
	    return date_format.format(resultdate);
	  }

	  public String getHead(Handler h)
	  {
	    return "<HTML>\n<HEAD>\n" + "<h2>Android automation result</h2>" + "\n</HEAD>\n<BODY>\n<PRE>\n"
	        + "<table border='1'>\n  "
//	        + "<tr bgcolor='#9acd32'><th>Time</th><th>Status</th><th>Log Message</th><th>Screenshot</th></tr>\n";
			+ "<tr bgcolor='#9acd32'><th>Time</th><th>Status</th><th>Log Message</th></tr>\n";
	  }

	  public String getTail(Handler h)
	  {
	    return "</table>\n  </PRE></BODY>\n</HTML>\n";
	  }
}
