package com.imejob.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * HeaderNavigationPage class represents the header section of the application.
 * It contains web elements for navigation links and methods to interact with them.
 *
 * Uses PageFactory to initialize web elements.
 */
public class HeaderNavigationPage {

    WebDriver driver;

    // Locator for the brand logo that links to the homepage
    @FindBy(css = "a[href='/'] img[alt='brand-logo']")
    WebElement brandLogo;

    // Locator for "Job Search" navigation link
    @FindBy(xpath = "//a[text() = 'Job Search']")
    WebElement jobSearch;

    // Locator for "About Us" navigation link
    @FindBy(xpath = "//a[text() = 'About Us']")
    WebElement aboutUs;

    // Locator for "Contact Us" navigation link
    @FindBy(xpath = "//a[text() = 'Contact Us']")
    WebElement contactUs;

    // Locator for "Join Community" navigation link
    @FindBy(xpath = "//a[text() = 'Join community']")
    WebElement joinCommunity;

    // Locator for "Blogs" navigation link
    @FindBy(xpath = "//a[text() = 'Blogs']")
    WebElement blogs;

    // Locator for "Pricing" navigation link inside menu item
    @FindBy(xpath = "//div[@class='menu-item']//a[text() = 'Pricing']")
    WebElement pricing;

    /**
     * Constructor to initialize the HeaderNavigationPage object.
     * Initializes the web elements using PageFactory.
     *
     * @param driver WebDriver instance passed from the test class
     */
    public HeaderNavigationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks the brand logo.
     * Usually navigates to the homepage.
     */
    public void clickBrandLogo() {
        brandLogo.click();
    }

    /**
     * Clicks the "Job Search" link in the header.
     */
    public void clickJobSearch() {
        jobSearch.click();
    }

    /**
     * Clicks the "About Us" link in the header.
     */
    public void clickAboutUs() {
        aboutUs.click();
    }

    /**
     * Clicks the "Contact Us" link in the header.
     */
    public void clickContactUs() {
        contactUs.click();
    }

    /**
     * Clicks the "Join Community" link in the header.
     */
    public void clickJoinCommunity() {
        joinCommunity.click();
    }

    /**
     * Clicks the "Blogs" link in the header.
     */
    public void clickBlogs() {
        blogs.click();
    }

    /**
     * Clicks the "Pricing" link in the header menu.
     */
    public void clickPricing() {
        pricing.click();
    }
}
