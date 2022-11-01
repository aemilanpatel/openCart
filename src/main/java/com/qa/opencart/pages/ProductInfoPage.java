package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	ElementUtil eleUtil;
	Map<String,String> productInfoMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	private By productHeader=By.cssSelector("div#content h1");
	private By productImg=By.cssSelector("div#content img");
	private By productMetaData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData=By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By qty=By.id("input-quantity");
	private By addToCartBtn=By.id("button-cart");
	private By successMsg=By.cssSelector("div.alert.alert-success.alert-dismissible");
	
	public String getProductHeaderTxt() {
		return eleUtil.doElementGetText(productHeader);
	}
	
	public int getProductImgCount() {
		return eleUtil.waitForElementsToBeVisible(productImg, Constants.DEFAULT_TIMEOUT).size();
	}
	
	public String addToCartProducts() {
		eleUtil.doSendKeys(qty,Constants.PRODUCT_QTY);
		eleUtil.doClick(addToCartBtn);
		String successMessage=eleUtil.doElementGetText(successMsg);
		return successMessage;
	}
	
	public Map<String,String> getProductInfo() {
		productInfoMap=new LinkedHashMap<String,String>();
		List<WebElement> metaDataList=eleUtil.waitForElementsToBeVisible(productMetaData,Constants.DEFAULT_TIMEOUT);
		getProductMetaData(metaDataList);
		List<WebElement> priceList=eleUtil.waitForElementsToBeVisible(productPriceData,Constants.DEFAULT_TIMEOUT);
		getProductPriceData(priceList);
		productInfoMap.put("Product Name", getProductHeaderTxt());
		productInfoMap.put("Product Image Count",String.valueOf(getProductImgCount()));
		return productInfoMap;
	}
	
	private void getProductPriceData(List<WebElement> list) {
		String price=list.get(0).getText().trim();
		String exPrice=(list.get(1).getText().split(":")[1]).trim();
		productInfoMap.put("Price", price);
		productInfoMap.put("Ex Tax", exPrice);
	}
	
	private Map<String,String> getProductMetaData(List<WebElement> list) {
 		for(WebElement e:list) {
			String text=e.getText().trim();
			String meta[]=text.split(":");
			String metaKey=meta[0].trim();
			String metaValue=meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
 		return productInfoMap;
	}
}
