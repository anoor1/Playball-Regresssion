package com.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;


public class BaseClass {
	public static WebDriverWait wait;
	public static Properties prop;
	public static int timestamp = (int) (new Date().getTime()/1000);
  
   public static WebDriver driver=null;

	public static final Logger log = LoggerFactory.getLogger(BaseClass.class);
	
	public static void launchDriver()
	{
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/resources/config.properties");
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("Initialization Test...");
		log.info("TimeStamp: "+timestamp);
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		//chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("--window-size=1920,1080");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		
	}

	public static void tearDown()
	{
		log.info("::::Active Browser is close::::");
		driver.quit();
		log.info("::::All Browser is quit::::");
	}

}
