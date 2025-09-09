package com.imejob.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class represents the Home Page of the IMEJob application.
 * It contains WebElements and actions that can be performed on the home page.
 */
public class HomePage {
    
    // WebDriver instance to interact with the browser
    WebDriver driver;

    // WebElement for the "Create an account now!" link
    @FindBy(xpath = "//a[text() = 'Create an account now!']")
    WebElement createAccount;

    // WebElement for the WhatsApp icon
    @FindBy(css = "img[alt='whatsapp-logo']")
    WebElement whatsappIcon;

    // WebElement for the "View all jobs" section/button
    @FindBy(xpath = "//div[text() ='View all jobs']")
    WebElement viewAllJobs;

    // WebElement for the "Book a demo" button
    @FindBy(xpath = "//button[text() ='Book a demo']")
    WebElement bookDemoButton;

    /**
     * Constructor to initialize the HomePage class with WebDriver instance
     * and initialize WebElements using PageFactory.
     * 
     * @param driver WebDriver instance passed from the test
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks on the "Create an account now!" link
     */
    public void clickCreateAccount() {
        createAccount.click();
    }

    /**
     * Clicks on the WhatsApp icon
     */
    public void clickWhatsAppIcon() {
        whatsappIcon.click();
    }

    /**
     * Clicks on the "View all jobs" section/button
     */
    public void clickViewAllJobs() {
        viewAllJobs.click();
    }

    /**
     * Clicks on the "Book a demo" button
     */
    public void clickBookDemo() {
        bookDemoButton.click();
    }
}
