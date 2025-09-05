package com.imejob.tests.functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.imejob.pages.HeaderNavigationPage;
import com.imejob.pages.JobSeekerAuthPage;

import driverproperties.BrowserHandler;
import utility.PropertiesReader;
import utility.WaitUtils;

public class JobSeekerAuthTest {
	WebDriver driver;
	PropertiesReader propertiesReader;
	HeaderNavigationPage headerNavigationPage;
	JobSeekerAuthPage jobSeekerAuthPage;
	@BeforeMethod
	public void start() {
		propertiesReader = new PropertiesReader();
		driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName());
		WaitUtils.implicitWait(driver);
		driver.get(propertiesReader.getUrl());
		headerNavigationPage = new HeaderNavigationPage(driver);
		jobSeekerAuthPage = new JobSeekerAuthPage(driver);
	}
	
	@Test(priority = 1) 
	public void navigateToCreateAccountTest() {
		headerNavigationPage.clickJobSeeker(); // click on the job seeker button
		jobSeekerAuthPage.createAccount();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("requestType=jobSeeker"));
	}
	
	
	@AfterMethod
	public void close() {
		driver.quit();
	}
}










