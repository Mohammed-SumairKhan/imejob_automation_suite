package com.imejob.tests.navigation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.imejob.pages.HeaderNavigationPage;
import com.imejob.utility.TestNavigationHelper;

import driverproperties.BrowserHandler;
import utility.PropertiesReader;

/**
 * Test class to validate header navigation links on ImeJob website. It verifies
 * that each header element navigates to the correct page.
 */
public class HeaderNavigationTest {

	WebDriver driver; // WebDriver instance for controlling the browser
	PropertiesReader propertiesReader; // Object to read values from config.properties
	HeaderNavigationPage headerNavigationPage; // Page Object for header navigation actions

	/**
	 * Setup method - initializes WebDriver, loads config values, and navigates to
	 * the base URL before each test.
	 */
	@BeforeMethod
	public void start() {
		propertiesReader = new PropertiesReader(); // Create PropertiesReader object
		driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName()); // Launch browser from config
		driver.get(propertiesReader.getUrl()); // Open the base URL
		headerNavigationPage = new HeaderNavigationPage(driver); // Initialize Page Object
	}

	/**
	 * Verify that clicking the brand logo redirects to the homepage.
	 */
	@Test(priority = 1)
	public void brandLogo() {
		headerNavigationPage.clickAboutUs(); // Navigate away from homepage
		TestNavigationHelper.verifyNavigation(driver, () -> headerNavigationPage.clickBrandLogo(), // Click action
																									// passed as lambda
				"imejob"); // Expected URL substring
	}

	/**
	 * Verify Job Search navigation.
	 */
	@Test(priority = 2)
	public void jobSearch() {
		TestNavigationHelper.verifyNavigation(driver, () -> headerNavigationPage.clickJobSearch(), // Click action
																									// passed as lambda
				"job-search/all-jobs"); // Expected URL substring
	}

	/**
	 * Verify About Us navigation.
	 */
	@Test(priority = 3)
	public void aboutUs() {
		TestNavigationHelper.verifyNavigation(driver, () -> headerNavigationPage.clickAboutUs(), // Click action passed
																									// as lambda
				"about-us"); // Expected URL substring
	}

	/**
	 * Verify Contact Us navigation.
	 */
	@Test(priority = 4)
	public void contactUs() {
		TestNavigationHelper.verifyNavigation(driver, () -> headerNavigationPage.clickContactUs(), // Click action
																									// passed as lambda
				"contact-us"); // Expected URL substring
	}

	/**
	 * Verify Join Community navigation.
	 */
	@Test(priority = 5)
	public void joinCommunity() {
		TestNavigationHelper.verifyNavigation(driver, () -> headerNavigationPage.clickJoinCommunity(), // Click action
																										// passed as
																										// lambda
				"community"); // Expected URL substring
	}

	/**
	 * Verify Blogs navigation.
	 */
	@Test(priority = 6)
	public void blogs() {
		TestNavigationHelper.verifyNavigation(driver, () -> headerNavigationPage.clickBlogs(), // Click action passed as
																								// lambda
				"blogs"); // Expected URL substring
	}

	/**
	 * Verify Pricing navigation.
	 */
	@Test(priority = 7)
	public void pricing() {
		TestNavigationHelper.verifyNavigation(driver, () -> headerNavigationPage.clickPricing(), // Click action passed
																									// as lambda
				"pricing"); // Expected URL substring
	}

	/**
	 * Verify job seeker navigation.
	 */
	@Test(priority = 8)
	public void jobSeeker() {
		TestNavigationHelper.verifyNavigation(driver, () -> headerNavigationPage.clickJobSeeker(), // Click action
																									// passed as lambda
				"requestType=jobSeeker"); // Expected URL substring
	}

	/**
	 * Verify employer navigation.
	 */
	@Test(priority = 9)
	public void employer() {
		TestNavigationHelper.verifyNavigation(driver, () -> headerNavigationPage.clickEmployer(), // Click action passed
																									// as lambda
				"requestType=employer"); // Expected URL substring
	}

	/**
	 * Tear down method - closes the browser after each test.
	 */
	@AfterMethod
	public void close() {
		driver.quit(); // Quit browser
	}
}
