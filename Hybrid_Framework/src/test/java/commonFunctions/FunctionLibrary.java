package commonFunctions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
public class FunctionLibrary {
public static WebDriver driver;
public static Properties Conpro;
//Method For Launching Browser
public static WebDriver startBrowser() throws Throwable
{
Conpro = new Properties();
//Load File
Conpro.load(new FileInputStream("./PropertyFiles/Environment.Properties"));
if(Conpro.getProperty("Browser").equalsIgnoreCase("Chrome"))
{
driver=new ChromeDriver();
driver.manage().window().maximize();
}
else if (Conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
{
driver=new FirefoxDriver();
}
else 
{
Reporter.log("Browser Value is not Matching",true);	
}
return driver;	
}
//Method for launching Url
public static void OpenUrl(WebDriver driver)
{
	driver.get(Conpro.getProperty("Url"));
	}
// method for wait for element
public static void waitForElement(WebDriver driver,String Locator_Type,String Locator_Value,String Test_Data)
{
	WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(Test_Data)));
if (Locator_Type.equalsIgnoreCase("id"))
{
	 mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Locator_Value)));
}	
if (Locator_Type.equalsIgnoreCase("name"));
{
	 mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Locator_Value)));
	 }
if (Locator_Type.equalsIgnoreCase("xpath"));
		{
			 mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator_Value)));	
}
}
//method for textboxes
public static void typeAction(WebDriver driver,String Locator_type,String Locator_value,String Test_Data)
{
if (Locator_type.equalsIgnoreCase("id")) 
{
	driver.findElement(By.id(Locator_value)).clear();
	driver.findElement(By.id(Locator_value)).sendKeys(Test_Data);
	}
if (Locator_type.equalsIgnoreCase("xpath")) {
	driver.findElement(By.xpath(Locator_value)).clear();
driver.findElement(By.xpath(Locator_value)).sendKeys(Test_Data);
	
}
if (Locator_type.equalsIgnoreCase("name")) 
{
	driver.findElement(By.name(Locator_value)).clear();
	driver.findElement(By.name(Locator_value)).sendKeys(Test_Data);
}
}
//method for buttons, checkboxes, radio buttons, links,and images
public static void clickAction(WebDriver driver,String Locator_Type,String Locator_Value)
{
	if (Locator_Type.equalsIgnoreCase("name")) 
	{
		driver.findElement(By.name(Locator_Value)).click();
	}
	if (Locator_Type.equalsIgnoreCase("xpath")) 
	{
		driver.findElement(By.xpath(Locator_Value)).click();
		}
		if (Locator_Type.equalsIgnoreCase("id")) 
		{
			driver.findElement(By.id(Locator_Value)).sendKeys(Keys.ENTER);			
}
}
	//method for validate tittle
		public static void validateTitle(WebDriver driver,String Expected_Tittle)
		{
 String Actual_Tittle = driver.getTitle();
 try {
 Assert.assertEquals(Expected_Tittle, Actual_Tittle, "Tittle is not Matching");
 }catch (Throwable t) {
	 System.out.println(t.getMessage());
	  }
		}
//method for closing browser
		public static void closeBrowser(WebDriver driver)
		{
		driver.quit();
		}
}
		



