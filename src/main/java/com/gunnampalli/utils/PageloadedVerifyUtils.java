package com.gunnampalli.utils;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gunnampalli.drivers.DriverManager;

public class PageloadedVerifyUtils {
    private PageloadedVerifyUtils() {}

    public static boolean waitForPageLoad() {
        WebDriver driver = DriverManager.getDriver();
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            wd -> ((JavascriptExecutor) wd)
                    .executeScript("return document.readyState")
                    .equals("complete")
        );
    }
}
