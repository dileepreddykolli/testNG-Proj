package com.app.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.framework.commons.WebCommons;
import com.framework.reports.ReportsClass;
import com.framework.utilities.ReadProp;
import com.framework.webdriver.WebDriverClass;

public class LoginPage extends WebCommons {

	// WebElements

	@FindBy(xpath = "//img[@title='ParaBank']")
	private WebElement logo;

	@FindBy(xpath = "//p[@class='caption']")
	private WebElement caption;

	@FindBy(xpath = "//h2")
	private WebElement loginHeader;

	@FindBy(name = "username")
	private WebElement usernameTxtb;

	@FindBy(name = "password")
	private WebElement passwordTxtb;

	@FindBy(xpath = "//input[@class='button']")
	private WebElement loginBtn;

	@FindBy(linkText = "Forgot login info?")
	private WebElement forgotPassLink;

	@FindBy(linkText = "Register")
	private WebElement regLink;

	// Actions

	public void launchApplication() {
		driver.get(ReadProp.readData("Config.properties").getProperty("url"));
		logPass("Application successfully Launched");
	}

	public void verifyLogo() {
		if (logo.isDisplayed())
			logPass("Logo is Displayed");
		else
			logFail("Logo is not Displayed");
	}

	public void verifyAppTitle() {
		if (driver.getTitle().equals(ReadProp.readData("Config.properties").getProperty("Title")))
			logPass("Title is Matching");
		else
			logFail("Title is not Matching");
	}

	public void verifyApplicationCaption() {
		if (getElementText(caption).equals(ReadProp.readData("Config.properties").getProperty("Caption")))
			logPass("Application caption is matching");
		else
			logFail("Application caption is not matching");

	}

	public void verifyApplicationLogin(String username, String password) {
		enterText(usernameTxtb, username);
		enterText(passwordTxtb, password);
		click(loginBtn);
		logInfo("Username :"+username+" , Password: "+password);
		logInfo("Username and Password Entered and Clicked on Login Button");
	}
	
	public void getRegistrationPage() {
		click(regLink);
		logInfo("Click on Registration Link");

	}
	
	public void getForgotLoginInfoPage() {
		click(forgotPassLink);
		logInfo("Click on forgot login info Link");
	}

	public static LoginPage getLoginPage() {
		return PageFactory.initElements(WebDriverClass.getDriver(), LoginPage.class);
	}

}
