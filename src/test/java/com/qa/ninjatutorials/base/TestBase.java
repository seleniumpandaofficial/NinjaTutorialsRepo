package com.qa.ninjatutorials.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.ninjatutorials.utilities.Utilities;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static Properties dataprop;
	public static ChromeOptions options;
	public static FirefoxOptions options1;
	
	
	public TestBase() throws Exception{
		prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\ninjatutorials\\propertiesfiles\\config.properties");
	    prop.load(ip);	
	    
	    dataprop = new Properties();
		FileInputStream ip1 = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\ninjatutorials\\propertiesfiles\\testdata.properties");
	    dataprop.load(ip1);	
	}
	
	public WebDriver initializeBrowser(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			options.addArguments("--start-maximized");
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
		}else if(browserName.equalsIgnoreCase("firefox")) {
			options1 = new FirefoxOptions();
			options1.addArguments("-private");
			driver = new FirefoxDriver(options1);
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGELOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}

}
