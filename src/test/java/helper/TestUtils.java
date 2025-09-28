package helper;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import utility.WaitUtils;
import utility.WindowUtils;

/**
 * Utility class for common test operations related to verifying footer links.
 * This class provides reusable methods to handle link clicks, window switching,
 * URL verification, and cleanup (closing the new tab and switching back to parent).
 */
public class TestUtils {

    /**
     * Verifies that a footer link opens the expected URL in a new window/tab.
     * <p>
     * Steps performed:
     * 1. Switches to the newly opened window.
     * 2. Waits until the URL contains the expected text.
     * 3. Asserts that the current URL matches the expected URL.
     * 4. Closes the new tab and switches back to the parent window.
     * 
     * @param driver      The WebDriver instance used for the test.
     * @param expectedUrl The expected part of the URL to verify.
     */
    public static void verifyFooterLink(WebDriver driver, String expectedUrl) {
        // Switch to the newly opened window and store parent window reference
        String parentWindow = WindowUtils.switchToNewWindow(driver);

        // Wait until the URL contains the expected string and assert
        WaitUtils.waitUntilUrlContains(driver, expectedUrl);
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl),
                "Expected URL: " + expectedUrl + " but got: " + driver.getCurrentUrl());

        // Close the new window/tab and switch back to the parent window
        driver.close();
        WindowUtils.switchBackToParent(driver, parentWindow);
    }
    
    public static void waitAndAssertUrlContains(WebDriver driver, String expectedPart) {
        WaitUtils.waitUntilUrlContains(driver, expectedPart);
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedPart),
            "Expected URL to contain: " + expectedPart + " but got: " + driver.getCurrentUrl());
    }
    
    public static void waitAndAssertUrlExact(WebDriver driver, String expectedUrl) {
        WaitUtils.waitUntilUrlContains(driver, expectedUrl); // optional, wait for URL to load
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl,
            "Expected URL: " + expectedUrl + " but got: " + driver.getCurrentUrl());
    }

}
