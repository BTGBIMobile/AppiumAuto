package com.sas.mobile.demo.Android;

import org.openqa.selenium.By;

import com.sas.mobile.xpathsource.map_mbi_android;
import com.sas.mobile.android.bimobile.common.CommonFunctions;
import com.sas.mobile.main.Main;

public class SwitchSections extends Main{
	
	public static void switch2Library() throws InterruptedException{
		Thread.sleep(20000);
		driver.findElementByClassName("android.widget.LinearLayout").click();
		Thread.sleep(10000);
		driver.findElementByName("Library").click();
		Thread.sleep(10000);
		CommonFunctions.screenshot.getScreenshot("Library");
	}
	
//	xPath cannot be used on Windows, Mac is OK
	public static void switch2LibraryByxPath() throws InterruptedException{
		Thread.sleep(15000);
//		driver.findElementByXPath(map_mbi_android.NaviBar.navi_Menu).click();
		driver.findElement(By.xpath(map_mbi_android.NaviBar.navi_Menu)).click();
		Thread.sleep(15000);
//		driver.findElementByXPath(map_mbi_android.NaviBar.navi_Library).click();
		driver.findElement(By.xpath(map_mbi_android.NaviBar.navi_Library)).click();
	}
}
