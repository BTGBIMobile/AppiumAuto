package com.sas.bimobile;


import com.sas.mobile.android.bimobile.Connection;
import com.sas.mobile.android.bimobile.Report;
import com.sas.mobile.android.bimobile.AppDrawer;
import com.sas.mobile.appium.capabilities.ConfigCapParas;
import com.sas.mobile.loggenerator.GetResultStatus;
import com.sas.mobile.loggenerator.TestOrder;
import com.sas.mobile.main.Main;


	public class BIMobile_Android_Lev0 extends Main{
		
		public static void viewAllSectionsOfApp(){
			AppDrawer.viewAllSections();
		}
		
		public static void addConnection_Guest(String url, String port){
			GetResultStatus.startCase("addConnection_Guest");
			Connection.addConnection(url, port, true, "", "");//add server as Guest
		}
		
		public static void addConnection_UIDAndPwd(String url, String port, String uid, String password){
			GetResultStatus.startCase("addConnection_UIDandPWD");
			Connection.addConnection(url, port, false, uid, password);//add server as normal user
			
		}
		
		public static void searchReport(String keyword){
			Report.searchReport(keyword);
			
		}

		public static void subscribeToReport(String server, String reportName){
			Report.subscribeToReport(server,reportName);
		}

		public static void deleteConnection(String serverName){
			GetResultStatus.startCase("DeleteConnection");
			Connection.removeConnection(serverName);
		}

		public static void openReport(String reportName){
			Report.viewAllSectionsOfReport(reportName);
		}
		
}
