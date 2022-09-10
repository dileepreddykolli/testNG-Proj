package com.framework.reports;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.framework.commons.WebCommons;

public class ReportsClass {
	
	//This class will have all the methods to generate test report
	
	public static ExtentReports extent = null; 
	public static ExtentTest logger = null;
	
	
	//Method to generate report
	@BeforeSuite(alwaysRun=true)
	public static void setupReport() {
		ExtentHtmlReporter htmlReport= new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\AutomationReport"+WebCommons.uniqueId()+".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReport);		
		extent.setSystemInfo("Application Name", "Parabank");
	}
	
	//Method to start printing messages under test case
	public void startReporting(String testcase) {
		logger = extent.createTest(testcase);
	} 
	
	//Method to stop printing messages under test case
	@AfterSuite(alwaysRun=true)
	public void stopReporting() {
		extent.flush();
	}
	
	//Method to share logger details with other classes
	public static ExtentTest getLogger() {
		return logger;
	}

}
