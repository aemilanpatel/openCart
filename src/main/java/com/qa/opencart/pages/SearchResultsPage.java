package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	private By searchPageHeader=By.cssSelector("div#content h1");
	private By products=By.cssSelector("div.caption a");

	@Step("get Results Page Header")
	public String getResultsPageHeader() {
			return eleUtil.doElementGetText(searchPageHeader);
	}
	
	@Step("get Product Search Count")
	public int getProductSearchCount() {
		return eleUtil.waitForElementsToBeVisible(products,Constants.DEFAULT_TIMEOUT).size();
	}
	
	@Step("get product results list")
	public List<String> getProductResultsList() {
		List<WebElement> productList=eleUtil.waitForElementsToBeVisible(products,Constants.DEFAULT_TIMEOUT);
		List<String> actproductList=new ArrayList<String>();
		for(WebElement e:productList) {
			String text=e.getText();
			actproductList.add(text);
		}
		return actproductList;
	}
	
	@Step("select product")
	public ProductInfoPage selectProduct(String subProductName) {
		System.out.println("sub product search name : "+subProductName);
		List<WebElement> productList=eleUtil.waitForElementsToBeVisible(products,Constants.DEFAULT_TIMEOUT);
		for(WebElement e:productList) {
			String text=e.getText();
			if(text.equalsIgnoreCase(subProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
}    
