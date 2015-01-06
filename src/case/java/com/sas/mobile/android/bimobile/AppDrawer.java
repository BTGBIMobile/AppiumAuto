package com.sas.mobile.android.bimobile;

import com.sas.mobile.android.bimobile.common.CommonFunctions.navigationDrawer;
import com.sas.mobile.android.bimobile.common.FunctionHub;
import com.sas.mobile.loggenerator.GetResultStatus;
import com.sas.mobile.main.Main;

public class AppDrawer extends Main{
	
	
	public static void switchSection(int sectionIndex){
		navigationDrawer.selectOptionInDrawer(sectionIndex);
	}
	
	/**
	 * This method is just used to view all sections without any action
	 * */
	public static void viewAllSections(){
		GetResultStatus.startCase("viewAllSectionsOfApp");
		String[] sectionName = {"Portfolio", "Library", "Search", "Settings", "Help"};
		try{
			for(int i=0; i<sectionName.length; i++){
				if(i==0){//When launching app, the first section is portfolio, so no need to open navi drawer, just get the screenshot
					FunctionHub.action_GetScreenshot("viewAllSections_"+sectionName[i]);
				}if(i>0){
					navigationDrawer.selectOptionInDrawer(i);
					FunctionHub.action_GetScreenshot("viewAllSections_"+sectionName[i]);
					driver.sendKeyEvent(4);//back
				}
			}
			GetResultStatus.passCase();
		}catch(Exception e){
			GetResultStatus.failCase();
		}
	}
}
