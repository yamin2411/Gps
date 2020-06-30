package gps.Utilities;
import java.io.FileReader;
import java.util.Properties;

public class ReadFromProperties {
	
	public static String read(String propertyName)
	{
		String PropertyValue="";
		
		try {
		FileReader reader = new FileReader("./src/test/java/gps/Configurations/Config.properties");
	    Properties props = new Properties();
	    props.load(reader);
	    PropertyValue=props.getProperty(propertyName);
	    }
		catch(Exception e)
		{
			System.out.print("Exception is "+e.getMessage());
		}
		return PropertyValue;
		
		
	}

}
