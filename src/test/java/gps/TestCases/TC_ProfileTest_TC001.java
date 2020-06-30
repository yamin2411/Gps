package gps.TestCases;

import java.io.IOException;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import gps.Base.BaseClass;
import gps.PageObjects.ProfilePage;
import gps.Utilities.ReadFromProperties;

public class TC_ProfileTest_TC001 extends BaseClass {
	ProfilePage pp = new ProfilePage();

	
	@Parameters("ReadFrom")    // Passing Parameter to Read User name and Password from Excel or Config
	@BeforeClass
	public void loginSetup(String ReadFrom) throws IOException 
	{
		/* Below code Create object of LoginTest_TC001 class and call loginTest method to login to GPS
		 * and check whatzNew window after login and close if present
		 */
		TC_LoginTest_TC001 tp = new TC_LoginTest_TC001();
		tp.testLogin(ReadFrom);
		isWhatzNewWindowShown();
	}

	@Test(priority = 11)
	public void testEmployeeId() {
		test = extent.createTest("VerifyEmployeeId");
		
		/*Below Code Move to Profile Page HR tab and compare EmployeePayroll with EmployeePayroll in 
		 * Config file and if matched will mark Test case Pass
		 */
		pp.GotoHRProfileMenu();
		String EmployeeIdFromGoalSheet = pp.GetEmployeeId();
		String EmployeeIdfromConfig = ReadFromProperties.read("EmployeePayroll");
		if (EmployeeIdFromGoalSheet.equals(EmployeeIdfromConfig)) {
			logger.info("VerifyEmployeeId: Correct Employee Id shown");
			Assert.assertTrue(true);
		} else {
			logger.info("VerifyEmployeeId: Incorrect Employee Id shown");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 12)
	public void testemployeeContactInfo() {
		test = extent.createTest("VerifyEmployeeContactInfo");
		
		/*Below Code Move to Profile Page HR tab and Get Employee Contact info
		 * from Contact details Section and if all details found will mark Test Casee Pass
		 */
		pp.GotoHRProfileMenu();
		ArrayList<String> ContactInfo_GoalSheet = pp.GetEmployeeContactInfo();

		for (int i = 0; i < ContactInfo_GoalSheet.size(); i++) {
			if (ContactInfo_GoalSheet.get(i).isEmpty()) {
				logger.info("GetEmployeeContactInfo: One or more Employee Contact Info Missing");
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}

		}
		logger.info("EmployeeContactInfo : Skype Id =  " + ContactInfo_GoalSheet.get(0) + ", Email Id = "
				+ ContactInfo_GoalSheet.get(1) + ", MobileNo = " + ContactInfo_GoalSheet.get(2));
	}

}
