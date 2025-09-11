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
 * This class contains functional tests for the default job cards displayed 
 * on the Home Page of IMEJob. 
 * Tests include verifying card count, content, skills, and navigation links.
 */
public class HomePageJobCardsTest {

    WebDriver driver; // inline // WebDriver instance to control the browser
    JobCardsPage jobCardsPage; // inline // Page Object for job cards on Home Page
    PropertiesReader propertiesReader; // inline // Utility to read configuration (browser, URL, etc.)
    JsonReader jsonReader; // inline // Utility to read test data from JSON files

    /**
     * Setup method executed before each test.
     * Initializes driver, opens base URL, prepares Page Object and JSON reader.
     */
    @BeforeMethod
    public void setUp() {
        propertiesReader = new PropertiesReader(); // inline // Load config properties
        driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName()); // inline // Launch browser
        driver.get(propertiesReader.getUrl()); // inline // Navigate to base URL
        jobCardsPage = new JobCardsPage(driver); // inline // Initialize Page Object for job cards
        jsonReader = new JsonReader(); // inline // Initialize JSON reader
        WaitUtils.implicitWait(driver); // inline // Apply implicit wait for element loading
        jsonReader.loadJson("defaultJobcards"); // inline // Load default job cards data from JSON
    }

    /**
     * Test to verify that default job cards are displayed on the Home Page.
     */
    @Test(priority = 1)
    public void verifyDefaultJobCardsCount() {
        int count = jobCardsPage.getDefaultJobCardsCount(); // inline // fetch number of job cards
        Assert.assertTrue(count > 0, "No default job cards are displayed on the Home Page"); // inline // verify card count
    }

    /**
     * Test to verify that text of each default job card is not null or empty.
     */
    @Test(priority = 2)
    public void verifyDefaultJobCardTextIsNotEmpty() {
        int totalCards = jobCardsPage.getDefaultJobCardsCount(); // inline // get total job cards
        Assert.assertTrue(totalCards > 0, "No default job cards are displayed on the Home Page"); // inline // fail if no cards

        for (int i = 0; i < totalCards; i++) {
            String cardText = jobCardsPage.getJobCardTextByIndex(i); // inline // get text of each card
            Assert.assertNotNull(cardText, "Job card text is null for card index: " + i); // inline // check null
            Assert.assertFalse(cardText.trim().isEmpty(), "Job card text is empty for card index: " + i); // inline // check empty string
        }
    }

    /**
     * Test to verify navigation when clicking the first default job card.
     * URL is read from the JSON test data.
     */
    @Test(priority = 3)
    public void testClickFirstCard() {
        jobCardsPage.clickFirstCard(); // inline // Click the first job card

        String expectedUrlPart = jsonReader.getValue("defaultJobCards", "firstCard", "url"); // inline // Fetch expected URL from JSON
        WaitUtils.waitUntilUrlContains(driver, expectedUrlPart); // inline // Wait until URL contains expected part

        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrlPart), "First card URL mismatch"); // inline // Verify navigation
    }

    /**
     * Test to verify navigation when clicking the last default job card.
     * URL is read from the JSON test data.
     */
    @Test(priority = 4)
    public void testClickLastCard() {
        jobCardsPage.clickLastCard(); // inline // Click the last job card

        String expectedUrlPart = jsonReader.getValue("defaultJobCards", "lastCard", "url"); // inline // Fetch expected URL from JSON
        WaitUtils.waitUntilUrlContains(driver, expectedUrlPart); // inline // Wait until URL contains expected part

        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrlPart), "Last card URL mismatch"); // inline // Verify navigation
    }

    /**
     * Test to verify that first and last job cards contain required fields
     * such as job title, company, location, and all skill tags as per JSON data.
     */
    @Test(priority = 5)
    public void verifyFirstAndLastJobCardContainsRequiredFields() {
        String firstCardText = jobCardsPage.getFirstCardText(); // inline // Get text of first card

        // Verify static fields of first card
        Assert.assertTrue(firstCardText.contains(jsonReader.getValue("defaultJobCards", "firstCard", "jobTitle")),
                "First card missing Job Title"); // inline // Verify job title
        Assert.assertTrue(firstCardText.contains(jsonReader.getValue("defaultJobCards", "firstCard", "companyName")),
                "First card missing Company Name"); // inline // Verify company
        Assert.assertTrue(firstCardText.contains(jsonReader.getValue("defaultJobCards", "firstCard", "location")),
                "First card missing Location"); // inline // Verify location

        // Verify all skill tags of first card
        JsonNode firstCardSkills = jsonReader.getJsonNode("defaultJobCards", "firstCard", "skills"); // inline // Fetch skill array from JSON
        for (JsonNode skill : firstCardSkills) {
            Assert.assertTrue(firstCardText.toLowerCase().contains(skill.asText().toLowerCase()),
                    "First card missing skill: " + skill.asText()); // inline // Check each skill
        }

        String lastCardText = jobCardsPage.getLastCardText(); // inline // Get text of last card

        // Verify static fields of last card
        Assert.assertTrue(lastCardText.contains(jsonReader.getValue("defaultJobCards", "lastCard", "jobTitle")),
                "Last card missing Job Title"); // inline // Verify job title
        Assert.assertTrue(lastCardText.contains(jsonReader.getValue("defaultJobCards", "lastCard", "companyName")),
                "Last card missing Company Name"); // inline // Verify company
        Assert.assertTrue(lastCardText.contains(jsonReader.getValue("defaultJobCards", "lastCard", "location")),
                "Last card missing Location"); // inline // Verify location

        // Verify all skill tags of last card
        JsonNode lastCardSkills = jsonReader.getJsonNode("defaultJobCards", "lastCard", "skills"); // inline // Fetch skill array from JSON
        for (JsonNode skill : lastCardSkills) {
            Assert.assertTrue(lastCardText.toLowerCase().contains(skill.asText().toLowerCase()),
                    "Last card missing skill: " + skill.asText()); // inline // Check each skill
        }
    }

    /**
     * Tear down method executed after each test.
     * Closes browser if driver is initialized.
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) { // inline // Check if WebDriver is initialized
            driver.quit(); // inline // Close browser and cleanup
        }
    }
}
