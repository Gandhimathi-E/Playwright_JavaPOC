package com.amazon.base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Page;
import com.amazon.factory.PlaywrightFactory;
import com.amazon.pages.AmazonHomePage;
import com.amazon.pages.AmazonLoginPage;

public class BaseTest {

	PlaywrightFactory pf;
	Page page;
	protected Properties prop;

	protected AmazonHomePage homePage;
	protected AmazonLoginPage loginPage;

	@Parameters({ "browser" })
	@BeforeTest
	public void setup(String browserName) {
		pf = new PlaywrightFactory();

		prop = pf.init_prop();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}

		page = pf.initBrowser(prop);
		homePage = new AmazonHomePage(page);
	}

	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}

}
