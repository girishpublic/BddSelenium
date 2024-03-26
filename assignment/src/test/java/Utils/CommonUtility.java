package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

/**
 * class to define Common Utility methods which are repetitive 
 * @author Girish Kumar
 *
 * This class has a many utility methods such as click, setText(Sendkeys), check visibility etc..  
 * We can use the methods of this directly by using classname
 */

public class CommonUtility {
	
	// click the element with given implicitly wait duration 
	public void click(By element,int seconds, WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seconds));
		 WebElement ele = driver.findElement(element);
		 if(ele.isDisplayed())
		 {
			 ele.click();
		 }
		 
	}
	
	// enter the text into textbox with given implicitly wait duration
	public void setText(By element,int seconds, WebDriver driver, String givenText)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		WebElement ele = driver.findElement(element);
		 if(ele.isDisplayed())
		 {
			 ele.clear();
			 ele.sendKeys(givenText);
		 }
		
	}
	
	// Read the excel (.xlsx ) sheet row by row and add first column as key and second as value
	public HashMap<String, String >  excelFileToMap(String absolutePathURL, String sheetName )
	{
		
		  HashMap<String, String> dataMap = new HashMap<String, String>();
	        try {
	            FileInputStream file = new FileInputStream(absolutePathURL);
	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	            XSSFSheet sheet = workbook.getSheet(sheetName);

	            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	                XSSFRow row = sheet.getRow(i);
	                String key = row.getCell(0).getStringCellValue();
	                String value = row.getCell(1).getStringCellValue();
	                dataMap.put(key, value);
	            }

	            workbook.close();
	            file.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return dataMap;
	    }
	

   public void selectDropdownByVisibleText(WebDriver driver,By element,String text)
   {
	   WebElement ele = driver.findElement(element);
	   Select select = new Select(ele);
	   select.selectByVisibleText(text);
	   
	   
   }
   
   public String formatString(String xpath,String text) {
	   return String.format(xpath,text);
   }
   
   public boolean validateElementPresent(String given,WebDriver driver)
   {
	   WebElement ele=driver.findElement(By.xpath(given));
	   WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	   wait.until(ExpectedConditions.visibilityOf(ele));
	   return ele.isDisplayed();
   }
   
   public void actionMouseHover(WebDriver driver, By element)
   {
	   try {
		   WebElement elementToHover = driver.findElement(element);
		   WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		   wait.until(ExpectedConditions.visibilityOf(elementToHover));
		   Actions actions = new Actions(driver);
		   actions.moveToElement(elementToHover).perform();
		   Thread.sleep(5000);
		
	   	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   	}
   }
   
   public String getTextFromElement(WebDriver driver, By element, int timeToWait)
   {
		   WebElement elementText = driver.findElement(element);
		   WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		   wait.until(ExpectedConditions.visibilityOf(elementText));
		   return elementText.getText();
		   
   }
   
   public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {

		//TakesScreenshot ts = (TakesScreenshot) driver;
		//String source =ts.getScreenshotAs(OutputType.BASE64);
		
		String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
     
		return base64Screenshot;
		
	}
   
   // Get execution results based on ITestResult
   public static void  getExecutionReport(ITestResult res, ExtentTest logger, ExtentReports extent)
   {
	   try {
			int status = res.getStatus();
			String name =res.getName();
			String screenshotPath = CommonUtility.getScreenshot(CucumberRunner.driver, name);
			
			if(status==ITestResult.FAILURE)
			{
				
				logger.log(Status.FAIL, MarkupHelper.createLabel(name, ExtentColor.RED).getMarkup(),MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
			}
			else if(res.getStatus()==ITestResult.SUCCESS)
			{
				
				logger.log(Status.PASS,  MarkupHelper.createLabel(res.getName(), ExtentColor.GREEN).getMarkup(), MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
			}
			else if(status==ITestResult.SKIP)
			{
				logger.log(Status.SKIP, MarkupHelper.createLabel(res.getName(), ExtentColor.YELLOW));
			}
			
	}
	catch(Exception e){}

	extent.flush();
   }

public static void closeBrowser() {
	// TODO Auto-generated method stub
	CucumberRunner.driver.quit();
	
}

}
	
	
