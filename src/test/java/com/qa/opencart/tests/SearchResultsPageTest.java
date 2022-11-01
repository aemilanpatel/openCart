package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class SearchResultsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void searchTest() {
		searchResultsPage=accPage.doSearch("Macbook");
		String actSearchHeader=searchResultsPage.getResultsPageHeader();
		Assert.assertTrue(actSearchHeader.contains("Macbook"));
	}
	
	@Test
	public void searchProductTest() {
		searchResultsPage=accPage.doSearch("Macbook");
		int actProductCount=searchResultsPage.getProductSearchCount();
		Assert.assertEquals(actProductCount,Constants.MACBOOK_PRODUCT_COUNT);
	}
	
	@Test
	public void searchProductListTest() {
		searchResultsPage=accPage.doSearch("Macbook");
		List<String> actProductList=searchResultsPage.getProductResultsList();
		Assert.assertEquals(actProductList,Constants.MACBOOK_PRODUCT_LIST);
	}
}

