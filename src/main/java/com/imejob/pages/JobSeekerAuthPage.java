package com.imejob.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtils;

/**
 * Page Object Model (POM) class for the Job Seeker Authentication Page.
 * 
 * This class handles user registration workflows such as filling personal details,
 * selecting dropdown values, choosing skills, uploading a resume, accepting terms,
 * and clicking the register button.
 */
public class JobSeekerAuthPage {

    private WebDriver driver; // WebDriver instance to interact with the browser

    // -------------------- Web Elements --------------------

    @FindBy(xpath = "//div[text() = 'New here?']/child::a")
    private WebElement createAccountLink; // Link to navigate to the create account page

    @FindBy(name = "firstName")
    private WebElement firstNameInput; // Input field for first name

    @FindBy(name = "middleName")
    private WebElement middleNameInput; // Input field for middle name

    @FindBy(name = "lastName")
    private WebElement lastNameInput; // Input field for last name

    @FindBy(name = "email")
    private WebElement emailInput; // Input field for email

    @FindBy(name = "phone")
    private WebElement phoneInput; // Input field for phone number

    @FindBy(name = "password")
    private WebElement passwordInput; // Input field for password

    @FindBy(name = "acceptTerms")
    private WebElement acceptTermsCheckbox; // Checkbox to accept terms and conditions

    @FindBy(xpath = "//div[text() = 'Upload Resume']//input[@type ='file']")
    private WebElement uploadResumeInput; // File upload input for resume

    @FindBy(xpath = "//button[text() ='Register']")
    private WebElement registerButton; // Button to register a new account

    // -------------------- Constructor --------------------

    /**
     * Constructor to initialize the page elements using PageFactory.
     *
     * @param driver WebDriver instance
     */
    public JobSeekerAuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize web elements
    }

    // -------------------- Page Actions --------------------

    /**
     * Clicks on the 'Create Account' link to open the registration form.
     */
    public void clickCreateAccount() {
        By loc = By.xpath("//a[contains(text(),'Create an Account')]");
        WaitUtils.waitForElementVisible(driver, loc); // Wait until link is visible
        createAccountLink.click(); // Click the link
    }

    /**
     * Fills personal details in the registration form.
     *
     * @param firstName User's first name
     * @param middleName User's middle name
     * @param lastName User's last name
     * @param email User's email
     * @param phone User's phone number
     * @param password User's password
     */
    public void fillPersonalDetails(String firstName, String middleName, String lastName,
                                    String email, String phone, String password) {
        firstNameInput.sendKeys(firstName);   // Enter first name
        middleNameInput.sendKeys(middleName); // Enter middle name
        lastNameInput.sendKeys(lastName);     // Enter last name
        emailInput.sendKeys(email);           // Enter email
        phoneInput.sendKeys(phone);           // Enter phone number
        passwordInput.sendKeys(password);     // Enter password
    }

    /**
     * Selects a value from a dropdown by index.
     *
     * @param index Dropdown index (0-based)
     * @param value The value to select
     */
    public void selectDropdownValue(int index, String value) {
        By dropdownsLocator = By.xpath("//div[contains(@class , 'indicatorContainer')]");
        WaitUtils.waitForAllElementsVisible(driver, dropdownsLocator); // Wait for dropdowns to be visible

        List<WebElement> dropdownArrows = driver.findElements(dropdownsLocator);

        if (dropdownArrows.isEmpty() || index >= dropdownArrows.size()) {
            throw new RuntimeException("Dropdown arrow not found at index: " + index);
        }

        dropdownArrows.get(index).click(); // Click dropdown arrow
        By option = By.xpath("//div[text()='" + value + "']");
        WaitUtils.waitForElementVisible(driver, option); // Wait for option to be visible
        driver.findElement(option).click(); // Click the option
    }

    /**
     * Selects multiple skills from the skills dropdown.
     *
     * @param skills List of skills to select
     */
    public void selectSkills(List<String> skills) {
        By dropdownsLocator = By.xpath("//div[contains(@class , 'indicatorContainer')]");
        WaitUtils.waitForAllElementsVisible(driver, dropdownsLocator); // Wait for all dropdowns

        List<WebElement> dropdownArrows = driver.findElements(dropdownsLocator);

        if (dropdownArrows.size() <= 3) {
            throw new RuntimeException("Skills dropdown arrow not found at index 3");
        }

        // Select each skill from the dropdown
        for (String skill : skills) {
            dropdownArrows.get(3).click(); // Click the skills dropdown
            By option = By.xpath("//div[text()='" + skill + "']");
            WaitUtils.waitForElementVisible(driver, option); // Wait until skill option is visible
            driver.findElement(option).click(); // Click the skill
        }
    }

    /**
     * Uploads a resume file.
     *
     * @param filePath Path of the resume file
     */
    public void uploadResume(String filePath) {
        uploadResumeInput.sendKeys(filePath); // Send file path to input
    }

    /**
     * Accepts the terms and conditions if not already selected.
     */
    public void acceptTerms() {
        if (!acceptTermsCheckbox.isSelected()) { // Check if already selected
            acceptTermsCheckbox.click(); // Select checkbox
        }
    }

    /**
     * Clicks the Register button to submit the form.
     */
    public void clickRegister() {
        registerButton.click(); // Click register button
    }

    /**
     * Creates a new account by filling in all required fields, selecting dropdowns,
     * uploading resume, and accepting terms.
     *
     * @param firstName User's first name
     * @param middleName User's middle name
     * @param lastName User's last name
     * @param email User's email
     * @param phone User's phone number
     * @param password User's password
     * @param experience User's total experience
     * @param location User's current location
     * @param gender User's gender
     * @param skills List of technical skills
     * @param resumePath Path of resume file
     * @param agreeTerms Whether to accept terms
     */
    public void createAccount(String firstName, String middleName, String lastName,
                              String email, String phone, String password,
                              String experience, String location, String gender,
                              List<String> skills, String resumePath, boolean agreeTerms) {

        fillPersonalDetails(firstName, middleName, lastName, email, phone, password);

        selectDropdownValue(0, experience); // Select experience dropdown
        selectDropdownValue(1, location);   // Select location dropdown
        selectDropdownValue(2, gender);     // Select gender dropdown
        selectSkills(skills);               // Select skills

        uploadResume(resumePath);           // Upload resume
        if (agreeTerms) acceptTerms();      // Accept terms if required
    }

}
