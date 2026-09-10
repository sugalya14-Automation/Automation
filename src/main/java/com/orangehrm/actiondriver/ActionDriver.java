package com.orangehrm.actiondriver;

import java.time.Duration;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.base.BaseClass;

public class ActionDriver {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	
	public ActionDriver(WebDriver driver)
	{
		this.driver = driver;
		
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(BaseClass.getProp().getProperty("explicitWait"))));
		
	}
	
	//Click
	public void Click(By by)
	{
		try {
			waitForElementToBeClickable(by);
			driver.findElement(by).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 System.out.println("Unable to click: "+e.getMessage());
		}
	}
	
	//Enter text
	public void enterText(By by,String value)
	{
		try {
			waitForElementToBeVisible(by);
			driver.findElement(by).clear();
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to enter value: "+e.getMessage());
		}
	}
	
	//get text
	public String getText(By by)
	{
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to get Text"+e.getMessage());
			return "";
		}
	}
	
	//CompareText
	public void compareText(By by,String expectedText)
	{
		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if(expectedText.equals(actualText))
			{
				System.out.println("Matching:");
			}
			else
			{
				System.out.println("Not Matching:");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to compare"+e.getMessage());
		}
	}
	
	//element displayed
	public boolean isDisplayed(By by)
	{
	 try {
		waitForElementToBeVisible(by);
		 return driver.findElement(by).isDisplayed();
		 
	 } catch (Exception e) {
		// TODO Auto-generated catch block
		 System.out.println("Not Visible");
		 return false;
	 }
	 
	}
	
	//Scroll
	public void scrolltoElement(By by)
	{
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0],scrollIntoView(true)",element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 System.out.println("Failed to locate ");
		}
		
	}
	
	//page wait to load
	public void waitForPageLoad(int timeOutInSec)
	{
		try {
			wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver -> ((JavascriptExecutor)WebDriver).executeScript("return document.readyState").equals("Complete"));
			System.out.println("Page load success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Page load failed");
		}
		
	}
	//Clickable
	public @NonNull WebElement waitForElementToBeClickable(By by) {
	 try {	
		@NonNull
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		return element;
	 } catch (Exception e)
	 {
		 System.out.println("element not clickable:"+e.getMessage());
	 
	 return null;
	 }	
	}
	
	//Visible
	public WebElement waitForElementToBeVisible(By by) {
		 try {	
			 return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		 } catch (Exception e)
		 {
			 System.out.println("element not Visible:"+e.getMessage());
			 return null;
		 }
			
		}
}
