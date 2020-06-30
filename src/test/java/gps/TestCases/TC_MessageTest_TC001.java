package gps.TestCases;

import static gps.Utilities.Constants.*;

import java.io.IOException;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import gps.Base.BaseClass;
import gps.PageObjects.MessagesPage;
import gps.Utilities.ReadFromExcel;

public class TC_MessageTest_TC001 extends BaseClass {
	MessagesPage mp = new MessagesPage();

	@Parameters("ReadFrom") 
							
	@BeforeClass
	public void loginSetup(String ReadFrom) throws IOException {
		/* Below code Create object of LoginTest_TC001 class and call loginTest method to login to GPS
		 * and check whatzNew window after login and close if present
		 */
		TC_LoginTest_TC001 tp = new TC_LoginTest_TC001();
		tp.testLogin(ReadFrom);
		isWhatzNewWindowShown();
	}

	@Test(priority = 9)
	public void testMessageSendToSelf() throws InterruptedException 
	{
		test = extent.createTest("SendMessagetoSelf");
		
		/*Below Code moves to New Message Menu and composeMsg method send message to Self
		 *  and returns boolean value and as per returned value test case will be marked failed/pass
		 */
		mp.goToNewMessageMenu();
		ArrayList<String> MsgDetails = ReadFromExcel.ReadExcel(MessageSheet, "MessageData", 1);

		Boolean MesageSentflag = mp.composeMsg("Self", "", MsgDetails.get(0), MsgDetails.get(1));
		if (MesageSentflag == true) {
			logger.info("ComposeMsgSelf: Message Sent Successfully");
			Assert.assertTrue(true);
		} else {
			logger.info("ComposeMsgSelf: Message Sending Failed");
			Assert.assertTrue(false);
		}

	}

}
