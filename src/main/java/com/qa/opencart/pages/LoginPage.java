package com.qa.opencart.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Step;

public class LoginPage{

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. private by locator
	private By emailTxt=By.id("input-email");
	private By passwordTxt=By.id("input-password");
	private By loginBtn=By.cssSelector("input[value='Login']");
	private By forgottenPasswordLnk=By.xpath("//input[@id='input-password']/following-sibling::a");
	private By registerLnk=By.xpath("//aside[@id='column-right']//child::a[text()='Register']");
	private By loginErrorMsg=By.cssSelector("div.alert.alert-danger.alert-dismissible");
	private By logout=By.linkText("Logout");
	
	
	
	//2. public constructor
	public LoginPage(WebDriver driver){
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	//3. public page action
	@Step("getting login page title")
	public String getLoginPageTitle() {
		//return driver.getTitle();
		return eleUtil.waitForTitleIs(Constants.DEFAULT_TIMEOUT,Constants.LOGIN_PAGE_TITLE);
	}
	
	@Step("getting login page url")
	public String getLoginPageUrl() {
		//return driver.getCurrentUrl();
		return eleUtil.waitForUrl(Constants.DEFAULT_TIMEOUT,Constants.LOGIN_PAGE_FRACTION_URL);
	}
	
	@Step("checking forgotten password link exist?")
	public boolean isForgottenPasswordLnkExist() {
		//return driver.findElement(forgottenPasswordLnk).isDisplayed();
		return eleUtil.doIsDisplayed(forgottenPasswordLnk);
	}
	
	@Step("login to appication with username {0} and password {1}")
	public AccountsPage doLogin(String un,String pwd) {
	
//		driver.findElement(emailTxt).clear();
//		driver.findElement(emailTxt).sendKeys(un);
//		driver.findElement(passwordTxt).clear();
//		driver.findElement(passwordTxt).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		eleUtil.waitForElementToBeVisible(emailTxt,Constants.DEFAULT_TIMEOUT).sendKeys(un);;
		eleUtil.doSendKeys(passwordTxt, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	@Step("login to appication with username {0} and password {1}")
	public boolean doInvalidLogin(String un,String pwd) {
	
//		driver.findElement(emailTxt).clear();
//		driver.findElement(emailTxt).sendKeys(un);
//		driver.findElement(passwordTxt).clear();
//		driver.findElement(passwordTxt).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		eleUtil.waitForElementToBeVisible(emailTxt,Constants.DEFAULT_TIMEOUT).sendKeys(un);;
		eleUtil.doSendKeys(passwordTxt, pwd);
		eleUtil.doClick(loginBtn);
		String actualErrorMsg=eleUtil.doElementGetText(loginErrorMsg);
		if(actualErrorMsg.contains(Errors.LOGIN_PAGE_ERROR_MSG)) {
			return true;
		}
		return false;
	}
	
	
	@Step("checking register link exist?")
	public boolean isRegisterLnkExist() {
		//return driver.findElement(registerLnk).isDisplayed();
		return eleUtil.waitForElementToBeVisible(registerLnk,Constants.DEFAULT_TIMEOUT).isDisplayed();
	}
	
	@Step("navigation to registration page")
	public RegistrationPage navigateToRegisterPge() {
		if(isRegisterLnkExist()) {
			//driver.findElement(registerLnk).click();
			eleUtil.doClick(registerLnk);
			return new RegistrationPage(driver);
		}else {
		return null;
		}
	}

	

	
}
