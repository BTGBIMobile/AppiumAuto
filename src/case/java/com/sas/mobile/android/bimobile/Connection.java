package com.sas.mobile.android.bimobile;

import org.openqa.selenium.WebElement;

import com.sas.mobile.android.bimobile.common.FunctionHub;
import com.sas.mobile.loggenerator.GetResultStatus;
import com.sas.mobile.main.Main;

public class Connection extends Main{
	
	public static void addConnection(String server, String port, boolean checkGuest, String uid, String password){
		WebElement we = null;
		
		//open the entrance of adding new connection
		FunctionHub.setting_OpenEntranceWindowOfAddConnection();
		//input the server url and port number
		FunctionHub.setting_InputServerAndPort(server, port);
		//click Next button to verify whether the server and port are invalid
		FunctionHub.action_ClickButton(1);
		//
		try{
			we = driver.findElementById("com.sas.android.bimobile:id/createaccount_button_next");
		}catch(Exception e){
			we = null;
		}
		//if we == null, case will be ended
		if(we == null){
			FunctionHub.action_GetScreenshot("Connection Verify Failed");
			GetResultStatus.failCase("Connection Verify Failed.");
		}else{
			// log on as guest or real uid and pwd
			if(checkGuest==true){
				FunctionHub.setting_LogonAsGuest();
			}else{
				// logon with UID and PWD
				FunctionHub.setting_LogonWithUIDAndPwd(uid, password);
			}
			//click Next button to logging on to the server
			FunctionHub.action_ClickButton(1);
			try{
				we = driver.findElementById("com.sas.android.bimobile:id/createaccount_button_next");
			}catch(Exception e){
				FunctionHub.action_GetScreenshot("Connection Verify Failed");
				GetResultStatus.failCase("Connection Log On Failed");
			}
			if(){
				
			}else{
				
			}
		}
	}
	
	
	/**
	 * add server with guest mode
	 **/
	public static void addConnection(String server, String port){
		try{
			FunctionHub.setting_OpenEntranceWindowOfAddConnection();
			FunctionHub.action_GetScreenshot("addConnection_ServerAndPort");
			FunctionHub.setting_InputServerAndPort(server, port);
			try{
				FunctionHub.action_ClickButton(1);//click on Next button
				try{
					FunctionHub.setting_LogonAsGuest();
					FunctionHub.action_GetScreenshot("addConnection_CheckLogOnGuest");
					try{
						FunctionHub.action_ClickButton(1);//click on Next button
						try{
							WebElement okbutton = driver.findElementById("com.sas.android.bimobile:id/createaccount_button_next");
							okbutton.click();
							GetResultStatus.passCase();
							driver.sendKeyEvent(4);//back to Portfolio section
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}catch (Exception e) {
							GetResultStatus.info("Cannot find the 'Next' button ");
							FunctionHub.action_GetScreenshot("addConnection_Exception_NoNextButton");
						}
					}catch (Exception e) {}
				}catch (Exception e) {}
			}catch (Exception e) {}
		}catch (Exception e) {
			GetResultStatus.failCase();
		}
	}
	/**
	 * add server with uid and pwd mode, common uid will be perfect since cleartext password used.
	 * */
	public static void addConnection(String server, String port, String uid, String password){
		try{
			FunctionHub.setting_OpenEntranceWindowOfAddConnection();
			FunctionHub.setting_InputServerAndPort(server, port);
			try{
				FunctionHub.action_ClickButton(1);//click on Next button
				try{
					FunctionHub.setting_LogonWithUIDAndPwd(uid, password);
					FunctionHub.action_GetScreenshot( "addConnection_CommonUserUIDPWD");
					try{
						FunctionHub.action_ClickButton(1);//click on Next button
						try{
							WebElement okbutton = driver.findElementById("com.sas.android.bimobile:id/createaccount_button_next");
							okbutton.click();
							GetResultStatus.passCase();
							driver.sendKeyEvent(4);//back to Portfolio section
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}catch (Exception e) {
							GetResultStatus.info("Cannot find the 'Next' button ");
						}
					}catch (Exception e) {}
				}catch (Exception e) {}
			}catch (Exception e) {}
		}catch (Exception e) {
			GetResultStatus.failCase();
		}
	}
	public static void addConnection_DuplicateServer(String server, String port){
		try{
			FunctionHub.setting_OpenEntranceWindowOfAddConnection();
			FunctionHub.setting_InputServerAndPort(server, port);
			try{
				FunctionHub.action_ClickButton(1);//click on Next button
				try{
					FunctionHub.setting_LogonAsGuest();
					try{
						FunctionHub.action_ClickButton(1);//click on Next button
						try{
							WebElement okbutton = driver.findElementById("com.sas.android.bimobile:id/createaccount_button_next");
							okbutton.click();
//							GetResultStatus.passCase();
							driver.sendKeyEvent(4);//back to Portfolio section
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							try{
								FunctionHub.setting_OpenEntranceWindowOfAddConnection();
								FunctionHub.setting_InputServerAndPort(server, port);
								try{
									FunctionHub.action_ClickButton(1);//click on Next button
									try{
										FunctionHub.setting_LogonAsGuest();
										try{
											FunctionHub.action_ClickButton(1);//Click on Next button
											FunctionHub.action_GetScreenshot("addConnection_DuplicateServer");
											driver.sendKeyEvent(4);//Dismiss the warning window
											FunctionHub.action_ClickButton(0);//Click Cancel button to return to Settings
											driver.sendKeyEvent(4);//Return to Portfolio
										}catch (Exception e) {}
									}catch (Exception e) {}
								}catch (Exception e) {}
							}catch (Exception e) {}
						}catch (Exception e) {
							GetResultStatus.info("Cannot find the 'Next' button ");
//							FunctionHub.action_GetScreenshot("addConnection_Exception_NoNextButton");
						}
					}catch (Exception e) {}
				}catch (Exception e) {}
			}catch (Exception e) {}
		}catch (Exception e) {
			GetResultStatus.failCase();
		}
	}
	public static void removeConnection(String serverName){
		try{
			FunctionHub.navigate_openSection(3);
			try{
				WebElement we = driver.findElementByName(serverName);
				we.click();
				we = driver.findElementByName("Remove connection");
				we.click();
				FunctionHub.action_GetScreenshot("removeConnection_QueryWindow");
				FunctionHub.action_ClickButtonInQueryWindow(0);//click cancel
				GetResultStatus.passCase();
				
				driver.sendKeyEvent(4);
			}catch (Exception e) {
				GetResultStatus.info("Cannot find the server: "+serverName);
			}
		}catch (Exception e) {
			GetResultStatus.failCase();
		}
	}
	
}
