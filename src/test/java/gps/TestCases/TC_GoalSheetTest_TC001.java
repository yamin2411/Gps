package gps.TestCases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import gps.Base.BaseClass;
import gps.PageObjects.GoalSheetPage;

public class TC_GoalSheetTest_TC001 extends BaseClass {
	
	
	GoalSheetPage gp = new GoalSheetPage();

	@Parameters("ReadFrom") // Passing Parameter to Read Username and Password for Login either From
							// ExcelSheet or Config File
	@BeforeClass
	public void loginSetup(String ReadFrom) throws IOException {
		
		/* Below code Create object of LoginTest_TC001 class and call loginTest method to login to GPS
		 * and check whatzNew window after login and close if present
		 */
		TC_LoginTest_TC001 tp = new TC_LoginTest_TC001();
		tp.testLogin(ReadFrom);
		isWhatzNewWindowShown();

	}

	@Test(priority = 2)
	public void testGoalSheetDate() {
		test = extent.createTest("checkGoalSheetDateOnLogin");
		SimpleDateFormat df = new SimpleDateFormat("EEEE, MMMM d, yyyy");
		Date dateobj = new Date();

		/* CompareGoalSheetDate method from GoalSheet Page Class will check if current
		 * date is shown on Goalsheet after login
		 */
		if (gp.compareGoalSheetDate(df.format(dateobj))) {
			logger.info("CurrentDateCheck: Correct date shown on GoalSheet");
			Assert.assertTrue(true);

		} else {
			logger.info("CurrentDateCheck: Incorrect date shown on GoalSheet");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 3)
	public void testEmployeeLead() {
		test = extent.createTest("checkEmployeeLead");

		// CountEmployeeLead method checks if Employee has any lead added or not
		if (gp.countEmployeeLead() != 0) {
			logger.info("CheckEmployeeLead: Lead Name shown for the Employee");
			Assert.assertTrue(true);
		} else {
			logger.info("CheckEmployeeLead: Lead Name not shown for the Employee");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 4)
	public void testEmployeeFulltimeProject() {
		test = extent.createTest("checkEmployeeFulltimeProject");

		/* EmployeeFull Time Project will return employee's full Time Project Name from
		 * GoalSheet
		 */
		if (gp.employeeFullTimeProject() != null) {
			logger.info("EmployeeFullTimeProject: Employee is currently working on project- "
					+ gp.employeeFullTimeProject());
			Assert.assertTrue(true);
		} else {

			logger.info("EmployeeFullTimeProject: Employee is not currently working on any project");
			Assert.assertTrue(false);
		}
	}

}
