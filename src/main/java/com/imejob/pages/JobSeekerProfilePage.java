package com.imejob.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JobSeekerProfilePage {
	WebDriver driver;
	
	@FindBy(xpath = "//span[text() = 'Profile']")
	WebElement profileLink;
	
	@FindBy(xpath = "//div[text() = 'Edit Profile']")
	WebElement editProfileButton;
	
	@FindBy(xpath = "//button[text() = 'Update Changes']")
	WebElement updateButton;
	
	@FindBy(xpath = "//div[contains(text() , 'updated successfully!')]")
	WebElement popUpText;
	
	public JobSeekerProfilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnProfile() {
		try {
			profileLink.click();
		} catch(Exception e) {
			throw new RuntimeException("cann't click on Profile: " + e);
		}
	}
	
	public void clickOnEditProfile() {
		try {
			editProfileButton.click();
		} catch(Exception e) {
			throw new RuntimeException("cann't click on Edit Profile: " + e);
		}
	}
	
	public void clickOnUpdate() {
		try {
			updateButton.click();
		} catch(Exception e) {
			throw new RuntimeException("cann't click on update button: " + e);
		}
	}
	
	public boolean isPopTextDisplayed() {
		return popUpText.isDisplayed();
	}
	
}
