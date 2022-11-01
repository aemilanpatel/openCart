package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.utils.Browser;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	private Properties prop;
	public static String highlight;
	public OptionsManager optionManger;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static final Logger log=Logger.getLogger(DriverFactory.class);

	/**
	 * this method used to initialize or load properties file on the basis of given
	 * environment :qa/dev/stage/prod
	 * 
	 * @return Properties
	 */
	public Properties init_prop() {

		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");
		System.out.println("Running tests in environment:" + envName);
		log.info("running tests on environment"+envName);

		if (envName == null) {
								System.out.println("NO environment provided so we are running test in qa environment");
								log.info("no env is given,hence running it in qa");
								try {
									ip = new FileInputStream("./resources/config/config.properties");
									prop.load(ip);
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		} else 
		{
					try {
							switch (envName.toLowerCase()) {
							case "qa":
								log.info("running it on qa");
								ip = new FileInputStream("./resources/config/qa.config.properties");
								break;
							case "stage":
								log.info("running it on stage");
								ip = new FileInputStream("./resources/config/stage.config.properties");
								break;
							case "dev":
								ip = new FileInputStream("./resources/config/dev.config.properties");
								break;
							case "uat":
								ip = new FileInputStream("./resources/config/uat.config.properties");
								break;
							case "prod":
								ip = new FileInputStream("./resources/config/config.properties");
								break;
							default:
								System.out.println("please pass the right environment " + envName);
								log.error("please pass the right environment"+envName);
								log.warn("env name is not found");
								log.fatal("env is not found");
								break;
							}
					} catch (Exception e) {
					}

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * this method used to initialize the webdriver on the basis of given browser
	 * name this method will take care of local and remote execution
	 * 
	 * @param browserName
	 * @return WebDriver
	 */
	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser").trim();
		System.out.println("broser name is :" + browserName);
		log.info("browser name is:"+browserName);
		highlight = prop.getProperty("highlight").trim();

		optionManger = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase(Browser.CHROME_BROWSER_VALUE)) {
			log.info("running on chrome browser");
			//System.setProperty(Browser.CHROME_DRIVER_BINARY_KEY,Browser.CHROME_DRIVER_PATH);
			WebDriverManager.chromedriver().setup();
			// driver=new ChromeDriver(optionManger.getChromeOption());
			tlDriver.set(new ChromeDriver(optionManger.getChromeOption()));
		} else if (browserName.equalsIgnoreCase(Browser.FIREFOX_BROWSER_VALUE)) {
			WebDriverManager.firefoxdriver().setup();
			// driver=new FirefoxDriver(optionManger.getFirefoxOption());
			tlDriver.set(new FirefoxDriver(optionManger.getFirefoxOption()));
		} else if (browserName.equalsIgnoreCase(Browser.SAFARI_BROWSER_VALUE)) {
			// driver=new SafariDriver();
			tlDriver.set(new SafariDriver());
		} else if (browserName.equalsIgnoreCase(Browser.EDGE_BROWSER_VALUE)) {
			WebDriverManager.edgedriver().setup();
			// driver=new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		} else
			System.out.println("please pass the right browser name :" + browserName);

		// driver.manage().deleteAllCookies();
		getDriver().manage().deleteAllCookies();

		// driver.manage().window().maximize();
		getDriver().manage().window().maximize();

		// driver.get(prop.getProperty("url"));
		getDriver().get(prop.getProperty("url"));

		
		// return driver;
		return getDriver();
	}

	/**
	 * this will return thread local copy of WebDriver(driver)
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot" + System.currentTimeMillis() + ".png";
		File destFile = new File(path);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
