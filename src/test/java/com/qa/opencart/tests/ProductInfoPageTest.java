package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	@Description("pre login for product info")
	public void productInfoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
//	@DataProvider
//	public Object[][] getProductData() {
//		return new Object[][] {
//								{"MacBook","MacBook Pro"},
//								{"MacBook","MacBook Air"},
//								{"Apple","Apple Cinema 30\""}
//							  };
//	}
	
	@DataProvider
	public Object[][] getProductData(){
		Object[][] productData=ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
		return productData;
	}
	
	
	@Test(dataProvider ="getProductData")
	@Description("checking subproduct")
	@Severity(SeverityLevel.NORMAL)
	public void productInfoHeaderTest(String product,String subProduct) {
		searchResultsPage=accPage.doSearch(product);
		productInfoPage=searchResultsPage.selectProduct(subProduct);
		String actProductHeder=productInfoPage.getProductHeaderTxt();
		Assert.assertEquals(actProductHeder,subProduct);
	}
	
	@Test
	@Description("count number of product image")
	@Severity(SeverityLevel.NORMAL)
	public void productImgTest() {
		searchResultsPage=accPage.doSearch("Macbook");
		productInfoPage=searchResultsPage.selectProduct("MacBook Pro");
		int actProductImg=productInfoPage.getProductImgCount();
		Assert.assertEquals(actProductImg,Constants.MACBOOK_PRO_IMG_COUNT);
	}
	
	@Test
	@Description("check meta data of product")
	@Severity(SeverityLevel.NORMAL)
	public void productInfoTest() {
		searchResultsPage=accPage.doSearch("Macbook");
		productInfoPage=searchResultsPage.selectProduct("MacBook Pro");
		Map<String,String> actProductInfo=productInfoPage.getProductInfo();
		actProductInfo.forEach((k,v) -> System.out.println(k + ":" +v));
		softAssert.assertEquals(actProductInfo.get("Brand"),Constants.MACBOOKPRO_BRAND);
		softAssert.assertEquals(actProductInfo.get("Product Code"),Constants.MACBOOKPRO_PRODUCT_CODE);
		softAssert.assertEquals(actProductInfo.get("Reward Points"),Constants.MACBOOKPRO_REWARD_POINTS);
		softAssert.assertEquals(actProductInfo.get("Availability"),Constants.MACBOOKPRO_AVAILABILITY);
		softAssert.assertEquals(actProductInfo.get("Price"),Constants.MACBOOKPRO_PRICE);
		softAssert.assertAll();
	}
	
	@Test
	@Description("check add to cart product successful message")
	@Severity(SeverityLevel.NORMAL)
	public void productAddToCartTest() {
		searchResultsPage=accPage.doSearch("Macbook");
		productInfoPage=searchResultsPage.selectProduct("MacBook Pro");
		String actSuccessMsg=productInfoPage.addToCartProducts();
		Assert.assertTrue(actSuccessMsg.contains(Constants.PRODUCT_SUCCESS_MSG +"MacBook Pro"));
		}
}
