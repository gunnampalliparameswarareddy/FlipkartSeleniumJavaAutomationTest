package com.gunnampalli.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gunnampalli.drivers.DriverManager;
import com.gunnampalli.enums.ExplicitWaitStratergy;
import com.gunnampalli.extentreports.ExtentLogger;
import com.gunnampalli.factories.ExplicitWaitFactory;

public class HomePage extends BasePage{

	private String loginWindowPath = "//div[@class='_17UxrZ']";
	private String mobilePath = "//span[@class='_1XjE3T']/span[contains(text(),'Mobiles')]";
	private String lowtoHighPricePath="//div[contains(text(),'Price -- Low to High')]";
	private String lowestMobilePricePath = "//div[@class=\"hl05eU\"]/descendant::div[contains(@class,\"Nx9bqj\")]";
	private String lowestMobileAmountPath ="(//div[contains(@class,'ZFwe0M')]/descendant::div[@class='oFEPlD']/descendant::div[contains(@class,'hZ3P6w')])[1]";
	public HomePage isLoginPageEnabled()
	{
		List<WebElement> loginWindows = DriverManager.getDriver().findElements(By.xpath(loginWindowPath));
	    
	    if (!loginWindows.isEmpty() && loginWindows.get(0).isDisplayed()) {
	        String closeButtonPath = "//span[@class='_30XB9F']";
	        click(By.xpath(closeButtonPath), ExplicitWaitStratergy.CLICKABLE, "Login Window Closed");
	    }
		return this;
	}
	public HomePage clickMobilesTablets()
	{
		click(By.xpath(mobilePath),ExplicitWaitStratergy.CLICKABLE,"Mobiles and Tablets menu ");
		return this;
	}
	
	public HomePage clickLowToHighPrice()
	{
		click(By.xpath(lowtoHighPricePath),ExplicitWaitStratergy.CLICKABLE,"Low To High Price");
		return this;
	}
	
	
	public HomePage SelectMinAndMaxPrice()
	{
		String minAmountPath = "//div[@class='FrhiFV']";
		String minAmountSelectPath = "//div[@class='FrhiFV']/select[@class = 'hbnjE2']";
		String maxAmountPath = "//div[@class='WoGl7t']";
		String maxAmountSelectPath = "//div[@class='WoGl7t']/select[@class='hbnjE2']";
		
		
		click(By.xpath(minAmountPath),ExplicitWaitStratergy.CLICKABLE,"Min Amount ");
		dropDown(By.xpath(minAmountSelectPath), ExplicitWaitStratergy.VISIBLE, "0","Min amount is selected");
		click(By.xpath(maxAmountPath),ExplicitWaitStratergy.CLICKABLE,"Max Amount ");
		dropDown(By.xpath(maxAmountSelectPath), ExplicitWaitStratergy.VISIBLE, "0","Max amount is selected");
		return this;
	}
	
	public String validateLowestMobilePrice()
	{
		String mobilePrice = fetchText(By.xpath(lowestMobileAmountPath), ExplicitWaitStratergy.VISIBLE_AND_ENABLED, "Lowest Mobile Price is Displayed");

		// Remove the currency symbol and keep only digits
		String numericPrice = mobilePrice.replaceAll("[^0-9]", "");
//		System.out.println(numericPrice);
//		// Convert to integer if needed
//		int price = Integer.parseInt(numericPrice);
		
		return numericPrice;
	}
}
