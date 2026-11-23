package com.orangehrm.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private static Map<Long,WebDriver> driverMap=new HashMap<>();
	
	public static ExtentReports getReporter() {
		
		String reportDirpath = System.getProperty("user.dir") + "/test-output/ExtentReport";
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
		        .format(new Date());
		
		File reportDir = new File(reportDirpath);

		if (!reportDir.exists()) {
		    reportDir.mkdirs();
		}
		if(extent==null) {
			String reportPath = reportDirpath+"/ExtentReport"+timeStamp+".html";
			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
			spark.config().setReportName("Automation Test Report");
			spark.config().setDocumentTitle("orangeHRM Report");
			spark.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Operating System", System.getProperty("os.name"));
			extent.setSystemInfo("Java Version", System.getProperty("java.version"));
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			
		}
		return extent;
	}
	
	public static ExtentTest startTest(String testname) {
		ExtentTest extentTest =getReporter().createTest(testname);
		test.set(extentTest);
		return extentTest;
	}
	
	public static void endTest() {
	    if (extent != null) {
	        extent.flush();
	    }
	}
	
	public static ExtentTest getTest()
	{
		return test.get();
	}
	
	public static String getTestName() {
		ExtentTest currenTest = getTest();
		if(currenTest!=null)
			return currenTest.getModel().getName();
		else
			return "No test is currently active for this thread";
	}
	
	public static void logstep(String logMessage) {
		getTest().info(logMessage);
	}
	
	public static void logStepWithScreenshot(WebDriver driver,String logMessage,String screenshotMeesage) {
		getTest().pass(logMessage);
		attachScreenshot(driver, logMessage);
		
	}
	
	public static void logFailure(WebDriver driver,String logMessage,String screenshotMeesage) {
		String colorMessage = String.format("span style='color:red;'%s</span>'", logMessage);
		getTest().fail(colorMessage);
		attachScreenshot(driver, screenshotMeesage);
		
	}
	
	public static void logSkip(String logMessage) {
		String colorMessage = String.format("span style='color:orange;'%s</span>'", logMessage);
		getTest().skip(colorMessage);
		
	}
	
	public static String takeScreenshot(WebDriver driver,String screenShotName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		
		String destPath =  System.getProperty("user.dir")+"/test-output/screenshots/"+screenShotName+"_"+timeStamp+".png";
	
		File finalPath =new File(destPath);
		try {
			FileUtils.copyFile(src, finalPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String base64Format = convertToBase64(src);
		return base64Format;
	}
	
	public static String convertToBase64(File screenShotFile)
	{
		String base64Format ="";
		try {
			byte[] fileContent = FileUtils.readFileToByteArray(screenShotFile);
			base64Format = Base64.getEncoder().encodeToString(fileContent);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return base64Format;
	}
	
	public static void attachScreenshot(WebDriver driver,String message) {
		try {
			String screenShotBase64 = takeScreenshot(driver, message);
			getTest().info(message,com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String(screenShotBase64).build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getTest().fail("Failed to attach screenshot"+message);
			e.printStackTrace();
		}
	}
	
	
	public static void registerDriver(WebDriver driver) {
		driverMap.put(Thread.currentThread().threadId(),driver);
	
	}

}
