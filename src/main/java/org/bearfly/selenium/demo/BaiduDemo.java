package org.bearfly.selenium.demo;

import org.apache.log4j.Logger;
import org.bearfly.selenium.App;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaiduDemo {
	private static final Logger logger = Logger.getLogger(App.class);

	public void run() {
		System.setProperty("webdriver.gecko.driver", "D:\\Programs\\Mozilla Firefox\\geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "D:\\Programs\\Mozilla Firefox\\firefox.exe");
		logger.info("start firefox");
		WebDriver driver = new FirefoxDriver();

		logger.info("open baidu!");
		driver.get("http://www.baidu.com");
		driver.manage().window().maximize();

		logger.info("get input id");

		WebElement textInput = driver.findElement(By.id("kw"));
		WebElement submit = driver.findElement(By.id("su"));
		textInput.sendKeys("Selenium");
		submit.click();
		try {
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				//logger.info("Title Return:" + driver.getTitle());
				return driver.getTitle().toLowerCase().startsWith("selenium");
			}
		});
		}catch (Exception e) {
			logger.warn(e);
		}
		logger.info("wait 1 seconds!");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
}
