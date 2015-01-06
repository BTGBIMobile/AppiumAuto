package com.sas.mobile.demo.Android;

import org.openqa.selenium.WebElement;

import com.sas.mobile.android.bimobile.common.FunctionHub;
import com.sas.mobile.loggenerator.GetResultStatus;
import com.sas.mobile.main.Main;

public class Connection extends Main{
	/**
	 * add server with guest mode
	 * */
	public static void addConnection(String server, String port){
		
		GetResultStatus.startCase("addConnection_Guest");
		
		try{
			FunctionHub.setting_OpenEntranceWindowOfAddConnection();
			FunctionHub.action_GetScreenshot(driver.getExecuteMethod().toString()+ "_ServerAndPort");
			FunctionHub.setting_InputServerAndPort(server, port);
			FunctionHub.action_ClickButton(1);//click on Next button
			
			FunctionHub.setting_LogonAsGuest();
			FunctionHub.action_GetScreenshot(driver.getExecuteMethod().toString()+ "_CheckLogOnGuest");
			FunctionHub.action_ClickButton(1);//click on Next button
			
			WebElement okbutton = driver.findElementById("com.sas.android.bimobile:id/createaccount_button_next");
			okbutton.click();
			
			driver.sendKeyEvent(4);//back to Portfolio section
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			GetResultStatus.passCase();
		}catch(Exception e){
			GetResultStatus.failCase(e.toString());
		}
	}
	/**
	 * add server with uid and pwd needed mode, common uid will be perfect since cleartext password used.
	 * */
	public static void addConnection(String server, String port, String uid, String password){
		
		GetResultStatus.startCase("addConnection_CommonUser");
		
		try{
			FunctionHub.setting_OpenEntranceWindowOfAddConnection();
			FunctionHub.setting_InputServerAndPort(server, port);
			FunctionHub.action_ClickButton(1);//click on Next button
			
			FunctionHub.setting_LogonWithUIDAndPwd(uid, password);
			FunctionHub.action_GetScreenshot(driver.getExecuteMethod().toString()+ "_CommonUserUIDPWD");
			FunctionHub.action_ClickButton(1);//click on Next button
			
			WebElement okbutton = driver.findElementById("com.sas.android.bimobile:id/createaccount_button_next");
			okbutton.click();
			
			driver.sendKeyEvent(4);//back to Portfolio section
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			GetResultStatus.passCase();
		}catch(Exception e){
			GetResultStatus.failCase(e.toString());
		}
	}
	
	public static void removeConnection(String serverName){
		GetResultStatus.startCase("DeleteConnection");
		FunctionHub.navigate_openSection(3);
		try{
			WebElement we = driver.findElementByName(serverName);
			we.click();
			driver.findElementByName("Remove connection").click();
			FunctionHub.action_GetScreenshot(driver.getExecuteMethod().toString()+ "_QueryWindow");
			FunctionHub.action_ClickButtonInQueryWindow(0);//click cancel
			driver.sendKeyEvent(4);
			GetResultStatus.passCase();
		}catch(Exception e){
			GetResultStatus.info("Cannot find the element: "+ serverName);
			driver.sendKeyEvent(4);
		}
	}
	
}
