package com.gunnampalli.extentreports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentReportManager {
	private ExtentReportManager()
	{
		
	}
	
	private static ThreadLocal<ExtentTest> et = new ThreadLocal<>();
	
	public static void setExtentTest(ExtentTest extentTest)
	{
		et.set(extentTest);
	}
	
	public static ExtentTest getExtentTest()
	{
		return et.get();
	}
	
	public static void unload()
	{
		et.remove();
	}
}
