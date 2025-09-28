package com.imejob.tests.navigation;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.imejob.pages.FooterPage;

import driverproperties.BrowserHandler;
import helper.TestUtils;
import utility.JsonReader;
import utility.PropertiesReader;
import utility.WaitUtils;

public class FooterNavigationTest {
	// 🔹 Class Variables
	WebDriver driver; // WebDriver instance to control the browser
	PropertiesReader propertiesReader; // Reads config values from properties file
	FooterPage footerPage; // Page object for Search functionality
	JsonReader jsonReader; // Utility for reading test data from JSON

	/**
	 * Setup method executed before each test. Initializes WebDriver, loads the test
	 * URL, and prepares page objects.
	 */
	@BeforeMethod
	public void start() {
		propertiesReader = new PropertiesReader(); // load properties
		driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName()); // launch browser
		WaitUtils.implicitWait(driver); // apply implicit wait
		driver.get(propertiesReader.getUrl2()); // navigate to app URL
		jsonReader = new JsonReader();
		jsonReader.loadJson("footerUrl");
		footerPage = new FooterPage(driver); // initialize footerPage object
	}

	@Test(priority = 1)
	public void testFresherTestingQAJobsLink() {
		String expectedUrl = jsonReader.getValue("url", "FresherTestingQAJobs");
		footerPage.clickFresherTestingQAJobs();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 2)
	public void testFresherJavaJobsLink() {
		String expectedUrl = jsonReader.getValue("url", "FresherJavaJobs");
		footerPage.clickFresherJavaJobs();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 3)
	public void testFresherDataAnalystJobsLink() {
		String expectedUrl = jsonReader.getValue("url", "FresherDataAnalystJobs");
		footerPage.clickFresherDataAnalystJobs();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 4)
	public void testHireBestDevelopersLink() {
		String expectedUrl = jsonReader.getValue("url", "HireBestDevelopers");
		footerPage.clickHireBestDevelopers();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 5)
	public void testPricingLink() {
		String expectedUrl = jsonReader.getValue("url", "Pricing");
		footerPage.clickPricing();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 6)
	public void testLinkedinLink() {
		String expectedUrl = jsonReader.getValue("url", "Linkedin");
		footerPage.clickLinkedin();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 7)
	public void testHiringServicesLink() {
		String expectedUrl = jsonReader.getValue("url", "HiringServices");
		footerPage.clickHiringServices();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 8)
	public void testAboutUsLink() {
		String expectedUrl = jsonReader.getValue("url", "AboutUs");
		footerPage.clickAboutUs();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 9)
	public void testImeJobCVAnalysisLink() {
		String expectedUrl = jsonReader.getValue("url", "ImeJobCVAnalysis");
		footerPage.clickImeJobCVAnalysis();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 10)
	public void testBookDemoLink() {
		String expectedUrl = jsonReader.getValue("url", "BookDemo");
		footerPage.clickBookDemo();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 11)
	public void testTermsAndConditionLink() {
		String expectedUrl = jsonReader.getValue("url", "TermsAndCondition");
		footerPage.clickTermsAndCondition();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 12)
	public void testHireSoftwareDevelopersLink() {
		String expectedUrl = jsonReader.getValue("url", "HireSoftwareDevelopers");
		footerPage.clickHireSoftwareDevelopers();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 13)
	public void testJobPostingPortalsLink() {
		String expectedUrl = jsonReader.getValue("url", "JobPostingPortals");
		footerPage.clickJobPostingPortals();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 14)
	public void testRecruitFromSocialMediaLink() {
		String expectedUrl = jsonReader.getValue("url", "RecruitFromSocialMedia");
		footerPage.clickRecruitFromSocialMedia();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 15)
	public void testBestITJobPortalLink() {
		String expectedUrl = jsonReader.getValue("url", "BestITJobPortal");
		footerPage.clickBestITJobPortal();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 16)
	public void testPythonJobsMumbaiLink() {
		String expectedUrl = jsonReader.getValue("url", "PythonJobsMumbai");
		footerPage.clickPythonJobsMumbai();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 17)
	public void testFresherJobsLink() {
		String expectedUrl = jsonReader.getValue("url", "FresherJobs");
		footerPage.clickFresherJobs();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 18)
	public void testReactJsJobsBangaloreLink() {
		String expectedUrl = jsonReader.getValue("url", "ReactJsJobsBangalore");
		footerPage.clickReactJsJobsBangalore();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 19)
	public void testPythonJobsBangaloreLink() {
		String expectedUrl = jsonReader.getValue("url", "PythonJobsBangalore");
		footerPage.clickPythonJobsBangalore();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 20)
	public void testJavaJobsBangaloreLink() {
		String expectedUrl = jsonReader.getValue("url", "JavaJobsBangalore");
		footerPage.clickJavaJobsBangalore();
		TestUtils.verifyFooterLink(driver, expectedUrl);
	}

	@Test(priority = 21)
	public void testAboutLink() {
		String expectedUrl = jsonReader.getValue("url", "About");
		footerPage.clickAbout();
		WaitUtils.waitUntilUrlContains(driver, expectedUrl);
		Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl));
	}

	@Test(priority = 22)
	public void testSitemapLink() {
		String expectedUrl = jsonReader.getValue("url", "Sitemap");
		footerPage.clickSitemap();
		WaitUtils.waitUntilUrlContains(driver, expectedUrl);
		Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl));
	}

	@Test(priority = 23)
	public void testContactLink() {
		String expectedUrl = jsonReader.getValue("url", "Contact");
		footerPage.clickContact();
		WaitUtils.waitUntilUrlContains(driver, expectedUrl);
		Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl));
	}

	@Test(priority = 24)
	public void testTermsLink() {
		String expectedUrl = jsonReader.getValue("url", "Terms");
		footerPage.clickTerms();
		WaitUtils.waitUntilUrlContains(driver, expectedUrl);
		Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl));
	}

	/**
	 * Tear down method executed after each test. Closes the browser instance.
	 */
	@AfterMethod
	public void close() {
		driver.quit(); // close browser
	}
}
