package com.amazon;

import java.nio.file.Paths;

import com.microsoft.playwright.*;

public class AmazonLoginXPathTest {
	public static void main(String[] args) {
		
		  String productName ="//*[contains(text(),'Amazon Basics 2.4 Ghz Wireless Optical Computer Mouse with USB Nano Receiver, Black')]";
          String strHelloName = "Hello, Gandhimathi";
          String signIn ="//a[@id='nav-link-accountList']";
          String emailId = "//input[@id='ap_email']";
          String continueBtn = "//input[@id='continue']";
          String password= "//input[@id='ap_password']";
          String helloNameStr = "//span[@id='nav-link-accountList-nav-line-1']";
          String signBtn = "//input[@id='signInSubmit']";
          String shoppingListLink = "//*[contains(text(),'Shopping List')]/parent :: a";
          
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
            
            page.waitForSelector(signIn);
            page.hover(signIn);
            page.click(signIn);
            
            page.waitForSelector(emailId);
            page.fill(emailId,"gandhi.perfect@gmail.com");
            page.click(continueBtn);
            
            page.waitForSelector(password);
            page.fill(password, "rosess@40");
            
            
            page.click(signBtn);
            
            page.waitForSelector(helloNameStr);
            String helloName = page.textContent(helloNameStr);
            if(helloName.equalsIgnoreCase(strHelloName)) {
            	System.out.println("Logged in Successfully");
            }
            else {
            	System.out.println("Logged not success");
            }
            context.tracing().stop(new Tracing.StopOptions()
  	    		  .setPath(Paths.get("trace.zip")));
            // Close the browser
            browser.close();
        }
    } 
  
}
