package gps.Base;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import gps.Report.ExtentReport;
import gps.Utilities.ReadFromProperties;

public class BaseClass extends ExtentReport{
	
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("Browser")
	@BeforeClass
	public void Browsersetup(String Browser)
	{
		if(Browser.equals("chrome"))
		{
		    System.setProperty("webdriver.chrome.driver",ReadFromProperties.read("Chrome"));
		    driver= new ChromeDriver();
		}
		else if(Browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",ReadFromProperties.read("Firefox"));
			driver= new FirefoxDriver();
		}
		else
		{
			System.setProperty("webdriver.ie.driver",ReadFromProperties.read("InternetExplorer"));
			driver= new InternetExplorerDriver();
		}
		
		logger=Logger.getLogger("GPS");
		PropertyConfigurator.configure("log4j.properties");
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void isWhatzNewWindowShown()
	{
		String Parentwindow = driver.getWindowHandle();
		Set<String> windows= driver.getWindowHandles();
		int noOfWindows = windows.size();
		if(noOfWindows>1)
		{
			for(String s: windows)
			{
				driver.switchTo().window(s);
			}
			
			driver.close();
			driver.switchTo().window(Parentwindow);
	}
		
		
	}
	
	public WebElement getElement(WebDriver driver, WebElement element, By locator)
	{
		return driver.findElement(locator);
	}
}


