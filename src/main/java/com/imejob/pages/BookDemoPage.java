package com.imejob.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtils;

/**
 * Page Object class for the "Book Demo" page.
 * Encapsulates WebElements and actions for the Book Demo and Contact Us flow.
 */
public class BookDemoPage {

    WebDriver driver; // WebDriver instance to control browser interactions

    // WebElements on the Book Demo page
    @FindBy(xpath = "//button[text() = 'Book a demo']")
    WebElement bookDemo; // Button to open the Book Demo / Contact Us page

    @FindBy(name = "email")
    WebElement emailInput; // Email input field

    @FindBy(name = "phoneNumber")
    WebElement phoneNumberInput; // Phone number input field

    @FindBy(name = "firstName")
    WebElement firstNameInput; // First name input field

    @FindBy(name = "lastName")
    WebElement lastNameInput; // Last name input field

    @FindBy(name = "message")
    WebElement messageInput; // Message textarea input field

    @FindBy(xpath = "//button[text() = 'Submit']")
    WebElement submitButton; // Submit button for contact form

    @FindBy(xpath = "//div[contains(@class, 'indicatorContainer')]")
    WebElement dropDown; // Dropdown element for selecting subject

    @FindBy(xpath = "//*[contains(text(),'Thanks for contacting us!')]")
    private WebElement successMessage; // Success message displayed after form submission

    /**
     * Constructor to initialize WebDriver and PageFactory elements.
     *
     * @param driver WebDriver instance
     */
    public BookDemoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize PageFactory elements
    }

    /**
     * Clicks the "Book a demo" button.
     */
    public void clickBookDemo() {
        try {
            bookDemo.click(); //inline // Click the Book Demo button
        } catch (Exception e) {
            throw new RuntimeException("Can't click the Book Demo button: " + e); //inline // Throw runtime exception if fails
        }
    }

    /**
     * Clicks the Submit button on the contact form.
     */
    public void clickSubmitButton() {
        try {
            submitButton.click(); //inline // Click the Submit button
        } catch (Exception e) {
            throw new RuntimeException("Can't click the Submit button: " + e); //inline // Throw runtime exception if fails
        }
    }

    /**
     * Fills the contact form fields and selects the subject.
     *
     * @param email       Email address
     * @param phoneNumber Phone number
     * @param firstName   First name
     * @param lastName    Last name
     * @param subject     Subject from dropdown
     * @param message     Message text
     */
    public void fillContactForm(String email, String phoneNumber, String firstName, String lastName, String subject,
                                String message) {
        emailInput.sendKeys(email); //inline // Enter email
        phoneNumberInput.sendKeys(phoneNumber); //inline // Enter phone number
        firstNameInput.sendKeys(firstName); //inline // Enter first name
        lastNameInput.sendKeys(lastName); //inline // Enter last name
        selectSubject(subject); //inline // Select subject from dropdown
        messageInput.sendKeys(message); //inline // Enter message
    }

    /**
     * Selects a subject from the dropdown.
     *
     * @param subject Subject text to select
     */
    public void selectSubject(String subject) {
        dropDown.click(); //inline // Click dropdown to expand options

        // Locate the option dynamically using its text
        WebElement options = driver.findElement(By.xpath("//div[text() = '" + subject + "']")); //inline
        WaitUtils.waitForElementClickable(driver, options); //inline // Wait until option is clickable
        options.click(); //inline // Click the desired option
    }

    /**
     * Returns the success message displayed after submitting the form.
     *
     * @return Success message text
     */
    public String getStatusMessage() {
        WaitUtils.waitForElementVisible(driver, successMessage); //inline // Wait until success message is visible
        return successMessage.getText(); //inline // Return message text
    }
}
