package com.framework.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.framework.reports.ReportsClass;
import com.framework.utilities.ReadProp;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverClass extends ReportsClass{

	// This class will have all the methods related to browser

	WebDriver driver;
	public static ThreadLocal<WebDriver> thread = new ThreadLocal<WebDriver>();

	//Method to launch browser window
	@BeforeMethod(alwaysRun = true)
	@Parameters(value = "browser")
	public void setupBrowser(String browser) {
		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			Assert.fail("Invalid browser value");
		}
		setDriver(driver);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(ReadProp.readData("Config.properties").getProperty("URL"));
	}

	//Method to close browser windows
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}

	//Method to store browser session in thread local
	public static synchronized void setDriver(WebDriver driver) {
		thread.set(driver);
	}

	//Method to share driver details with other class
	public static synchronized WebDriver getDriver() {
		return thread.get();
	}

}
