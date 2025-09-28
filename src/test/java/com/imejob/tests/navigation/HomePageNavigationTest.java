package com.imejob.tests.navigation;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.imejob.pages.HomePage;

import driverproperties.BrowserHandler;
import helper.TestUtils;
import utility.PropertiesReader;
import utility.WaitUtils;

/**
 * This class contains navigation tests for the Home Page of IMEJob.
 * It verifies that various clickable elements on the Home Page
 * navigate to the correct URLs.
 */
public class HomePageNavigationTest {

    // WebDriver instance for browser interaction
    WebDriver driver;

    // PropertiesReader instance to read configuration values like URL and browser name
    PropertiesReader propertiesReader;

    // HomePage object to access elements and actions on the Home Page
    HomePage homePage;

    /**
     * Setup method to initialize WebDriver, open the URL, 
     * initialize Page Object, and set implicit wait.
     */
    @BeforeMethod
    public void start() {
        propertiesReader = new PropertiesReader(); // Create PropertiesReader object
        driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName()); // Launch browser from config
        driver.get(propertiesReader.getUrl()); // Open the base URL
        homePage = new HomePage(driver); // Initialize Page Object
        WaitUtils.implicitWait(driver); // Apply implicit wait
    }

    /**
     * Test to verify navigation when "Create an account now!" is clicked
     */
    @Test(priority = 1)
    public void CreateAccountNavTest() {
        homePage.clickCreateAccount(); // Click Create Account link
        TestUtils.waitAndAssertUrlContains(driver, "auth/login?"); // verify url
    }

    /**
     * Test to verify WhatsApp icon click opens the correct WhatsApp link in a new tab
     */
    @Test(priority = 2)
    public void clickWhatsAppIconTest() {
        String mainWindow = driver.getWindowHandle(); // Store the current main window handle

        homePage.clickWhatsAppIcon(); // Click WhatsApp icon

        // Switch to new tab
        Set<String> allWindows = driver.getWindowHandles(); // Get all window handles
        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window); // Switch to the new WhatsApp tab
                break;
            }
        }

        // Verify URL contains WhatsApp link
        TestUtils.waitAndAssertUrlContains(driver, "whatsapp.com");

        // Close new tab and switch back to main window
        driver.close();
        driver.switchTo().window(mainWindow);
    }

    /**
     * Test to verify navigation when "View all jobs" is clicked
     */
    @Test(priority = 3)
    public void ViewAllJobNavTest() {
        homePage.clickViewAllJobs(); // Click "View all jobs"
        TestUtils.waitAndAssertUrlContains(driver, "all-jobs");// Verify URL
    }

    /**
     * Test to verify navigation when "Book a demo" button is clicked
     */
    @Test(priority = 4)
    public void bookDemoNavTest() {
        homePage.clickBookDemo(); // Click "Book a demo"
        TestUtils.waitAndAssertUrlContains(driver, "contact-us");// Verify URL
    }

    /**
     * Tear down method to close the browser after tests
     */
    @AfterMethod
    public void close() {
        driver.quit(); // Close all browser windows and end WebDriver session
    }
}
