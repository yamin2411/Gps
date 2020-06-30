package gps.TestCases;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import gps.Base.BaseClass;
import gps.Utilities.*;
public class Listeners extends TestListenerAdapter{
	
	/* In case of any Test Failure screenshot method of testUtilities class will called 
	to take Screenshot and Save to Screenshots Folder
	*/
	public void onTestFailure(ITestResult itr)
	{
		try {
			TestUtilities.ScreenshotAdd(BaseClass.driver,itr.getMethod().getMethodName());
			System.out.println(itr.getMethod().getMethodName()+" Test case failed: Sreenshot Saved to Screenshots Folder");
			} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
