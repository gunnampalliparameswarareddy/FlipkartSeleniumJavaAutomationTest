package com.gunnampalli.drivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.gunnampalli.exceptions.FrameworkExceptions;

public final class RemoteDocker {
	private RemoteDocker()
	{
		
	}
	static WebDriver setUpChromeDriver(String version,String dockerHost,String port)
	{
			WebDriver driver = null;
			ChromeOptions options = new ChromeOptions();
			options.setBrowserVersion(version);
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			options.setExperimentalOption("useAutomationExtension", false);
			if(version.equalsIgnoreCase("117.0"))
			{
				port = "5551";

			}
			else if(version.equalsIgnoreCase("116.0"))
			{
				port = "5552";

			}

			try {
				driver = new RemoteWebDriver(new URL(dockerHost+":"+port+"/"),options);
			} catch (MalformedURLException e) {
				throw new FrameworkExceptions("Remote Driver Failed Exception");
			}
			
			return driver;
	}
	
	static WebDriver setUpFirefoxDriver(String version,String dockerHost,String port)
	{
		WebDriver driver = null;
		
		FirefoxOptions options = new FirefoxOptions();
		options.setBrowserVersion(version);

		if(version.equalsIgnoreCase("117.0"))
		{
			port = "5553";
		}
		else if(version.equalsIgnoreCase("116.0"))
		{
			port = "5554";
		}
		try {
			driver = new RemoteWebDriver(new URL(dockerHost+":"+port+"/"),options);
		} catch (MalformedURLException e) {
			throw new FrameworkExceptions("Remote Driver Failed Exception");
		}
		
		return driver;
	}
	
	static WebDriver setUpEdgeDriver(String version,String dockerHost,String port)
	{
		WebDriver driver= null;
		EdgeOptions options = new EdgeOptions();
		options.setBrowserVersion(version);

		if(version.equalsIgnoreCase("117.0"))
		{
			port ="5555";
		}
		else if(version.equalsIgnoreCase("116.0"))
		{
			port ="5556";
		}
		try {
			driver = new RemoteWebDriver(new URL(dockerHost+":"+port+"/"),options);
		} catch (MalformedURLException e) {
			throw new FrameworkExceptions("Remote Driver Failed Exception");
		}
		return driver;
	}
}
