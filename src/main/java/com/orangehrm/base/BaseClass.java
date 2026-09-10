package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;


 
public class BaseClass {
	
	static Properties prop;
	protected WebDriver driver;
	
	
	public void loadconfig() throws IOException
	{
		 prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);
	}
	
	
	@BeforeSuite
	public void setup() throws IOException
	{
		 driver = new ChromeDriver();
		System.out.println("Execution starts..");
		loadconfig();
		launchBrowser();
		
	}
	
	public void launchBrowser() 
	{
		System.out.println("Launch Browser");
		//implicit wait
		int wait = Integer.parseInt(prop.getProperty("implicitWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
			

		//Maximize the browser
		driver.manage().window().maximize();
		
		//Navigate to URL
		String AppUrl = prop.getProperty("url");
		System.out.println("Launch URL..");	
		driver.get(AppUrl);
		staticWait(20);
		
	}
	
	@AfterMethod
public void quit()
{
  if(driver!=null)
  {
	  System.out.println("Close Browser");
	 	driver.quit();  
  }
	
	
}

public void staticWait(int seconds)
{
	  LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
}

//Getter
public WebDriver getDriver()
{
	  return driver;
}
	
//Setter
public void setDriver(WebDriver driver) {
	this.driver = driver;
}
	
	public static Properties getProp()
	{
		try {
			return prop;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
