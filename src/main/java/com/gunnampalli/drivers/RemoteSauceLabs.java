package com.gunnampalli.drivers;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.gunnampalli.exceptions.MalformedURLExceptionError;

public final class RemoteSauceLabs {
	private RemoteSauceLabs() {
		
	}
	
	static WebDriver setUpChromeDriver(Method method)
	{
		WebDriver driver = null;
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
		return driver;
	}
	
	static WebDriver setUpFirefoxDriver(Method method)
	{
		WebDriver driver = null;
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
		return driver;
	}
	
	static WebDriver setupEdgeDriver(Method method)
	{
		WebDriver driver= null;
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
		return driver;
	}
}
