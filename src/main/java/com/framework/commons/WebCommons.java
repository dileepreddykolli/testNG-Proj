package com.framework.commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.reports.ReportsClass;
import com.framework.webdriver.WebDriverClass;

public class WebCommons {

	// This class will have all the common methods to handle web applications

	public WebDriver driver = WebDriverClass.getDriver();

	// Method to scroll down
	public void scrollToElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView()", element);
	}

	// Method to click on element
	public void click(WebElement element) {
		scrollToElement(element);
		element.click();
	}

	// Method to enter text
	public void enterText(WebElement element, String value) {
		scrollToElement(element);
		element.clear();
		element.sendKeys(value);
	}

	// Method to Select check-box
	public void selectCheckbox(WebElement element) {
		scrollToElement(element);
		if (!element.isSelected())
			element.click();
	}

	// Method to select option from drop down
	public void selectOption(WebElement element, String option, String method) {
		scrollToElement(element);
		Select s = new Select(element);
		if (method.equalsIgnoreCase("Index")) {
			s.selectByIndex(Integer.parseInt(option));
		} else if (method.equalsIgnoreCase("value")) {
			s.selectByValue(option);
		} else if (method.equalsIgnoreCase("visibleText")) {
			s.selectByVisibleText(option);
		}
	}

	// Method to wait (java wait)
	public void hardWait(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Implicit wait
	public void implicitWait(long seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}

	// Explicit wait
	public void waitForLocator(By locator, long seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
	}

	// Fluent Wait
	public void fluentWait(By locator, long seconds, long interval) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(seconds))
				.pollingEvery(Duration.ofSeconds(interval));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
	}

	// Method to take Screenshot of entire window
	public  String takeWindowScreenshot(String name) throws IOException {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"\\Screenshots\\"+ name+uniqueId() + ".png";
		FileUtils.copyFile(screenshot, new File(path));
		return path;
	}

	// Method to take Screenshot of element
	public static  String takeElementScreenshot(WebElement element, String name) throws IOException {
		File screenshot = element.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"\\Screenshots\\"+ name+uniqueId() + ".png";
		FileUtils.copyFile(screenshot, new File(path));
		return path;
	}

	//Method to generate uniqueId
	public static String uniqueId() {
		SimpleDateFormat SDF = new SimpleDateFormat("ddMMyyyyhhmmss");
		String uniqueId = SDF.format(Calendar.getInstance().getTime());
		return uniqueId;
	}
	
	//Method to perform double click
	public void doubleClick(WebElement element) {
		scrollToElement(element);
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}
	
	//Method to perform right click
	public void rightClick(WebElement element) {
		scrollToElement(element);
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}
	
	//Method to open new tab
	public void openNewTab() {
		driver.switchTo().newWindow(WindowType.TAB);
	}

	//Method to open new window
	public void openNewWindow() {
		driver.switchTo().newWindow(WindowType.WINDOW);
	}
	
	//Method to get text of element
	public String getElementText(WebElement element) {
		return element.getText();
	}
	
	//Method to get  element attribute value
	public String getElementAttributeValue(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}
	
	//Method to print info messages in the reports
	public void logInfo(String message) {
		ReportsClass.getLogger().info(message);
	}
	
	//Method to print pass messages in the reports
	public void logPass(String message) {
		ReportsClass.getLogger().pass(message);
	}
	
	//Method to print fail messages in the reports
	public void logFail(String message) {
		ReportsClass.getLogger().fail(message);
	}
	
	//Method to print warning messages in the reports
	public void logWarning(String message) {
		ReportsClass.getLogger().warning(message);
	}
}
