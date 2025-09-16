package com.imejob.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.WaitUtils;

public class ApplyPage {
	WebDriver driver;
	
	@FindBy(xpath = "//span[text() = 'Job Search']")
	WebElement jobSearch;
	
	@FindBy(xpath = "//div[@class = 'col mb-5 ']")
	List<WebElement> jobCards;
	
	@FindBy(xpath = "//div[contains(@class , 'indicatorContainer')]")
	List<WebElement> dropdown;
	
	
	@FindBy(xpath = "//button[text() = 'Apply for this job']")
	WebElement applyButton;
	
	@FindBy(xpath = "//input[@id = 'questionnaires.0.answer']")
	WebElement handsOnExp;
	
	@FindBy(xpath = "//input[@id = 'questionnaires.1.answer']")
	WebElement handsOnExp2;
	
	@FindBy(xpath = "//div[@class =  ' css-19bb58m']//input[@id = 'react-select-12-input']")
	WebElement notice;
	
	@FindBy(xpath = "//div[@class =  ' css-19bb58m']//input[@id = 'react-select-13-input']")
	WebElement joiner;
	
	@FindBy(xpath = "//div[text() = 'Upload your resume']//input[@type ='file']")
	WebElement uploadResumeInput;
	
	@FindBy(xpath = "//button[text() = 'Send Application']")
	private WebElement sendApplication;
	
	
	
	public ApplyPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickJobSearch() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(jobSearch));
		jobSearch.click();
	}
	
	public void clickApplyButton() {
		jobCards.get(0).click();
		WaitUtils.waitForElementClickable(driver, applyButton);
		applyButton.click();
	}
	
	public void uploadResume(String filePath) {
		uploadResumeInput.sendKeys(filePath); // Send file path
	}
	
	public void questionnaires(String handsOnExpe, String handsOnExpe2, 
			String noticeperiod, String immediatelyAvailble, boolean newResume, 
			String resumePath ) {
		handsOnExp.sendKeys(handsOnExpe, Keys.ENTER);
		handsOnExp2.sendKeys(handsOnExpe2, Keys.ENTER);
		
		notice.sendKeys(noticeperiod, Keys.ENTER);
		joiner.sendKeys(immediatelyAvailble, Keys.ENTER);
		
		if (newResume) {
			uploadResume(resumePath); // Upload new resume if required
		}
		
	}
	
	public void clickSendApplication() {
		try {
			sendApplication.click(); // Click Send Application
		} catch (Exception e) {
			System.out.println("Unable to click Send Application button: " + e.getMessage());
		}
	}
	
	public String getApplicationStatusMessage() {
		By messageLocator = By.xpath("//*[contains(text(),'Hurry!') or contains(text(),'already applied')]");
		WaitUtils.waitForElementVisible(driver, messageLocator); // Wait until message visible
		return driver.findElement(messageLocator).getText(); // Return message text
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
