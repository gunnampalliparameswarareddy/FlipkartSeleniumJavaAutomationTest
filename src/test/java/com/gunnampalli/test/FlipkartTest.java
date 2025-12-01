package com.gunnampalli.test;


import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.gunnampalli.annotations.FrameworkAnnotation;
import com.gunnampalli.enums.CategoryType;
import com.gunnampalli.pages.HomePage;


public class FlipkartTest extends BaseTest {

	@FrameworkAnnotation(authors= {"gunnampalli","rajesh","John"},categories = {CategoryType.SMOKE,CategoryType.SANITY,CategoryType.REGRESSION})
	@Test
	public void flipkartValidTest(Map<String,String> map)
	{
		SoftAssert softAssert = new SoftAssert();
		String mobilePrice = new HomePage().isLoginPageEnabled().clickMobilesTablets().SelectMinAndMaxPrice().clickLowToHighPrice().validateLowestMobilePrice();
		System.out.println("Test is executed");
		String expectedValue = map.get("price");
		
		softAssert.assertEquals(mobilePrice, expectedValue,"Price Mismatch");
		
	}
	
	@FrameworkAnnotation(authors= {"gunnampalli","rajesh","Shyam"},categories = {CategoryType.SMOKE,CategoryType.SANITY,CategoryType.REGRESSION})
	@Test
	public void flipkartInvalidTest(Map<String,String> map)
	{
		SoftAssert softAssert = new SoftAssert();
		String mobilePrice = new HomePage().isLoginPageEnabled().clickMobilesTablets().SelectMinAndMaxPrice().clickLowToHighPrice().validateLowestMobilePrice();
		System.out.println("Test is executed");
		String expectedValue = map.get("price");
		
		softAssert.assertNotEquals(mobilePrice, expectedValue,"Price Mismatch");
		softAssert.assertAll();
		
	}
}
