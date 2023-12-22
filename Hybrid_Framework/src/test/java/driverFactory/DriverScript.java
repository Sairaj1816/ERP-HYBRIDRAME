package driverFactory;

import java.util.Iterator;

import org.openqa.selenium.WebDriver;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript {
	public static WebDriver driver;
	String inputpath = "./FileInput/Controller.xlsx";
	String outputpath = "./FileOutput/HybridResults.xlxs";
	String TestCases = "MasterTestCases";
	public void startTest() throws Throwable
	{
		String Module_status = "";
		// create object for Exfile util class
		ExcelFileUtil xl =  new ExcelFileUtil(inputpath);
		//iterate all rows in testcases sheet
		for(int i=1;i<xl.rowCount(TestCases);i++)			
			{
			//read execution cell
			String Execution_status = xl.getCellData(TestCases, i, 2);
			if(Execution_status.equalsIgnoreCase("Y")) 
			{
			//store all sheets into TCmodule
				String TCmodue = xl.getCellData(TestCases, i, 1);
				// iterate all rows in TCmodule
				for(int j =1;j<xl.rowCount(TCmodue);j++)
				{
					//read cell from TCmodule sheet
					String Description = xl.getCellData(TCmodue, j, 0);
					String Object_Type = xl.getCellData(TCmodue, j, 1);
					String Locator_Type = xl.getCellData(TCmodue, j, 2);
					String Locator_Value = xl.getCellData(TCmodue, j, 3);
					String Test_Data = xl.getCellData(TCmodue, j, 4);
					try {
					if(Object_Type.equalsIgnoreCase("startBrowser")) {
						driver = FunctionLibrary.startBrowser();
						
					}
					if(Object_Type.equalsIgnoreCase("openUrl"))
						 FunctionLibrary.OpenUrl (driver);
					{
				if(Object_Type.equalsIgnoreCase("waitForElement"))
			FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
				{									
								 }
				if(Object_Type.equalsIgnoreCase("typeAction")) 
				{
				FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
				
				}
				if(Object_Type.equalsIgnoreCase("clickAction")) {
					FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
					
				}
				if(Object_Type.equalsIgnoreCase("validateTitle")) {
					FunctionLibrary.validateTitle(driver, Test_Data);
					
				}
				if (Object_Type.equalsIgnoreCase("closeBrowser")) {
				}
				FunctionLibrary.closeBrowser(driver);
				}
					//write as pass into status cel in TCmodle
					xl.setCellData(TCmodue, j, 5, "Pass", outputpath);
					Module_status = "True";
					
							
				} catch (Exception e) 
					{
						System.out.println(e.getMessage());
						// write as fail into status cell in TCmodule
						xl.setCellData(TCmodue, j, 5, "Pass", outputpath);
						Module_status= "False";
		}
					if (Module_status.equalsIgnoreCase("True"))
					{
						//write as pass into testcases
						xl.setCellData(TestCases, i, 3, "Pass", outputpath);
					}
					if (Module_status.equalsIgnoreCase("False"))
					
					{
						// write as fail into testcases
						xl.setCellData(TestCases, i, 3, "Fail", outputpath);
						
						
						
					}
					}
				}
					else
			
		{
				// write as blocked into status cell in Testcase sheet for flag N
				xl.setCellData(TestCases, i, 3,"Blocked", outputpath);
				
}
			
	}
			}
	}
		
	
	
	
	


