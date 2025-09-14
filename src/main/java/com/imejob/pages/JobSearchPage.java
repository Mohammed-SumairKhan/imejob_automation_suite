package com.imejob.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtils;

/**
 * 🔹 JobSearchPage
 * 
 * Page Object Model (POM) class for the Job Search page. Encapsulates locators
 * and actions performed on the Job Search page, such as entering skills,
 * location, applying filters, and retrieving job results.
 */
public class JobSearchPage {

	WebDriver driver; // WebDriver instance to control the browser

	// ----------------- Input fields -----------------
	@FindBy(xpath = "(//div[@class=' css-19bb58m']//input)[1]")
	WebElement skillInput; // Skill input field

	@FindBy(xpath = "(//div[@class=' css-19bb58m']//input)[2]")
	WebElement locationInput; // Location input field

	// ----------------- Buttons -----------------
	@FindBy(xpath = "//button[text()='Search']")
	WebElement searchButton; // Search button

	// ----------------- Job results -----------------
	@FindBy(xpath = "//div[@class='col mb-5 ']")
	List<WebElement> jobCards; // List of job card elements displayed after search

	@FindBy(xpath = "//*[contains(text(), 'No jobs found')]")
	WebElement noJobsFoundMessage; // "No jobs found" message

	// ----------------- Filter options -----------------
	@FindBy(xpath = "//label[text() ='Internship']")
	WebElement intershipButton; // Internship filter checkbox

	@FindBy(xpath = "//label[text() ='Remote']")
	WebElement remoteButton; // Remote filter checkbox

	@FindBy(xpath = "//label[text() ='Contract']")
	WebElement contractButton; // Contract filter checkbox

	@FindBy(xpath = "//label[text() ='Freelance']")
	WebElement freelanceButton; // Freelance filter checkbox

	@FindBy(xpath = "//div[contains(@class, 'indicatorContainer')]")
	List<WebElement> relevanceDropdown; // adjust xpath for actual dropdown

	@FindBy(xpath = "//div[@class = ' css-10wo9uf-option'][1]")
	WebElement relevanceA_Z; // revelenece A-Z

	@FindBy(xpath = "//div[@class = ' css-10wo9uf-option'][2]")
	WebElement relevanceZ_A; // revelenece Z-A

	// ----------------- Header -----------------
	@FindBy(xpath = "//div[@class='TopBar_title__gKBWH']")
	WebElement totalJobs; // Header showing total number of jobs

	// Locator to capture only job titles inside job cards
	@FindBy(xpath = "//div[@class ='Jobcard_jobTitle__LUngf']")
	List<WebElement> jobTitles;

	/**
	 * Constructor to initialize SearchPage elements using PageFactory.
	 *
	 * @param driver WebDriver instance
	 */
	public JobSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // initialize elements with PageFactory
	}

	/**
	 * Generic helper method to type text into an input field and press Enter.
	 *
	 * @param input WebElement input field
	 * @param value Value to type
	 */
	private void typeAndEnter(WebElement input, String value) {
		try {
			input.click(); // click input field to focus
			input.clear(); // clear any existing value
			input.sendKeys(value, Keys.ENTER); // type value and press Enter
		} catch (Exception e) {
			throw new RuntimeException("Failed to type '" + value + "' in input field", e);
		}
	}

	/**
	 * Clicks the search button safely after waiting for it to be clickable.
	 */
	public void clickSearchButton() {
		try {
			WaitUtils.waitForElementClickable(driver, searchButton); // wait for search button to be clickable
			searchButton.click(); // click on search button
		} catch (Exception e) {
			throw new RuntimeException("Failed to click on Search button", e);
		}
	}

	/**
	 * Enters a single skill into the skill input field.
	 *
	 * @param skill Skill string to search
	 */
	public void searchBySkill(String skill) {
		typeAndEnter(skillInput, skill); // type skill into skill input field
	}

	/**
	 * Enters a location into the location input field.
	 *
	 * @param location Location string to search
	 */
	public void searchByLocation(String location) {
		typeAndEnter(locationInput, location); // type location into location input field
	}

	/**
	 * Enters multiple skills into the skill input field.
	 *
	 * @param skills List of skills
	 */
	public void searchByMultipleSkills(List<String> skills) {
		for (String skill : skills) {
			typeAndEnter(skillInput, skill); // enter each skill one by one
		}
	}

	/**
	 * Retrieves text of the first job card safely.
	 *
	 * @return Text of the first job card
	 */
	public String getFirstJobCardText() {
		try {
			WaitUtils.waitForAllElementsVisible(driver, By.xpath("//div[@class='col mb-5 ']")); // wait until job cards
																								// appear
			return jobCards.get(0).getText(); // return first job card text
		} catch (IndexOutOfBoundsException e) {
			throw new NoSuchElementException("No job cards found to get first card text", e);
		}
	}

	/**
	 * Retrieves text of the last job card safely.
	 *
	 * @return Text of the last job card
	 */
	public String getLastJobCardText() {
		try {
			WaitUtils.waitForAllElementsVisible(driver, By.xpath("//div[@class='col mb-5 ']")); // wait until job cards
																								// appear
			return jobCards.get(jobCards.size() - 1).getText(); // return last job card text
		} catch (IndexOutOfBoundsException e) {
			throw new NoSuchElementException("No job cards found to get last card text", e);
		}
	}

	/**
	 * Retrieves "No jobs found" message safely.
	 *
	 * @return 'No jobs found' message text or fallback message
	 */
	public String getNoJobsFoundMessage() {
		try {
			return noJobsFoundMessage.getText(); // return no jobs message text
		} catch (NoSuchElementException e) {
			return "'No jobs found' message not displayed"; // fallback text
		}
	}

	/**
	 * Applies a given filter (e.g., Company, Experience, Salary).
	 *
	 * @param filterName Name of the filter to apply
	 */
	public void applyFilter(String filterName) {
		try {
			List<WebElement> filters = driver.findElements(By.xpath("//label[contains(text(),'" + filterName + "')]")); // find
																														// matching
																														// filter

			if (filters.isEmpty()) { // if no filter found
				System.out.println("Filter not found on page: " + filterName);
				return; // skip applying filter
			}

			WebElement filter = filters.get(0); // pick first filter element
			filter.click(); // click filter checkbox

			WaitUtils.waitForPageToLoad(driver); // wait for page update after filter applied
		} catch (Exception e) {
			System.out.println("Error applying filter '" + filterName + "': " + e.getMessage());
		}
	}

	/**
	 * Retrieves the number of job cards displayed.
	 *
	 * @return count of job cards
	 */
	public int getJobCardsCount() {
		try {
			return jobCards.size(); // return number of job cards
		} catch (Exception e) {
			return 0; // return 0 if no job cards are present
		}
	}

	/**
	 * Toggles Internship filter.
	 */
	public void toggleInternshipFilter() {
		try {
			intershipButton.click(); // click internship filter
		} catch (Exception e) {
			throw new NoSuchElementException("No Internship filter found", e);
		}
	}

	/**
	 * Toggles Remote filter.
	 */
	public void toggleRemoteFilter() {
		try {
			remoteButton.click(); // click remote filter
		} catch (Exception e) {
			throw new NoSuchElementException("No Remote filter found", e);
		}
	}

	/**
	 * Toggles Contract filter.
	 */
	public void toggleContractFilter() {
		try {
			contractButton.click(); // click contract filter
		} catch (Exception e) {
			throw new NoSuchElementException("No Contract filter found", e);
		}
	}

	/**
	 * Toggles Freelance filter.
	 */
	public void toggleFreelanceFilter() {
		try {
			freelanceButton.click(); // click freelance filter
		} catch (Exception e) {
			throw new NoSuchElementException("No Freelance filter found", e);
		}
	}

	/**
	 * Extracts total number of jobs displayed in the header. Example: "107 Java
	 * Jobs in Bangalore" → returns "107"
	 *
	 * @return Total jobs count as String
	 */
	public String getTotalJobsText() {
		String fullText = totalJobs.getText(); // e.g., "107 Java Jobs in Bangalore"

		Pattern p = Pattern.compile("\\d+"); // regex to match digits
		Matcher m = p.matcher(fullText);

		if (m.find()) {
			return m.group(); // return first number found
		} else {
			throw new NoSuchElementException("No total jobs number found in element.");
		}
	}

	/**
	 * selects the relevence A-Z
	 */
	public void clickRelevanceDropdown() {
		try {
			WaitUtils.waitForElementClickable(driver, relevanceDropdown.get(2));
			;
			relevanceDropdown.get(2).click();
		} catch (Exception e) {
			throw new NoSuchElementException("No elemenet  found", e);
		}
	}

	/**
	 * selects the relevence A-Z
	 */
	public void selectRelevanceAZ() {
		try {
			relevanceA_Z.click();
			WaitUtils.waitForPageToLoad(driver);
		} catch (Exception e) {
			throw new NoSuchElementException("No elemenet  found", e);
		}
	}

	/**
	 * selects the relevence Z-A
	 */
	public void selectRelevanceZA() {
		try {
			relevanceZ_A.click();
			WaitUtils.waitForPageToLoad(driver);
		} catch (Exception e) {
			throw new NoSuchElementException("No elemenet  found", e);
		}
	}

	/**
	 * Retrieves all job titles from the search results.
	 * 
	 * @return List of job titles (String)
	 */
	public List<String> getAllJobTitles() {
		List<String> titles = new ArrayList<>();
		for (WebElement titleElement : jobTitles) {
			titles.add(titleElement.getText().trim());
		}
		return titles;
	}
}
