package com.imejob.tests.functional;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.imejob.pages.JobSeekerProfilePage;
import com.imejob.pages.JobSeekerSignInPage;

import driverproperties.BrowserHandler;
import helper.TestUtils;
import utility.JsonReader;
import utility.PropertiesReader;
import utility.WaitUtils;

public class JobSeekerProfileTest {
	WebDriver driver; // WebDriver instance to control the browser
	PropertiesReader propertiesReader; // Utility to read properties like URL, browser name
	JobSeekerSignInPage signInPage; // Page object for Job Seeker Sign In page
	JobSeekerProfilePage profilePage; // Page object for Document
	JsonReader jsonReader; // Utility to read JSON test data

	/**
	 * Setup method to initialize browser, page objects, and login before running
	 * tests
	 */
	@BeforeClass
	public void setup() {
		propertiesReader = new PropertiesReader(); // Initialize PropertiesReader
		driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName()); // Launch browser
		driver.get(propertiesReader.getUrl()); // Navigate to application URL
		WaitUtils.implicitWait(driver); // Apply implicit wait
		jsonReader = new JsonReader(); // Initialize JSON reader

		jsonReader.loadJson("signInData"); // Load JSON data for sign-in
		signInPage = new JobSeekerSignInPage(driver); // Initialize sign-in page object
		profilePage = new JobSeekerProfilePage(driver); // Initialize document page object

		// Use credentials from PropertiesReader
		String email = jsonReader.getValue("signIn", "email"); // Fetch email
		String password = jsonReader.getValue("signIn", "password"); // Fetch password
		signInPage.signIn(email, password);// Perform login

		WaitUtils.waitForPageToLoad(driver); // Wait for dashboard page to fully load
	}
	/**
	 * This method test that 
	 * edit profile button and update button are working properly or not..
	 * 
	 */
	@Test
	public void testEditProfile() {
		WaitUtils.waitUntilUrlContains(driver, "/job-seeker/applications");
		profilePage.clickOnProfile();
		profilePage.clickOnEditProfile();
		TestUtils.waitAndAssertUrlContains(driver, "job-seeker/profile/edit");
		profilePage.clickOnUpdate();
		Assert.assertTrue(profilePage.isPopTextDisplayed());
	}
	/**
	 * Tear down method to quit browser after all tests
	 */
	@AfterClass
	public void tearDown() {
		driver.quit(); // Close browser
	}

}
