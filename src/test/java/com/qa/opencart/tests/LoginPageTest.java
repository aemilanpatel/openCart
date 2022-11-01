package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic 100 - design login page for open cart application ")
@Story("US 101 - design login page features ")
public class LoginPageTest extends BaseTest {

	@Test
	@Description("###Login Page Title Test###")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest() {
		String actTitle=loginPage.getLoginPageTitle();
		System.out.println("login page title :"+actTitle);
		Assert.assertEquals(actTitle,Constants.LOGIN_PAGE_TITLE,Errors.LOGIN_PAGE_TITLE_MISMATCHED);
	}
	
	@Test
	@Description("###Login Page Current URL Test###")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageUrlTest() {
		String actUrl=loginPage.getLoginPageUrl();
		System.out.println("login page url :"+actUrl);
		Assert.assertTrue(actUrl.contains(Constants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test
	@Description("###Check Forgotten Password Link Test###")
	@Severity(SeverityLevel.CRITICAL)
	public void ForgottenPasswordLnkTest() {
		boolean act=loginPage.isForgottenPasswordLnkExist();
		Assert.assertTrue(act);
	}
	
	@Test
	@Description("###Account Page Navigation Test with Username and Password")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() throws InterruptedException {
		accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(accPage.isAccountsPageLogoExist());
	}
	
	@Test
	@Description("###Register Link Exist Test###")
	@Severity(SeverityLevel.CRITICAL)
	public void isResigerLnkExistTest() {
		Assert.assertTrue(loginPage.isRegisterLnkExist());
	}
}
