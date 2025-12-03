package com.gunnampalli.drivers;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Optional;

import com.gunnampalli.constants.FrameworkConstants;
import com.gunnampalli.enums.ConfigProperties;
import com.gunnampalli.enums.DriverType;
import com.gunnampalli.exceptions.FrameworkExceptions;
import com.gunnampalli.exceptions.MalformedURLExceptionError;
import com.gunnampalli.utils.PropertyUtils;

public final class DriverFactory {
	private DriverFactory()
	{}

	public static WebDriver initDriverType(@Optional("chrome")String browser,String version,Method method)
	{
		WebDriver driver= null;
		String runmode = PropertyUtils.getConfigMap(ConfigProperties.RUNMODE);
		if(runmode.equalsIgnoreCase(DriverType.REMOTE_DOCKER.toString()))
		{
			String port="";
			String dockerHost = "http://192.168.99.100";
			if(browser.equalsIgnoreCase("chrome"))
			{
				driver = RemoteDocker.setUpChromeDriver(version, dockerHost, port);
			}
			else if(browser.equalsIgnoreCase("firefox"))
			{	
				driver = RemoteDocker.setUpFirefoxDriver(version, dockerHost, port);
			}
			else if(browser.equalsIgnoreCase("edge"))
			{
				driver = RemoteDocker.setUpEdgeDriver(version, dockerHost, port);
			}
		}
		else if(runmode.equalsIgnoreCase(DriverType.REMOTE_SAUCELABS.toString()))
		{
			if(browser.equalsIgnoreCase(FrameworkConstants.getChromebrowsertype()))
			{
				driver = RemoteSauceLabs.setUpChromeDriver(method);
			}
			else if(browser.equalsIgnoreCase(FrameworkConstants.getFirefoxbrowsertype()))
			{
				driver = RemoteSauceLabs.setUpFirefoxDriver(method);
			}
			else if(browser.equalsIgnoreCase(FrameworkConstants.getEdgebrowsertype()))
			{
				driver = RemoteSauceLabs.setupEdgeDriver(method);
			}
		}
		else if (runmode.equalsIgnoreCase(DriverType.LOCAL.toString())) {

			if (browser.equalsIgnoreCase(FrameworkConstants.getChromebrowsertype())) {

				driver = LocalBrowsers.setUpChromeDriver();
			}

			else if (browser.equalsIgnoreCase(FrameworkConstants.getFirefoxbrowsertype())) {
				driver = LocalBrowsers.setUpFirefoxDriver();
			}

			else if (browser.equalsIgnoreCase(FrameworkConstants.getEdgebrowsertype())) {
				driver = LocalBrowsers.setUpEdgeDriver();
			}
		}
		else
		{
			if (browser.equalsIgnoreCase(FrameworkConstants.getChromebrowsertype())) {
				driver = LocalBrowsers.setUpChromeDriver();
			}
		}
		return driver;
	}
}
