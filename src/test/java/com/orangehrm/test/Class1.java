package com.orangehrm.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;


public class Class1 extends BaseClass{

	@Test
	public void class1()
	{
		System.out.println("Class1");
		String title = getDriver().getTitle();
		
		try {
	        Assert.assertEquals(title, "OrangeHRM", "Title is not matching");
	        logger.info("Pass: Title matched successfully -> " + title);
	    } catch (AssertionError e) {
	        logger.error("Title is not matching! Expected: OrangeHRM, but got: " + title);
	        throw e;
	    }
	}
}
