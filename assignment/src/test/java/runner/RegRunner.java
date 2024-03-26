package runner;


import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import Utils.CommonUtility;
import Utils.ExtentManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * class to interact with page
 * @author Girish Kumar
 *
 * This class has Runner file for Register-And-Login feature file */

@CucumberOptions(
	        features = "src/test/java/features/Register-AND-Login.feature",
	        glue = "StepDefinitions",
	        plugin = {"pretty", "html:target/cucumber"},
	        tags="@Ui_Test")
	
	public class RegRunner extends AbstractTestNGCucumberTests {
		
	public static ExtentTest test;
	public static ExtentReports extent;
	
/**
 *  Below method onTestStart with @BeforeTest annotation runs before every test and initialize the extent reports
 *  It will help to create extent HTML report using runner class name. */
 
	@BeforeTest 
	public void onTestStart()
	{
		String runnerClassName=this.getClass().getSimpleName();
		extent=ExtentManager.getInstance(runnerClassName);
		test=extent.createTest(runnerClassName);
		
	}
	
/**
 *  Below method postCondition runs after every method get update report with,
 *  test result PASS or FAIL including Screenshots
 */
	@AfterMethod(alwaysRun = true)
	public void postCondition(ITestResult res)
	{
		CommonUtility.getExecutionReport(res, test, extent);
		
	}
	
	/**
	 *  Below method onTestStop runs after every test close the testing browsers
	 */
	@AfterTest
	public void onTestStop()
	{
		CommonUtility.closeBrowser();
	}
	
}


