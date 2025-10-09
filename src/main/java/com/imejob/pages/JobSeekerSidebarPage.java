package com.imejob.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtils;

/**
 * Page Object class for the Sidebar navigation menu.
 * Provides WebElements for each sidebar item and a method to click on them.
 * Supports navigation to pages like Job Search, Interviews, Documents, Applications, Profile, My Posts, Saved Jobs, Community, Dashboard, Home, and Logout.
 */
public class JobSeekerSidebarPage {

	WebDriver driver; // WebDriver instance to control the browser

	@FindBy(xpath = "//span[text() = 'Job Search']")
	WebElement jobSearchLink; // Link for Job Search page

	@FindBy(xpath = "//span[text() = 'Interviews/Chat']")
	WebElement interviewLink; // Link for Interviews/Chat page

	@FindBy(xpath = "//span[text() = 'Documents']")
	WebElement documentsLink; // Link for Documents page

	@FindBy(xpath = "//span[text() = 'Applications']")
	WebElement applicationLink; // Link for Applications page

	@FindBy(xpath = "//span[text() = 'Profile']")
	WebElement profileLink; // Link for Profile page

	@FindBy(xpath = "//span[text() = 'My Posts']")
	WebElement myPostLink; // Link for My Posts page

	@FindBy(xpath = "//span[text() = 'My Saved Posts']")
	WebElement mySavedJobLink; // Link for My Saved Jobs page

	@FindBy(xpath = "//img[@alt='brand-logo']")
	WebElement brandLogo; // Brand logo element (used to navigate home)

	@FindBy(xpath = "//button[normalize-space()='Dashboard']")
	WebElement dashBoardButton; // Dashboard button in sidebar

	@FindBy(xpath = "//div[@aria-label='Toggle navigation']")
	WebElement profileLogo; // Profile logo element (for logout dropdown)

	@FindBy(xpath = "//div[text() = 'Logout']")
	WebElement logoutButton; // Logout button in profile menu

	@FindBy(xpath = "//button[contains(@class,'btn me-4 btn-icon btn-hover-rise btn-md')]")
	WebElement communityIcon; // Community icon button

	@FindBy(xpath = "//div[@class = 'app-navbar-item ms-1 ms-lg-2'][1]")
	WebElement homeIcon; // Home icon button

	/**
	 * Constructor to initialize WebDriver and page elements
	 * @param driver WebDriver instance
	 */
	public JobSeekerSidebarPage(WebDriver driver) {
		this.driver = driver; // Initialize driver
		PageFactory.initElements(driver, this); // Initialize page elements with PageFactory
	}

	/**
	 * Clicks on a sidebar element by name
	 * @param name Name of the sidebar element to click (case-insensitive)
	 */
	public void clickOn(String name) {
		try {
			WebElement elementToClick = null;

			// Determine which element to click based on input name
			switch (name.toLowerCase()) {
			case "job search":
				elementToClick = jobSearchLink;
				break;
			case "interviews":
				elementToClick = interviewLink;
				break;
			case "documents":
				elementToClick = documentsLink;
				break;
			case "applications":
				elementToClick = applicationLink;
				break;
			case "profile":
				elementToClick = profileLink;
				break;
			case "my posts":
				elementToClick = myPostLink;
				break;
			case "my saved jobs":
				elementToClick = mySavedJobLink;
				break;
			case "logo":
				elementToClick = brandLogo;
				break;
			case "dashboard":
				elementToClick = dashBoardButton;
				break;
			case "profilelogo":
				elementToClick = profileLogo;
				break;
			case "logout":
				elementToClick = logoutButton;
				break;
			case "community":
				elementToClick = communityIcon;
				break;
			case "homeicon":
				elementToClick = homeIcon;
				break;
			default:
				throw new RuntimeException("Invalid sidebar name: " + name);
			}
			
			WaitUtils.waitForElementVisible(driver, elementToClick);
			WaitUtils.waitForElementClickable(driver, elementToClick); // Wait until the element is clickable before performing click
			elementToClick.click(); // Perform click action

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to click on: " + name + " | " + e.getMessage());
		}
	}
}
