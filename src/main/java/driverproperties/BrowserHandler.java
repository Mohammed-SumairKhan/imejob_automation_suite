package driverproperties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * BrowserHandler class is responsible for initializing and returning
 * the WebDriver instance based on the browser name provided.
 * 
 * Supported browsers:
 * - Chrome
 * - Firefox
 */
public class BrowserHandler {
	 /**
     * Returns a WebDriver instance for the given browser.
     * 
     * @param browserName The name of the browser (e.g., "chrome", "firefox")
     * @return WebDriver instance for the requested browser
     */
    public static WebDriver getBrowser(String browserName) {

        WebDriver driver = null; // Declare WebDriver, will be initialized later

        switch (browserName.toLowerCase()) { // Convert to lowercase to avoid case mismatch
            case "chrome":
                driver = new ChromeDriver(); // Launch Chrome browser
                break;

            case "firefox":
                driver = new FirefoxDriver(); // Launch Firefox browser
                break;

            default:
                System.out.println("Browser not supported: " + browserName); // Handle unsupported browser
        }

        if (driver != null) {
            driver.manage().window().maximize(); // Maximize browser window
        }

        return driver; // Return WebDriver instance (or null if unsupported)
    }
}
