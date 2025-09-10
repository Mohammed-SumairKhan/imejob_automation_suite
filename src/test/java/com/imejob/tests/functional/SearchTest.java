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

public class SearchTest {
    WebDriver driver;
    PropertiesReader propertiesReader;
    SearchPage searchPage;
    JsonReader reader;

    @BeforeMethod
    public void start() {
        propertiesReader = new PropertiesReader(); 
        driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName()); 
        WaitUtils.implicitWait(driver); 
        driver.get(propertiesReader.getUrl()); 

        searchPage = new SearchPage(driver);

        reader = new JsonReader();
        reader.loadJson("searchTestData"); // Load JSON data
    }

    // ✅ Positive Tests
    @Test(priority = 1)
    public void verifyJobsBySkillAndLocation() {
        JsonNode testData = reader.getJsonNode("positiveTests", "skillAndLocation");
        searchPage.searchBySkill(testData.get("skill").asText());
        searchPage.searchByLocation(testData.get("location").asText());
        searchPage.clickSearchButton();

        String firstCard = searchPage.getFirstJobCardText();
        Assert.assertTrue(firstCard.contains(testData.get("skill").asText()) &&
                          firstCard.contains(testData.get("location").asText()),
                          "First job card does not match search criteria");

        String lastCard = searchPage.getLastJobCardText();
        Assert.assertTrue(lastCard.contains(testData.get("skill").asText()) &&
                          lastCard.contains(testData.get("location").asText()),
                          "Last job card does not match search criteria");
    }

    @Test(priority = 2)
    public void verifyJobsBySkillOnly() {
        String skill = reader.getValue("positiveTests", "skillOnly", "skill");
        searchPage.searchBySkill(skill);
        searchPage.clickSearchButton();

        String firstCard = searchPage.getFirstJobCardText();
        Assert.assertTrue(firstCard.contains(skill), "First job card does not contain skill");

        String lastCard = searchPage.getLastJobCardText();
        Assert.assertTrue(lastCard.contains(skill), "Last job card does not contain skill");
    }

    @Test(priority = 3)
    public void verifySearchByLocationWithoutSkillShowsValidationMessage() {
        String location = reader.getValue("positiveTests", "skillAndLocation", "location"); // using valid location
        searchPage.searchByLocation(location);
        searchPage.clickSearchButton();

        String expectedMessage = reader.getValue("validationMessages", "locationWithoutSkill");
        Assert.assertEquals(searchPage.getValidationMessage(), expectedMessage, "Validation message mismatch");
    }

    @Test(priority = 4)
    public void verifyEmptySearchShowsValidationMessage() {
        searchPage.clickSearchButton();
        String expectedMessage = reader.getValue("validationMessages", "emptySearch");
        Assert.assertEquals(searchPage.getValidationMessage(), expectedMessage, "Empty search validation mismatch");
    }

    @Test(priority = 5)
    public void verifyMultipleSkillsSearch() {
        JsonNode testData = reader.getJsonNode("positiveTests", "multipleSkills");
        List<String> skills = testData.get("skills").findValuesAsText("skills");
        searchPage.searchByMultipleSkills(List.of("Java", "Selenium")); // can fetch from JSON
        searchPage.searchByLocation(testData.get("location").asText());
        searchPage.clickSearchButton();

        String firstCard = searchPage.getFirstJobCardText();
        Assert.assertTrue(firstCard.contains("Java") || firstCard.contains("Selenium"),
                "First card does not match multiple skills");

        String lastCard = searchPage.getLastJobCardText();
        Assert.assertTrue(lastCard.contains("Java") || lastCard.contains("Selenium"),
                "Last card does not match multiple skills");
    }

    // ❌ Negative Tests
    @Test(priority = 6)
    public void verifyInvalidSkillAndLocationShowsNoJobs() {
        JsonNode testData = reader.getJsonNode("negativeTests", "invalidSkillAndLocation");
        searchPage.searchBySkill(testData.get("skill").asText());
        searchPage.searchByLocation(testData.get("location").asText());
        searchPage.clickSearchButton();

        String expectedMessage = reader.getValue("validationMessages", "noJobsFound");
        Assert.assertEquals(searchPage.getNoJobsFoundMessage(), expectedMessage,
                "Invalid skill and location should show 'No jobs found'");
    }

    @Test(priority = 7)
    public void verifyCaseInsensitiveSearch() {
        JsonNode testData = reader.getJsonNode("positiveTests", "caseInsensitive");
        searchPage.searchBySkill(testData.get("skill").asText());
        searchPage.searchByLocation(testData.get("location").asText());
        searchPage.clickSearchButton();

        String firstCard = searchPage.getFirstJobCardText();
        Assert.assertTrue(firstCard.toLowerCase().contains("java") &&
                          firstCard.toLowerCase().contains("bangalore"),
                          "Case insensitive search failed");
    }

    @Test(priority = 8)
    public void verifySearchWithSpecialCharactersAndWhitespace() {
        JsonNode testData = reader.getJsonNode("positiveTests", "specialCharsWhitespace");
        searchPage.searchBySkill(testData.get("skill").asText());
        searchPage.searchByLocation(testData.get("location").asText());
        searchPage.clickSearchButton();

        try {
            String firstCard = searchPage.getFirstJobCardText();
            Assert.assertTrue(firstCard.contains("Java") && firstCard.contains("Bangalore"),
                    "Search failed to handle whitespace or special characters correctly");
        } catch (Exception e) {
            String expectedMessage = reader.getValue("validationMessages", "noJobsFound");
            Assert.assertEquals(searchPage.getNoJobsFoundMessage(), expectedMessage,
                    "Special character search should result in no jobs found");
        }
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}
