package com.imejob.tests.functional;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.imejob.pages.EmployerAuthPage;

import driverproperties.BrowserHandler;
import helper.TestUtils;
import utility.PropertiesReader;
import utility.WaitUtils;

/**
 * Functional test class for validating navigation flow in Employer
 * Authentication pages: - Create Account - Sign In - Forgot Password - Login
 * 
 * Uses TestNG framework with BrowserHandler, PropertiesReader, and WaitUtils
 * for setup, configuration, and waits.
 */
public class EmployerAuthTest {

	// WebDriver instance to control the browser
	WebDriver driver; // Selenium driver

	// Page Object for Employer Authentication page
	EmployerAuthPage employerAuthPage; // POM class reference

	// Utility to read config properties (browser, url, etc.)
	PropertiesReader propertiesReader; // Config reader

	/**
	 * Setup method executed before each test. Initializes driver, loads URL, and
	 * prepares page objects.
	 */
	@BeforeMethod
	public void setUp() {
		propertiesReader = new PropertiesReader(); // Load config properties
		driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName()); // Launch browser
		driver.get(propertiesReader.getUrl()); // Navigate to base URL
		employerAuthPage = new EmployerAuthPage(driver); // Initialize page object
		WaitUtils.implicitWait(driver); // Apply implicit wait
	}

	/**
	 * Test: Navigate to "Create Account" page. Verifies redirection URL after
	 * clicking Employer → Create Account.
	 */
	@Test(priority = 1)
	public void verifyNavigateToCreateAccount() {
		employerAuthPage.navigateToCreateAccount(); // Action clicks on create account button
		TestUtils.waitAndAssertUrlContains(driver, "auth/register?"); // wait & verify url
	}

	/**
	 * Test: Navigate to "Sign In" page. Verifies redirection URL after clicking
	 * Employer → Sign In.
	 */
	@Test(priority = 2)
	public void verifyNavigateToSignIn() {
		employerAuthPage.navigateToSignIn(); // Action clicks on signIn button
		TestUtils.waitAndAssertUrlContains(driver, "auth/login?"); // wait & verify url
	}

	/**
	 * Test: Navigate to "Forgot Password" page. Verifies redirection URL after
	 * clicking Employer → Forgot Password.
	 */
	@Test(priority = 3)
	public void verifyNavigateToForgotPassword() {
		employerAuthPage.navigateToForgotPassword(); // Action clicks Forget Password button
		TestUtils.waitAndAssertUrlContains(driver, "auth/forgot-password?"); // wait & verify url
	}

	/**
	 * Test: Navigate to "Login" page. Verifies redirection URL after clicking
	 * Employer → Login.
	 */
	@Test(priority = 4)
	public void verifyNavigateToLogin() {
		employerAuthPage.navigateToLogin(); // Action clicks Login button
		TestUtils.waitAndAssertUrlContains(driver, "auth/login?"); // wait & verify url
	}

	/**
	 * Cleanup method executed after each test. Closes browser if driver is not
	 * null.
	 */
	@AfterMethod
	public void tearDown() {
		if (driver != null) { // Check if driver is initialized
			driver.quit(); // Close browser
		}
	}
}
