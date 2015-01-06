/**
 * @author scnmip
 * 
 */


package com.sas.mobile.main;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.sas.bimobile.BIMobile_Android_Lev0;
import com.sas.mobile.android.bimobile.common.FunctionHub;
import com.sas.mobile.android.vah.html5.LogOnVAH;
import com.sas.mobile.appium.capabilities.SetCapabilities;
import com.sas.mobile.loggenerator.TestOrder;
import com.sas.mobile.loggenerator.GenerateHtmlFile;
import com.sas.mobile.loggenerator.GetResultStatus;


/**
 * There are 3 methods in Main : setUp(), runTest() and tearDown().
 * setUp(): set up Appium and generate html file log 
 * runTest(): run test cases
 * tearDown(): end run circle
 * Since we choose Java project to run the test other than Maven project so 
 * setUp(), runTest() and tearDown() should be ordered correctly in main() method.
 **/

public class Main {
	
	protected static AndroidDriver driver;//From 2.0 version of java-client, AppiumDriver is changed to abstract, so we use AndroidDriver/IOSDriver to define driver;For more details of 'AppiumDriver' please refer to http://appium.github.io/java-client/
	protected static DesiredCapabilities capabilities = new DesiredCapabilities();
	protected static File projectRootPath = new File(System.getProperty("user.dir"));
	public static String LogLocation = projectRootPath+"//result";// Log stored at "result" directory in projectRootPath
	protected static String configFile="android_native"+".cfg"; //select which configure file to load; 4 options : android_native/android_browser/ios_native/ios_browser
	
    private void setUp() throws Exception {
	   	SetCapabilities.setCap(); // set up Appium   
	   	GenerateHtmlFile.createLogFile();//generate file log in the form of an HTML page
	}
    
    private void runTest() throws InterruptedException {
    	//for mobile BI Level 0
    	FunctionHub.assertNaviDrawer();//assert drawer displayed or not
    	BIMobile_Android_Lev0.viewAllSectionsOfApp();
    	BIMobile_Android_Lev0.addConnection_Guest("rdcesx16171.race.sas.com","7980");
    	BIMobile_Android_Lev0.addConnection_UIDAndPwd("rdcesx16171.race.sas.com","7980","nlsbic", "2014@btg");
//    	BIMobile_Android_Lev0.searchReport("基准报表-Baseline");
//    	BIMobile_Android_Lev0.subscribeToReport("guest@rdcesx16171.race.sas.com","基准报表-Baseline");
//    	BIMobile_Android_Lev0.openReport("基准报表-Baseline");
//    	BIMobile_Android_Lev0.deleteConnection("SAS Demo Server");
    	
    	
    	//for mobile BI Level 1
//    	FunctionHub.assertNaviDrawer();//assert drawer displayed or not
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
//    	GenerateHtmlFile.createLogFile();
    	//for vah vav html5
//		LogOnVAH.logOn();
    	
    	
//    	BIMobile_Android_Lev0 levo = null;
//		try {
//			levo = (BIMobile_Android_Lev0) Class.forName("BIMobile_Android_Lev0").newInstance();
//		} catch (InstantiationException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IllegalAccessException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//    	
//    	Method[] allMethods = levo.getClass().getDeclaredMethods();
//    	Hashtable<Integer, Method> ht=new Hashtable<Integer,Method>();
//  	  	for (Method method:allMethods){
//  		  if(method.isAnnotationPresent(TestOrder.class)){
//  			  TestOrder test = method.getAnnotation(TestOrder.class);
//  			  ht.put(test.Order(), method);
//  		  }
//  	  	}
//  	  	ArrayList<Integer> al = new ArrayList<Integer>(ht.keySet());
//  	  	Collections.sort(al);
//  	  
//  	  	int count=1;
//  	  
//  	  	for (Iterator<Integer> i = al.iterator();i.hasNext();){
//  		  Method m = (Method) ht.get(i.next());
//  		  String mname=m.getName();
//  		  GetResultStatus.startCase(count, mname);
//  		  try{
//  			  m.setAccessible(true);
//  			  m.invoke(levo);
//  			  GetResultStatus.passCase(count);
//  			  count++;
//  			  System.out.println(mname+" Pass!");
//  			  GetResultStatus.endCase("");
//  		  }catch(InvocationTargetException x){
//  			  Throwable cause=x.getCause();
//  			  GetResultStatus.failCase(count, cause.getMessage());
//  			  System.out.println(mname+" Failed!");
//  			  count++;
//  		  }catch(Exception e){
//  			  e.printStackTrace();
//  		  }
//  	  }
    	
    	
	}
    
    private void tearDown() throws Exception {
		 driver.quit();
	}
	
	public static void main(String[] args) throws Exception {
		Main m = new Main();
		m.setUp();
		m.runTest();
		m.tearDown();
	}

}
