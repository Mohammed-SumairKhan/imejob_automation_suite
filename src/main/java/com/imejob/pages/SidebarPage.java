package com.imejob.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtils;

public class SidebarPage {
	WebDriver driver;
	@FindBy(xpath = "//span[text() = 'Job Search']")
	WebElement jobSearchLink;

	@FindBy(xpath = "//span[text() = 'Interviews/Chat']")
	WebElement interviewLink;

	@FindBy(xpath = "//span[text() = 'Documents']")
	WebElement documentsLink;

	@FindBy(xpath = "//span[text() = 'Applications']")
	WebElement applicationLink;

	@FindBy(xpath = "//span[text() = 'Profile']")
	WebElement profileLink;

	@FindBy(xpath = "//span[text() = 'My Posts']")
	WebElement myPostLink;

	@FindBy(xpath = "//span[text() = 'My Saved Posts']")
	WebElement mySavedJobLink;

	public SidebarPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOn(String name) {
		try {
			switch (name) {
			case "job search":
				
				jobSearchLink.click();
				break;
			case "interviews":
				WaitUtils.waitForElementClickable(driver, interviewLink);
				interviewLink.click();
				break;
			case "documents":
				WaitUtils.waitForElementClickable(driver, documentsLink);
				documentsLink.click();
				break;
			case "applications":
				WaitUtils.waitForElementClickable(driver, applicationLink);
				applicationLink.click();
				break;
			case "profile":
				WaitUtils.waitForElementClickable(driver, profileLink);
				profileLink.click();
				break;
			case "my posts":
				WaitUtils.waitForElementClickable(driver, myPostLink);
				myPostLink.click();
				break;
			case "my saved jobs":
				WaitUtils.waitForElementClickable(driver, mySavedJobLink);
				mySavedJobLink.click();
				break;
			default:
				System.out.println("The given name is not valid: " + name);
				break;
			}
		} catch (Exception e) {
			throw new RuntimeException("Cann't click on: " + name + e);
		}
	}
}
