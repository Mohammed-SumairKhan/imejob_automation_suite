package com.imejob.tests.navigation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.imejob.pages.JobSeekerSignInPage;
import com.imejob.pages.SidebarPage;

import driverproperties.BrowserHandler;
import helper.TestUtils;
import utility.JsonReader;
import utility.PropertiesReader;
import utility.WaitUtils;
import utility.WindowUtils;

/**
 * TestNG test class for Sidebar navigation functionality in Job Seeker dashboard.
 * 
 * This class contains automated tests to verify navigation through the sidebar links, 
 * including Job Search, Interviews, Documents, Applications, Profile, My Posts, Saved Jobs, 
 * Community, Dashboard, Home, and Logout.
 * 
 * It uses Page Object Model (POM) with SidebarPage and JobSeekerSignInPage objects, 
 * and TestUtils for reusable wait and assertion methods.
 */
public class SidebarNavigationTest {
	WebDriver driver; // WebDriver instance to control the browser
	PropertiesReader propertiesReader; // Utility to read properties like URL, browser name
	JobSeekerSignInPage signInPage; // Page object for Job Seeker Sign In page
	SidebarPage sidebarPage; // Page object for Sidebar navigation
	JsonReader jsonReader; // Utility to read JSON test data

	/**
	 * Setup method to initialize browser, page objects, and login before running tests
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
		sidebarPage = new SidebarPage(driver); // Initialize sidebar page object

		// Use credentials from PropertiesReader
		String email = jsonReader.getValue("signIn", "email"); // Fetch email
		String password = jsonReader.getValue("signIn", "password"); // Fetch password
		signInPage.signIn(email, password);// Perform login

		WaitUtils.waitForPageToLoad(driver); // Wait for dashboard page to fully load
	}
	
	/**
	 * Test navigation to Home Page via logo click and verify exact URL
	 */
	@Test(priority = 1)
	public void testNavigationHomePage() {
		WaitUtils.waitUntilUrlContains(driver, "https://client.imejob.com/dashboard/job-seeker/applications"); // Wait before click
		sidebarPage.clickOn("logo"); // Click on logo icon
		TestUtils.waitAndAssertUrlExact(driver, "https://client.imejob.com/"); // Verify exact URL
	}

	/**
	 * Test navigation to Dashboard via dashboard icon and verify partial URL
	 */
	@Test(priority = 2)
	public void testNavigationDashBoard() {
		WaitUtils.waitUntilUrlContains(driver, "https://client.imejob.com/"); // Wait until dashboard is loaded
		sidebarPage.clickOn("dashboard");  // Click dashboard link
		TestUtils.waitAndAssertUrlContains(driver, "dashboard/job-seeker/applications"); // Verify URL contains expected part
	}
	
	/**
	 * Test navigation to Job Search page which opens in new window/tab
	 */
	@Test(priority = 3)
	public void testNavigationToSearchJob() {
		WaitUtils.waitUntilUrlContains(driver, "dashboard/job-seeker/applications"); // Wait before click
		sidebarPage.clickOn("job search"); // Click job search link
		String parentHandle = WindowUtils.switchToNewWindow(driver); // Switch to new window
		TestUtils.waitAndAssertUrlContains(driver, "/job-search/all-jobs"); // Verify URL contains expected part
		driver.close(); // Close new tab
		WindowUtils.switchBackToParent(driver, parentHandle);  // Switch back to parent window
	}
	
	/**
	 * Test navigation to Interviews/Chat page
	 */
	@Test(priority = 4)
	public void testNavigationToInterviewsChat() {
		sidebarPage.clickOn("interviews");  // Click interviews link
		TestUtils.waitAndAssertUrlContains(driver, "job-seeker/interviews"); // Verify URL
	}

	/**
	 * Test navigation to Documents page
	 */
	@Test(priority = 5)
	public void testNavigationToDocuments() {
		sidebarPage.clickOn("documents"); // Click documents link
		TestUtils.waitAndAssertUrlContains(driver, "job-seeker/documents"); // Verify URL
	}

	/**
	 * Test navigation to Applications page
	 */
	@Test(priority = 6)
	public void testNavigationToApplications() {
		sidebarPage.clickOn("applications"); // Click applications link
		TestUtils.waitAndAssertUrlContains(driver, "job-seeker/applications"); // Verify URL
	}
	
	/**
	 * Test navigation to Profile page
	 */
	@Test(priority = 7)
	public void testNavigationToProfile() {
		sidebarPage.clickOn("profile"); // Click profile link
		TestUtils.waitAndAssertUrlContains(driver, "job-seeker/profile"); // Verify URL
	}

	/**
	 * Test navigation to My Posts page
	 */
	@Test(priority = 8)
	public void testNavigationMyPost() {
		sidebarPage.clickOn("my posts"); // Click my posts link
		TestUtils.waitAndAssertUrlContains(driver, "job-seeker/my-posts"); // Verify URL
	}

	/**
	 * Test navigation to My Saved Jobs page
	 */
	@Test(priority = 9)
	public void testNavigationMySavedPost() {
		sidebarPage.clickOn("my saved jobs"); // Click my saved jobs link
		TestUtils.waitAndAssertUrlContains(driver, "job-seeker/saved-posts"); // Verify URL
	}

	/**
	 * Test navigation to Community page
	 */
	@Test(priority = 10)
	public void testNavigationCommunity() {
		sidebarPage.clickOn("community"); // Click community link
		TestUtils.waitAndAssertUrlContains(driver, "community"); // Verify URL
	}

	/**
	 * Test logout functionality from profile logo
	 */
	@Test(priority = 11)
	public void testNavigationLogout() {
		sidebarPage.clickOn("profileLogo"); // Click profile logo
		sidebarPage.clickOn("logout"); // Click logout link
		TestUtils.waitAndAssertUrlContains(driver, "community"); // Verify user is redirected after logout
	}
	
	/**
	 * Test navigation back to Home page via home icon and exact URL verification
	 */
	@Test(priority = 12)
	public void testNavigationHomePagee() {
		sidebarPage.clickOn("homeIcon"); // Click home icon
		TestUtils.waitAndAssertUrlExact(driver, "https://client.imejob.com/"); // Verify exact URL
	}

	/**
	 * Tear down method to quit browser after all tests
	 */
	@AfterClass
	public void tearDown() {
		driver.quit(); // Close browser
	}
}
