package com.gunnampalli.factories;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gunnampalli.constants.FrameworkConstants;
import com.gunnampalli.drivers.DriverManager;
import com.gunnampalli.enums.ExplicitWaitStratergy;

public final class ExplicitWaitFactory {

	private ExplicitWaitFactory()
	{

	}
	public static WebElement explicitWaitFactory(By by,ExplicitWaitStratergy explicitWait)
	{
		WebElement element = null;

		if(explicitWait == ExplicitWaitStratergy.CLICKABLE)
		{
			element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwaittime())).until(ExpectedConditions.elementToBeClickable(by));
		}
		else if(explicitWait==ExplicitWaitStratergy.VISIBLE)
		{
			element = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(FrameworkConstants.getExplicitwaittime())).until(ExpectedConditions.visibilityOfElementLocated(by));
		}
		else if(explicitWait==ExplicitWaitStratergy.INVISIBLE)
		{
			new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwaittime())).until(ExpectedConditions.invisibilityOfElementLocated(by));
		}
		else if(explicitWait==ExplicitWaitStratergy.PRESENCE)
		{
			element = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(FrameworkConstants.getExplicitwaittime())).until(ExpectedConditions.presenceOfElementLocated(by));
		}
		else if(explicitWait==ExplicitWaitStratergy.VISIBLE_AND_ENABLED)
		{
			element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitwaittime())).until(driver ->
			{
				WebElement el = driver.findElement(by);
				return (el.isDisplayed() && el.isEnabled())? el : null;
			}
					);
		}
		else if(explicitWait == ExplicitWaitStratergy.NONE)
		{
			element = DriverManager.getDriver().findElement(by);
		}
		return element;
	}

	public static List<WebElement> explicitWaitFactorys(By by, ExplicitWaitStratergy explicitWait) {
		List<WebElement> elements = null;
		WebDriverWait wait = new WebDriverWait(
				DriverManager.getDriver(),
				Duration.ofSeconds(FrameworkConstants.getExplicitwaittime())
				);

		switch (explicitWait) {
		case VISIBLE:
			elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
			break;

		case PRESENCE:
			elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
			break;

		case CLICKABLE:
			// For lists, clickable is less common, but you can adapt
			WebElement singleElement = wait.until(ExpectedConditions.elementToBeClickable(by));
			elements = new ArrayList<>();
			elements.add(singleElement);
			break;

		default:
			throw new IllegalArgumentException("Unsupported ExplicitWaitStrategy: " + explicitWait);
		}

		return elements;
	}

}

