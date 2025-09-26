package com.imejob.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtils;

public class BookDemoPage {
	WebDriver driver;
	
	@FindBy(xpath = "//button[text() = 'Book a demo']")
	WebElement bookDemo;
	
	@FindBy(name = "email")
	WebElement emailInput;
	
	@FindBy(name = "phoneNumber")
	WebElement phoneNumberInput;
	
	@FindBy(name = "firstName")
	WebElement firstNameInput;
	
	@FindBy(name = "lastName")
	WebElement lastNameInput;
	
	@FindBy(name = "message")
	WebElement messageInput;
	
	@FindBy(xpath = "//button[text() = 'Submit']")
	WebElement submitButton;
	
	@FindBy(xpath = "//div[contains(@class, 'indicatorContainer')]")
	WebElement dropDown;
	
	public BookDemoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickBookDemo() {
		try {
			bookDemo.click();
		} catch(Exception e) {
			throw new RuntimeException("cann't click the book demo button " + e);
		}
	}
	
	public void clickSubmitButton() {
		try {
			submitButton.click();
		} catch(Exception e) {
			throw new RuntimeException("cann't click the submit button " + e);
		}
	}
	public void fillContactForm(String email, String phoneNumber, String firstName, String lastName,
			String subject, String message) {
		emailInput.sendKeys(email);
		phoneNumberInput.sendKeys(phoneNumber);
		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		selectSubject(subject);
		messageInput.sendKeys(message);
	}
	
	public void selectSubject(String subject) {
		
		dropDown.click();
		//div[text() = 'Looking for Dedicated HR Services']
	
		 WebElement options= driver.findElement(By.xpath("//div[text() = '"+subject+"']"));
		WaitUtils.waitForElementClickable(driver, options);
		 options.click();
		  
	}
 }








