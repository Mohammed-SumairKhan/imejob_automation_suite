package com.imejob.utility;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utility.WaitUtils;

/**
 * Test utility class for verifying navigation in header links. Keeps reusable
 * methods for navigation assertions.
 */
public class TestNavigationHelper {

	/**
	 * Clicks on a header element and asserts if the URL contains the expected
	 * value. This method is meant for tests only (uses TestNG Assert).
	 *
	 * @param driver          WebDriver instance
	 * @param clickAction     Lambda / Runnable that performs the click
	 * @param expectedUrlPart The expected URL substring after click
	 */
	public static void verifyNavigation(WebDriver driver, Runnable clickAction, String expectedUrlPart) {
		clickAction.run(); // Perform the click action
		WaitUtils.waitUntilUrlContains(driver, expectedUrlPart); // Wait until URL contains expected part
		Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrlPart),  // Validate URL
				"Navigation failed! Expected URL part: " + expectedUrlPart + " but found: " + driver.getCurrentUrl());
	}
}
