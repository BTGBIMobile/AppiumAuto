package com.sas.mobile.android.bimobile.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.sas.mobile.android.bimobile.common.CommonFunctions.navigationDrawer;
import com.sas.mobile.appium.capabilities.ConfigCapParas;
import com.sas.mobile.loggenerator.GetResultStatus;
import com.sas.mobile.main.Main;

public class FunctionHub extends Main{
	
	
//	/**
//	 * If mobile app is new installed or the capability "fullReset" is 'true', the Navigation drawer will be displayed.
//	 * So, if the capability "fullReset" is 'true', we should close the drawer by sending 'back' command.
//	 **/
//	public static void assertNaviDrawer(){
//		if(ConfigCapParas.FULLRESET.equals("false")){
//			//no action
//		}if(ConfigCapParas.FULLRESET.equals("true")){
//			driver.findElementById("com.sas.android.bimobile:id/drawer");
//			driver.sendKeyEvent(4);//close the navi drawer
//		}
//	}
	
	/**
	 * If app is new installed ,when open it for the 1st time, navigate drawer will be shown, and now we should close the drawer to continiue our testing.
	 **/
	public static void assertNaviDrawer(){
		WebElement we = null;
		try{
			we = driver.findElementById("com.sas.android.bimobile:id/drawer");
			driver.sendKeyEvent(4);//back key, close the navi drawer
		}catch(Exception e){
			//no action needed
		}
	}
	
	
	public static void action_GetScreenshot(String imageName){
		String screenshotInserted2Htmlfile=null;
		try {
			driver.findElementsByClassName("android.widget.LinearLayout");//waiting for the window which to be captured to be load completely
			
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(projectRootPath,"result/images/"+imageName+".png"));
			
			screenshotInserted2Htmlfile = "<a href='" + "images" + "/" + imageName + ".png' target='_blank'><img src='" + "images" +"\\" + imageName + ".png' width='100' height='40'></a>";
			
		} catch (IOException e1) {
				e1.printStackTrace();
				GetResultStatus.info("Cannot get screenshot since the window did not pop up.");
		}
		GetResultStatus.screenshot(screenshotInserted2Htmlfile);
	}
//	
	/**
	 * In some conditions, such as delete report or delete connection, query window will be popped up. User should select Cancel or OK to determine whether to delete the report/connection or not.
	 * Normally, the index 0 is cancel and 1 is OK(accept)
	 * */
	public static void action_ClickButtonInQueryWindow(int i){
		WebElement we = null;
		try{
			we = driver.findElementByClassName("android.widget.Button");//waiting for the Next button shown completely
			try{
				List<WebElement> button = driver.findElementsByClassName("android.widget.Button");
				button.get(i).click();//click on Next
			}catch(Exception e){
				GetResultStatus.info("Cannot find the wanted button.");
			}
		}catch(Exception e){
			GetResultStatus.info("No Query Window pop up");
		}
	}
	/**
	 * For adding connection, we'll meet query in app but not query window, Normally, the index 0 is cancel and 1 is Next
	 **/
	public static void action_ClickButton(int i){
		WebElement we = null;
		try{
			we = driver.findElementByClassName("android.widget.Button");//waiting for the Next button shown completely
			try{
				List<WebElement> button = driver.findElementsByClassName("android.widget.Button");
				button.get(i).click();//click on Next
			}catch(Exception e){
				GetResultStatus.info("Cannot find the wanted button.");
			}
		}catch(Exception e){
			GetResultStatus.info("No wanted button displayed.");
		}
	}
	/**
	 * The button at the top right corner is called as Menu button because of the drop down list can supply many other operations in some condition, such as in report or in expanded view
	 * After the button got clicked, alternatives will be pop up. these alternatives maybe 'select' in expanded view or others in report viewing state.
	 **/
	public static void action_MenuButtonAtTRC(int indexOfOption){
		WebElement we = null;
		try{
			we = driver.findElementByName("android.widget.ImageButton");
			we.click();
			try{
				List<WebElement> option = driver.findElementsByClassName("android.widget.LinearLayout");
				option.get(indexOfOption).click();
			}catch(Exception e){
				GetResultStatus.info("Cannot find the options of Menu.");
			}
		}catch(Exception e){
			GetResultStatus.info("Cannot find the Menu button.");
		}
	}
	/**
	 * Close the Welcome guide
	 **/
	public static void action_CloseWelcomeGuide(){
		WebElement we = null;
		try{
			we = driver.findElementById("com.sas.android.bimobile:id/infoText");
			try{
				we = driver.findElementByName("OK");
				we.click();//close the Welcome guide
			}catch(Exception e){
				//no action needed
			}
		}catch(Exception e){
			//no action needed
		}
	}
	
	
	public static void navigate_openNavigationDrawer(){
		WebElement naviBar = driver.findElementByClassName("android.widget.LinearLayout");
		naviBar.click();//open navi drawer
	}
	public static void navigate_openSection(int i){
		navigate_openNavigationDrawer();
		List<WebElement> item = driver.findElementsById("android:id/text1");
		item.get(i).click();
	}
	
	
	public static void setting_OpenEntranceWindowOfAddConnection(){
		navigationDrawer.selectOptionInDrawer(3);//open Settings
		WebElement add_Account = driver.findElementById("com.sas.android.bimobile:id/menu_add_account");
		add_Account.click();
		driver.findElementByClassName("android.widget.ScrollView");//waiting for the SERVER content shown completely
	}
	public static void setting_InputServerAndPort(String server, String port){
		driver.findElementByClassName("android.widget.ScrollView");//waiting for the server and port content shown completely
		List<WebElement> editText = driver.findElementsByClassName("android.widget.EditText");
		editText.get(0).clear();
		editText.get(0).sendKeys(server);
		editText.get(1).clear();
		editText.get(1).sendKeys(port);
	}
	public static void setting_LogonAsGuest(){
		driver.findElementByClassName("android.widget.ScrollView");//waiting for the UID and Password content shown completely
		List<WebElement> checkBox = null;
		try{
			checkBox = driver.findElementsByClassName("android.widget.CheckBox");
			checkBox.get(0).click();
		}catch(Exception e){
			GetResultStatus.info("Cannot find Logon As Guest checkbox.");
		}
	}
	public static void setting_LogonWithUIDAndPwd(String uid, String password){
		driver.findElementByClassName("android.widget.ScrollView");//waiting for the UID and Password content shown completely
		List<WebElement> editText = null;
		try{
			editText = driver.findElementsByClassName("android.widget.EditText");
			editText.get(0).sendKeys(uid);
			editText.get(1).sendKeys(password);
		}catch(Exception e){
			GetResultStatus.info("Cannot find UID and PWD input window.");
		}
	}
	public static void setting_DeleteConnection(String connectionToBeDeleted){
		navigate_openSection(3);
		WebElement we = null;
		try{
			we = driver.findElementByName(connectionToBeDeleted);
			we.click();
		}catch(Exception e){
			GetResultStatus.info("Cannot find the server to be deleted.");
		}
	}
	
	
	public static void search_InputKeywordAndSearch(String keywords){
		WebElement searchbox = driver.findElementById("android:id/search_src_text");
		searchbox.sendKeys(keywords);//input the keyword to search
		driver.sendKeyEvent(66);//enter key
		driver.findElementById("com.sas.android.bimobile:id/view_all");//'see all' button will appear when searched reports are listed
	}
	public static void search_filteByLocation(int index){
		List<WebElement> checkbox = driver.findElementsByClassName("android.widget.CheckBox");
		checkbox.get(index).click();
	}
	public static void search_filteByAuthor(int index){
		List<WebElement> checkbox = driver.findElementsByClassName("android.widget.CheckBox");
		checkbox.get(index).click();
	}
	public static void search_filteByDateupdated(int index){
		List<WebElement> checkbox = driver.findElementsByClassName("android.widget.CheckBox");
		checkbox.get(index).click();
	}
	public static void search_setFilterBerforeSearch(int locationIndex, int dateupdateIndex){
		search_filteByLocation(locationIndex);
		search_filteByDateupdated(dateupdateIndex);
		driver.findElementByAccessibilityId("com.sas.android.bimobile:id/filter_apply");//after choose the filter, click Apply button to apply the setting	
	}
	public static void search_setFilterAfterSearch(int locationIndex,  int AuthorIndex, int dateUpdateIndex){
		search_filteByLocation(locationIndex);
		search_filteByAuthor(AuthorIndex);
		search_filteByDateupdated(dateUpdateIndex);
		driver.findElementByAccessibilityId("com.sas.android.bimobile:id/filter_apply");//after choose the filter, click Apply button to apply the setting	
	}
	
	
	public static void report_SubscribeToDemoReport(String serverDescription){//Uncompleted
		WebElement we = driver.findElementByName(serverDescription);
		we.click();
		//not complete
	}
	
	public static void report_SubscribeToReport(String serverName,String reportName){
		action_CloseWelcomeGuide();
		WebElement we = null;
		//check whether the server exists
		try{
			we = driver.findElementByName(serverName);
			we.click();
			try{
				String[] path = {"SAS Projects","NLS","SMB","Current Release","I18N_Baseline"};
				for(int i =0; i<path.length; i++){
					we = driver.findElementByName(path[i]);
					we.click();
				}
				//check whether the report exist or not
			
				we = driver.findElementByName(reportName);
				we.click();
				//check whether the report can be subscribed
				try{
					we = driver.findElementById("com.sas.android.bimobile:id/subscribe");
					we.click();
					driver.sendKeyEvent(4);
					report_CheckSubscribedReportStatus();//check whether report is in subscribing state
				}catch(Exception e){
					driver.sendKeyEvent(4);//report has been subscribed. Close the subscribe window
				}
			}catch(Exception e){
				GetResultStatus.info("Cannot find the report : "+reportName);
			}
		}catch(Exception e){
			GetResultStatus.info("Cannot find the server : "+serverName);
		}	
	}
	
	public static void report_CheckSubscribedReportStatus(){
		//Check if the report still be in downloading state (progress bar is running)
		WebElement progressBar = null;
		try {
			progressBar = driver.findElementByClassName("android.widget.ProgressBar");
			while(progressBar!=null){
				try{
					progressBar = driver.findElementByClassName("android.widget.ProgressBar");
				}catch (Exception e) {
					progressBar = null;
				}
			}
		} catch (Exception e) {}
	}
	public static void report_openReport(String reportName){
		WebElement we = null;
		try{
			we = driver.findElementByName(reportName);
			we.click();
		}catch(Exception e){
			GetResultStatus.info("Cannot find the report: " +reportName + " to open.");
		}
		driver.findElementById("com.sas.android.bimobile:id/report_view_pager");//waiting for report loaded completely
	}
	public static void report_SortBy(int indexOfSortItem){//the 
		WebElement we = driver.findElementById("com.sas.android.bimobile:id/sortspinner");//find sort spinner
		we.click();//click sort spinner
		List<WebElement> sortItem = driver.findElementsByClassName("android.widget.FrameLayout");
		sortItem.get(indexOfSortItem).click();
	}
	public static void report_Delete(){
		
	}
	public static void report_RunIntoSeeAll(int indexOfSection){// indextOfSection : 0-Subscribe 1-Recent 2-Favorites
		List<WebElement> we = null;
		try{
			we = driver.findElementsByName("See All");
			we.get(indexOfSection).click();
			try{
				driver.findElementByClassName("android.widget.GridView");//
			}catch(Exception e){
				//no action needed.
			}
		}catch(Exception e){
			GetResultStatus.info("Cannot find the SeeAll button.");
		}
	}
	public static int report_CalculateSectionNumberOfReport(){
		WebElement we = driver.findElementById("com.sas.android.bimobile:id/menu_contents");
		we.click();
		we = driver.findElementByClassName("android.widget.HorizontalScrollView");
		List<WebElement> sectionNumber = driver.findElementsById("com.sas.android.bimobile:id/preview_view");
		int sectionNum = sectionNumber.size();
		sectionNumber.get(0).click();//make sure the first section is shown
//		driver.sendKeyEvent(4);
		return sectionNum;
		
	}
	
	
}
