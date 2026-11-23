package com.orangehrm.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ExcelUtils;
import com.orangehrm.utils.ExtentManager;


public class LoginTest extends BaseClass{
	
	private LoginPage loginPage;
	
	@BeforeMethod
	public void setupLogin() {
		System.out.println("Before method");
		loginPage = new LoginPage(getDriver());	
		System.out.println("setup done");
	}
	
	@DataProvider(name = "excelData")
	public Object[][] getExcelData() throws IOException {

	    List<Map<String, String>> allData =
	            ExcelUtils.readExcel(
	                "src/test/resources/testdata/TestData.xlsx",
	                "TestData"
	            );

	    List<Map<String, String>> selectedData = new ArrayList<>();

	    for (Map<String, String> row : allData) {

	        if ("Y".equalsIgnoreCase(row.get("Run"))) {
	            selectedData.add(row);
	        }
	    }

	    Object[][] data = new Object[selectedData.size()][1];

	    for (int i = 0; i < selectedData.size(); i++) {
	        data[i][0] = selectedData.get(i);
	    }

	    return data;
	}
	
	@Test(dataProvider = "excelData")
	public void login(Map<String, String> testData) {
		System.out.println("Test method");
		
		
		    String username = testData.get("Username");
		    String password = testData.get("Password");
		    String expectedResult = testData.get("ExpectedResult");
		    System.out.println("Executing login for: " + username);
	

		    loginPage.login(username, password);

		    
		    ExtentManager.logStepWithScreenshot(getDriver(),"username", username);


		    if ("Pass".equalsIgnoreCase(expectedResult)) {
		    	
		    	  Assert.assertTrue(
				            loginPage.isDashboardDisplayed(),
				            "Login failed unexpectedly for: " + username
				        );
		        ExtentManager.logStepWithScreenshot(getDriver(),"pass", "inside pass");

		    } else if ("Fail".equalsIgnoreCase(expectedResult)) {

		        Assert.assertTrue(
		            loginPage.isErrorMessageDisplayed(),
		            "Error message not displayed"
		        );

		        ExtentManager.logStepWithScreenshot(getDriver(),"Fail", "inside Fail");
		    } else {
		        Assert.fail("Invalid ExpectedResult in Excel: " + expectedResult);
		    }

	}
}
