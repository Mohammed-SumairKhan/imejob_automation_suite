package com.imejob.tests.functional;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.imejob.pages.HeaderNavigationPage;
import com.imejob.pages.JobSeekerAuthPage;

import driverproperties.BrowserHandler;
import utility.JsonReader;
import utility.PropertiesReader;
import utility.WaitUtils;

/**
 * Functional test class for validating the Job Seeker account creation flow.
 * 
 * <p>
 * This class covers navigation to the create account page and the full account
 * registration process using data-driven testing.
 * </p>
 */
public class JobSeekerAuthTest {
	WebDriver driver;
	PropertiesReader propertiesReader;
	HeaderNavigationPage headerNavigationPage;
	JobSeekerAuthPage jobSeekerAuthPage;
	JsonReader jsonReader;

	/**
	 * Setup method that runs before each test.
	 * 
	 * <p>
	 * Initializes browser, applies waits, loads application URL, initializes Page
	 * Objects, and loads test data from JSON.
	 * </p>
	 */
	@BeforeMethod
	public void start() {
		propertiesReader = new PropertiesReader(); // initialize properties reader
		driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName()); // launch browser
		WaitUtils.implicitWait(driver); // apply implicit wait
		driver.get(propertiesReader.getUrl()); // open application URL

		// initialize page objects
		headerNavigationPage = new HeaderNavigationPage(driver);
		jobSeekerAuthPage = new JobSeekerAuthPage(driver);

		// load JSON test data
		jsonReader = new JsonReader();
		jsonReader.loadJson("createAccountData");
	}

	/**
	 * Test to validate navigation to the Job Seeker account creation page.
	 */
	@Test(priority = 1)
	public void navigateToCreateAccountTest() {
		headerNavigationPage.clickJobSeeker(); // click on the job seeker button
		jobSeekerAuthPage.clickCreateAccount(); // click on create account button
		// validate that user is redirected to job seeker account creation page
		Assert.assertTrue(driver.getCurrentUrl().contains("requestType=jobSeeker"),
				"Navigation to Job Seeker account creation page failed!");
	}

	/**
	 * Data-driven test for creating a new Job Seeker account.
	 * 
	 * @param firstName       - user's first name
	 * @param middleName      - user's middle name
	 * @param lastName        - user's last name
	 * @param email           - user's email address
	 * @param phone           - user's phone number
	 * @param totalExperience - total work experience
	 * @param location        - job location preference
	 * @param password        - account password
	 * @param gender          - user's gender
	 * @param skillsArray     - array of skills
	 * @param resumePath      - file path of the resume to upload
	 * @param agreeTerms      - checkbox flag for terms agreement
	 */
	@Test(priority = 2, dependsOnMethods = "navigateToCreateAccountTest", dataProvider = "getCreateAccountData", dataProviderClass = com.imejob.dataprovider.CreateAccountJsonDataProvider.class)
	public void createAccountTest(String firstName, String middleName, String lastName, String email, String phone,
			String totalExperience, String location, String password, String gender, String[] skillsArray,
			String resumePath, boolean agreeTerms) {

		List<String> skills = Arrays.asList(skillsArray); // convert skills array to list

		headerNavigationPage.clickJobSeeker(); // click on job seeker
		jobSeekerAuthPage.clickCreateAccount(); // click on create account link

		// fill the account form using POM
		jobSeekerAuthPage.createAccount(firstName, middleName, lastName, email, phone, password, totalExperience,
				location, gender, skills, resumePath, agreeTerms);

		jobSeekerAuthPage.clickRegister(); // click register button

		String message = jobSeekerAuthPage.getRegisterStatusMessage(); // stores the status
		if (message != null) {
			System.out.println("Register outcome: " + message);
			Assert.assertTrue(message.contains("Email address") || message.contains("Mobile number"));
		} else {
			jobSeekerAuthPage.enterOTP(jsonReader.getValue("createAccount", "otp")); // sends 1,2,3,4 to the 4 separate
																						// boxes
			jobSeekerAuthPage.clickVerifyAndProceed();
			String exp_url = "https://client.imejob.com/dashboard/job-seeker/applications";
			WaitUtils.waitUntilUrlContains(driver, exp_url);
			Assert.assertEquals(exp_url, driver.getCurrentUrl());
		}

	}

	/**
	 * Tear down method that runs after each test.
	 * 
	 * <p>
	 * Closes the browser after test execution.
	 * </p>
	 */
	@AfterMethod
	public void close() {
		driver.quit(); // close browser after test execution
	}
}
