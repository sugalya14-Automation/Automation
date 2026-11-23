package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.orangehrm.actiondriver.ActionDriver;


public class LoginPage {
	
	
	public LoginPage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
	}
	
	private ActionDriver actionDriver;
	
	private By userNameField = By.name("username");
	private By passwordField = By.cssSelector("input[type='password']");
	private By loginButton = By.xpath("//button[text()=' Login ']");
	private By errorMessage = By.cssSelector(
		    ".oxd-alert-content oxd-alert-content--error"
			);
	private By dashboard = By.xpath("//h6[text()='Dashboard']");
	
	
	
	//Login
	public void login(String userName,String password)
	{
		System.out.println("Logging in");
		
		actionDriver.highlightElement(userNameField);
			actionDriver.enterText(userNameField,userName);
			actionDriver.enterText(passwordField,password);
			
			actionDriver.click(loginButton);
			
			
		
	}
	
	
	//Verify login
	public Boolean isDashboardDisplayed()
	{
		
		Boolean b = actionDriver.waitForElementToBeVisible(dashboard).isDisplayed();
		
		actionDriver.highlightElement(dashboard);
		
		return b;
		
	}
	
	public boolean isErrorMessageDisplayed()
	{
		
		Boolean b = actionDriver.isDisplayed(errorMessage);
		actionDriver.highlightElement(dashboard);
		return b;
	}
	
}
