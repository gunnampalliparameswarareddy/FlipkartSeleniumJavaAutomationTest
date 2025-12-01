package com.gunnampalli.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.gunnampalli.drivers.DriverManager;

public class ScreenshotUtils {

    private ScreenshotUtils() {}

    public static String getBase64Image() {
        WebDriver driver = DriverManager.getDriver();

        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Cannot capture screenshot.");
        }

        try {
            if (driver instanceof FirefoxDriver) {
                return ((FirefoxDriver) driver).getFullPageScreenshotAs(OutputType.BASE64);
            } else if (driver instanceof ChromeDriver) {
                return ((ChromeDriver)driver).getScreenshotAs(OutputType.BASE64);
            } else if (driver instanceof EdgeDriver) {
                return ((EdgeDriver)driver).getScreenshotAs(OutputType.BASE64);
            } else {
                return ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            }
        } catch (Exception e) {
            System.err.println("Screenshot capture failed: " + e.getMessage());
            return ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        }
    }
}
