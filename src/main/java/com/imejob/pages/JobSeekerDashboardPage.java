package com.imejob.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class JobSeekerDashboardPage {
	WebDriver driver;
	
	
	
	
	
		public JobSeekerDashboardPage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		
		
		
		
		
}
