package com.gunnampalli.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.gunnampalli.enums.ExplicitWaitStratergy;
import com.gunnampalli.extentreports.ExtentLogger;
import com.gunnampalli.factories.ExplicitWaitFactory;

public class BasePage {
	protected void click(By by,ExplicitWaitStratergy explicitywaitstratergy,String elementname)
	{
		WebElement element = ExplicitWaitFactory.explicitWaitFactory(by, explicitywaitstratergy);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		element.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentLogger.pass(elementname+" is clicked", false);
	}
	protected String fetchText(By by,ExplicitWaitStratergy explicitWaitStratergy,String elementname)
	{
		WebElement element = ExplicitWaitFactory.explicitWaitFactory(by, explicitWaitStratergy);
		ExtentLogger.pass(elementname+" and text is fetched", false);

		return element.getText();
	}
	protected void visible(By by,ExplicitWaitStratergy explicitwaistrateStratergy,String elementname)
	{
		ExplicitWaitFactory.explicitWaitFactory(by, explicitwaistrateStratergy);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentLogger.pass(elementname+" is visible", false);
	}

	protected void dropDown(By by, ExplicitWaitStratergy explicitWaitStratergy, String dropDownIndex, String testName) {			
			WebElement element = ExplicitWaitFactory.explicitWaitFactory(by, explicitWaitStratergy);
			Select dropDown = new Select(element);
			int index = Integer.parseInt(dropDownIndex);
			dropDown.selectByIndex(index);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Re-fetch element to avoid stale reference
		    element = ExplicitWaitFactory.explicitWaitFactory(by, explicitWaitStratergy);
		    dropDown = new Select(element);
			String selectedDropDown = dropDown.getFirstSelectedOption().getText();
			ExtentLogger.pass("Index " + index + " selected (" + selectedDropDown + ") - " + testName, false);
	}
}
