package com.imejob.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtils;

public class JobSeekerAuthPage {
	WebDriver driver;
	
	@FindBy(xpath = "//div[text() = 'New here?']/child::a")
	WebElement createAccount;
	
	public JobSeekerAuthPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createAccount() {
		By loc = By.xpath("//a[contains(text(),'Create an Account')]");
		WaitUtils.waitForElementVisible(driver, loc );
		createAccount.click();
	}
	
}
