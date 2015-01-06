package com.sas.mobile.android.vah.html5;

import com.sas.mobile.android.bimobile.common.CommonFunctions;
import com.sas.mobile.main.Main;

public class LogOnVAH extends Main{
	public static void logOn() throws InterruptedException{
//		Thread.sleep(20000);
		//input url and load it
		driver.get("http://rdcesx16171.race.sas.com:7980/SASVisualAnalyticsViewer/?hubMode=modern"); 
//		Thread.sleep(20000);
//		CommonFunction.screenshot.getScreenshot("LogOnWindow");
		
	}
}
