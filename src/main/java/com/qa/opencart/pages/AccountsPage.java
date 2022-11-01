package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	private By searchTxt=By.name("search");
	private By searchBtn=By.cssSelector("div#search button");
	private By Logo=By.xpath("//img[@title='naveenopencart']");
	private By headerList=By.cssSelector("div#content h2");
	
	public String getAccountsPageTitle() {
		//return driver.getTitle();
		return eleUtil.waitForTitleIs(Constants.DEFAULT_TIMEOUT,Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	
	public boolean isAccountsPageLogoExist() {
		//return driver.findElement(Logo).isDisplayed();
		return eleUtil.doIsDisplayed(Logo);
	}
	
	public boolean isSearchFildExist() {
		//return driver.findElement(searchTxt).isDisplayed();
		return eleUtil.waitForElementPresent(searchTxt,Constants.DEFAULT_TIMEOUT).isDisplayed();
	}
	
	public SearchResultsPage doSearch(String productName) {
		if(isSearchFildExist()) {
		eleUtil.doSendKeys(searchTxt, productName);
		eleUtil.doClick(searchBtn);
		return new SearchResultsPage(driver);
		}
		return null;
	}
	
	public List<String> getAccountsPageHeaderList() {
		//List<WebElement> headerlist=driver.findElements(headerList);
		List<WebElement> headerlist=eleUtil.getElements(headerList);
		List<String> actheaderlist=new ArrayList<String>();
		for(WebElement e:headerlist) {
			String text=e.getText();
			actheaderlist.add(text);
		}
		return actheaderlist;
	}
}
