package com.PhysicianPortal.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.PhysicianPortal.Automation.Login_Page;



public class PhysicianPortalFeatures 
{
	public static WebDriver driver;
	
	@Test(priority = 1)
	public void login() throws Exception 
	{
		Login_Page login_page = new Login_Page(driver);
		driver =login_page.startBrowser(); 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		login_page.LoginToPhysicianPortal();
	}

  
}
