package gps.Utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFromExcel 
{
	public static ArrayList<String> ReadExcel(String SheetPath, String Sheet, Integer row) 
	{
		ArrayList<String> list = new ArrayList<String>(); 
		try 
		{
			FileInputStream fread= new FileInputStream(SheetPath);
		    XSSFWorkbook xs= new XSSFWorkbook(fread);
		    XSSFSheet sheetName = xs.getSheet(Sheet);
		    if(Sheet=="Users") 
		    {
		    	list.add(sheetName.getRow(row).getCell(0).getStringCellValue());
		        list.add(sheetName.getRow(row).getCell(1).getStringCellValue());
		    }
		    else if(Sheet=="Goals")
		    {
		    	list.add(sheetName.getRow(row).getCell(0).getStringCellValue());
			    list.add(sheetName.getRow(row).getCell(1).getStringCellValue());
		    }
		
		    else if (Sheet=="Comments of the Day")
		        list.add(sheetName.getRow(row).getCell(0).getStringCellValue());
		    
		    else if(Sheet.equals("MessageData"))
		    {
		    	list.add(sheetName.getRow(row).getCell(0).getStringCellValue());
		        list.add(sheetName.getRow(row).getCell(1).getStringCellValue());
		        list.add(sheetName.getRow(row).getCell(2).getStringCellValue());
		    }
		  xs.close();
		}
		catch(Exception e)
		{
			System.out.print("Exception :"+e.getMessage());
		}
		
		return list;
	}

}

