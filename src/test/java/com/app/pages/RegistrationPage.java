package com.app.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.framework.commons.WebCommons;
import com.framework.reports.ReportsClass;
import com.framework.webdriver.WebDriverClass;

public class RegistrationPage extends WebCommons{
	
	@FindBy(xpath="//h1[@class='title']")
	private WebElement title;
	
	By titleLocator = By.xpath("//h1[@class='title']");
	
	@FindBy(xpath="//input[@name='customer.firstName']")
	private WebElement firstName;
	
	@FindBy(xpath="//input[@name='customer.lastName']")
	private WebElement lastName;
	
	@FindBy(xpath="//input[@name='customer.address.street']")
	private WebElement address;
	
	@FindBy(xpath="//input[@name='customer.address.city']")
	private WebElement city;
	
	@FindBy(xpath="//input[@name='customer.address.state']")
	private WebElement state;
	
	@FindBy(xpath="//input[@name='customer.address.zipCode']")
	private WebElement zipCode;
	
	@FindBy(xpath="//input[@name='customer.phoneNumber']")
	private WebElement phoneNumber;
	
	@FindBy(xpath="//input[@name='customer.ssn']")
	private WebElement ssn;
	
	@FindBy(xpath="//input[@name='customer.username']")
	private WebElement username;
	
	@FindBy(xpath="//input[@name='customer.password']")
	private WebElement password;
	
	@FindBy(xpath="//input[@name='repeatedPassword']")
	private WebElement confirmPassword;
	
	@FindBy(xpath="//input[@value='Register']")
	private WebElement registerButton;
	
	@FindBy(xpath="//span[@class='error']")
	private List<WebElement> errors;
	
	By registrationSuccess = By.xpath("//h1[@class='title' and contains(text(),'Welcome')]");
	
	public void verifyRegPage() {
		waitForLocator(titleLocator, 30);
		logPass("Registration page successfully launched");
	}

	public static RegistrationPage getRegistrationPage() {
		return PageFactory.initElements(WebDriverClass.getDriver(), RegistrationPage.class);
	}

}
