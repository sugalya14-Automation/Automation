package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.orangehrm.utilities.LoggerManager;


 
public class BaseClass {
	
	static Properties prop;
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static final Logger logger =  LoggerManager.getLogger(BaseClass.class);
	
	@BeforeSuite
	public void loadconfig() throws IOException
	{
		 prop = new Properties();
		try(FileInputStream fis = new FileInputStream("src/main/resources/config.properties"))
		{
		prop.load(fis);
		}
		logger.info("config.properties file loaded");
	}
	
	
	@BeforeMethod
	public void setup()
	{
		
		
		try
		{
			String browser = prop.getProperty("browser");
			if (browser == null || browser.isBlank()) {
		
		
		        throw new IllegalArgumentException(
		            "Browser is missing in config.properties"
		        );
		    }
		  
		if (browser.equalsIgnoreCase("chrome")) {
		    setDriver(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("firefox")) {
		    setDriver(new FirefoxDriver());
		} else {
		    throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
		logger.info("Execution starts..");
		
		launchBrowser();
	
		logger.info("Browser launched");
		logger.trace("Trace");
		logger.debug("Debug");
		logger.warn("Warn");
		} catch (Exception e) {

	        try {
	            WebDriver webDriver = getDriver();

	            if (webDriver != null) {
	                webDriver.quit();
	            }

	        } finally {
	            driver.remove();
	        }

	        throw e;
			
		}
		 
	}
	
	public void launchBrowser() 
	{
		System.out.println("Launch Browser");
		
		if (getDriver() == null) {
		    throw new IllegalStateException("WebDriver initialization failed");
		}

			String windowHandle = getDriver().getWindowHandle();
			System.out.println("Browser launched successfully: " + windowHandle);

		//Maximize the browser
			getDriver().manage().window().maximize();
		
		WebDriverWait wait1 = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		
		//Navigate to URL
		String appUrl = prop.getProperty("url");
		if (appUrl == null || appUrl.isBlank()) {
		    throw new IllegalStateException("Application URL is missing in config.properties");
		}

		System.out.println("Launch URL..");	
		getDriver().get(appUrl);
		Boolean title = wait1.until(ExpectedConditions.titleContains("OrangeHRM"));
		Assert.assertTrue(title, "OrangeHRM page did not load successfully");
	}
	
	@AfterMethod(alwaysRun = true)
public void quit()
{
		
	    try {
	    	WebDriver webDriver = getDriver();


		        if (webDriver != null) {
		            webDriver.quit();
		            logger.info("Browser closed successfully");
		        }
		    } finally {
		        driver.remove();
		    }	
	  
}


//Getter
	public static WebDriver getDriver() {
	    return driver.get();
	}

	public static void setDriver(WebDriver webDriver) {
	    driver.set(webDriver);
	}
	
	public static Properties getProp()
	{
			return prop;
		
	}
}
