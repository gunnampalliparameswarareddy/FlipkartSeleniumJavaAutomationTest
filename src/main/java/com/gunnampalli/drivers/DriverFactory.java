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
			}
			else if(browser.equalsIgnoreCase("firefox"))
			{	
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
			}
			else if(browser.equalsIgnoreCase("edge"))
			{
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
			}
		}
		else if(runmode.equalsIgnoreCase(DriverType.REMOTE_SAUCELABS.toString()))
		{
			if(browser.equalsIgnoreCase(FrameworkConstants.getChromebrowsertype()))
			{
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--lang=en");
				options.addArguments("--incognito");
				options.setPlatformName("Windows 11");
				options.setBrowserVersion("latest");

				Map<String,Object> sauceOptions = new HashMap<>();
				sauceOptions.put("username", "oauth-gunnampalliparamesh-1dc43");
				sauceOptions.put("accessKey", "6614c6bc-03e8-4ddf-9bab-08139fc2535a");
				sauceOptions.put("build", "selenium-build-W1C1E");
				sauceOptions.put("name", method.getName());

				options.setCapability("sauce:options", sauceOptions);

				URL url = null;
				try {
					url = new URL("https://ondemand.eu-central-1.saucelabs.com/wd/hub");
				} catch (MalformedURLException e) {
					throw new MalformedURLExceptionError("URL Exception in Sauce Labs",e);
				}
				driver = new RemoteWebDriver(url, options);
			}
			else if(browser.equalsIgnoreCase(FrameworkConstants.getFirefoxbrowsertype()))
			{
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--lang=en");
				options.addArguments("--private");
				options.setPlatformName("Windows 11");
				options.setBrowserVersion("latest");

				Map<String,Object> sauceOptions = new HashMap<>();
				sauceOptions.put("username", "oauth-gunnampalliparamesh-1dc43");
				sauceOptions.put("accessKey", "6614c6bc-03e8-4ddf-9bab-08139fc2535a");
				sauceOptions.put("build", "selenium-build-W1C1E");
				sauceOptions.put("name", method.getName());

				options.setCapability("sauce:options", sauceOptions);
				URL url = null;
				try {
					url = new URL("https://ondemand.eu-central-1.saucelabs.com/wd/hub");
				} catch (MalformedURLException e) {
					throw new MalformedURLExceptionError("URL Exception in Sauce Labs",e);
				}
				driver = new RemoteWebDriver(url, options);
			}
			else if(browser.equalsIgnoreCase(FrameworkConstants.getEdgebrowsertype()))
			{
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--lang=en");
				options.addArguments("--inprivate");
				options.setPlatformName("Windows 11");
				options.setBrowserVersion("latest");
				

				Map<String,Object> sauceOptions = new HashMap<>();
				sauceOptions.put("username", "oauth-gunnampalliparamesh-1dc43");
				sauceOptions.put("accessKey", "6614c6bc-03e8-4ddf-9bab-08139fc2535a");
				sauceOptions.put("build", "selenium-build-W1C1E");
				sauceOptions.put("name", method.getName());

				options.setCapability("sauce:options", sauceOptions);
				URL url = null;
				try {
					url = new URL("https://ondemand.eu-central-1.saucelabs.com/wd/hub");
				} catch (MalformedURLException e) {
					throw new MalformedURLExceptionError("URL Exception in Sauce Labs",e);
				}
				driver = new RemoteWebDriver(url, options);
			}
		}
		else if (runmode.equalsIgnoreCase(DriverType.LOCAL.toString())) {

		    if (browser.equalsIgnoreCase(FrameworkConstants.getChromebrowsertype())) {
		    	ChromeOptions options = new ChromeOptions();
//		    	options.addArguments("--user-data-dir=C:/Users/HP/AppData/Local/Google/Chrome/User Data");
//		    	options.addArguments("--profile-directory=Profile 2");
		    	options.addArguments("--disable-infobars");
		    	options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
		    	options.setExperimentalOption("useAutomationExtension", false);
		    	driver = new ChromeDriver(options);

		    }

		    else if (browser.equalsIgnoreCase(FrameworkConstants.getFirefoxbrowsertype())) {
		    	ProfilesIni profileIni = new ProfilesIni();
		    	FirefoxProfile profile = profileIni.getProfile("default-release"); // or "default"

		    	FirefoxOptions options = new FirefoxOptions();
		    	options.setProfile(profile);
		    	options.addArguments("--private");

		    	driver = new FirefoxDriver(options);
		    }

		    else if (browser.equalsIgnoreCase(FrameworkConstants.getEdgebrowsertype())) {
		    	String userProfile = "C:/Users/HP/AppData/Local/Microsoft/Edge/User Data";
		    	String profileDirectory = "Default";

		    	EdgeOptions options = new EdgeOptions();
		    	options.setBinary("C:/Program Files (x86)/Microsoft/Edge/Application/msedge.exe"); // ✅ force 64-bit Edge
		    	options.addArguments("user-data-dir=" + userProfile);
		    	options.addArguments("profile-directory=" + profileDirectory);
		    	options.addArguments("--remote-allow-origins=*");

		    	// optional: test without --inprivate first
		    	// options.addArguments("--inprivate");

		    	driver = new EdgeDriver(options);

		    }
		}
		else
		{
			if (browser.equalsIgnoreCase(FrameworkConstants.getChromebrowsertype())) {
		    	ChromeOptions options = new ChromeOptions();
//		    	options.addArguments("--user-data-dir=C:/Users/HP/AppData/Local/Google/Chrome/User Data");
//		    	options.addArguments("--profile-directory=Profile 2");
		    	options.addArguments("--disable-infobars");
		    	options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
		    	options.setExperimentalOption("useAutomationExtension", false);
		    	driver = new ChromeDriver(options);
		    }

		}


		return driver;
	}


}
