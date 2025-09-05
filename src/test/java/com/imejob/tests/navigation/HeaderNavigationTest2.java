package com.imejob.tests.navigation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.imejob.pages.HeaderNavigationPage;
import com.imejob.utility.TestNavigationHelper;

import driverproperties.BrowserHandler;
import utility.PropertiesReader;

/**
 * Test class to validate header navigation links on ImeJob website.
 * It verifies that each header element navigates to the correct page.
 */
public class HeaderNavigationTest2 {

    WebDriver driver; 
    PropertiesReader propertiesReader; 
    HeaderNavigationPage headerNavigationPage; 

    /**
     * Setup method - initializes WebDriver, loads config values, 
     * and navigates to the base URL before each test.
     */
    @BeforeMethod
    public void start() {
        propertiesReader = new PropertiesReader(); 
        driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName()); 
        driver.get(propertiesReader.getUrl()); 
        headerNavigationPage = new HeaderNavigationPage(driver); 
    }

    /**
     * DataProvider for header navigation tests.
     * Each row contains: 
     *   1) Name of the link 
     *   2) Lambda click action 
     *   3) Expected URL part
     */
    @DataProvider(name = "headerLinks")
    public Object[][] headerLinks() {
        return new Object[][] {
            {"Brand Logo",      (Runnable) () -> headerNavigationPage.clickBrandLogo(),   "imejob"},
            {"Job Search",      (Runnable) () -> headerNavigationPage.clickJobSearch(),   "job-search/all-jobs"},
            {"About Us",        (Runnable) () -> headerNavigationPage.clickAboutUs(),     "about-us"},
            {"Contact Us",      (Runnable) () -> headerNavigationPage.clickContactUs(),   "contact-us"},
            {"Join Community",  (Runnable) () -> headerNavigationPage.clickJoinCommunity(),"community"},
            {"Blogs",           (Runnable) () -> headerNavigationPage.clickBlogs(),       "blogs"},
            {"Pricing",         (Runnable) () -> headerNavigationPage.clickPricing(),     "pricing"},
            {"Job Seeker",      (Runnable) () -> headerNavigationPage.clickJobSeeker(),   "requestType=jobSeeker"},
            {"Employer",        (Runnable) () -> headerNavigationPage.clickEmployer(),    "requestType=employer"}
        };
    }

    /**
     * Generic navigation test using DataProvider.
     */
    @Test(dataProvider = "headerLinks")
    public void verifyHeaderNavigation(String linkName, Runnable clickAction, String expectedUrlPart) {
        TestNavigationHelper.verifyNavigation(driver, clickAction, expectedUrlPart);
        System.out.println("✅ Verified navigation for: " + linkName);
    }

    /**
     * Tear down method - closes the browser after each test.
     */
    @AfterMethod
    public void close() {
        driver.quit(); 
    }
}
