package com.imejob.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterPage {

	WebDriver driver;

	// ---------- Footer Links (only clickable) ----------
	@FindBy(xpath = "//a[text() ='Fresher Testing QA jobs']")
	private WebElement fresherTestingQAJobs;

	@FindBy(xpath = "//a[text() ='Fresher java jobs']")
	private WebElement fresherJavaJobs;

	@FindBy(xpath = "//a[text() ='Fresher data analyst jobs ']")
	private WebElement fresherDataAnalystJobs;

	@FindBy(xpath = "//a[text() ='Hire the Best Developers in IT']")
	private WebElement hireBestDevelopers;

	@FindBy(xpath = "//a[@href ='https://imejob.com/pricing']")
	private WebElement pricing;

	@FindBy(xpath = "//a[text()='Linkedin ']")
	private WebElement linkedin;

	@FindBy(xpath = "//a[text()='Hiring Services ']")
	private WebElement hiringServices;

	@FindBy(xpath = "//a[@href='https://imejob.com/about-us']")
	private WebElement aboutUs;

	@FindBy(xpath = "//a[text()='Facebook']")
	private WebElement facebook;

	@FindBy(xpath = "//a[text()='IMEJOBCV Analysis ']")
	private WebElement imeJobCVAnalysis;

	@FindBy(xpath = "//a[text()='Book Demo']")
	private WebElement bookDemo;

	@FindBy(xpath = "//a[text()='Terms and Condition ']")
	private WebElement termsAndCondition;

	@FindBy(xpath = "//a[text()='Hire Software Developers at low cost']")
	private WebElement hireSoftwareDevelopers;

	@FindBy(xpath = "//a[text()='5 top free Job Posting Portals']")
	private WebElement jobPostingPortals;

	@FindBy(xpath = "//a[text()='How to Recruit from Social Media']")
	private WebElement recruitFromSocialMedia;

	@FindBy(xpath = "//a[text()='Best IT Job portal in India']")
	private WebElement bestITJobPortal;

	@FindBy(xpath = "//a[text()='Python Jobs in Mumbai']")
	private WebElement pythonJobsMumbai;

	@FindBy(xpath = "//a[text()='Fresher jobs']")
	private WebElement fresherJobs;

	@FindBy(xpath = "//a[text()='react js jobs in bangalore']")
	private WebElement reactJsJobsBangalore;

	@FindBy(xpath = "//a[text()='Python jobs in banglore']")
	private WebElement pythonJobsBangalore;

	@FindBy(xpath = "//a[text()='Java Jobs in Bangalore ']")
	private WebElement javaJobsBangalore;

	@FindBy(xpath = "//a[text()='About']")
	private WebElement about;

	@FindBy(xpath = "//a[text()='Sitemap']")
	private WebElement sitemap;

	@FindBy(xpath = "//a[text()='Contact']")
	private WebElement contact;

	@FindBy(xpath = "//a[text()='Terms']")
	private WebElement terms;

	// ---------- Constructor ----------
	public FooterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ---------- Generic Click ----------
	private void clickElement(WebElement element, String elementName) {
		try {
		//	WaitUtils.waitForElementClickable(driver, element);
			element.click();
			System.out.println("Clicked Element:" + elementName);
		} catch (Exception e) {
			throw new RuntimeException("Failed to click on: " + elementName, e);
		}
	}

	// ---------- Footer Actions ----------
	public void clickFresherTestingQAJobs() {
		clickElement(fresherTestingQAJobs, "Fresher Testing QA Jobs");
	}

	public void clickFresherJavaJobs() {
		clickElement(fresherJavaJobs, "Fresher Java Jobs");
	}

	public void clickFresherDataAnalystJobs() {
		clickElement(fresherDataAnalystJobs, "Fresher Data Analyst Jobs");
	}

	public void clickHireBestDevelopers() {
		clickElement(hireBestDevelopers, "Hire Best Developers");
	}

	public void clickPricing() {
		clickElement(pricing, "Pricing");
	}

	public void clickLinkedin() {
		clickElement(linkedin, "LinkedIn");
	}

	public void clickHiringServices() {
		clickElement(hiringServices, "Hiring Services");
	}

	public void clickAboutUs() {
		clickElement(aboutUs, "About Us");
	}

	public void clickFacebook() {
		clickElement(facebook, "Facebook");
	}

	public void clickImeJobCVAnalysis() {
		clickElement(imeJobCVAnalysis, "IMEJOB CV Analysis");
	}

	public void clickBookDemo() {
		clickElement(bookDemo, "Book Demo");
	}

	public void clickTermsAndCondition() {
		clickElement(termsAndCondition, "Terms and Condition");
	}

	public void clickHireSoftwareDevelopers() {
		clickElement(hireSoftwareDevelopers, "Hire Software Developers");
	}

	public void clickJobPostingPortals() {
		clickElement(jobPostingPortals, "Job Posting Portals");
	}

	public void clickRecruitFromSocialMedia() {
		clickElement(recruitFromSocialMedia, "Recruit from Social Media");
	}

	public void clickBestITJobPortal() {
		clickElement(bestITJobPortal, "Best IT Job Portal");
	}

	public void clickPythonJobsMumbai() {
		clickElement(pythonJobsMumbai, "Python Jobs Mumbai");
	}

	public void clickFresherJobs() {
		clickElement(fresherJobs, "Fresher Jobs");
	}

	public void clickReactJsJobsBangalore() {
		clickElement(reactJsJobsBangalore, "React JS Jobs Bangalore");
	}

	public void clickPythonJobsBangalore() {
		clickElement(pythonJobsBangalore, "Python Jobs Bangalore");
	}

	public void clickJavaJobsBangalore() {
		clickElement(javaJobsBangalore, "Java Jobs Bangalore");
	}

	public void clickAbout() {
		clickElement(about, "About");
	}

	public void clickSitemap() {
		clickElement(sitemap, "Sitemap");
	}

	public void clickContact() {
		clickElement(contact, "Contact");
	}

	public void clickTerms() {
		clickElement(terms, "Terms");
	}
}
