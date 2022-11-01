package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String LOGIN_PAGE_URL = "https://demo.opencart.com/index.php?route=account/login&language=en-gb";
	public static final String LOGIN_PAGE_FRACTION_URL ="route=account/login";
	public static final String ACCOUNTS_PAGE_TITLE ="My Account";
	public static final List<String> EXP_ACC_HEADER_LIST = Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	public static final int DEFAULT_TIMEOUT =5;
	public static final Object MACBOOK_PRODUCT_COUNT =3;
	public static final List<String> MACBOOK_PRODUCT_LIST =Arrays.asList("MacBook","MacBook Air","MacBook Pro");
	public static final Object IMAC_PRODUCT_COUNT =1;
	
	public static final Object MACBOOK_PRO_IMG_COUNT =4;
	public static final String MACBOOKPRO_PRODUCT_CODE ="Product 18";
	public static final String MACBOOKPRO_BRAND ="Apple";
	public static final String MACBOOKPRO_REWARD_POINTS ="800";
	public static final String MACBOOKPRO_AVAILABILITY ="In Stock";
	public static final String MACBOOKPRO_PRICE ="$2,000.00";
	public static final String PRODUCT_QTY ="2";
	public static final String PRODUCT_SUCCESS_MSG ="Success: You have added ";
	public static final CharSequence REGISTER_SUCCESS_MSG ="Your Account Has Been Created";
	public static final String REGISTER_SHEET_NAME = "registerData";
	public static final String PRODUCT_SHEET_NAME = "productData";
	public static final String TEST_DATA_SHEET_PATH="./resources/testdata/DemoCartTestData.xlsx";
}
