package gps.PageObjects;

import java.util.ArrayList;
import org.openqa.selenium.By;

import gps.Base.BaseClass;

public class ProfilePage extends BaseClass
{
	//Profile Page Elements
	By MyProfileMenu   = By.id("ucGPSMenu_hlnkMyProfile");
	By HRInfoTab       = By.xpath("//span[@id='__tab_TabContainer1_view7']");
	
	//HR Details Section Elements
	By EmployeeIdField = By.id("TabContainer1_view7_lblEmployeeId");
	
	//Contact Details Section Elements
    By Contact_Skype   = By.id("TabContainer1_view7_txtSkypeId");
	By Contact_Email   = By.id("TabContainer1_view7_txtPersonalId");
	By Contact_Mobile  = By.id("TabContainer1_view7_txtMobileNo");
	
	/*=====================================================================================================================*/
	
	//Method to Naviagate to Profile Menu and then Go to HR Info Tab
	public void GotoHRProfileMenu()
	{
		driver.findElement(MyProfileMenu).click();
		driver.findElement(HRInfoTab).click();
	}
	
	//Method to return Employee Id
	public String GetEmployeeId()
	{
		String EmployeeId=driver.findElement(EmployeeIdField).getText();
		return EmployeeId;
	}
	
	//Method to Return Employee Contact Info i.e Skype ID, Email Id , and MobileNo
	public ArrayList<String> GetEmployeeContactInfo()
	{
	   
	   ArrayList<String> ContactList= new ArrayList<String>();
	  
	   ContactList.add(driver.findElement(Contact_Skype).getAttribute("value"));
	   ContactList.add(driver.findElement(Contact_Email).getAttribute("value"));
	   ContactList.add(driver.findElement(Contact_Mobile).getAttribute("value"));
	   
	  return ContactList;
	   
	}

}
