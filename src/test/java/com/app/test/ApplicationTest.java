package com.app.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.app.pages.AccountOverviewPage;
import com.app.pages.ForgotPasswordPage;
import com.app.pages.LoginPage;
import com.app.pages.RegistrationPage;
import com.framework.utilities.ReadExcel;
import com.framework.webdriver.WebDriverClass;

public class ApplicationTest extends WebDriverClass{


	@Test(priority=1,groups= {"Functional"})
	public void verifyApplicationLogo() {
		LoginPage loginpage = LoginPage.getLoginPage();
		loginpage.verifyLogo();
	}
	
	@Test(priority=2,groups= {"Smoke","Regression"})
	public void verifyApplicationTitle() {
		LoginPage loginpage = LoginPage.getLoginPage();
		loginpage.verifyAppTitle();
	}	
	
	@Test(priority =3, dataProvider = "data",groups= {"Regression"})
	public void verifyApplicationLogin(String username, String password) {
		LoginPage page = LoginPage.getLoginPage();
		AccountOverviewPage aoPage = AccountOverviewPage.getAccountOverviewPage();
		page.verifyApplicationLogin(username, password);
		aoPage.verifySuccessfulLogin();
	}
		
	@Test(priority=4,groups= {"Sanity"})
	public void verifyApplicationCaption() {		
		LoginPage loginpage = LoginPage.getLoginPage();
		loginpage.verifyApplicationCaption();
	}
	
	@Test(priority=5,groups= {"Regression"})
	public void verifyRegistrationPage() {		
		LoginPage loginpage = LoginPage.getLoginPage();
		RegistrationPage regPage = RegistrationPage.getRegistrationPage();
		loginpage.getRegistrationPage();
		regPage.verifyRegPage();
	}
	
	@Test(priority=6,groups= {"Regression"})
	public void verifyForgotPassword() {		
		LoginPage loginpage = LoginPage.getLoginPage();
		ForgotPasswordPage forgotPasspage = ForgotPasswordPage.getForgotPassPage();
		loginpage.getForgotLoginInfoPage();
		forgotPasspage.verifyForgotPassPage();
		
	}
	
	@DataProvider(name = "data")
	public String[][] testData() {
		String[][] data = ReadExcel.readData("TestData.xlsx", "Sheet1");
		return data;
	}


}
