package com.gunnampalli.test;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.gunnampalli.drivers.Driver;
import com.gunnampalli.utils.PageloadedVerifyUtils;

public class BaseTest {
	protected BaseTest()
	{
		
	}
//	The below method if we want to Execute test using  Excel file need to uncomment
	/*
	 * @BeforeMethod public void setupDriver(Object[] data,Method method) {
	 * System.out.println("Executed Basetest"); Map<String,String> map =
	 * (Map<String,String>)data[0];
	 * Driver.initDriver(map.get("browser"),map.get("version"),method);
	 * Assert.assertTrue(PageloadedVerifyUtils.waitForPageLoad()); }
	 */
	
	@Parameters({"browser","version"})
	@BeforeMethod
	public void setupDriver(String browser,String version,Method method)
	{
		Driver.initDriver(browser,version,method);
	    Assert.assertTrue(PageloadedVerifyUtils.waitForPageLoad()); 	
	}
	
	@AfterMethod
	public void quitDriver()
	{
		Driver.teardown();
	}
}
