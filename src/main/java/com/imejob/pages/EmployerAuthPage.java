package com.imejob.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Page Object class representing the Employer Authentication page
 * on imejob.com. 
 * <p>
 * It contains locators and actions for navigating to 
 * Create Account, Sign In, Forgot Password, and Login pages 
 * from the Employer section.
 */
public class EmployerAuthPage {

    WebDriver driver;

    /** Employer main menu button on the homepage */
    @FindBy(xpath = "//button[text() = 'Employer']")
    WebElement employerButton;

    /** Link to navigate to the "Create an Account" page */
    @FindBy(xpath = "//a[text() = 'Create an Account']")
    WebElement createAccountLink;

    /** Link to navigate to the "Sign In" page */
    @FindBy(xpath = "//a[text() = 'Sign In']")
    WebElement signInLink;

    /** Link to navigate to the "Forgot Password" page */
    @FindBy(xpath = "//a[text() = 'Forgot Password?']")
    WebElement forgotPasswordLink;

    /** Link to navigate to the "Login" page */
    @FindBy(xpath = "//a[text() = 'Login']")
    WebElement loginLink;

    /**
     * Constructor initializes the WebDriver instance and 
     * initializes all the WebElements using PageFactory.
     * 
     * @param driver WebDriver instance passed from the test
     */
    public EmployerAuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigates to the "Create Account" page by first clicking 
     * the Employer button and then the Create Account link.
     */
    public void navigateToCreateAccount() {
        employerButton.click();
        createAccountLink.click();
    }

    /**
     * Navigates to the "Sign In" page by first clicking 
     * the Employer button and then the Sign In link.
     */
    public void navigateToSignIn() {
        employerButton.click();
        createAccountLink.click();
        signInLink.click();
    }

    /**
     * Navigates to the "Forgot Password" page by first clicking 
     * the Employer button and then the Forgot Password link.
     */
    public void navigateToForgotPassword() {
        employerButton.click();
        forgotPasswordLink.click();
    }

    /**
     * Navigates to the "Login" page by first clicking 
     * the Employer button, then the Forgot Password link,
     * and finally clicking the Login link.
     */
    public void navigateToLogin() {
        employerButton.click();
        forgotPasswordLink.click();
        loginLink.click();
    }
}
