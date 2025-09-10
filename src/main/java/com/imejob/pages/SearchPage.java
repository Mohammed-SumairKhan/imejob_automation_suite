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

public class SearchPage {
    WebDriver driver;

    // Input fields (skills & location)
    @FindBy(xpath = "(//div[@class=' css-19bb58m']//input)[1]")
    WebElement skillInput;

    @FindBy(xpath = "(//div[@class=' css-19bb58m']//input)[2]")
    WebElement locationInput;

    @FindBy(xpath = "//button[text()='Search']")
    WebElement searchButton;

    @FindBy(xpath = "//div[contains(text() ,'Please enter search keywords')]")
    WebElement validationMessage;

    @FindBy(xpath = "//div[@class='col mb-5 ']")
    List<WebElement> jobCards;

    @FindBy(xpath = "//*[contains(text(), 'No jobs found')]")
    WebElement noJobsFoundMessage;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Generic method to safely type and press Enter
    private void typeAndEnter(WebElement input, String value) {
        try {
            input.click();
            input.clear();
            input.sendKeys(value, Keys.ENTER);
        } catch (Exception e) {
            throw new RuntimeException("Failed to type '" + value + "' in input field", e);
        }
    }

    // Safe click
    public void clickSearchButton() {
        try {
            WaitUtils.waitForElementClickable(driver, searchButton);
            searchButton.click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on Search button", e);
        }
    }

    // Search by single skill
    public void searchBySkill(String skill) {
        typeAndEnter(skillInput, skill);
    }

    // Search by location
    public void searchByLocation(String location) {
        typeAndEnter(locationInput, location);
    }

    // Search by multiple skills
    public void searchByMultipleSkills(List<String> skills) {
        for (String skill : skills) {
            typeAndEnter(skillInput, skill);
        }
    }

    // Get first job card safely
    public String getFirstJobCardText() {
        try {
            WaitUtils.waitForAllElementsVisible(driver, By.xpath("//div[@class='col mb-5 ']"));
            return jobCards.get(0).getText();
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException("No job cards found to get first card text", e);
        }
    }

    // Get last job card safely
    public String getLastJobCardText() {
        try {
            WaitUtils.waitForAllElementsVisible(driver, By.xpath("//div[@class='col mb-5 ']"));
            return jobCards.get(jobCards.size() - 1).getText();
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException("No job cards found to get last card text", e);
        }
    }

    // Get validation message safely
    public String getValidationMessage() {
        try {
            return validationMessage.getText();
        } catch (NoSuchElementException e) {
            return "Validation message not displayed";
        }
    }

    // Get 'No jobs found' message safely
    public String getNoJobsFoundMessage() {
        try {
            return noJobsFoundMessage.getText();
        } catch (NoSuchElementException e) {
            return "'No jobs found' message not displayed";
        }
    }
}
