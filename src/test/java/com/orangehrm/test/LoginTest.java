package com.orangehrm.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.LoginPage;


public class LoginTest extends BaseClass{
	
	private LoginPage loginpage;
	
	@BeforeMethod
	public void setupLogin() {
		System.out.println("Before method");
		loginpage = new LoginPage(getDriver());	
		System.out.println("setup done");
	}
	
	@Test
	public void login() {
		System.out.println("Test method");
		
		loginpage.login("admin", "admin123");
	//	new HomePage();
	}

}
