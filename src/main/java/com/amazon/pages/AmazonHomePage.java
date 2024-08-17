package com.amazon.pages;

import com.microsoft.playwright.Page;

public class AmazonHomePage {

	private Page page;

	// 1. String Locators - OR
	private String search = "input[id='twotabsearchtextbox']";
	private String searchIcon = "input[id='nav-search-submit-button']"; 
	private String searchPageHeader = "//span[@class='a-color-state a-text-bold']";
	private String searchResultStr = "//h2[@class='a-size-medium-plus a-spacing-none a-color-base a-text-bold']";
	private String signInLink = "//a[@id='nav-link-accountList']";

	// 2. page constructor:
	public AmazonHomePage(Page page) {
		this.page = page;
	}

	// 3. page actions/methods:
	public String getHomePageTitle() {
		String title =  page.title();
		System.out.println("page title: " + title);
		return title;
	}

	public String getHomePageURL() {
		String url =  page.url();
		System.out.println("page url : " + url);
		return url;
	}

	public String doSearch(String productName) {
		page.fill(search, productName);
		page.click(searchIcon);
		page.waitForSelector(searchPageHeader);
		String header =  page.textContent(searchPageHeader);
		//page.waitForSelector(searchResultStr);
		//String header =  page.textContent(searchResultStr);
		System.out.println("search header: " + header);
		return header;
	}
	
	public AmazonLoginPage navigateToLoginPage() {
		page.waitForSelector(signInLink);
		page.click(signInLink);
	//	page.click(loginLink);
		return new AmazonLoginPage(page);
	}

}
