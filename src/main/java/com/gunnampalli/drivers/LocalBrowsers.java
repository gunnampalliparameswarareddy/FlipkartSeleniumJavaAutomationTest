package com.gunnampalli.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;

public final class LocalBrowsers {
 private LocalBrowsers() {}
 
 	static WebDriver setUpChromeDriver()
 	{
 		WebDriver driver = null;
 		ChromeOptions options = new ChromeOptions();
//    	options.addArguments("--user-data-dir=C:/Users/HP/AppData/Local/Google/Chrome/User Data");
//    	options.addArguments("--profile-directory=Profile 2");
    	options.addArguments("--disable-infobars");
    	options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
    	options.setExperimentalOption("useAutomationExtension", false);
    	driver = new ChromeDriver(options);
 		return driver;	
 	}
 	
 	static WebDriver setUpFirefoxDriver()
 	{
 		WebDriver driver = null;
 		ProfilesIni profileIni = new ProfilesIni();
    	FirefoxProfile profile = profileIni.getProfile("default-release");

    	FirefoxOptions options = new FirefoxOptions();
    	options.setProfile(profile);
    	options.addArguments("--private");

    	driver = new FirefoxDriver(options);
 		return driver;
 	}
 	static WebDriver setUpEdgeDriver()
 	{
 		WebDriver driver = null;
 		String userProfile = "C:/Users/HP/AppData/Local/Microsoft/Edge/User Data";
    	String profileDirectory = "Default";

    	EdgeOptions options = new EdgeOptions();
		/*
		 * options.
		 * setBinary("C:/Program Files (x86)/Microsoft/Edge/Application/msedge.exe");
		 * options.addArguments("user-data-dir=" + userProfile);
		 * options.addArguments("profile-directory=" + profileDirectory);
		 */
    	options.addArguments("--remote-allow-origins=*");

    	// optional: test without --inprivate first
    	// options.addArguments("--inprivate");

    	driver = new EdgeDriver(options);
 		return driver;
 	}
}
