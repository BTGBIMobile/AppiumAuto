package com.sas.mobile.appium.capabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.sas.mobile.main.Main;

public class ConfigCapParas extends Main{
	public static String PLATFORM_NAME;
	public static String PLATFORM_VERSION;
	public static String DEVICE_NAME;
	public static String UDID;
	public static String APP_NAME;
	public static String APP_ACTIVITY;
	public static String APP_PACKAGE;
	public static String BROWSER_NAME;
	public static String DRIVER_AGENT;
	public static String AUTOMATION_NAME;
	public static String UNICODEKEYBOARD;
	public static String ORIENTATION;
	public static String NORESET;
	public static String FULLRESET;
	public static String TIME_OUT;
	
	private static Properties prop = new Properties();
//	private static String configFile="android_browser"+".cfg"; //4 options : android_native/android_browser/ios_native/ios_browser
	
	public  static void setConfigs(){
		  File configdir = new File(projectRootPath, "ConfigFiles/");
		  try{
		 	 	InputStream is = new FileInputStream(configdir+"/"+configFile);
		     	prop.load(is);
		     	is.close();
		    } catch (IOException e){
		     	throw new RuntimeException("Failed to read " + configFile +
		                 " file.");
		    }
		  
		  PLATFORM_NAME = prop.getProperty("platform_name");
		  PLATFORM_VERSION =prop.getProperty("platform_version");
		  DEVICE_NAME =prop.getProperty("device_name");
		  UDID =prop.getProperty("udid");
		  APP_NAME =projectRootPath.toString()+prop.getProperty("app");
		  APP_PACKAGE =prop.getProperty("app_package");
		  APP_ACTIVITY =prop.getProperty("app_activity");
		  BROWSER_NAME =prop.getProperty("browserName");
		  DRIVER_AGENT =prop.getProperty("driver_agent");
		  AUTOMATION_NAME =prop.getProperty("automation_name");
		  UNICODEKEYBOARD =prop.getProperty("unicodekeyboard");
		  ORIENTATION = prop.getProperty("orientation");
		  NORESET = prop.getProperty("noReset");
		  FULLRESET = prop.getProperty("fullReset");
		  TIME_OUT = prop.getProperty("timeout");
	  }
}
