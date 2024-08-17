package com.amazon.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amazon.base.BaseTest;
import com.amazon.constants.AppConstants;

public class AmazonLoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void appLoginTest() {
		loginPage = homePage.navigateToLoginPage();
		boolean loginStatus = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(loginStatus);
	}
	 
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
				{ "wireless mouse"}
		};
	}

	@Test(dataProvider = "getProductData" , priority = 2)
	public void searchTest(String productName) throws InterruptedException {
		Thread.sleep(5000);
		String actualSearchHeader = homePage.doSearch(productName).replace("\"", "").trim();
		Assert.assertEquals(actualSearchHeader, productName);
	}
	

	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] {
				{"Amazon Basics 2.4 Ghz Wireless Optical Computer Mouse with USB Nano Receiver, Black"}
		};
	}
	
	@Test(dataProvider = "getProductName" ,priority = 3)
	public void addToCartTest(String productName ) {
		boolean addToCartStatus = loginPage.addToCart(productName);
		Assert.assertTrue(addToCartStatus);
	}

}
