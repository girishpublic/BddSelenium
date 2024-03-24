package runner;


import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import Utils.CommonUtility;
import Utils.ExtentManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	        features = "src/test/java/features/Register-AND-Login.feature",
	        glue = "StepDefinitions",
	        plugin = {"pretty", "html:target/cucumber"},
	        tags="@Ui_Test")
	
	public class RegRunner extends AbstractTestNGCucumberTests {
		
	public static ExtentTest test;
	public static ExtentReports extent;
	

	
	@BeforeTest
	public void onTestStart()
	{
		String runnerClassName=this.getClass().getSimpleName();
		extent=ExtentManager.getInstance(runnerClassName);
		test=extent.createTest(runnerClassName);
		
	}
	@AfterMethod(alwaysRun = true)
	public void postcondition(ITestResult res)
	{
		CommonUtility.getExecutionReport(res, test, extent);
		CommonUtility.closeBrowser();
	}
	
}


