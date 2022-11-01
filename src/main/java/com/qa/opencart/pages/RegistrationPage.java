package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class RegistrationPage {
	WebDriver driver;
	ElementUtil eleUtil;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By firstNameTxt = By.id("input-firstname");
	private By lastNameTxt = By.id("input-lastname");
	private By emailTxt = By.id("input-email");
	private By telephoneTxt = By.id("input-telephone");
	private By passwordTxt = By.id("input-password");
	private By passwordConfirmTxt = By.id("input-confirm");
	private By yesRbtn = By.xpath("//input[@value='1' and @name='newsletter']");
	private By noRbtn = By.xpath("//input[@value='0' and @name='newsletter']");
	private By privacyPolicyCkb = By.xpath("//input[@type='checkbox']");
	private By contiueBtn = By.xpath("//input[@value='Continue']");
	private By successMsg = By.cssSelector("div#content h1");
	private By logoutLnk = By.linkText("Logout");
	private By registerLnk = By.linkText("Register");

	@Step("account registration")
	public boolean accountRegistration(String firstName, String lastName, String email, String telephone,
			String password, String subscribe) {
		eleUtil.waitForElementToBeVisible(firstNameTxt, Constants.DEFAULT_TIMEOUT).sendKeys(firstName);
		eleUtil.doSendKeys(lastNameTxt, lastName);
		eleUtil.doSendKeys(emailTxt, email);
		eleUtil.doSendKeys(telephoneTxt, telephone);
		eleUtil.doSendKeys(passwordTxt, password);
		eleUtil.doSendKeys(passwordConfirmTxt, password);
		if (subscribe.trim().equalsIgnoreCase("yes")) {
			eleUtil.doClick(yesRbtn);
		} else {
			eleUtil.doClick(noRbtn);
		}
		eleUtil.doClick(privacyPolicyCkb);
		eleUtil.doClick(contiueBtn);
		if (getAccountRegisterSuccessMsg().contains(Constants.REGISTER_SUCCESS_MSG)) {
			goToRegisterPage();
			return true;
		}
		return false;

	}

	@Step("get account register success message")
	public String getAccountRegisterSuccessMsg() {
		return eleUtil.waitForElementToBeVisible(successMsg, Constants.DEFAULT_TIMEOUT).getText();
	}

	@Step("go to register page")
	private void goToRegisterPage() {
		eleUtil.doClick(logoutLnk);
		eleUtil.waitForElementToBeVisible(registerLnk, Constants.DEFAULT_TIMEOUT).click();
	}

}
