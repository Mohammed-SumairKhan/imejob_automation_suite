package com.imejob.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.imejob.pages.HeaderNavigationPage;

import driverproperties.BrowserHandler;
import utility.PropertiesReader;

public class HeaderNavigationTest {
	WebDriver driver;
	PropertiesReader propertiesReader;
	HeaderNavigationPage headerNavigationPage;
	
	@BeforeMethod
	public void start() {
		propertiesReader = new PropertiesReader();
		driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName());
		driver.get(propertiesReader.getUrl());
		headerNavigationPage = new HeaderNavigationPage(driver);
		
	}
	
	@Test
	public void jobSearch() {
		headerNavigationPage.clickJobSearch();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.urlContains("job-search/all-jobs"));
		Assert.assertTrue(driver.getCurrentUrl().contains("job-search/all-jobs"));
	}
	
	@AfterMethod
	public void close() {
		driver.quit();
	}
}












