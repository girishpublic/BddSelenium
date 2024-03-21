package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

public class CommonUtility {
	
	
	public void click(By element,int seconds, WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
		
		 WebElement ele = driver.findElement(element);
		 if(ele.isDisplayed())
		 {
			 ele.click();
		 }
		 
	}
	
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
	  
		Thread.sleep(2000);
	   	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   	}
   }
   
   public String getTextFromElement(WebDriver driver, By element, int timeToWait)
   {
	   try {
		   WebElement elementText = driver.findElement(element);
		   WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		   wait.until(ExpectedConditions.visibilityOf(elementText));
		   Thread.sleep(2000);
		   return elementText.getText();
		   	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   	}
	   return "";
   }

}
	
	
