package com.imejob.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtils;

/**
 * Page Object Model class for the Search Page.
 * Encapsulates all locators and actions related to the search functionality.
 */
public class SearchPage {
    WebDriver driver; // WebDriver instance to control the browser

    // 🔹 Input fields (skills & location)
    @FindBy(xpath = "(//div[@class=' css-19bb58m']//input)[1]")
    WebElement skillInput; // Skill input field

    @FindBy(xpath = "(//div[@class=' css-19bb58m']//input)[2]")
    WebElement locationInput; // Location input field

    // 🔹 Search button
    @FindBy(xpath = "//button[text()='Search']")
    WebElement searchButton; // Search button

    // 🔹 Validation message (shown for empty/invalid searches)
    @FindBy(xpath = "//div[contains(text() ,'Please enter search keywords')]")
    WebElement validationMessage; // Validation message

    // 🔹 Job cards on results page
    @FindBy(xpath = "//div[@class='col mb-5 ']")
    List<WebElement> jobCards; // List of job card elements

    // 🔹 "No jobs found" message
    @FindBy(xpath = "//*[contains(text(), 'No jobs found')]")
    WebElement noJobsFoundMessage; // "No jobs found" message

    /**
     * Constructor to initialize SearchPage elements using PageFactory.
     * @param driver WebDriver instance
     */
    public SearchPage(WebDriver driver) {
        this.driver = driver; 
        PageFactory.initElements(driver, this); // Initialize elements with PageFactory
    }

    /**
     * Generic method to safely type text and press Enter in an input field.
     * @param input WebElement input field
     * @param value Value to type
     */
    private void typeAndEnter(WebElement input, String value) {
        try {
            input.click(); // Focus on input field
            input.clear(); // Clear existing value
            input.sendKeys(value, Keys.ENTER); // Type value and hit Enter
        } catch (Exception e) {
            throw new RuntimeException("Failed to type '" + value + "' in input field", e);
        }
    }

    /**
     * Clicks the search button safely after waiting for it to be clickable.
     */
    public void clickSearchButton() {
        try {
            WaitUtils.waitForElementClickable(driver, searchButton); // Wait for button
            searchButton.click(); // Click on search button
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on Search button", e);
        }
    }

    /**
     * Enters a single skill into the skill input field.
     * @param skill Skill string to search
     */
    public void searchBySkill(String skill) {
        typeAndEnter(skillInput, skill); // Inline: Reuse helper to type skill
    }

    /**
     * Enters a location into the location input field.
     * @param location Location string to search
     */
    public void searchByLocation(String location) {
        typeAndEnter(locationInput, location); // Inline: Reuse helper to type location
    }

    /**
     * Enters multiple skills into the skill input field.
     * @param skills List of skills
     */
    public void searchByMultipleSkills(List<String> skills) {
        for (String skill : skills) {
            typeAndEnter(skillInput, skill); // Inline: Add each skill one by one
        }
    }

    /**
     * Retrieves text of the first job card safely.
     * @return Text of the first job card
     */
    public String getFirstJobCardText() {
        try {
            WaitUtils.waitForAllElementsVisible(driver, By.xpath("//div[@class='col mb-5 ']")); // Wait until cards are visible
            return jobCards.get(0).getText(); // Return first job card
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException("No job cards found to get first card text", e);
        }
    }

    /**
     * Retrieves text of the last job card safely.
     * @return Text of the last job card
     */
    public String getLastJobCardText() {
        try {
            WaitUtils.waitForAllElementsVisible(driver, By.xpath("//div[@class='col mb-5 ']")); // Wait until cards are visible
            return jobCards.get(jobCards.size() - 1).getText(); // Return last job card
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException("No job cards found to get last card text", e);
        }
    }

    /**
     * Retrieves validation message when no skill is entered.
     * @return Validation message text
     */
    public String getValidationMessage() {
        try {
            return validationMessage.getText(); // Return validation message
        } catch (NoSuchElementException e) {
            return "Validation message not displayed"; // Inline: Return fallback message
        }
    }

    /**
     * Retrieves "No jobs found" message safely.
     * @return 'No jobs found' message text
     */
    public String getNoJobsFoundMessage() {
        try {
            return noJobsFoundMessage.getText(); // Return no jobs message
        } catch (NoSuchElementException e) {
            return "'No jobs found' message not displayed"; // Inline: Return fallback message
        }
    }
}
