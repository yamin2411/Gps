package gps.PageObjects;

import org.openqa.selenium.By;

import gps.Base.BaseClass;

public class LoginPage extends BaseClass{
	
	  //Login Page WebElements
		By loginUserName = By.xpath("//input[@id='txtName']");
		By loginPassword = By.xpath("//input[@id='txtPassword']");
		By loginButton   = By.xpath("//input[@id='btnLogin']");
	
	public void SetUsername(String username)
	{
		driver.findElement(loginUserName).sendKeys(username);
	}
	
	public void SetPassword(String password)
	{
		driver.findElement(loginPassword).sendKeys(password);
	}
	
	public void ClickLoginBtn()
	{
		driver.findElement(loginButton).click();
	}

}
