package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.orangehrm.actiondriver.ActionDriver;


public class LoginPage {
	
	public LoginPage(WebDriver driver) {
		this.actionDriver = new ActionDriver(driver);
	}
	
	private ActionDriver actionDriver;
	
	private By userNameField = By.name("username");
	private By passwordField = By.cssSelector("input[type='password']");
	private By loginButton = By.xpath("//button[text()=' Login ']");
	private By errorMessage = By.xpath("//p[text()='Invalid']");
	private By Dashboard = By.xpath("//h6[text()='Dashboard']");
	
	//Login
	public void login(String userName,String password)
	{
		System.out.println("Logging in");
		if(actionDriver.isDisplayed(userNameField) & actionDriver.isDisplayed(passwordField))
		{
			actionDriver.enterText(userNameField,userName);
			actionDriver.enterText(passwordField,password);
			if(actionDriver.waitForElementToBeClickable(loginButton)!=null)
			{
			actionDriver.Click(loginButton);
			verifyLogin();
			}
			else
			{
				System.out.println("Element not Clickable");
			}
		}
		else
		{
			System.out.println("Field not visible");
		}
	}
	
	
	//Verify login
	public void verifyLogin()
	{
		if(actionDriver.waitForElementToBeVisible(Dashboard)!=null)
		{
			System.out.println("Login Successfull");
		}
		else if(actionDriver.waitForElementToBeVisible(errorMessage)!=null)
		{
			System.out.println("Invalid credentials");	
		}
	}
	
}
