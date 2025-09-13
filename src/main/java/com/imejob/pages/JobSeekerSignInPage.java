package com.imejob.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtils;

/**
 * Page Object Model (POM) class for the Job Seeker Sign-In page of imejob.com.
 * 
 * This class contains locators and actions for interacting with the Job Seeker Sign-In form,
 * including signing in, handling Forgot Password, and navigating back to Login.
 */
public class JobSeekerSignInPage {

    WebDriver driver; // WebDriver instance to interact with the browser

    /** Locator for "Job Seeker" button to open the Sign In form */
    @FindBy(xpath = "//button[text() = 'Job Seeker']")
    WebElement jobSeeker;

    /** Locator for the email input field */
    @FindBy(name = "email")
    WebElement email;

    /** Locator for the password input field */
    @FindBy(name = "password")
    WebElement password;

    /** Locator for the reCAPTCHA checkbox */
    @FindBy(css = "div.recaptcha-checkbox-border")
    WebElement checkBox;

    /** Locator for the Sign In button */
    @FindBy(xpath = "//button[text() ='Sign In']")
    WebElement signIn;

    /** Locator for the Forgot Password link */
    @FindBy(xpath = "//a[text() = 'Forgot Password?']")
    WebElement forgetPass;

    /** Locator for the Login link on the Forgot Password page */
    @FindBy(xpath = "//a[text() = 'Login']")
    WebElement login;

    /**
     * Constructor to initialize the PageFactory elements and WebDriver instance.
     * 
     * @param driver WebDriver instance passed from the test
     */
    public JobSeekerSignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Performs the complete sign-in action for a Job Seeker.
     * Steps:
     * 1. Click the Job Seeker button to open the Sign In form.
     * 2. Enter email and password.
     * 3. Switch to reCAPTCHA iframe and click the checkbox.
     * 4. Switch back to the main page.
     * 5. Click the Sign In button.
     * 
     * @param yourEmail    Email address of the Job Seeker
     * @param yourPassword Password of the Job Seeker
     */
    public void signIn(String yourEmail, String yourPassword) {
        jobSeeker.click();

        email.sendKeys(yourEmail);
        password.sendKeys(yourPassword);

        // Switch to reCAPTCHA iframe and click checkbox
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src, 'recaptcha')]")));
        WaitUtils.waitForElementClickable(driver, checkBox);
        checkBox.click();
       // WaitUtils.waitForAttributeToBe(driver, checkBox, "aria-checked", "true");
        driver.switchTo().defaultContent();
    }

    /**
     * Clicks the "Forgot Password?" link from the Job Seeker Sign In form.
     * This navigates the user to the Forgot Password page.
     */
    public void clickForgetPassword() {
        jobSeeker.click();
        forgetPass.click();
    }

    /**
     * Clicks the "Login" link on the Forgot Password page to return to the Sign In form.
     * Assumes the user is already on the Forgot Password page.
     */
    public void clickLogin() {
        login.click();
    }
    
    public void clickSignIn() {
        WaitUtils.waitForElementClickable(driver, signIn);
        signIn.click();
    }
}
