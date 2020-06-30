package gps.TestCases;

import static gps.Utilities.Constants.MessageSheet;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import gps.Base.BaseClass;
import gps.PageObjects.MessagesPage;
import gps.Utilities.ReadFromExcel;

public class TC_MessageTest_TC002 extends BaseClass {
	MessagesPage mp = new MessagesPage();

	@Parameters("ReadFrom") // Passing Parameter to Read User name and Password for Login either From
							// ExcelSheet or Config File
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

	@Test(priority = 10)
	public void testMessageSendToOther() throws InterruptedException 
	{
		test = extent.createTest("SendMessageToOther");
		/*Below Code moves to New Message Menu and composeMsg method send message to Selected People(Included self)
		 *  provided and returns boolean value and as per returned value test case will be marked failed/pass
		 */
		
		mp.goToNewMessageMenu();
		ArrayList<String> MsgDetails = ReadFromExcel.ReadExcel(MessageSheet, "MessageData", 2);

		Boolean MesageSentflag = mp.composeMsg("Other", MsgDetails.get(2), MsgDetails.get(0), MsgDetails.get(1));
		if (MesageSentflag == true) {
			logger.info("ComposeMsgOther: Message Sent Successfully");
			Assert.assertTrue(true);
		} else {
			logger.info("ComposeMsgOther: Message Sending Failed");
			Assert.assertTrue(false);
		}
	}

}
