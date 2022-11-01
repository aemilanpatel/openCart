package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void regPageSetup() {
		registrationPage=loginPage.navigateToRegisterPge();
	}
	
	public String getRandomEmail() {
		Random random=new Random();
		String email="milan"+random.nextInt(1000)+"@gmail.com";
		return email;
	}
	
//	@DataProvider
//	public String[][] getRegisterData() {
//		return new String[][] {
//								{"Nitesh","Agarwal","1234567890","nitesh@123","yes"},
//								{"Anu","Shal","9087654321","anu@123","yes"},
//								{"Palak","Patel","6789054321","patel@123","yes"},
//							  };
//	}
	
	@DataProvider
	public Object[][] getRegisterData(){
		Object regData[][]=ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider="getRegisterData")
	public void userRegistrationTest(String firstName,String lastName, String telephone,String password,String subscribe) {
		Assert.assertTrue(registrationPage.accountRegistration(firstName,lastName,getRandomEmail(),telephone,password,subscribe));
	}
	
}
