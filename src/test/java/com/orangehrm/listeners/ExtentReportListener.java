
package com.orangehrm.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utils.ExtentManager;

public class ExtentReportListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
    	 System.out.println("EXTENT LISTENER STARTED");
        ExtentManager.startTest(result.getMethod().getMethodName());
        ExtentManager.logstep("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.getTest().fail(result.getThrowable());

        if (BaseClass.getDriver() != null) {
            ExtentManager.attachScreenshot(
                BaseClass.getDriver(),
                "Test Failure"
            );
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().skip("Test Skipped");
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
    	 System.out.println("EXTENT LISTENER FINISHING");

    	    ExtentManager.endTest();

    	    System.out.println("EXTENT REPORT FLUSHED");
    }
}
