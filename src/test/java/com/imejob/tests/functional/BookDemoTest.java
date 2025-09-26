package com.imejob.tests.functional;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.imejob.pages.BookDemoPage;

import driverproperties.BrowserHandler;
import utility.JsonReader;
import utility.PropertiesReader;
import utility.WaitUtils;

public class BookDemoTest {
	WebDriver driver; // WebDriver instance to control the browser
	PropertiesReader propertiesReader; // Utility to read configuration (browser, URL, etc.)
	JsonReader jsonReader; // Utility to read test data from JSON files
	BookDemoPage bookDemoPage;

	@BeforeMethod
	public void setUp() {
		propertiesReader = new PropertiesReader(); // Load configuration properties
		driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName()); // Launch browser
		driver.get(propertiesReader.getUrl()); // Navigate to base URL
		jsonReader = new JsonReader(); // Initialize JSON reader
		WaitUtils.implicitWait(driver); // Apply implicit wait for element loading
		bookDemoPage = new BookDemoPage(driver);
		
		
	}
	
	@Test
	public void clickBookDemoButtonTest() {
		bookDemoPage.clickBookDemo();
		WaitUtils.waitUntilUrlContains(driver, "contact-us");
		Assert.assertTrue(driver.getCurrentUrl().contains("contact-us"), 
				"Failed Url does not conatins contact-us");
	}

	@Test(dependsOnMethods = "clickBookDemoButtonTest")
	public void fillContactFormTest() {
		bookDemoPage.clickBookDemo();
		bookDemoPage.fillContactForm("sumair@123", "123", "samir", "khan"
				,"Looking for Dedicated HR Services", "i have to connect");
		bookDemoPage.clickSubmitButton();
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		//driver.quit();
	}




}









