package gps.Report;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import gps.Base.BaseClass;
import gps.Utilities.*;

public class ExtentReport 
{
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
    public static ExtentTest test;
	
    @Parameters("Browser")
	@BeforeSuite
	public void InitReport(String Browser)
	{
		htmlReporter = new ExtentHtmlReporter("./Reports/Report_"+TestUtilities.getCurrentDateTime()+".html");
		extent= new ExtentReports();
		extent.attachReporter(htmlReporter);
		
	    //To add system or environment info by using the setSystemInfo method.
        extent.setSystemInfo("Browser", Browser);
        extent.setSystemInfo("Host name", "localhost");
        extent.setSystemInfo("Environemnt", "Live");
        extent.setSystemInfo("User", "Yamini");
        
        //Configuration items to change the look and feel
     
        htmlReporter.config().setReportName("GPS Test Report");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}
    
	
	@AfterMethod
	public void getResult(ITestResult itr) throws IOException
	{
		/*Below code will display if Test Cases are failed or Passed or SKipped on basis of ITResult Status
		and will capture screenshot in case of Failed Test
		*/
		if(itr.getStatus()==ITestResult.FAILURE)
		{
			test.fail(MarkupHelper.createLabel(itr.getName()+" Test Case Failed", ExtentColor.RED));
			test.fail(itr.getThrowable());
			String ScreenshotPath=TestUtilities.ScreenshotAdd(BaseClass.driver, itr.getName());
			test.addScreenCaptureFromPath("."+ScreenshotPath);
		}
		else if(itr.getStatus()==ITestResult.SUCCESS)
			test.pass(MarkupHelper.createLabel(itr.getName()+" Test Case Passed", ExtentColor.GREEN));

		else
			test.skip(MarkupHelper.createLabel(itr.getName()+" Test Case Skipped", ExtentColor.YELLOW));
	}
	
	
	@AfterSuite
	public void Reportflush()
	{
		extent.flush();
	}

}
