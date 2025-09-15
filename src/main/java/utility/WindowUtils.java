package utility;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowUtils {

    // Switch to new window and return parent window handle
    public static String switchToNewWindow(WebDriver driver) {
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                return parentWindow; // return parent so we can switch back later
            }
        }
        throw new RuntimeException("No new window found to switch!");
    }

    // Switch back to parent window
    public static void switchBackToParent(WebDriver driver, String parentWindow) {
        driver.switchTo().window(parentWindow);
    }
}
