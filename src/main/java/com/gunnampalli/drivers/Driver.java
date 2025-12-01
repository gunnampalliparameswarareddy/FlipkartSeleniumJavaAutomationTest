package com.gunnampalli.drivers;

import java.lang.reflect.Method;
import java.util.Objects;

import com.gunnampalli.enums.ConfigProperties;
import com.gunnampalli.utils.PropertyUtils;

public final class Driver {
	private Driver()
	{
		
	}
	public static void initDriver(String browser, String version,Method method)
	{
		if(Objects.isNull(DriverManager.getDriver()))
		{
			DriverManager.setDriver(DriverFactory.initDriverType(browser, version,method));
			DriverManager.getDriver().manage().window().maximize();
			DriverManager.getDriver().get(PropertyUtils.getConfigMap(ConfigProperties.URL));
		}
	}
	
	public static void teardown()
	{
		if(Objects.nonNull(DriverManager.getDriver()))
		{
			System.out.println("teardown");
			DriverManager.getDriver().quit();
		}
	}
}
