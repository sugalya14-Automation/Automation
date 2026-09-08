package com.orangehrm.test;

import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;

@Test
public class Class1 extends BaseClass{

	public void class1()
	{
		String title = driver.getTitle();
		
		assert title.equals("OrangeHRM"):"Not matching";
		
		System.out.println("Pass");
	}
}
