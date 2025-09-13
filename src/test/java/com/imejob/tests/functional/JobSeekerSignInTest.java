package com.imejob.tests.functional;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.imejob.pages.JobSeekerSignInPage;

import driverproperties.BrowserHandler;
import helper.SignInDataProvider;
import utility.PropertiesReader;
import utility.WaitUtils;

public class JobSeekerSignInTest {
	WebDriver driver;
	PropertiesReader propertiesReader;
	
	JobSeekerSignInPage jobSeekerSignInPage;
	@BeforeMethod
	public void start() {
		propertiesReader = new PropertiesReader(); // initialize properties reader
		driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName()); // launch browser
		WaitUtils.implicitWait(driver); // apply implicit wait
		driver.get(propertiesReader.getUrl()); // open application URL

		// initialize page objects
		jobSeekerSignInPage = new JobSeekerSignInPage(driver);
	}
	
	@DataProvider(name = "signInData")
	public Object[][] signInData() {
		return SignInDataProvider.getSingInData();
	}

	@Test(priority = 1, dataProvider = "signInData")
	public void signInTest(String email, String password) {
		jobSeekerSignInPage.signIn(email, password);
		
		jobSeekerSignInPage.clickSignIn();
		
		WaitUtils.waitUntilUrlContains(driver, "dashboard");
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));;
	}
	
	@Test(priority = 2)
	public void forgetPassNavigationTest() {
		jobSeekerSignInPage.clickForgetPassword();
		WaitUtils.waitUntilUrlContains(driver, "auth/forgot-password?");
		Assert.assertTrue(driver.getCurrentUrl().contains("auth/forgot-password?"));
	}
	
	@Test(priority = 3)
	public void loginNavigationTest() {
		jobSeekerSignInPage.clickForgetPassword();
		jobSeekerSignInPage.clickLogin();
		WaitUtils.waitUntilUrlContains(driver, "auth/login?");
		Assert.assertTrue(driver.getCurrentUrl().contains("auth/login?"));
	}
	
	@AfterMethod
	public void close() {
		driver.quit();
	}
}
