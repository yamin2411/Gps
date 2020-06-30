package gps.TestCases;

import gps.Base.BaseClass;
import gps.PageObjects.LoginPage;
import gps.Utilities.ReadFromExcel;
import gps.Utilities.ReadFromProperties;

import static gps.Utilities.Constants.*;

import java.io.IOException;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_LoginTest_TC001 extends BaseClass {
	String Username = "", Password = "";

	@Parameters("ReadFrom") // Passing Parameter to Read User name and Password from Excel or Config
	@Test(priority = 1)
	public void testLogin(String ReadFrom) throws IOException {
		test = extent.createTest("LoginTest");

		/*
		 * Below if else block reads Username and password either from ExcelSheet or
		 * Config File based on ReadFrom Parameter
		 */
		if (ReadFrom.equals("Excel")) {
			ArrayList<String> locl_list = new ArrayList<String>();
			locl_list = ReadFromExcel.ReadExcel(UserSheet, "Users", 1);
			Username = locl_list.get(0);
			Password = locl_list.get(1);
		} else {
			Username = ReadFromProperties.read("Username");
			Password = ReadFromProperties.read("Password");
		}

		/*
		 * Below Code Opens GPS URL , enter Username and Password and Try to login to
		 * GPS and Compare title with User title Provided in Config file and mark Test Case pass/Failed
		 */

		String URL = ReadFromProperties.read("URL");
		driver.get(URL);
		LoginPage lp = new LoginPage();
		lp.SetUsername(Username);
		logger.info("Entered Username");
		lp.SetPassword(Password);
		logger.info("Entered Password");
		lp.ClickLoginBtn();

		if (driver.getTitle().equals(ReadFromProperties.read("UserTitle") + " | Goal Sheet | www.ourgoalplan.com")) {
			Assert.assertTrue(true);
			logger.info("GPS Login Successful");
		} else {
			Assert.assertTrue(false);
			logger.info("GPS Login Failed");
		}
	}

}
