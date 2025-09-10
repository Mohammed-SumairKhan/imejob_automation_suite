package com.imejob.dataprovider;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utility.JsonReader;

public class Demo {
	public static void dataProvider() {
		JsonReader createAccountJsonReader = new JsonReader();
		createAccountJsonReader.loadJson("createAccountData");
		System.out.println(createAccountJsonReader.getValue("createAccount" ,"firstName"));
		System.out.println(createAccountJsonReader.getValue("createAccount" ,"middleName"));
		System.out.println(createAccountJsonReader.getValue("createAccount" ,"lastName"));
		System.out.println(createAccountJsonReader.getValue("createAccount" ,"email"));
		System.out.println(createAccountJsonReader.getValue("createAccount" ,"phoneNumber"));
		System.out.println(createAccountJsonReader.getValue("createAccount" ,"totalExperience"));
		System.out.println(createAccountJsonReader.getValue("createAccount" ,"currentLocation"));
		System.out.println(createAccountJsonReader.getValue("createAccount" ,"password"));
		System.out.println(createAccountJsonReader.getValue("createAccount" ,"gender"));
		System.out.println(createAccountJsonReader.getValue("createAccount" ,"technicalSkills"));
		System.out.println(createAccountJsonReader.getValue("createAccount" ,"resumePath"));
		System.out.println(createAccountJsonReader.getValue("createAccount" ,"agreeTerms"));
	}
		public static void main(String[] args) throws InterruptedException {
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://imejob.com/");
//			
//			List<WebElement> elements= driver.findElements(By.xpath("//div[contains(@class, 'indicatorContainer')]"));
//			elements.get(1).click();
//			Thread.sleep(5000);
//			String skill = "Bangalore";
//			WebElement option = driver.findElement(By.xpath("//div[text()='" + skill + "']"));
//			option.click();
			
			WebElement element =driver.findElement(By.xpath("//div[@class = ' css-19bb58m']//input"));
			element.click();
			element.sendKeys("abc", Keys.ENTER);
		}
		
}




















