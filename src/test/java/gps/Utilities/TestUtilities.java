package gps.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

public class TestUtilities 
{
	 public static String ScreenshotAdd(WebDriver driver, String MethodName) throws IOException
		{
		    File Src =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    SimpleDateFormat df = new SimpleDateFormat("MMM,d yyyy hh-mm-ss");
		    Date Currentdate= new Date();
		    String Destination= "./Screenshots/"+MethodName+"_"+df.format(Currentdate)+".png";
		    File Dest= new File(Destination);
		    FileUtils.copyFile(Src, Dest);
			return Destination;
		}
	 
	 public static String getCurrentDateTime() {
			
			DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");  //MM_dd_yyyy_HH_mm_ss
			Date currentDate = new Date();
			
			return customFormat.format(currentDate);
		}

}
