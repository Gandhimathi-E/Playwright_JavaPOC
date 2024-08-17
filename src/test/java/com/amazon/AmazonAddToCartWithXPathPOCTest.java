package com.amazon;

import java.nio.file.Paths;

import com.microsoft.playwright.*;

public class AmazonAddToCartWithXPathPOCTest {
	public static void main(String[] args) {
		
		 String productName ="Amazon Basics 2.4 Ghz Wireless Optical Computer Mouse with USB Nano Receiver, Black";
         String shoppingListLink = "//*[contains(text(),'Shopping List')]/parent :: a";
         String searchBox ="//input[@id='twotabsearchtextbox']";
         String submitBtn ="//input[@type='submit' and @value='Go']";
         
        // Initialize Playwright
        try (Playwright playwright = Playwright.create()) {
            // Launch a Chromium browser
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            
            // Create a new browser context
            BrowserContext context = browser.newContext();
            
            // Create a new page
            Page page = context.newPage();
           
            // Trace Viewer
            context.tracing().start(new Tracing.StartOptions()
  	    		  .setScreenshots(true)
  	    		  .setSnapshots(true));
            
            // Navigate to Amazon's homepage
            page.navigate("https://www.amazon.com");
            
            // Search for a specific product
            page.fill(searchBox,"wireless mouse");
            page.click(submitBtn);
            
            // Wait for the search results to load
            //page.waitForSelector("//div[@class='s-main-slot']//div[contains(@class, 's-result-item')]");
            //page.waitForSelector("//div[contains(@class, 's-main-slot')]//div[contains(@class,'s-result-item')][1]//a[contains(@class,'a-link-normal')]");
            page.waitForSelector("//*[contains(text(),'Amazon Basics 2.4 Ghz Wireless Optical Computer Mouse with USB Nano Receiver, Black')]/parent :: a");
            
            
            // Select the first product in the search results
           // page.click("//div[@class='s-main-slot']//div[contains(@class, 's-result-item')][1]//a[@class='a-link-normal']");
            //page.click("//div[contains(@class, 's-main-slot')]//div[contains(@class,'s-result-item')][1]//a[contains(@class,'a-link-normal')]");
            page.click("//*[contains(text(),'Amazon Basics 2.4 Ghz Wireless Optical Computer Mouse with USB Nano Receiver, Black')]/parent :: a");
            
          
            // Wait for the product page to load
            page.waitForSelector("//input[@id='add-to-cart-button']");
            
            // Add the product to the cart
            page.click("//input[@id='add-to-cart-button']");
            
            // Optionally, wait for the cart confirmation popup or navigate to the cart
            page.waitForSelector("//span[@id='nav-cart-count']");
            
            // Print a confirmation message
            System.out.println("Product added to cart successfully.");
            
            page.waitForSelector("//a[@id='nav-cart']");
            page.click("//a[@id='nav-cart']");
            
            page.waitForSelector(productName);
            Locator locator = page.locator(productName);
            
            System.out.println("locator.count: "+locator.count());
            // Check if the element exists
            if (locator.count() > 0) {
                System.out.println("Product added in AddtoCart Page");
            } else {
                System.out.println("Product does not added in AddtoCart Page .");
            }
            context.tracing().stop(new Tracing.StopOptions()
  	    		  .setPath(Paths.get("trace.zip")));
            // Close the browser
            browser.close();
        }
    } 
  
}
