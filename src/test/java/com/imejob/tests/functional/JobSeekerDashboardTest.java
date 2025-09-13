package com.imejob.tests.functional;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.imejob.pages.JobSeekerDashboardPage;
import driverproperties.BrowserHandler;
import utility.PropertiesReader;
import utility.WaitUtils;

public class JobSeekerDashboardTest {
	WebDriver driver;
	PropertiesReader propertiesReader;
	JobSeekerDashboardPage jobSeekerDashboardPage;
	@BeforeMethod
	public void start() {
		propertiesReader = new PropertiesReader(); // initialize properties reader
		driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName()); // launch browser
		WaitUtils.implicitWait(driver); // apply implicit wait
		//driver.get(propertiesReader.getUrl()); // open application URL

		// initialize page objects
		jobSeekerDashboardPage = new JobSeekerDashboardPage(driver);
		driver.get(propertiesReader.getJobSeekerDashboardUrl());
		
	}


	@Test(priority = 1)
	public void verifyDashboardLoads() {
		System.out.println("working");
	}















}
