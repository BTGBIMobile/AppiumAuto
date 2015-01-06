package com.sas.mobile.loggenerator;

public class GetResultStatus {
	
	public static void startCase(String caseName) {
		GenerateHtmlFile.Logger.info("Execute case : "+ caseName);
		
	}
	public static void startCase(int count,String caseName) {
		GenerateHtmlFile.Logger.info("Execute case "+count+": "+ caseName);
		
	}
	
	public static void passCase(){
		GenerateHtmlFile.Logger.finest("Pass");
		
	}
	public static void passCase(int count,String caseName){
		GenerateHtmlFile.Logger.finest("Pass case "+count+": "+ caseName);
		
	}
	
	public static void failCase(){
		GenerateHtmlFile.Logger.severe("Failed");
		
	}
	public static void failCase(String failed){
		GenerateHtmlFile.Logger.severe(failed);
		
	}
	
	public static void failCase(int count,String errorReason){
		GenerateHtmlFile.Logger.severe("Failed case "+count+": "+errorReason);
		
	}
	
	public static void endCase(String exceptionInfo){
		GenerateHtmlFile.Logger.config("");
		
	}
	
	public static void info(String message){
		GenerateHtmlFile.Logger.finer(message);
		
	}
	
	public static void screenshot(String message){
		GenerateHtmlFile.Logger.fine(message);
		
	}
	
	public static void ExceptionCase(String exceptionInfo){
		GenerateHtmlFile.Logger.config("NOT COMPLETED WITH EXCEPTION: " + exceptionInfo);
		
	}
}
