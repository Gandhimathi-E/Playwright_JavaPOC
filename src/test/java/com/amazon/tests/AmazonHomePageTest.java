package com.amazon.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amazon.base.BaseTest;
import com.amazon.constants.AppConstants;

public class AmazonHomePageTest extends BaseTest {

	@Test
	public void homePageTitleTest() {
		String actualTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
	}

	@Test
	public void homePageURLTest() {
		String actualURL = homePage.getHomePageURL();
		Assert.assertEquals(actualURL, prop.getProperty("url"));
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
				{ "wireless mouse"}
		};
	}

	@Test(dataProvider = "getProductData")
	public void searchTest(String productName) throws InterruptedException {
		Thread.sleep(5000);
		String actualSearchHeader = homePage.doSearch(productName).replace("\"", "").trim();
		Assert.assertEquals(actualSearchHeader, productName);
	}

}
