package com.gunnampalli.extentreports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.gunnampalli.enums.ConfigProperties;
import com.gunnampalli.utils.PropertyUtils;
import com.gunnampalli.utils.ScreenshotUtils;

public class ExtentLogger {

    private static void pass(String testName) {
        ExtentReportManager.getExtentTest().pass(testName + " and it is passed");
    }

    private static void fail(String testName) {
        ExtentReportManager.getExtentTest().fail(testName + " and it is failed");
    }

    private static void skip(String testName) {
        ExtentReportManager.getExtentTest().skip(testName + " and it is skipped");
    }

    public static void pass(String testName, boolean isScreenShotNeeded) {
        if (PropertyUtils.getConfigMap(ConfigProperties.PASSTESTSCREENSHOT).equalsIgnoreCase("yes") && isScreenShotNeeded) {
            try {
                ExtentReportManager.getExtentTest().pass(
                    testName + " and it is passed",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build()
                );
            } catch (Exception e) {
                pass(testName); // fallback if screenshot fails
            }
        } else {
            pass(testName);
        }
    }

    public static void fail(String testName, boolean isScreenShotNeeded) {
        if (PropertyUtils.getConfigMap(ConfigProperties.FAILTESTSCREENSHOT).equalsIgnoreCase("yes") && isScreenShotNeeded) {
            try {
                ExtentReportManager.getExtentTest().fail(
                    testName + " and it is failed",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build()
                );
            } catch (Exception e) {
                fail(testName);
            }
        } else {
            fail(testName);
        }
    }

    public static void skip(String testName, boolean isScreenShotNeeded) {
        if (PropertyUtils.getConfigMap(ConfigProperties.SKIPTESTSCREENSHOT).equalsIgnoreCase("yes") && isScreenShotNeeded) {
            try {
                ExtentReportManager.getExtentTest().skip(
                    testName + " and it is skipped",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build()
                );
            } catch (Exception e) {
                skip(testName);
            }
        } else {
            skip(testName);
        }
    }
}
