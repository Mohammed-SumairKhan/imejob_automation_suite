package com.imejob.tests.functional;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.imejob.dataprovider.DocumentDataProvider;
import com.imejob.pages.JobSeekerDocumentPage;
import com.imejob.pages.JobSeekerSignInPage;

import driverproperties.BrowserHandler;
import utility.JsonReader;
import utility.PropertiesReader;
import utility.WaitUtils;

public class JobSeekerDocumentTest {
	WebDriver driver; // WebDriver instance to control the browser
	PropertiesReader propertiesReader; // Utility to read properties like URL, browser name
	JobSeekerSignInPage signInPage; // Page object for Job Seeker Sign In page
	JobSeekerDocumentPage documentPage; // Page object for Document
	JsonReader jsonReader; // Utility to read JSON test data

	/**
	 * Setup method to initialize browser, page objects, and login before running
	 * tests
	 */
	@BeforeClass
	public void setup() {
		propertiesReader = new PropertiesReader(); // Initialize PropertiesReader
		driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName()); // Launch browser
		driver.get(propertiesReader.getUrl()); // Navigate to application URL
		WaitUtils.implicitWait(driver); // Apply implicit wait
		jsonReader = new JsonReader(); // Initialize JSON reader

		jsonReader.loadJson("signInData"); // Load JSON data for sign-in
		signInPage = new JobSeekerSignInPage(driver); // Initialize sign-in page object
		documentPage = new JobSeekerDocumentPage(driver); // Initialize document page object

		// Use credentials from PropertiesReader
		String email = jsonReader.getValue("signIn", "email"); // Fetch email
		String password = jsonReader.getValue("signIn", "password"); // Fetch password
		signInPage.signIn(email, password);// Perform login

		WaitUtils.waitForPageToLoad(driver); // Wait for dashboard page to fully load
	}

	/**
	 * Test method to verify that uploading a document without selecting a category
	 * shows the appropriate error message.
	 * 
	 * Steps: 1. Wait until the dashboard URL is loaded. 2. Click on the "Documents"
	 * link in the sidebar. 3. Attempt to upload a document without selecting any
	 * category. 4. Assert that the error/danger message is visible indicating that
	 * a category must be selected.
	 * 
	 * @param category Name of the document category (from data provider, not used
	 *                 in this test)
	 * @param filePath Absolute path of the document file to upload (from data
	 *                 provider)
	 */
	@Test(priority = 1, dataProvider = "documentData", dataProviderClass = DocumentDataProvider.class)
	public void testUploadDocumentWithoutSelectingCategory(String category, String filePath) {

		WaitUtils.waitUntilUrlContains(driver, "dashboard/job-seeker/applications"); // Wait until the dashboard page is
																						// fully loaded and URL contains
																						// expected text

		documentPage.clickOnDocuments(); // Navigate to the Documents page via sidebar

		// Attempt to upload the document without selecting a category
		// This method should handle only sending the file to the input and clicking
		// Upload
		documentPage.uploadDocumentToNoCategory(filePath);

		Assert.assertTrue(documentPage.isDangerTextVisible(), "Please select category"); // Assert is Danger text is
																							// visible or not
	}

	/**
	 * Test method to verify that a Job Seeker can upload a document to a specific
	 * category. Uses data from the DocumentDataProvider (category name and file
	 * path).
	 * 
	 * Steps: 1. Wait until the dashboard URL is loaded. 2. Click on the "Documents"
	 * link in the sidebar. 3. Upload the document to the specified category. 4.
	 * Print the success popup message (for debugging purposes). 5. Assert that the
	 * document upload was successful.
	 * 
	 * @param category Name of the document category (from data provider)
	 * @param filePath Absolute path of the document file to upload (from data
	 *                 provider)
	 */
	@Test(priority = 2,dataProvider = "documentData", dataProviderClass = DocumentDataProvider.class)
	public void testUploadDocumentToCategory(String category, String filePath) {
		WaitUtils.waitUntilUrlContains(driver, "dashboard/job-seeker/applications"); // wait for url contains
		documentPage.clickOnDocuments(); // clicks on document link
		documentPage.uploadDocumentToCategory(category, filePath); // Perform document upload: select category, upload
																	// file, click Upload button

		Assert.assertTrue(documentPage.isUploadSuccessful(), "Document upload failed!");// Assert the popup message
	}

	/**
	 * Tear down method to quit browser after all tests
	 */
	@AfterClass
	public void tearDown() {
		driver.quit(); // Close browser
	}
}
