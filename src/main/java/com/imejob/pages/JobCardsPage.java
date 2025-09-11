package com.imejob.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtils;

/**
 * Page Object class representing the Job Cards section on the Home Page.
 * Contains methods to interact with job cards, retrieve their text, and click them.
 */
public class JobCardsPage {
    WebDriver driver; // WebDriver instance for browser interaction

    @FindBy(xpath = "//div[@class ='col mb-5 ']")
    List<WebElement> jobCards; // List of all default job card elements

    @FindBy(xpath = "//div[contains(@class, 'Jobcard_jobTitle__LUngf')]")
    List<WebElement> jobCardsText; // Optional: List of job card title elements
    
    @FindBy(xpath = "//button[text() = 'Apply for this job']")
    WebElement applyButton;
    
    @FindBy(xpath = "//button[text() = 'Login']")
    WebElement loginButton;
    
    
    // Constructor initializes WebDriver and PageFactory elements
    public JobCardsPage(WebDriver driver) {
        this.driver = driver; // inline // assign driver to class variable
        PageFactory.initElements(driver, this); // inline // initialize WebElements
    }

    /**
     * Get the number of default job cards displayed on the home page.
     * @return int number of job cards, 0 if none found
     */
    public int getDefaultJobCardsCount() {
        try {
            return jobCards.size(); // inline // returns the count of all job cards
        } catch (Exception e) {
            return 0; // inline // return 0 if no cards are found
        }
    }

    /**
     * Get text of all default job cards.
     * @return List of job card texts
     */
    public List<String> getAllJobCardsText() {
        List<String> texts = new ArrayList<>();
        for (WebElement card : jobCards) { // inline // iterate through all job cards
            texts.add(card.getText()); // inline // add text of each card to list
        }
        return texts;
    }

    /**
     * Get text of a specific job card by index.
     * @param index index of the job card
     * @return text of the card or null if not found
     */
    public String getJobCardTextByIndex(int index) {
        try {
            return jobCards.get(index).getText(); // inline // return text of specific card
        } catch (Exception e) {
            return null; // inline // return null if card not found
        }
    }

    /**
     * Click the first job card safely.
     */
    public void clickFirstCard() {
        try {
            jobCards.get(0).click(); // inline // click the first card
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No job cards available to click (first card)."); // inline // handle no cards
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while clicking the first card: " + e.getMessage());
        }
    }

    /**
     * Click the last job card safely.
     */
    public void clickLastCard() {
        try {
            jobCards.get(jobCards.size() - 1).click(); // inline // click the last card
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No job cards available to click (last card)."); // inline // handle no cards
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while clicking the last card: " + e.getMessage());
        }
    }

    /**
     * Get the text of the first job card.
     * @return text of first card or null if no cards found
     */
    public String getFirstCardText() {
        int totalCards = getDefaultJobCardsCount(); // inline // get total job cards
        if (totalCards > 0) {
            return getJobCardTextByIndex(0).trim(); // inline // return trimmed text of first card
        }
        return null; // inline // no cards found
    }

    /**
     * Get the text of the last job card.
     * @return text of last card or null if no cards found
     */
    public String getLastCardText() {
        int totalCards = getDefaultJobCardsCount(); // inline // get total job cards
        if (totalCards > 0) {
            return getJobCardTextByIndex(totalCards - 1).trim(); // inline // return trimmed text of last card
        }
        return null; // inline // no cards found
    }
    
    public void clickOnApply() {
        try {
        	WaitUtils.waitForElementClickable(driver, applyButton);//explicit wait
            applyButton.click(); // inline // attempt to click the apply button
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on Apply button", e); // inline // provide meaningful error if click fails
        }
    }
    
    public void clickOnLogin() {
        try {
        	WaitUtils.waitForElementClickable(driver, loginButton);//explicit wait
            loginButton.click(); // inline // attempt to click the apply button
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on Apply button", e); // inline // provide meaningful error if click fails
        }
    }
    
}
