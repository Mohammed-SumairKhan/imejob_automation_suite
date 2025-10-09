package com.imejob.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtils;

/**
 * Page Object class for Job Seeker Document Page in IMEJob. This class contains
 * web elements and actions related to navigating the Documents section,
 * selecting document categories, uploading files, and verifying uploaded
 * documents.
 */
public class JobSeekerDocumentPage {

	WebDriver driver;

	@FindBy(xpath = "//span[text()='Documents']")
	WebElement documentsLink; // Link to navigate to Documents page

	@FindBy(xpath = "//div[@class=' css-1xc3v61-indicatorContainer']")
	WebElement dropDown; // Dropdown to select document category

	@FindBy(xpath = "//input[@class= 'd-none']")
	WebElement uploadDocument; // Input element to upload document

	@FindBy(xpath = "//button[text()='Upload']")
	WebElement uploadButton; // Button to confirm document upload

	@FindBy(xpath = "//*[contains(text(),'Document Uploaded')]")
	WebElement uploadSuccessPopup; // element to check popUp message

	@FindBy(xpath = "//div[contains(@class, 'text-danger')]")
	WebElement textDanger; // element to check for the text

	/**
	 * This element appears only after the document is successfully uploaded. We use
	 * it to wait and verify that the upload is complete before clicking upload
	 * button.
	 */
	@FindBy(xpath = "//div[@class='text-muted ms-2']")
	WebElement verifyTheDocument; // Element to verify document upload

	/**
	 * Constructor initializes the WebElements using PageFactory.
	 * 
	 * @param driver WebDriver instance from the test
	 */
	public JobSeekerDocumentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Clicks on the Documents link in the sidebar to navigate to Documents page.
	 */
	public void clickOnDocuments() {
		try {
			documentsLink.click();
		} catch (Exception e) {
			throw new RuntimeException("Cannot click on Documents link: " + e);
		}
	}

	/**
	 * Returns a dynamic WebElement for a given document category.
	 * 
	 * @param category Name of the document category
	 * @return WebElement representing the category
	 */
	public WebElement getCertificateCategory(String category) {
		return driver.findElement(By.xpath("//div[text()='" + category + "']"));
	}

	/**
	 * Uploads a document file to the input element.
	 * 
	 * @param filePath Absolute path of the file to upload
	 */
	public void uploadDocument(String filePath) {
		uploadDocument.sendKeys(filePath);
	}

	/**
	 * Clicks on the Upload button after waiting for the document to be fully
	 * uploaded.
	 */
	public void clickUploadButton() {
		try {
			WaitUtils.waitForElementVisible(driver, verifyTheDocument);
			uploadButton.click();
		} catch (Exception e) {
			throw new RuntimeException("Cannot click on Upload button: " + e);
		}
	}

	/**
	 * Combined method to select a category, upload a document, and click the Upload
	 * button in a single step.
	 * 
	 * @param category Name of the document category to select
	 * @param filePath Absolute path of the file to upload
	 */
	public void uploadDocumentToCategory(String category, String filePath) {
		try {
			WaitUtils.waitForElementClickable(driver, dropDown);
			dropDown.click(); // Open category drop down
			getCertificateCategory(category).click(); // Select category dynamically
			uploadDocument.sendKeys(filePath); // Upload the document
			WaitUtils.waitForElementVisible(driver, verifyTheDocument); // Wait for upload
			uploadButton.click(); // Click Upload
		} catch (Exception e) {
			throw new RuntimeException("Failed to upload document: " + e);
		}
	}

	/**
	 * Checks if the upload success popup is visible and returns its text.
	 * 
	 * @return The popup text if visible, otherwise null.
	 */
	public String getUploadSuccessMessage() {
		try {
			WaitUtils.waitForElementVisible(driver, uploadSuccessPopup);
			return uploadSuccessPopup.getText().trim();
		} catch (Exception e) {
			return null; // Return null if popup not visible
		}
	}

	/**
	 * Returns true if document uploaded successfully (popup appeared with correct
	 * message)
	 */
	public boolean isUploadSuccessful() {
		String message = getUploadSuccessMessage();
		return message != null && message.toLowerCase().contains("document uploaded successfully");
	}

	/**
	 * Attempts to upload a document **without selecting any category**. This method
	 * is used for negative testing to verify that the system prevents uploads when
	 * no category is chosen.
	 *
	 * @param filePath Absolute path of the document file to upload
	 */
	public void uploadDocumentToNoCategory(String filePath) {
		try {
			// Note: No category is selected intentionally
			uploadDocument.sendKeys(filePath); // Upload the document file to the input element
			WaitUtils.waitForElementVisible(driver, verifyTheDocument);// This ensures that the upload attempt has been
																		// registered by the UI
			uploadButton.click(); // Click the Upload button to attempt uploading the document
		} catch (Exception e) {
			throw new RuntimeException("Failed to upload document: " + e); // Throw a runtime exception if any step
																			// fails
		}
	}

	/**
	 * Checks whether the danger/error message is visible on the Documents page.
	 * This is used to verify that the user is prompted to select a category before
	 * uploading a document.
	 *
	 * @return true if the danger text is displayed; false otherwise
	 */
	public boolean isDangerTextVisible() {
		WaitUtils.waitForElementVisible(driver, textDanger); // Wait for the visibility of the element
		return textDanger.isDisplayed();// Return whether the danger text is actually displayed
	}

}
