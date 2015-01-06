package com.sas.mobile.appium.capabilities;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.sas.mobile.main.Main;

/**
 * command 'capabilities.setCapability("platformName", platformNameValue);' is used to set Appium
 * more details of Appium server capabilities please refer to http://appium.io/slate/en/master/#caps.md
 * */

public class SetCapabilities extends Main{
		
	public static void setCap(){
		//initialize class variables
		ConfigCapParas.setConfigs();
		//set up appium
		capabilities.setCapability("automationName", ConfigCapParas.AUTOMATION_NAME);
		capabilities.setCapability("platformName", ConfigCapParas.PLATFORM_NAME);
		capabilities.setCapability("platformVersion", ConfigCapParas.PLATFORM_VERSION);
		capabilities.setCapability("deviceName", ConfigCapParas.DEVICE_NAME); //if real device used, replace "Android Emulator" to device name, like "411f62ef0268508f"
		capabilities.setCapability("udid", ConfigCapParas.UDID);
		capabilities.setCapability("orientation", ConfigCapParas.ORIENTATION);
		capabilities.setCapability("noReset", ConfigCapParas.NORESET);
		capabilities.setCapability("fullReset", ConfigCapParas.FULLRESET);
		capabilities.setCapability("app", ConfigCapParas.APP_NAME);//enable this command if no test app installed on device/emulator
		capabilities.setCapability("browserName", ConfigCapParas.BROWSER_NAME);//Note that this capability is not required for Android if you specify appPackage and appActivity capabilities (see below). Incompatible with browserName
		capabilities.setCapability("appPackage", ConfigCapParas.APP_PACKAGE);
		capabilities.setCapability("appActivity", ConfigCapParas.APP_ACTIVITY);
		capabilities.setCapability("unicodeKeyboard", ConfigCapParas.UNICODEKEYBOARD);
		capabilities.setCapability("newCommandTimeout", ConfigCapParas.TIME_OUT);
		 try {
			driver = new AndroidDriver(new URL(ConfigCapParas.DRIVER_AGENT), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);//buffer time 300 seconds for the command of finding elements
	}
	
	//Tips
	/**
	 * if your met the error 
	 * "Appium Error : A new session could not be created. (Original error: Did not get session redirect from Chromedriver)", 
	 * please update the ChromeDriver (..path..\npm\node_modules\appium\build\chromedriver) to lasted build.
	 * download chromedriver from here https://sites.google.com/a/chromium.org/chromedriver/downloads
	 * 
	 * or 
	 * 
	 * for windows: please kill the process of chromedriver32.exe in task manager
	 * for mac/linux: run the command below
	 * ps -e | grep /home/appium/.linuxbrew/lib/node_modules/appium/build/chromedriver/linux/chromedriver | grep -v grep |grep -e '--port=9515$' | awk '{ print $1 }' | xargs kill -15
	 * */
	
}
