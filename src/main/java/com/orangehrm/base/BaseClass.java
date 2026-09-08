package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

 
public class BaseClass {
	
	Properties prop;
	public WebDriver driver = new ChromeDriver();
	
	public void loadconfig() throws IOException
	{
		 prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);
	}
	
	
	@BeforeMethod
	public void setup() throws IOException
	{
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

	
	
	
}
