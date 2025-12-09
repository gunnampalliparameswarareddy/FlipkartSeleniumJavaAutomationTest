package com.gunnampalli.extentreports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.gunnampalli.constants.FrameworkConstants;
import com.gunnampalli.enums.CategoryType;
import com.gunnampalli.exceptions.FileNotFoundExceptions;

public final class ExtentReport {
	private ExtentReport() {}
	private static ExtentReports extentReport;
	private static String reportDateTime;
	
	public static void initExtentReport()
	{
		if(Objects.isNull(extentReport)) {
			extentReport = new ExtentReports();
			reportDateTime = getDateAndTime();
			ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getReportspath()+reportDateTime+".html");
			spark.config().setDocumentTitle("Flipkart Automation TestReport");
			spark.config().setReportName("Automation Test Report");
			spark.config().setTheme(Theme.DARK);
			extentReport.attachReporter(spark);
			System.out.println("InitExtentReport called in SUite");
		}

	}
	public static void flushExtentReport()
	{
		if(Objects.nonNull(extentReport))
		{
			extentReport.flush();
		}
		ExtentReportManager.unload();
		try {
			Desktop.getDesktop().browse(new File(FrameworkConstants.getReportspath()+reportDateTime+".html").toURI());
		} catch (IOException e) {
			throw new FileNotFoundExceptions(e.toString());
		}
	}
	
	public static void createTest(String testName)
	{
		ExtentReportManager.setExtentTest(extentReport.createTest(testName));
	}
	
	public static void addAuthors(String [] authors)
	{	
		for (String author : authors) {
			ExtentReportManager.getExtentTest().assignAuthor(author);
		}
	}
	
	public static void addCategoryType(CategoryType[] categories)
	{
		for (CategoryType categoryType : categories) {
			ExtentReportManager.getExtentTest().assignCategory(categoryType.toString());
		}
	}
	
	public static void addBrowserType(String browser)
	{
		ExtentReportManager.getExtentTest().assignDevice(browser);
	}
	
	public static void addSystemInfo(String value)
	{
		ExtentReportManager.getExtentTest().assignDevice(value);
	}
	public static ExtentReports getExtentReport()
	{
		return extentReport;
	}
	
	public static String getDateAndTime()
	{
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MMM_yyyy_hh_mm_ss");
		String formatedDate = now.format(formatter);

		return formatedDate;
	}
}
