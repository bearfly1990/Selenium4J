package org.bearfly.selenium.tools;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumUtils {
	private static WebDriver driver;
	private static String url = "http://www.google.com";
	private static WebElement curElement;
	static {
		// System.setProperty("webdriver.gecko.driver",
		// AppConfiguration.getProperty("webdriver.gecko.driver"));
		// System.setProperty("webdriver.firefox.bin",
		// AppConfiguration.getProperty("webdriver.firefox.bin"));
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public static void openBrowser() {
		driver.get(url);
	}

	public static void maxWindow() {
		driver.manage().window().maximize();
	}

	public static void openBrowser(String url) {
		driver.get(url);
	}

	public static WebElement findElementByID(String id) {
		curElement = driver.findElement(By.id(id));
		return curElement;
	}

	public static WebElement findElementByName(String name) {
		curElement = driver.findElement(By.name(name));
		return curElement;
	}

	public static WebElement findElementByLinkText(String linkText) {
		curElement = driver.findElement(By.linkText(linkText));
		return curElement;
	}
	
	public static WebElement findElementByXPath(String xpath) {
		driver.findElement(By.xpath(xpath));
		return curElement;
	}
	public static void clickByID(String id) {
		findElementByID(id).click();
	}

	public static void inputValue(String content) {
		if (curElement != null) {
			curElement.sendKeys(content);
		}
	}

	public static void clickByName(String name) {
		findElementByName(name).click();
	}

	public static void inputValue(String target, String content) {
		findElementByString(target).sendKeys(content);
	}

	public static WebElement findElementByString(String str) {
		switch (str.charAt(0)) {
		case '#':
			curElement = SeleniumUtils.findElementByID(str.substring(1));
			break;
		case '@':
			curElement = SeleniumUtils.findElementByName(str.substring(1));
			break;
		case '&':
			curElement = SeleniumUtils.findElementByLinkText(str.substring(1));
		}

		return curElement;
	}

}
