package com.amazon.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AmazonLoginPage {

	private Page page;

	// 1. String Locators - OR
	private String emailId = "//input[@id='ap_email']";
	private String continueBtn = "//input[@id='continue']";
	private String password= "//input[@id='ap_password']";
	private String signBtn = "//input[@id='signInSubmit']";
	private String helloNameStr = "//span[@id='nav-link-accountList-nav-line-1']";
	private String strHelloName = "Hello, Gandhimathi";
	private String addToCartbtn ="//input[@id='add-to-cart-button']";
	private String shoppingCartStr ="//h2[@class='a-size-extra-large a-text-normal']";
	

	// 2. page constructor:
	public AmazonLoginPage(Page page) {
		this.page = page;
	}
	
	public boolean doLogin(String appUserName, String appPassword) {
		System.out.println("Amazon creds: " + appUserName + ":" + appPassword);
		
		page.waitForSelector(emailId);
		page.fill(emailId, appUserName);
		page.click(continueBtn);
		
		page.waitForSelector(password);
		page.fill(password, appPassword);
		page.click(signBtn);
		
		 page.waitForSelector(helloNameStr);
		 String helloName = page.textContent(helloNameStr);
         if(helloName.equalsIgnoreCase(strHelloName)) {
         	System.out.println("Logged in Successfully");
         	return true;
         }
         else {
         	System.out.println("Logged in not success");
         	return false;
         }
	}
	public boolean addToCart(String productName) {
		
		//span[contains(text(),'Amazon Basics 2.4 Ghz Wireless Optical Computer Mouse with USB Nano Receiver, Black')]/parent :: a[contains(@class,'a-link-normal')])
		String productNameLink ="//span[contains(text(),'"+productName+"')]/parent :: a[contains(@class,'a-link-normal')]";
		
		 page.waitForSelector(productNameLink);
         
         
		 page.click(productNameLink);    
       
         // Wait for the product page to load
         page.waitForSelector(addToCartbtn);
         
         // Add the product to the cart
         page.click(addToCartbtn);
         
         // Optionally, wait for the cart confirmation popup or navigate to the cart
         
         
         
         // Print a confirmation message
        // System.out.println("Product added to cart successfully.");
         
         page.waitForSelector("//a[@id='nav-cart']");
         page.click("//a[@id='nav-cart']");
         
         page.waitForSelector(shoppingCartStr);
         
        // page.waitForSelector("//*[contains(text(),'"+productName+"')]/parent :: a");
         Locator locator = page.locator("//*[contains(text(),'"+productName+"')]/parent :: a");
         
         System.out.println("locator.count: "+locator.count());
         // Check if the element exists
         if (locator.count() > 0) {
             System.out.println("Product added in AddtoCart Page");
             return true;
         } else {
             System.out.println("Product does not added in AddtoCart Page .");
             return false;
         }
         
		
		
	}
}
