package com.gunnampalli.listeners;

import java.util.Arrays;
import java.util.Map;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.gunnampalli.annotations.FrameworkAnnotation;
import com.gunnampalli.enums.ResultType;
import com.gunnampalli.extentreports.ExtentLogger;
import com.gunnampalli.extentreports.ExtentReport;
import com.gunnampalli.extentreports.ExtentReportManager;
import com.gunnampalli.utils.ELKUtils;


public final class ListenerClass implements ISuiteListener,ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		
		FrameworkAnnotation annotation = result.getMethod()
				.getConstructorOrMethod()
				.getMethod()
				.getAnnotation(FrameworkAnnotation.class);
		System.out.println("OnTestStart - Listener");
		ExtentReport.createTest(result.getMethod().getMethodName());
 

		if (annotation != null) {
			ExtentReport.addAuthors(annotation.authors());
			ExtentReport.addCategoryType(annotation.categories());
		}
		ITestContext context = result.getTestContext();
	    String browser = context.getCurrentXmlTest().getParameter("browser");
	    String version = context.getCurrentXmlTest().getParameter("version");
	    ExtentReport.addSystemInfo(browser+" : "+version);
	    
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentLogger.pass(result.getMethod().getDescription(), false);
		//ELKUtils.sendDetailstoELK(result.getMethod().getMethodName(), ResultType.PASS.toString());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentLogger.fail(result.getMethod().getDescription(),true);
		ExtentLogger.fail(result.getThrowable().toString(),true);
		ExtentLogger.fail(result.getThrowable().getStackTrace().toString(),true);
		//ELKUtils.sendDetailstoELK(result.getMethod().getMethodName(), ResultType.FAIL.toString());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentLogger.skip(result.getMethod().getDescription(), false);
		//ELKUtils.sendDetailstoELK(result.getMethod().getMethodName(), ResultType.SKIP.toString());
	}

	@Override
	public void onStart(ISuite suite) {
		ExtentReport.initExtentReport();
		System.out.println("onStart called in Suite");

	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushExtentReport();
	}

}
