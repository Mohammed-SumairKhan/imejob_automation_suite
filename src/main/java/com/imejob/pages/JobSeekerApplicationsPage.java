package com.imejob.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtils;

public class JobSeekerApplicationsPage {
	WebDriver driver;

	@FindBy(xpath = "//button[contains(@class, 'btn w-100')]")
	WebElement viewORChat;

	@FindBy(xpath = "//h2[text() = 'Live Chat']")
	WebElement liveChatText;

	public JobSeekerApplicationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOn(String name) {
		WebElement elementToClick = null;
		switch (name.toLowerCase()) {
		case "vieworchat":
			elementToClick = viewORChat;
			break;
		default:
			System.out.println("Invalid name cann't click : " + name);
		}
		elementToClick.click();
	}

	public boolean isLiveChatTextVisible() {
		WaitUtils.waitForElementVisible(driver, liveChatText);
		return liveChatText.isDisplayed();
	}

}
