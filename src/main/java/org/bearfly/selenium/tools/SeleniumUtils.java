package org.bearfly.selenium.tools;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumUtils {
	private static WebDriver driver;
	private static String url = "http://www.google.com";
	private static WebElement curElement;
	static {
		try {
			Properties props = new Properties();
			props.load(ClassLoader.getSystemClassLoader().getResourceAsStream("Application.properties"));
			System.setProperty("webdriver.gecko.driver", props.getProperty("webdriver.gecko.driver"));
			System.setProperty("webdriver.firefox.bin", props.getProperty("webdriver.firefox.bin"));
			driver = new FirefoxDriver();
		} catch (IOException e) {
			e.printStackTrace();
		}

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

}
