package com.framework.listners;

import java.io.IOException;

import org.junit.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.framework.commons.WebCommons;
import com.framework.reports.ReportsClass;

public class TestListners extends ReportsClass implements ITestListener {

	public void onTestStart(ITestResult result) {
		startReporting(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		logger.pass(result.getMethod().getMethodName()+" is Executed Successfully");
		
	}

	public void onTestFailure(ITestResult result) {
		logger.fail(result.getMethod().getMethodName()+" is Failed");		
		try {
			logger.addScreenCaptureFromPath(new WebCommons().takeWindowScreenshot(result.getMethod().getMethodName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		logger.skip(result.getMethod().getMethodName()+" is Skipped");
		try {
			logger.addScreenCaptureFromPath("C:\\Training\\Frameworks\\TestNGAutomationProject_Evening\\Failure.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
