package com.imejob.tests.navigation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.imejob.pages.JobSeekerSignInPage;
import com.imejob.pages.SidebarPage;

import driverproperties.BrowserHandler;
import utility.PropertiesReader;
import utility.WaitUtils;
import utility.WindowUtils;

public class SidebarTest {
	WebDriver driver;
	PropertiesReader propertiesReader;
	JobSeekerSignInPage signInPage;
	SidebarPage sidebarPage;

	@BeforeClass
	public void setup() {
		propertiesReader = new PropertiesReader();
		driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName());
		driver.get(propertiesReader.getUrl());
		WaitUtils.implicitWait(driver);
		signInPage = new JobSeekerSignInPage(driver);
		sidebarPage = new SidebarPage(driver);
		signInPage.signIn("sumairk0777@gmail.com", "sumair@1");
		WaitUtils.applyHardWait();
	
	}

	@Test(priority = 1)
	public void testNavigationToSearchJob() {
		sidebarPage.clickOn("job search");
		String ParentWindow = WindowUtils.switchToNewWindow(driver);
		WaitUtils.waitUntilUrlContains(driver, "/job-search/all-jobs");
		Assert.assertTrue(driver.getCurrentUrl().contains("/job-search/all-jobs"));
		WindowUtils.switchBackToParent(driver, ParentWindow);
	}
	@Test(priority = 2)
	public void testNavigationToInterviewsChat() {
		sidebarPage.clickOn("interviews");
		WaitUtils.waitUntilUrlContains(driver, "job-seeker/interviews");
		Assert.assertTrue(driver.getCurrentUrl().contains("job-seeker/interviews"));
	}

	@Test(priority = 3)
	public void testNavigationToDocuments() {
		sidebarPage.clickOn("documents");
		WaitUtils.waitUntilUrlContains(driver, "job-seeker/documents");
		Assert.assertTrue(driver.getCurrentUrl().contains("job-seeker/documents"));

	}

	@Test(priority = 4)
	public void testNavigationToApplications() {
		sidebarPage.clickOn("applications");
		WaitUtils.waitUntilUrlContains(driver, "job-seeker/applications");
		Assert.assertTrue(driver.getCurrentUrl().contains("job-seeker/applications"));
	}

	@Test(priority = 5)
	public void testNavigationToProfile() {
		sidebarPage.clickOn("profile");
		WaitUtils.waitUntilUrlContains(driver, "job-seeker/profile");
		Assert.assertTrue(driver.getCurrentUrl().contains("job-seeker/profile"));
	}

	@Test(priority = 6)
	public void testNavigationMyPost() {
		sidebarPage.clickOn("my posts");
		WaitUtils.waitUntilUrlContains(driver, "job-seeker/my-posts");
		Assert.assertTrue(driver.getCurrentUrl().contains("job-seeker/my-posts"));
	}

	@Test(priority = 7)
	public void testNavigationMySavedPost() {
		sidebarPage.clickOn("my saved jobs");
		WaitUtils.waitUntilUrlContains(driver, "job-seeker/saved-posts");
		Assert.assertTrue(driver.getCurrentUrl().contains("job-seeker/saved-posts"));
	}

	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
