package com.sas.mobile.android.bimobile;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.sas.mobile.android.bimobile.common.FunctionHub;
import com.sas.mobile.loggenerator.GetResultStatus;
import com.sas.mobile.main.Main;

public class Report extends Main{
	
	public static void subscribeToDemoReport(){
		FunctionHub.navigate_openSection(1);
		
	}
	
	public static void subscribeToReport(String serverName,String reportName){
		GetResultStatus.startCase("subToUserCreatedReport");
		FunctionHub.navigate_openSection(1);//open Library
		FunctionHub.report_SubscribeToReport(serverName, reportName);
		
		WebElement we = null;
		try{
			we = driver.findElementByName(reportName);
			FunctionHub.action_GetScreenshot("subscribeToReport_SubscribedReport");
			GetResultStatus.passCase();
		}catch(Exception e){
			GetResultStatus.failCase();
		}
	}
	
	public static void viewSectionOfReport(String reportName, int sectionCount){
		GetResultStatus.startCase("viewSectionOfReport");
		int sectionNumber = 0;
		try{
			FunctionHub.report_openReport(reportName);//open report
			try{
				sectionNumber = FunctionHub.report_CalculateSectionNumberOfReport();//calculate the number of sections of the opened report
				WebElement we = driver.findElementById("com.sas.android.bimobile:id/menu_contents");
				we.click();
				List<WebElement> section = driver.findElementsById("com.sas.android.bimobile:id/preview_view");
				section.get(sectionCount).click();
				FunctionHub.action_GetScreenshot("viewSectionOfReport_Section"+(sectionCount));
				driver.sendKeyEvent(4);//close report
				GetResultStatus.passCase();
			}catch(Exception e){}
		}catch(Exception e){
			GetResultStatus.failCase();
		}
	}
	
	public static void viewAllSectionsOfReport(String reportName){
		GetResultStatus.startCase("viewAllSectionsOfReport");
		int sectionNumber = 0;
		try{
			FunctionHub.report_openReport(reportName);//open report
			try{
				sectionNumber = FunctionHub.report_CalculateSectionNumberOfReport();//calculate the number of sections of the opened report
				for(int i=0;i<sectionNumber;i++){//view all sections by POC
					WebElement we = driver.findElementById("com.sas.android.bimobile:id/menu_contents");
					we.click();
					List<WebElement> section = driver.findElementsById("com.sas.android.bimobile:id/preview_view");
					section.get(i).click();
					FunctionHub.action_GetScreenshot("viewAllSectionsOfReport_Section"+(i+1));
				}
				driver.sendKeyEvent(4);//close report
				GetResultStatus.passCase();
			}catch(Exception e){}
		}catch(Exception e){
			GetResultStatus.failCase();
		}
	}
	
	public static void searchReport(String keywords){
		GetResultStatus.startCase("searchReport");
		try{
			FunctionHub.navigate_openSection(2);//open search section
			try{
				FunctionHub.action_GetScreenshot("searchReport_SearchWindow");
				FunctionHub.search_InputKeywordAndSearch(keywords);
				GetResultStatus.passCase();
			}catch(Exception e){}
		}catch(Exception e){
			GetResultStatus.failCase();
		}
	}
	
	public static void deleteReport(String reportName){
		WebElement we = null;
		
		FunctionHub.report_RunIntoSeeAll(0);//only in Portfolio's expanded view can delete report, so parameter is 0
		
//		List<WebElement> reportThumbnailCount = null;
//		int reportNumber = 0;
		try{
//			reportThumbnailCount = driver.findElementsById("com.sas.android.bimobile:id/thumbnail");
//			reportNumber = reportThumbnailCount.size();
			FunctionHub.action_MenuButtonAtTRC(0);//click "Select" option
			try{
				we = driver.findElementByName(reportName);
				we.click();
				try{
					we = driver.findElementById("com.sas.android.bimobile:id/action_remove");
					we.click();
					FunctionHub.action_ClickButtonInQueryWindow(1);//click on OK to delete the report
					driver.sendKeyEvent(4);//back
					GetResultStatus.passCase();
				}catch(Exception e){
					GetResultStatus.info("Cannot find the Remove button");
				}
			}catch(Exception e){
				GetResultStatus.info("Cannot find the report : "+reportName);
			}
		}catch(Exception e){
			GetResultStatus.failCase();
		}
	}
	
	public static void sortReport(int indexOfExpandedView, int indexOfSortItem){
		//go to expanded view
		FunctionHub.report_RunIntoSeeAll(indexOfExpandedView);
		WebElement we = null;
		we = driver.findElementById("com.sas.android.bimobile:id/sortspinner");
		we.click();
		
		List<WebElement> sortItemCount = null;
		sortItemCount.get(indexOfSortItem).click();
		
	}
}
