package com.PhysicianPortal.Automation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import com.readandwriteexcel.Excel_Read;



public class Login_Page 
{
	WebDriver driver;
	
	//By language=By.xpath("//input[@ng-model='language']");
	By PhyFolder=By.xpath("//a[@href='www/']");
	By username=By.xpath("//input[@ng-model='$parent.userName']");
	By password=By.xpath("//input[@ng-model='$parent.password']");
	By login=By.xpath("//button[@ng-click='loginWithEmailId(myForm)']");
	By language= By.xpath("//div[@class='login-section']/label/select");
	
	public Login_Page(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void LoginToPhysicianPortal() throws IOException, InterruptedException
	{
		Excel_Read excel=new Excel_Read("src\\test\\resources\\files\\PhysicianPortalFeatures.xlsx");
		//highlightElement(driver, driver.findElement(username));
		
		//Select Language
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Select dropdown=new Select(driver.findElement(language));
		dropdown.selectByVisibleText("English");
		
		//Enter username
		driver.findElement(username).sendKeys(excel.getCredentials("Login_Data", 1, 1));
		
		//Enter password
		driver.findElement(password).sendKeys(excel.getCredentials("Login_Data", 1, 2));
		Thread.sleep(2000);
		//Click login button
		driver.findElement(login).click();
		
	}
	
	public WebDriver startBrowser() throws Exception 
	{
		Excel_Read excel = new Excel_Read("src\\test\\resources\\files\\PhysicianPortalFeatures.xlsx");
		String browser = excel.getBrowserName("Login_Data", 1, 3);
		if (browser.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\browserdrivers\\chromedriver1.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", "src\\test\\resources\\browserdrivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("firefox")) 
		{
			System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\browserdrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		
		driver.get(excel.getURL("Login_Data", 1, 0));
		Thread.sleep(2000);
		driver.findElement(PhyFolder).click();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Browser open successfully and page title is: " + driver.getTitle());
		return driver;
	}

}
