package org.bearfly.selenium;

import org.bearfly.selenium.models.TestSuite;
import org.bearfly.selenium.tools.AppConfiguration;


/**
 * Hello world!
 *
 */
public class App 
{
	static {
		AppConfiguration.init();
	}
    public static void main( String[] args )
    {
        //new BaiduDemo().run();
    	new TestSuite().run();
    }
}
