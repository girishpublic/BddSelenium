package runner;

import java.io.IOException;

import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	        features = "src/test/java/Register-AND-Login.feature",
	        glue = "StepDefinitions",
	        plugin = {"pretty", "html:target/cucumber"},
	        tags="@Ui_Test")

	public class RegRunner extends AbstractTestNGCucumberTests {
		
	@AfterMethod
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException
	{
//		if(testResult.getStatus()==ITestResult.FAILURE)
//		{
//			File scrFile=((TakesScreenshot))driver);
//		}
	}
	


}
