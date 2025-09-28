package com.imejob.tests.functional;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.imejob.dataprovider.BookDemoDataProvider;
import com.imejob.pages.BookDemoPage;

import driverproperties.BrowserHandler;
import helper.TestUtils;
import utility.JsonReader;
import utility.PropertiesReader;
import utility.WaitUtils;

/**
 * BookDemoTest class contains functional tests for the "Book Demo" flow.
 * It covers:
 * 1. Clicking the Book Demo button and verifying navigation to Contact Us page.
 * 2. Filling the contact form and verifying the success message after submission.
 */
public class BookDemoTest {

    WebDriver driver; // WebDriver instance to control the browser
    PropertiesReader propertiesReader; // Utility to read configuration (browser, URL, etc.)
    JsonReader jsonReader; // Utility to read test data from JSON files
    BookDemoPage bookDemoPage; // Page Object for the Book Demo page

    /**
     * setUp method initializes WebDriver, navigates to the base URL, and sets up utilities.
     * Runs before each test method.
     */
    @BeforeMethod
    public void setUp() {
        propertiesReader = new PropertiesReader(); // Load configuration properties
        driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName()); // Launch browser based on config
        driver.get(propertiesReader.getUrl()); // Navigate to the application URL
        jsonReader = new JsonReader(); // Initialize JSON reader for test data
        WaitUtils.implicitWait(driver); // Apply implicit wait for element loading
        bookDemoPage = new BookDemoPage(driver); // Initialize Page Object
    }

    /**
     * Test to verify clicking the Book Demo button navigates to Contact Us page.
     */
    @Test
    public void clickBookDemoButtonTest() {
        bookDemoPage.clickBookDemo(); // Click Book Demo button //inline
        TestUtils.waitAndAssertUrlContains(driver, "contact-us"); // wait and verify url
    }

    /**
     * Test to fill the contact form and submit.
     * Uses DataProvider to supply test data from JSON.
     * Depends on clickBookDemoButtonTest.
     * 
     * @param email       Email address
     * @param phoneNumber Phone number
     * @param firstName   First name
     * @param lastName    Last name
     * @param subject     Subject selected from dropdown
     * @param messgae     Message to submit
     */
    @Test(dependsOnMethods = "clickBookDemoButtonTest", 
          dataProvider = "bookDemoData", dataProviderClass = BookDemoDataProvider.class)
    public void fillContactFormTest(String email, String phoneNumber, String firstName, String lastName, String subject,
                                    String messgae) {

        bookDemoPage.clickBookDemo(); // Navigate to Contact Us page //inline
        bookDemoPage.fillContactForm(email, phoneNumber, firstName, lastName, subject, messgae); // Fill form fields //inline
        bookDemoPage.clickSubmitButton(); // Click Submit button //inline
        Assert.assertTrue(bookDemoPage.getStatusMessage().contains("Thanks for contacting us!")); 
            // Assert success message contains expected text //inline
    }

    /**
     * tearDown method quits the browser after each test method execution.
     */
    @AfterMethod
    public void tearDown() {
        driver.quit(); // Close all browser windows and safely end the session //inline
    }

}
