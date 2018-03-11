package org.bearfly.selenium.tools;

import java.io.IOException;
import java.util.Properties;

public class AppConfiguration {
	private static Properties  props = new Properties();
	static {
		try {
			props.load(ClassLoader.getSystemClassLoader().getResourceAsStream("AppConfig.properties"));
			System.setProperty("webdriver.gecko.driver", props.getProperty("webdriver.gecko.driver"));
			System.setProperty("webdriver.firefox.bin", props.getProperty("webdriver.firefox.bin"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void init() {
		
	}
	public static String getProperty(String key) {
		return props.getProperty(key);
	}
}
