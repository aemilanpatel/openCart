package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class cartPage {

	
	private By cart=By.id("cart");
	
	public void addTocart() {
		System.out.println("add to cart done");
	}
	
	public static void main(String[] args){
		System.out.println("cart page");
	}
}
