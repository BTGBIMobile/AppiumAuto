package com.sas.mobile.android.bimobile.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.sas.mobile.loggenerator.GetResultStatus;
import com.sas.mobile.main.Main;

public class CommonFunctions extends Main{
	
	
	/**
	 * There are 2 functions contained in this method. 
	 * 
	 * 1: get screenshot and stored to local. 
	 * 2: insert the screenshot to html file log
	 * */
	public static class screenshot{	
		public static String screenshotInserted2Htmlfile;//The screenshot just generated will be inserted to the html log file
		public static void getScreenshot(String imageName){
			screenshotInserted2Htmlfile = "<a href='" + "images" + "/" + imageName + ".png' target='_blank'><img src='" + "images" +"\\" + imageName + ".png' width='100' height='40'></a>";
			try {
				driver.findElementsByClassName("android.widget.LinearLayout");//waiting for the window which to be captured to be load completely, use the command below to judge whether the window is shown
				
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(projectRootPath,"result/images/"+imageName+".png"));
			} catch (IOException e1) {
					e1.printStackTrace();
			}
			GetResultStatus.screenshot(screenshotInserted2Htmlfile);//need update this method in 
		}
	}
	
	
	
	/**
	 * In some conditions, the index order of button is from left to right and the first button's index is 0
	 * */
	public static void clickOnButton(int i){
		driver.findElementByClassName("android.widget.Button");//waiting for the Next button shown completely
		List<WebElement> button = driver.findElementsByClassName("android.widget.Button");
		button.get(i).click();//click on Next
	}
	
	
	public static void queryWindow(int i){
		driver.findElementByClassName("android.widget.Button");//waiting for the Next button shown completely
		List<WebElement> button = driver.findElementsByClassName("android.widget.Button");
		button.get(i).click();//click on Next
	}
	/**
	 * Use this class when switching section by giving the index number(0-4) of the section to be selected
	 * */
	public static class navigationDrawer {
		public static void openNaviDrawer(){
			final List<WebElement> naviBar = driver.findElementsByClassName("android.widget.LinearLayout");
			naviBar.get(0).click();//open navi drawer
		}
		/**
		 * i : the index of the item in navi drawer
		 * 0 : Portfolio
		 * 1 : Library
		 * 2 : Search
		 * 4 : Settings
		 * 5 : Help
		 * */
		public static void selectOptionInDrawer(int i){
			openNaviDrawer();
			final List<WebElement> item = driver.findElementsById("android:id/text1");
			item.get(i).click();
		}
	}
	
	
	
	/**
	 * It contains all methods to invoke each status in add connection process.
	 * There're 3 windows when adding connection
	 * 1. server and port input window
	 * 2. guest or uid input window
	 * 3. server added successfully window
	 * */
	public static class connectionOperation{
		public static void openServerInputWindow(){
			navigationDrawer.selectOptionInDrawer(3);//open Settings
			WebElement add_Account = driver.findElementById("com.sas.android.bimobile:id/menu_add_account");
			add_Account.click();
			driver.findElementByClassName("android.widget.ScrollView");//waiting for the SERVER content shown completely
		}
		public static void inputServerAndPort(String server, String port){
			driver.findElementByClassName("android.widget.ScrollView");//waiting for the UID and Password content shown completely
			List<WebElement> editText = driver.findElementsByClassName("android.widget.EditText");
			editText.get(0).sendKeys(server);
			editText.get(1).clear();
			editText.get(1).sendKeys(port);
		}
		public static void logonAsGuest(){
			driver.findElementByClassName("android.widget.ScrollView");//waiting for the UID and Password content shown completely
			List<WebElement> checkBox = driver.findElementsByClassName("android.widget.CheckBox");
			checkBox.get(0).click();
		}
		public static void logonAsUID(String uid, String password){
			driver.findElementByClassName("android.widget.ScrollView");//waiting for the UID and Password content shown completely
			List<WebElement> editText = driver.findElementsByClassName("android.widget.EditText");
			editText.get(0).sendKeys(uid);
			editText.get(1).sendKeys(password);
		}
		
	}
	
	
	
	/**
	 * In search section, user can do these things below
	 * 1. input the keyword to search box
	 * 2. open filter and set parameters before searching
	 * 3. open filter and set parameters after searching
	 * */
	public static class searchOperation extends Main{
		public static void inputKeywordAndSearch(String keywords){
			WebElement searchbox = driver.findElementById("android:id/search_src_text");
			searchbox.sendKeys(keywords);//input the keyword to search
			driver.sendKeyEvent(66);//enter key
			driver.findElementById("com.sas.android.bimobile:id/view_all");//'see all' button will appear when searched reports are listed
		}
		public static void setFilterBerforeSearch(int locationIndex, int dateupdateIndex){
			filteByLocation( locationIndex);
			filteByDateupdated(dateupdateIndex);
			driver.findElementByAccessibilityId("com.sas.android.bimobile:id/filter_apply");//after choose the filter, click Apply button to apply the setting	
		}
		public static void setFilterAfterSearch(int locationIndex,  int AuthorIndex, int dateUpdateIndex){
			filteByLocation( locationIndex);
			filteByAuthor(AuthorIndex);
			filteByDateupdated(dateUpdateIndex);
			driver.findElementByAccessibilityId("com.sas.android.bimobile:id/filter_apply");//after choose the filter, click Apply button to apply the setting	
		}
		public static void filteByLocation(int index){
			List<WebElement> checkbox = driver.findElementsByClassName("android.widget.CheckBox");
			checkbox.get(index).click();
		}
		public static void filteByAuthor(int index){
			List<WebElement> checkbox = driver.findElementsByClassName("android.widget.CheckBox");
			checkbox.get(index).click();
		}
		public static void filteByDateupdated(int index){
			List<WebElement> checkbox = driver.findElementsByClassName("android.widget.CheckBox");
			checkbox.get(index).click();
		}
	}
	
	
	
	/**
	 * 
	 * */
	public static class subscribeOperation extends Main{
		public static void toDemoReport(String serverDescription){//Uncompleted
			WebElement we = driver.findElementByName(serverDescription);
			we.click();
		}
		public static void toCustomReport(String serverName,String reportName){
			WebElement we = driver.findElementByName(serverName);
			we.click();
			we = driver.findElementByName("SAS Projects");
			we.click();
			we = driver.findElementByName("NLS");
			we.click();
			we = driver.findElementByName("SMB");
			we.click();
			we = driver.findElementByName("Current Release");
			we.click();
			we = driver.findElementByName("I18N_Baseline");
			we.click();
			we = driver.findElementByName("基准报表-Baseline");
			we.click();
			we = driver.findElementById("com.sas.android.bimobile:id/subscribe");
			if(we.isEnabled()){
				we.click();//if the Subscribe button is enable, click it 
			}else{
				driver.sendKeyEvent(4);//close the subscribe window
			}
			driver.sendKeyEvent(4);//return to portfolio
		}
	}
	
	
	
	/**
	 * 
	 **/
	public static class reportOperation extends Main{
		
		public static void openReport(String reportName){
			WebElement we = driver.findElementByName(reportName);
			we.click();
			driver.findElementById("com.sas.android.bimobile:id/report_view_pager");//waiting for report loaded completely
		}
		/**
		 * The index number, if possible, of each section is 0 or 1 or 2     
		 * 0: Portfolio
		 * 1: Recent
		 * 2: Favorite
		 * */
		public static void goToExpandedView(int indexOfSection){
			List<WebElement> we = driver.findElementsByName("See All");
			we.get(indexOfSection).click();
			driver.findElementByClassName("android.widget.GridView");//
		}
		public static void sortBy(int indexOfSortItem){
			WebElement we = driver.findElementById("com.sas.android.bimobile:id/sortspinner");//find sort spinner
			we.click();//open sort items
			List<WebElement> sortItem = driver.findElementsByClassName("android.widget.FrameLayout");
			sortItem.get(indexOfSortItem).click();
		}
		public static void clickOnFavoriteIcon(){
			
		}
		public static void deleteReport(){
			WebElement we = driver.findElementByClassName("android.widget.LinearLayout");
			goToExpandedView(0);//only in Portfolio's expanded view can delete report, so parameter is 0
			List<WebElement> reportThumb = driver.findElementsById("com.sas.android.bimobile:id/thumbnail");
			int reportNumber = reportThumb.size();
			if(reportNumber==0){
				System.out.println("No Report");
				driver.sendKeyEvent(4);//return to last state
			}else{
				we = driver.findElementByClassName("android.widget.ImageButton");
				we.click();//click on menu button
				we = driver.findElementById("android:id/title");
				we.click();//click on Select
				
			}
			
		}
	}
}
