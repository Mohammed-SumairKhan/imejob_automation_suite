package com.imejob.tests.functional;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.imejob.pages.JobCardsPage;

import driverproperties.BrowserHandler;
import utility.JsonReader;
import utility.PropertiesReader;
import utility.WaitUtils;

/**
 * This class contains functional tests for the default job cards displayed on
 * the Home Page of IMEJob. Tests include verifying card count, content, skills,
 * and navigation links.
 */
public class HomePageJobCardsTest {

	WebDriver driver; // WebDriver instance to control the browser
	JobCardsPage jobCardsPage; // Page Object for job cards on Home Page
	PropertiesReader propertiesReader; // Utility to read configuration (browser, URL, etc.)
	JsonReader jsonReader; // Utility to read test data from JSON files

	/**
	 * Setup method executed before each test. Initializes driver, opens base URL,
	 * prepares Page Object and JSON reader.
	 */
	@BeforeMethod
	public void setUp() {
		propertiesReader = new PropertiesReader(); // Load config properties
		driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName()); // Launch browser
		driver.get(propertiesReader.getUrl()); // Navigate to base URL
		jobCardsPage = new JobCardsPage(driver); // Initialize Page Object for job cards
		jsonReader = new JsonReader(); // Initialize JSON reader
		WaitUtils.implicitWait(driver); // Apply implicit wait for element loading
		jsonReader.loadJson("defaultJobcards"); // Load default job cards data from JSON
	}

	/**
	 * Test to verify that default job cards are displayed on the Home Page.
	 */
	@Test(priority = 1)
	public void verifyDefaultJobCardsCount() {
		int count = jobCardsPage.getDefaultJobCardsCount(); // fetch number of job cards
		Assert.assertTrue(count > 0, "No default job cards are displayed on the Home Page"); // verify card count
	}

	/**
	 * Test to verify that text of each default job card is not null or empty.
	 */
	@Test(priority = 2)
	public void verifyDefaultJobCardTextIsNotEmpty() {
		int totalCards = jobCardsPage.getDefaultJobCardsCount(); // get total job cards
		Assert.assertTrue(totalCards > 0, "No default job cards are displayed on the Home Page"); // fail if no cards

		for (int i = 0; i < totalCards; i++) {
			String cardText = jobCardsPage.getJobCardTextByIndex(i); // get text of each card
			Assert.assertNotNull(cardText, "Job card text is null for card index: " + i); // check null
			Assert.assertFalse(cardText.trim().isEmpty(), "Job card text is empty for card index: " + i); // check empty string
		}
	}

	/**
	 * Test to verify navigation when clicking the first default job card. URL is
	 * read from the JSON test data.
	 */
	@Test(priority = 3)
	public void testClickFirstCard() {
		jobCardsPage.clickFirstCard(); // Click the first job card

		String expectedUrlPart = jsonReader.getValue("defaultJobCards", "firstCard", "url"); // Fetch expected URL from JSON
		WaitUtils.waitUntilUrlContains(driver, expectedUrlPart); // Wait until URL contains expected part

		Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrlPart), "First card URL mismatch"); // Verify navigation
	}

	/**
	 * Test to verify navigation when clicking the last default job card. URL is
	 * read from the JSON test data.
	 */
	@Test(priority = 4)
	public void testClickLastCard() {
		jobCardsPage.clickLastCard(); // Click the last job card

		String expectedUrlPart = jsonReader.getValue("defaultJobCards", "lastCard", "url"); // Fetch expected URL from JSON
		WaitUtils.waitUntilUrlContains(driver, expectedUrlPart); // Wait until URL contains expected part

		Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrlPart), "Last card URL mismatch"); // Verify navigation
	}

	/**
	 * Test to verify that first and last job cards contain required fields such as
	 * job title, company, location, and all skill tags as per JSON data.
	 */
	@Test(priority = 5)
	public void verifyFirstAndLastJobCardContainsRequiredFields() {
		String firstCardText = jobCardsPage.getFirstCardText(); // Get text of first card

		// Verify static fields of first card
		Assert.assertTrue(firstCardText.contains(jsonReader.getValue("defaultJobCards", "firstCard", "jobTitle")),
				"First card missing Job Title"); // Verify job title
		Assert.assertTrue(firstCardText.contains(jsonReader.getValue("defaultJobCards", "firstCard", "companyName")),
				"First card missing Company Name"); // Verify company
		Assert.assertTrue(firstCardText.contains(jsonReader.getValue("defaultJobCards", "firstCard", "location")),
				"First card missing Location"); // Verify location

		// Verify all skill tags of first card
		JsonNode firstCardSkills = jsonReader.getJsonNode("defaultJobCards", "firstCard", "skills"); // Fetch skill array from JSON
		for (JsonNode skill : firstCardSkills) {
			Assert.assertTrue(firstCardText.toLowerCase().contains(skill.asText().toLowerCase()),
					"First card missing skill: " + skill.asText()); // Check each skill
		}

		String lastCardText = jobCardsPage.getLastCardText(); // Get text of last card

		// Verify static fields of last card
		Assert.assertTrue(lastCardText.contains(jsonReader.getValue("defaultJobCards", "lastCard", "jobTitle")),
				"Last card missing Job Title"); // Verify job title
		Assert.assertTrue(lastCardText.contains(jsonReader.getValue("defaultJobCards", "lastCard", "companyName")),
				"Last card missing Company Name"); // Verify company
		Assert.assertTrue(lastCardText.contains(jsonReader.getValue("defaultJobCards", "lastCard", "location")),
				"Last card missing Location"); // Verify location

		// Verify all skill tags of last card
		JsonNode lastCardSkills = jsonReader.getJsonNode("defaultJobCards", "lastCard", "skills"); // Fetch skill array from JSON
		for (JsonNode skill : lastCardSkills) {
			Assert.assertTrue(lastCardText.toLowerCase().contains(skill.asText().toLowerCase()),
					"Last card missing skill: " + skill.asText()); // Check each skill
		}
	}
	
	/**
	 * Test to verify that the Login button on the job card redirects to the 
	 * expected login page.
	 */
	@Test(priority = 6)
	public void testLoginButtonRedirectsToLoginPage() {

		jobCardsPage.clickFirstCard(); // Open first job card
		jobCardsPage.clickOnApply(); // Click Apply button
		jobCardsPage.clickOnLogin(); // Click Login button

		String expectedLoginUrl = jsonReader.getValue("jobCardActions", "loginButton", "url"); // Read expected login URL from JSON

		WaitUtils.waitUntilUrlContains(driver, expectedLoginUrl); // Wait for navigation

		Assert.assertTrue(driver.getCurrentUrl().contains(expectedLoginUrl),
				"Login button did not redirect to expected login page"); // Validate URL
	}

	/**
	 * Tear down method executed after each test. Closes browser if driver is
	 * initialized.
	 */
	@AfterMethod
	public void tearDown() {
		if (driver != null) { // Check if WebDriver is initialized
			driver.quit(); // Close browser and cleanup
		}
	}
}
