package com.imejob.tests.functional;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.imejob.pages.SearchPage;
import driverproperties.BrowserHandler;
import utility.JsonReader;
import utility.PropertiesReader;
import utility.WaitUtils;

import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Functional test class for verifying job search functionality on IMEJob.
 * Covers positive and negative test cases using data-driven approach from JSON.
 */
public class SearchTest {

    // 🔹 Class Variables
    WebDriver driver;  // WebDriver instance to control the browser
    PropertiesReader propertiesReader;  // Reads config values from properties file
    SearchPage searchPage;  // Page object for Search functionality
    JsonReader jsonReader;  // Utility for reading test data from JSON

    /**
     * Setup method executed before each test.
     * Initializes WebDriver, loads the test URL, and prepares page objects.
     */
    @BeforeMethod
    public void start() {
        propertiesReader = new PropertiesReader();  // load properties
        driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName());  // launch browser
        WaitUtils.implicitWait(driver);  // apply implicit wait
        driver.get(propertiesReader.getUrl());  // navigate to app URL

        searchPage = new SearchPage(driver);  // initialize SearchPage object

        jsonReader = new JsonReader();  // create JsonReader instance
        jsonReader.loadJson("searchTestData"); // load JSON test data file
    }

    // ✅ Positive Tests

    /**
     * Verify jobs can be searched using both skill and location.
     */
    @Test(priority = 1)
    public void verifyJobsBySkillAndLocation() {
        JsonNode testData = jsonReader.getJsonNode("positiveTests", "skillAndLocation"); // fetch test data
        searchPage.searchBySkill(testData.get("skill").asText()); // enter skill
        searchPage.searchByLocation(testData.get("location").asText()); // enter location
        searchPage.clickSearchButton(); // click search

        String firstCard = searchPage.getFirstJobCardText(); // get first job card text
        Assert.assertTrue(firstCard.contains(testData.get("skill").asText()) &&
                          firstCard.contains(testData.get("location").asText()),
                          "First job card does not match search criteria"); // assert first card

        String lastCard = searchPage.getLastJobCardText(); // get last job card text
        Assert.assertTrue(lastCard.contains(testData.get("skill").asText()) &&
                          lastCard.contains(testData.get("location").asText()),
                          "Last job card does not match search criteria"); // assert last card
    }

    /**
     * Verify jobs can be searched using skill only.
     */
    @Test(priority = 2)
    public void verifyJobsBySkillOnly() {
        String skill = jsonReader.getValue("positiveTests", "skillOnly", "skill"); // fetch skill
        searchPage.searchBySkill(skill); // enter skill
        searchPage.clickSearchButton(); // click search

        String firstCard = searchPage.getFirstJobCardText(); // get first job card text
        Assert.assertTrue(firstCard.contains(skill), "First job card does not contain skill"); // assert first card

        String lastCard = searchPage.getLastJobCardText(); // get last job card text
        Assert.assertTrue(lastCard.contains(skill), "Last job card does not contain skill"); // assert last card
    }

    /**
     * Verify searching with only location (without skill) shows validation message.
     */
    @Test(priority = 3)
    public void verifySearchByLocationWithoutSkillShowsValidationMessage() {
        String location = jsonReader.getValue("positiveTests", "skillAndLocation", "location"); // fetch location
        searchPage.searchByLocation(location); // enter location only
        searchPage.clickSearchButton(); // click search

        String expectedMessage = jsonReader.getValue("validationMessages", "locationWithoutSkill"); // expected validation
        Assert.assertEquals(searchPage.getValidationMessage(), expectedMessage, "Validation message mismatch");
    }

    /**
     * Verify clicking search without entering skill or location shows validation message.
     */
    @Test(priority = 4)
    public void verifyEmptySearchShowsValidationMessage() {
        searchPage.clickSearchButton(); // click without entering anything
        String expectedMessage = jsonReader.getValue("validationMessages", "emptySearch"); // expected validation
        Assert.assertEquals(searchPage.getValidationMessage(), expectedMessage, "Empty search validation mismatch");
    }

    /**
     * Verify searching jobs with multiple skills.
     */
    @Test(priority = 5)
    public void verifyMultipleSkillsSearch() {
        JsonNode testData = jsonReader.getJsonNode("positiveTests", "multipleSkills"); // fetch multiple skill data
        searchPage.searchByMultipleSkills(List.of("Java", "Selenium")); // enter multiple skills
        searchPage.searchByLocation(testData.get("location").asText()); // enter location
        searchPage.clickSearchButton(); // click search

        String firstCard = searchPage.getFirstJobCardText(); // get first job card text
        Assert.assertTrue(firstCard.contains("Java") || firstCard.contains("Selenium"),
                "First card does not match multiple skills"); // assert

        String lastCard = searchPage.getLastJobCardText(); // get last job card text
        Assert.assertTrue(lastCard.contains("Java") || lastCard.contains("Selenium"),
                "Last card does not match multiple skills"); // assert
    }

    // ❌ Negative Tests

    /**
     * Verify searching with invalid skill and location shows "No jobs found".
     */
    @Test(priority = 6)
    public void verifyInvalidSkillAndLocationShowsNoJobs() {
        JsonNode testData = jsonReader.getJsonNode("negativeTests", "invalidSkillAndLocation"); // fetch invalid data
        searchPage.searchBySkill(testData.get("skill").asText()); // enter invalid skill
        searchPage.searchByLocation(testData.get("location").asText()); // enter invalid location
        searchPage.clickSearchButton(); // click search

        String expectedMessage = jsonReader.getValue("validationMessages", "noJobsFound"); // expected message
        Assert.assertEquals(searchPage.getNoJobsFoundMessage(), expectedMessage,
                "Invalid skill and location should show 'No jobs found'"); // assert
    }

    /**
     * Verify search is case-insensitive (e.g. "java" == "Java").
     */
    @Test(priority = 7)
    public void verifyCaseInsensitiveSearch() {
        JsonNode testData = jsonReader.getJsonNode("positiveTests", "caseInsensitive"); // fetch case-insensitive data
        searchPage.searchBySkill(testData.get("skill").asText()); // enter lowercase skill
        searchPage.searchByLocation(testData.get("location").asText()); // enter lowercase location
        searchPage.clickSearchButton(); // click search

        String firstCard = searchPage.getFirstJobCardText(); // get first card text
        Assert.assertTrue(firstCard.toLowerCase().contains("java") &&
                          firstCard.toLowerCase().contains("bangalore"),
                          "Case insensitive search failed"); // assert
    }

    /**
     * Verify search works with special characters and whitespace in input.
     */
    @Test(priority = 8)
    public void verifySearchWithSpecialCharactersAndWhitespace() {
        JsonNode testData = jsonReader.getJsonNode("positiveTests", "specialCharsWhitespace"); // fetch data
        searchPage.searchBySkill(testData.get("skill").asText()); // enter skill with space
        searchPage.searchByLocation(testData.get("location").asText()); // enter location with special char
        searchPage.clickSearchButton(); // click search

        try {
            String firstCard = searchPage.getFirstJobCardText(); // try to get job card
            Assert.assertTrue(firstCard.contains("Java") && firstCard.contains("Bangalore"),
                    "Search failed to handle whitespace or special characters correctly"); // assert
        } catch (Exception e) {
            // fallback if no job cards available
            String expectedMessage = jsonReader.getValue("validationMessages", "noJobsFound");
            Assert.assertEquals(searchPage.getNoJobsFoundMessage(), expectedMessage,
                    "Special character search should result in no jobs found"); // assert
        }
    }

    /**
     * Tear down method executed after each test.
     * Closes the browser instance.
     */
    @AfterMethod
    public void close() {
        driver.quit(); // close browser
    }
}
