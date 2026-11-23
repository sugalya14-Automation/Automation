package com.orangehrm.actiondriver;

import java.time.Duration;

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
	public void click(By by)
	{
			WebElement element = waitForElementToBeClickable(by);
			element.click();
	
	}
	
	//Enter text
	public void enterText(By by,String value)
	{
			WebElement element = waitForElementToBeVisible(by);
			element.clear();
			element.sendKeys(value);
	
	}
	
	//get text
	public String getText(By by)
	{
		WebElement element =	waitForElementToBeVisible(by);
			return element.getText();
		
	}
	
	//CompareText
	public boolean compareText(By by,String expectedText)
	{
		WebElement element = waitForElementToBeVisible(by);
			String actualText = element.getText();
			return expectedText.equals(actualText);
	}
	
	//element displayed
	public boolean isDisplayed(By by)
	{

		 return waitForElementToBeVisible(by).isDisplayed();
		 
	}
	
	//Scroll
	public void scrolltoElement(By by)
	{
	
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0].scrollIntoView(true);",element);
		
		
	}
	
	//page wait to load
	public void waitForPageLoad()
	{
		
		    wait.until(
		        webDriver -> ((JavascriptExecutor) webDriver)
		            .executeScript("return document.readyState")
		            .equals("complete")
		    );
		
		
	}
	//Clickable
	public WebElement waitForElementToBeClickable(By by) {
	
		return wait.until(ExpectedConditions.elementToBeClickable(by));
		
	}
	
	//Visible
	public WebElement waitForElementToBeVisible(By by) {
		 
			 return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			
		}

	public String getElementDescription(By locator) {
		if(driver==null)
			return "driver is null";
		if(locator==null)
			return "locator is null";
		try {
			WebElement element = driver.findElement(locator);
			String name = element.getDomProperty("name");
			String id = element.getDomProperty("id");
			String text = element.getText();
			String classname = element.getDomProperty("class");
			String placeholder = element.getDomProperty("placeholder");
			if(isNotEpmty(name))
				return "Element with name:"+name;
			else if(isNotEpmty(id))
				return "Element with id:"+id;
			else if(isNotEpmty(text))
				return "Element with text:"+truncate(text,50);
			else if(isNotEpmty(id))
				return "Element with classname:"+classname;
			else if(isNotEpmty(id))
				return "Element with placeholder:"+truncate(placeholder,50);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return "";
	
	}
	
	private boolean isNotEpmty(String value)
	{
		return value!=null && !value.isEmpty();
	}
	
	private String truncate(String value,int maxLength)
	{
		if(value==null || value.length()<=maxLength)
		return value;
		else
		return value.substring(0,maxLength)+"...";
	}
	
	
	public void highlightElement(By locator) {


	    WebElement element = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(locator)
	    );

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    js.executeScript(
	        "arguments[0].style.border='3px solid red';" +
	        "arguments[0].style.backgroundColor='yellow';",
	        element
	    );
	}
	
}
