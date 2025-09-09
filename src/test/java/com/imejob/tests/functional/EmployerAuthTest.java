package com.imejob.tests.functional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.imejob.pages.EmployerAuthPage;

import driverproperties.BrowserHandler;
import utility.PropertiesReader;
import utility.WaitUtils;

public class EmployerAuthTest {
	 WebDriver driver;
	 EmployerAuthPage employerAuthPage;
	 PropertiesReader propertiesReader;

	    @BeforeMethod
	    public void setUp() {
	    	propertiesReader = new PropertiesReader();
	    	driver = BrowserHandler.getBrowser(propertiesReader.getBrowserName());
	    	driver.get(propertiesReader.getUrl());
	        employerAuthPage = new EmployerAuthPage(driver);
	        WaitUtils.implicitWait(driver);
	    }

	    @Test(priority = 1)
	    public void verifyNavigateToCreateAccount() {
	        employerAuthPage.navigateToCreateAccount();
	        // Example: check if URL or page title contains "register"
	        Assert.assertTrue(driver.getCurrentUrl().contains("register") 
	                          || driver.getTitle().contains("Create Account"),
	                          "Create Account page not opened!");
	    }

	    @Test(priority = 2)
	    public void verifyNavigateToSignIn() {
	        employerAuthPage.navigateToSignIn();
	        // Example: check if URL or page title contains "login" or "sign-in"
	        Assert.assertTrue(driver.getCurrentUrl().contains("login") 
	                          || driver.getTitle().contains("Sign In"),
	                          "Sign In page not opened!");
	    }

	    @Test(priority = 3)
	    public void verifyNavigateToForgotPassword() {
	        employerAuthPage.navigateToForgotPassword();
	        // Example: check if URL or page title contains "forgot"
	        Assert.assertTrue(driver.getCurrentUrl().contains("forgot") 
	                          || driver.getTitle().contains("Forgot"),
	                          "Forgot Password page not opened!");
	    }

	    @Test(priority = 4)
	    public void verifyNavigateToLogin() {
	        employerAuthPage.navigateToLogin();
	        // Example: check if URL or page title contains "login"
	        Assert.assertTrue(driver.getCurrentUrl().contains("login") 
	                          || driver.getTitle().contains("Login"),
	                          "Login page not opened!");
	    }

	    @AfterMethod
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}
