package com.imejob.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtils;

/**
 * Page Object class representing the Job Cards section on the Home Page.
 * Contains methods to interact with job cards, fill personal information,
 * select dropdowns/skills, upload resumes, and submit job applications.
 */
public class JobCardsPage {

	private WebDriver driver; // WebDriver instance for browser interaction

	// -------------------- WebElements --------------------
	@FindBy(xpath = "//div[@class ='col mb-5 ']")
	private List<WebElement> jobCards; // List of all job cards

	@FindBy(xpath = "//div[contains(@class, 'Jobcard_jobTitle__LUngf')]")
	private List<WebElement> jobCardsText; // Job card title elements

	@FindBy(xpath = "//button[text() = 'Apply for this job']")
	private WebElement applyButton; // Apply button on job card

	@FindBy(xpath = "//button[text() = 'Login']")
	private WebElement loginButton; // Login button

	@FindBy(xpath = "//input[@name = 'firstName']")
	private WebElement firstName; // First name input field

	@FindBy(xpath = "//input[@name = 'middleName']")
	private WebElement middleName; // Middle name input field

	@FindBy(xpath = "//input[@name = 'lastName']")
	private WebElement lastName; // Last name input field

	@FindBy(xpath = "//input[@name = 'email']")
	private WebElement emailAddress; // Email input field

	@FindBy(xpath = "//input[@name = 'phone']")
	private WebElement phoneNumber; // Phone input field

	@FindBy(xpath = "//div[@class =' css-hlgwow']//input")
	private List<WebElement> personalInputs; // Dropdowns: experience, location, notice period, expected CTC

	@FindBy(xpath = "//button[text() = 'Continue']")
	private WebElement continueButton; // Continue button in application form

	@FindBy(xpath = "//div[text() = 'Upload your resume']//input[@type ='file']")
	private WebElement uploadResumeInput; // Resume file input

	@FindBy(xpath = "//button[text() = 'Send Application']")
	private WebElement sendApplication; // Send Application button

	// -------------------- Constructor --------------------
	public JobCardsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize PageFactory elements
	}

	// -------------------- Job Card Actions --------------------

	/**
	 * Get the total number of job cards displayed on the home page.
	 * @return count
	 */
	public int getDefaultJobCardsCount() {
		try {
			return jobCards.size();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Get the text of all job cards.
	 * @return list
	 */
	public List<String> getAllJobCardsText() {
		List<String> texts = new ArrayList<>();
		for (WebElement card : jobCards) {
			texts.add(card.getText());
		}
		return texts;
	}

	/**
	 * Get the text of a job card by index.
	 * @return jobCardByIndex
	 */
	public String getJobCardTextByIndex(int index) {
		try {
			return jobCards.get(index).getText();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Click the first job card safely.
	 */
	public void clickFirstCard() {
		try {
			jobCards.get(0).click(); // Click the first job card
		} catch (Exception e) {
			System.out.println("Unable to click first job card: " + e.getMessage());
		}
	}

	/**
	 * Click the last job card safely.
	 */
	public void clickLastCard() {
		try {
			jobCards.get(jobCards.size() - 1).click(); // Click the last job card
		} catch (Exception e) {
			System.out.println("Unable to click last job card: " + e.getMessage());
		}
	}

	/**
	 * Get text of the first job card.
	 * @return firstCard
	 */
	public String getFirstCardText() {
		if (getDefaultJobCardsCount() > 0) {
			return getJobCardTextByIndex(0).trim(); // Return trimmed text
		}
		return null;
	}

	/**
	 * Get text of the last job card.
	 * @return last card
	 */
	public String getLastCardText() {
		int count = getDefaultJobCardsCount();
		if (count > 0) {
			return getJobCardTextByIndex(count - 1).trim();
		}
		return null;
	}

	/**
	 * Click on Apply button.
	 */
	public void clickOnApply() {
		try {
			WaitUtils.waitForElementClickable(driver, applyButton); // Wait until clickable
			applyButton.click(); // Click Apply
		} catch (Exception e) {
			throw new RuntimeException("Failed to click on Apply button", e);
		}
	}

	/**
	 * Click on Login button.
	 */
	public void clickOnLogin() {
		try {
			WaitUtils.waitForElementClickable(driver, loginButton);
			loginButton.click(); // Click Login
		} catch (Exception e) {
			throw new RuntimeException("Failed to click on Login button", e);
		}
	}

	// -------------------- Form Filling --------------------

	/**
	 * Fill the personal information fields.
	 */
	public void fillPersonalDetails(String fName, String mName, String lName, String email, String phone) {
		firstName.sendKeys(fName); // Enter first name
		middleName.sendKeys(mName); // Enter middle name
		lastName.sendKeys(lName); // Enter last name
		emailAddress.sendKeys(email); // Enter email
		phoneNumber.sendKeys(phone); // Enter phone number
	}

	/**
	 * Select a value from dropdown by index.
	 */
	public void selectDropdownValue(int index, String value) {
		By dropdownsLocator = By.xpath("//div[contains(@class , 'indicatorContainer')]");
		WaitUtils.waitForAllElementsVisible(driver, dropdownsLocator);

		List<WebElement> dropdownArrows = driver.findElements(dropdownsLocator);

		if (dropdownArrows.isEmpty() || index >= dropdownArrows.size()) {
			throw new RuntimeException("Dropdown arrow not found at index: " + index);
		}

		dropdownArrows.get(index).click(); // Open dropdown
		By option = By.xpath("//div[text()='" + value + "']");
		WaitUtils.waitForElementVisible(driver, option);
		driver.findElement(option).click(); // Select option
	}

	/**
	 * Select multiple skills from the skills dropdown.
	 */
	public void selectSkills(List<String> skills) {
		By dropdownsLocator = By.xpath("//div[contains(@class , 'indicatorContainer')]");
		WaitUtils.waitForAllElementsVisible(driver, dropdownsLocator);

		List<WebElement> dropdownArrows = driver.findElements(dropdownsLocator);
		if (dropdownArrows.size() <= 3) {
			throw new RuntimeException("Skills dropdown arrow not found at index 4");
		}

		for (String skill : skills) {
			dropdownArrows.get(4).click(); // Open skills dropdown
			By option = By.xpath("//div[text()='" + skill + "']");
			WaitUtils.waitForElementVisible(driver, option);
			driver.findElement(option).click(); // Select skill
		}
	}

	/**
	 * Upload a resume file.
	 */
	public void uploadResume(String filePath) {
		uploadResumeInput.sendKeys(filePath); // Send file path
	}

	// -------------------- Job Application --------------------

	/**
	 * Fill form and apply for the job.
	 */
	public void applyJob(String firstName, String middleName, String lastName, String email, String phone,
			String experience, String location, String noticePeriod, String expectedCTC, List<String> skills,
			boolean newResume, String resumePath) {

		fillPersonalDetails(firstName, middleName, lastName, email, phone); // Fill personal info
		selectDropdownValue(0, experience); // Select experience
		selectDropdownValue(1, location); // Select location
		selectDropdownValue(2, noticePeriod); // Select notice period
		selectDropdownValue(3, expectedCTC); // Select expected CTC
		selectSkills(skills); // Select skills

		try {
			continueButton.click(); // Click Continue button
		} catch (Exception e) {
			System.out.println("Unable to click Continue button: " + e.getMessage());
		}

		if (newResume) {
			uploadResume(resumePath); // Upload new resume if required
		}
	}

	/**
	 * Click on Send Application button safely.
	 */
	public void clickSendApplication() {
		try {
			sendApplication.click(); // Click Send Application
		} catch (Exception e) {
			System.out.println("Unable to click Send Application button: " + e.getMessage());
		}
	}

	// -------------------- Utility --------------------

	/**
	 * Get the application status message after sending application.
	 * @return messgae
	 */
	public String getApplicationStatusMessage() {
		By messageLocator = By.xpath("//*[contains(text(),'Hurry!') or contains(text(),'already applied')]");
		WaitUtils.waitForElementVisible(driver, messageLocator); // Wait until message visible
		return driver.findElement(messageLocator).getText(); // Return message text
	}
}
