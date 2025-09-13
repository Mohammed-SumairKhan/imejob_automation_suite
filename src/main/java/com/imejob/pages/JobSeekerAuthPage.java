package com.imejob.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtils;

/**
 * Page Object Model (POM) class for Job Seeker Authentication Page.
 * 
 * <p>This class manages the registration workflow for Job Seekers,
 * including filling personal details, selecting dropdown values, choosing skills,
 * uploading a resume, accepting terms, clicking register, handling OTP, 
 * and validating registration messages.</p>
 */
public class JobSeekerAuthPage {

    private WebDriver driver; // WebDriver instance for browser interactions

    // -------------------- Web Elements --------------------

    @FindBy(xpath = "//div[text() = 'New here?']/child::a")
    private WebElement createAccountLink; // Link to open registration form

    @FindBy(name = "firstName")
    private WebElement firstNameInput; // Input for first name

    @FindBy(name = "middleName")
    private WebElement middleNameInput; // Input for middle name

    @FindBy(name = "lastName")
    private WebElement lastNameInput; // Input for last name

    @FindBy(name = "email")
    private WebElement emailInput; // Input for email

    @FindBy(name = "phone")
    private WebElement phoneInput; // Input for phone

    @FindBy(name = "password")
    private WebElement passwordInput; // Input for password

    @FindBy(name = "acceptTerms")
    private WebElement acceptTermsCheckbox; // Checkbox for terms

    @FindBy(xpath = "//div[text() = 'Upload Resume']//input[@type ='file']")
    private WebElement uploadResumeInput; // Resume file input

    @FindBy(xpath = "//button[text() ='Register']")
    private WebElement registerButton; // Register button

    @FindBy(xpath = "//div[@class ='my-10']//input")
    private List<WebElement> otpInputs; // List of OTP input boxes

    @FindBy(xpath = "//button[text() ='Verify & Proceed']")
    private WebElement verifyAndProceedButton; // Button to verify OTP

    // -------------------- Constructor --------------------

    /**
     * Constructor to initialize the page elements using PageFactory.
     *
     * @param driver WebDriver instance
     */
    public JobSeekerAuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize all @FindBy elements
    }

    // -------------------- Page Actions --------------------

    /**
     * Clicks the 'Create Account' link.
     * Waits for visibility before clicking.
     */
    public void clickCreateAccount() {
        try {
            createAccountLink.click(); // click safely
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Create Account' link: " + e.getMessage());
        }
    }

    /**
     * Fills personal details in the registration form.
     *
     * @param firstName  User's first name
     * @param middleName User's middle name
     * @param lastName   User's last name
     * @param email      User's email
     * @param phone      User's phone number
     * @param password   User's password
     */
    public void fillPersonalDetails(String firstName, String middleName, String lastName,
                                    String email, String phone, String password) {
        firstNameInput.sendKeys(firstName);   // enter first name
        middleNameInput.sendKeys(middleName); // enter middle name
        lastNameInput.sendKeys(lastName);     // enter last name
        emailInput.sendKeys(email);           // enter email
        phoneInput.sendKeys(phone);           // enter phone number
        passwordInput.sendKeys(password);     // enter password
    }

    /**
     * Selects a value from a dropdown by index.
     * Waits for the dropdown and option to be visible.
     *
     * @param index Dropdown index (0-based)
     * @param value Value to select
     */
    public void selectDropdownValue(int index, String value) {
        try {
            By dropdownsLocator = By.xpath("//div[contains(@class , 'indicatorContainer')]");
            WaitUtils.waitForAllElementsVisible(driver, dropdownsLocator); // wait for all dropdowns

            List<WebElement> dropdownArrows = driver.findElements(dropdownsLocator);

            if (dropdownArrows.isEmpty() || index >= dropdownArrows.size()) {
                throw new RuntimeException("Dropdown arrow not found at index: " + index);
            }

            dropdownArrows.get(index).click(); // click dropdown
            By option = By.xpath("//div[text()='" + value + "']");
            WaitUtils.waitForElementVisible(driver, option); // wait for option
            driver.findElement(option).click(); // select option
        } catch (Exception e) {
            throw new RuntimeException("Failed to select dropdown value: " + e.getMessage());
        }
    }

    /**
     * Selects multiple skills from the skills dropdown.
     *
     * @param skills List of skills to select
     */
    public void selectSkills(List<String> skills) {
        try {
            By dropdownsLocator = By.xpath("//div[contains(@class , 'indicatorContainer')]");
            WaitUtils.waitForAllElementsVisible(driver, dropdownsLocator);

            List<WebElement> dropdownArrows = driver.findElements(dropdownsLocator);
            if (dropdownArrows.size() <= 3) {
                throw new RuntimeException("Skills dropdown arrow not found at index 3");
            }

            for (String skill : skills) {
                dropdownArrows.get(3).click(); // open skills dropdown
                By option = By.xpath("//div[text()='" + skill + "']");
                WaitUtils.waitForElementVisible(driver, option); // wait for skill
                driver.findElement(option).click(); // select skill
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to select skills: " + e.getMessage());
        }
    }

    /**
     * Uploads a resume file.
     *
     * @param filePath Path to the resume file
     */
    public void uploadResume(String filePath) {
        try {
            uploadResumeInput.sendKeys(filePath); // upload file
            // Wait until the uploaded resume is displayed on the UI
            WaitUtils.waitForElementVisible(driver, By.xpath("//div[contains(text(), '.pdf')]"));
        } catch (Exception e) {
            throw new RuntimeException("Resume upload failed: " + e.getMessage());
        }
    }

    /**
     * Accepts terms and conditions if not already selected.
     */
    public void acceptTerms() {
        try {
            if (!acceptTermsCheckbox.isSelected()) {
                acceptTermsCheckbox.click(); // select checkbox
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to accept terms: " + e.getMessage());
        }
    }

    /**
     * Clicks the 'Register' button to submit the form.
     */
    public void clickRegister() {
        try {
            registerButton.click(); // safely click register
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Register button: " + e.getMessage());
        }
    }

    /**
     * Creates a new Job Seeker account by filling all fields, selecting dropdowns,
     * uploading resume, and accepting terms.
     */
    public void createAccount(String firstName, String middleName, String lastName,
                              String email, String phone, String password,
                              String experience, String location, String gender,
                              List<String> skills, String resumePath, boolean agreeTerms) {

        fillPersonalDetails(firstName, middleName, lastName, email, phone, password);
        selectDropdownValue(0, experience); // select experience
        selectDropdownValue(1, location);   // select location
        selectDropdownValue(2, gender);     // select gender
        selectSkills(skills);               // select skills
        uploadResume(resumePath);           // upload resume
        if (agreeTerms) acceptTerms();      // accept terms
    }

    /**
     * Returns registration status message if email or phone is already used.
     *
     * @return String message or null
     */
    public String getRegisterStatusMessage() {
        By messageLocator = By.xpath("//*[contains(text(),'Email address') or contains(text(),'Mobile number')]");
        try {
            List<WebElement> messages = driver.findElements(messageLocator);
            return messages.isEmpty() ? null : messages.get(0).getText();
        } catch (Exception e) {
            return null; // ignore exception if element not found
        }
    }

    /**
     * Enters OTP into the separate input boxes.
     *
     * @param otp 4-digit OTP string
     */
    public void enterOTP(String otp) {
        try {
            for (int i = 0; i < otpInputs.size(); i++) {
                otpInputs.get(i).sendKeys(String.valueOf(otp.charAt(i))); // enter each digit
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter OTP: " + e.getMessage());
        }
    }

    /**
     * Clicks the 'Verify & Proceed' button after entering OTP.
     */
    public void clickVerifyAndProceed() {
        try {
            verifyAndProceedButton.click(); // safely click
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Verify & Proceed button: " + e.getMessage());
        }
    }
}
