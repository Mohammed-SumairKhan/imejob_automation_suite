package com.imejob.tests.functional;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.imejob.pages.ApplyPage;
import com.imejob.pages.JobCardsPage;
import com.imejob.pages.JobSeekerSignInPage;
import com.imejob.pages.SearchPage;

import driverproperties.BrowserHandler;
import helper.SignInDataProvider;
import utility.JsonReader;
import utility.PropertiesReader;
import utility.WaitUtils;

public class ApplyJob {
	WebDriver driver; // WebDriver instance to control the browser
	JobCardsPage jobCardsPage; // Page Object for job cards on Home Page
	PropertiesReader propertiesReader; // Utility to read configuration (browser, URL, etc.)
	JsonReader jsonReader; // Utility to read test data from JSON files
	JobSeekerSignInPage jobSeekerSignInPage;
	ApplyPage applyPage;
	SearchPage searchPage;
	JsonReader jsonReader2;

	@BeforeMethod
	public void setUp() {
		propertiesReader = new PropertiesReader(); // Load configuration properties
		driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName()); // Launch browser
		driver.get(propertiesReader.getUrl()); // Navigate to base URL
		jobCardsPage = new JobCardsPage(driver); // Initialize Page Object for job cards
		jsonReader = new JsonReader(); // Initialize JSON reader
		WaitUtils.implicitWait(driver); // Apply implicit wait for element loading
		jsonReader.loadJson("searchTestData"); // Load default job cards data from JSON
		jobSeekerSignInPage = new JobSeekerSignInPage(driver);
		applyPage = new ApplyPage(driver);
		searchPage = new SearchPage(driver);

		jsonReader2 = new JsonReader();
		jsonReader2.loadJson("applyJob");
	}

	@DataProvider(name = "signInData")
	public Object[][] signInData() {
		return SignInDataProvider.getSingInData();
	}

	@Test(priority = 1, dataProvider = "signInData")
	public void signInTest(String email, String password) {
		jobSeekerSignInPage.signIn(email, password);
		jobSeekerSignInPage.clickSignIn();

		WaitUtils.applyHardWait();

		String parentWindow = driver.getWindowHandle();
		applyPage.clickJobSearch();

		// switch to the new tab...
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		WaitUtils.waitUntilUrlContains(driver, "job-search");

		JsonNode testData = jsonReader.getJsonNode("positiveTests", "skillAndLocation");
		searchPage.searchBySkill(testData.get("skill").asText());
		searchPage.searchByLocation(testData.get("location").asText());
		searchPage.clickSearchButton();

		applyPage.clickApplyButton();

		String exp = jsonReader2.getValue("Questionnaires", "HandOnexeperienceOnPrimarySkill");
		String exp2 = jsonReader2.getValue("Questionnaires", "HandOnexeperienceOnFrameWork");
		String noticePeriod = jsonReader2.getValue("Questionnaires", "Notice Period less than 30 days");
		String immediateJoiner = jsonReader2.getValue("Questionnaires", "Can you join immediately");
		boolean newResume = Boolean.parseBoolean(jsonReader2.getValue("Questionnaires", "newResume"));
		String resumePath = jsonReader2.getValue("Questionnaires", "resume");

		applyPage.questionnaires(exp, exp2, noticePeriod, immediateJoiner, newResume, resumePath);
		applyPage.clickSendApplication();
	

	String applicationMessage = jobCardsPage.getApplicationStatusMessage(); // Get application response message

	// Assert based on the message content
	if(applicationMessage.contains("Hurry!"))
	{
		Assert.assertTrue(applicationMessage.contains("Hurry!"), "New application success message is displayed");
	}else if(applicationMessage.contains("already applied"))
	{
		Assert.assertTrue(applicationMessage.contains("already applied"), "Already applied message is displayed");
	}else
	{
		Assert.fail("Unexpected message displayed: " + applicationMessage);
	}
}
	@AfterMethod
	public void tearDown() {
		if (driver != null) { // Check if WebDriver is initialized
			driver.quit(); // Close browser and cleanup
		}
	}

}
