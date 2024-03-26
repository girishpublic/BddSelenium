package Utils;

import java.util.Collections;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Girish Kumar
 *
 * This class has driver initialization with open browser with capabilities ( options )
 */ 

public class CucumberRunner {
	
	public static WebDriver driver=null;
	
	// get chrome driver instance and open the browser with given URL 
	public static WebDriver getChromeDriver()
	{
		WebDriverManager.chromedriver().setup();
	    ChromeOptions options=new ChromeOptions();
	    options.addArguments("--remote-allow-origins=*");
	    options.addArguments("--start-maximized");
	    options.addArguments("--disable-dev-shm-usage");
	    options.addArguments("--disable-extensions");
	    options.addArguments("--disable-infobars");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-popup-blocking");
	    options.addArguments("--disable-notifications");
	    options.addArguments("incognito");
	    options.setExperimentalOption("prefs", 
	      Collections.singletonMap("profile.default_content_setting_values.autofill", 1));
        driver = new ChromeDriver(options);
        driver.get("https://automationteststore.com");
        driver.manage().window().maximize();
        return driver;
	}

	// get firefox driver instance and open the browser with given URL 
	  public static WebDriver getFirefoxDriver(FirefoxOptions firefoxOptions) {
		  WebDriverManager.firefoxdriver().setup();
    	System.setProperty("webdriver.gecko.driver", "./DriverExecutable/geckodriver.exe");
        driver=new FirefoxDriver();
        return driver;
	}
	  
}
