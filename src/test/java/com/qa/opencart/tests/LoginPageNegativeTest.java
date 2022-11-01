package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageNegativeTest extends BaseTest{

	@DataProvider
	public Object[][] getLoginNegativeData() {
		return new Object[][]{ {"milan@gmail.com","12345"},
							   {"milanshah@gmail.com","12345"},
							 };
		
	}
	
	@Test(dataProvider ="getLoginNegativeData")
	@Description("login title test with invalid credentials")
	@Severity(SeverityLevel.NORMAL)
	public void invalidLoginTest(String username,String password){
		Assert.assertTrue(loginPage.doInvalidLogin(username,password));
	}
}
