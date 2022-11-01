 package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Epic("Epic 200 - design account page for open cart application")
public class AccountsPageTest extends BaseTest {

	@Description("pre login for accounts page tests")
	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Description("account page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accPageTitleTest() {
		String actAccountPageTitle=accPage.getAccountsPageTitle();
		System.out.println("account page title :"+actAccountPageTitle);
		Assert.assertEquals(actAccountPageTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	@Description("account page logo test")
	@Severity(SeverityLevel.NORMAL)
	public void accPageHeaderTest() {
		Assert.assertTrue(accPage.isAccountsPageLogoExist());
	}

	@Test
	@Description("account page search fild test")
	@Severity(SeverityLevel.CRITICAL)
	public void searchExist() {
		Assert.assertTrue(accPage.isSearchFildExist());
	}

	@Test
	@Description("account page header list test")
	@Severity(SeverityLevel.NORMAL)
	public void accHeaderListTest() {
		List<String> actHeaderList=accPage.getAccountsPageHeaderList();
		System.out.println("account page Header list : "+actHeaderList);
		Assert.assertEquals(actHeaderList,Constants.EXP_ACC_HEADER_LIST);
	}
	
	
}

