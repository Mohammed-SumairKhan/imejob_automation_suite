package com.imejob.tests.functional;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.imejob.pages.JobSeekerApplicationsPage;
import com.imejob.pages.JobSeekerSignInPage;

import driverproperties.BrowserHandler;
import utility.PropertiesReader;
import utility.WaitUtils;

public class JobSeekerApplicationTest {
	WebDriver driver;
	JobSeekerSignInPage signInPage;
	JobSeekerApplicationsPage applicationPage;
	PropertiesReader propertiesReader;

	@BeforeClass
	public void setUp() {
		propertiesReader = new PropertiesReader();
		driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName());
		driver.get(propertiesReader.getUrl());
		WaitUtils.implicitWait(driver);
		signInPage = new JobSeekerSignInPage(driver);
		applicationPage = new JobSeekerApplicationsPage(driver);

		signInPage.signIn("sumairk0777@gmail.com", "sumair@21");

	}

	@Test
	public void clickViewORChat() {
		WaitUtils.waitUntilUrlContains(driver, "job-seeker/applications");
		applicationPage.clickOn("viewORChat");
		Assert.assertTrue(applicationPage.isLiveChatTextVisible());
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
