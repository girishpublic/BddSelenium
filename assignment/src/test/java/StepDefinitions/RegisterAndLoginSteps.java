package StepDefinitions;

import static org.testng.Assert.*;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import Utils.CommonUtility;
import Utils.CucumberRunner;
import Utils.ExtentManager;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import pageObjects.RegistrationAndLogin_PageObjects;
import runner.RegRunner;

public class RegisterAndLoginSteps extends CommonUtility {
	WebDriver driver;
	  HashMap<String, String> userDetails;
	  
	@Given("I read excel file {string} and sheet {string}")
	public void i_read_excel_file_and_sheet(String excelPath, String sheetName) {
	    // Reading data from Excel ( . xlsx ) file and  hash Map 
		userDetails = excelFileToMap(excelPath, sheetName);
		System.out.println(userDetails);
		RegRunner.test.log(Status.PASS, "i read excel file and sheet");
	  

	}

	@When("User is on the landing page")
	public void user_is_on_the_landing_page() {
		// Get driver and assign it to WebDriver 
		// Open the Chrome Browser. He we can modify the browser
	    driver=CucumberRunner.getChromeDriver();
	    RegRunner.test.log(Status.PASS, "User is on the landing page");
	}

	@And("User clicks Sign In")
	public void user_clicks_sign_in() {
	    click(RegistrationAndLogin_PageObjects.LOGIN_LINK , 10, driver);
	    RegRunner.test.log(Status.PASS, "User clicks Sign In");
	    
	}

	@And("User select Register and click Continue")
	public void user_creates_account_with() {
		
		click(RegistrationAndLogin_PageObjects.REGISTER_RADIO_BTN,10,driver);
		click(RegistrationAndLogin_PageObjects.CONTINUE_BTN,10,driver);
		RegRunner.test.log(Status.PASS, "User select Register and click Continue");
		
	    
	}

	@When("User enters personal information and clicks Register")
	public void user_enters_personal_information_with_and_clicks_register() {
		
	    setText(RegistrationAndLogin_PageObjects.FIRST_NAME,10,driver,userDetails.get("FirstName"));
	    setText(RegistrationAndLogin_PageObjects.LAST_NAME,10,driver,userDetails.get("LastName"));
	    setText(RegistrationAndLogin_PageObjects.EMAIL,10,driver,userDetails.get("eMail"));
	    setText(RegistrationAndLogin_PageObjects.TELEPHONE,10,driver,userDetails.get("Telephone"));
	    setText(RegistrationAndLogin_PageObjects.FAX,10,driver,userDetails.get("Fax"));
	    setText(RegistrationAndLogin_PageObjects.COMPANY,10,driver,userDetails.get("Company"));
	    setText(RegistrationAndLogin_PageObjects.ADDRESS1,10,driver,userDetails.get("Address1"));
	    setText(RegistrationAndLogin_PageObjects.ADDRESS2,10,driver,userDetails.get("Address2"));
	    setText(RegistrationAndLogin_PageObjects.CITY,10,driver,userDetails.get("City"));
	    selectDropdownByVisibleText(driver, RegistrationAndLogin_PageObjects.COUNTRY, userDetails.get("Country"));
	    setText(RegistrationAndLogin_PageObjects.LAST_NAME,10,driver,userDetails.get("LastName"));
	    setText(RegistrationAndLogin_PageObjects.ZIPCODE,10,driver,userDetails.get("ZIP"));
	    selectDropdownByVisibleText(driver, RegistrationAndLogin_PageObjects.REGION_OR_STATE, userDetails.get("State"));
	    setText(RegistrationAndLogin_PageObjects.LOGIN_NAME,10,driver,userDetails.get("LoginName"));
	    
	    setText(RegistrationAndLogin_PageObjects.PASSWORD,10,driver,userDetails.get("Password"));
	    setText(RegistrationAndLogin_PageObjects.PASSWORD_CONFIRM,10,driver,userDetails.get("PasswordConfirm"));
	    click(RegistrationAndLogin_PageObjects.SUBSCRIBE_RADIO_BTN_YES,10,driver);
	    click(RegistrationAndLogin_PageObjects.PRIVACY_POLICY_CHK_BOX,10,driver);
	    click(RegistrationAndLogin_PageObjects.CONTINUE_BTN,10,driver);
	    click(RegistrationAndLogin_PageObjects.REG_COMPLETE_CONTINUE,10,driver);
	    RegRunner.test.log(Status.PASS, "User enters personal information and clicks Register");
		
	}

	@When("User validates correct name and surname on landing screen")
	public void user_validates_correct_name_and_surname_on_landing_screen() {
		
		String xpath=HomePage.USER;
		String formattedXpath=String.format(xpath,userDetails.get("FirstName"));
		System.out.println(" After format String " +formattedXpath);
		assertTrue(validateElementPresent(formattedXpath, driver));
		 RegRunner.test.log(Status.PASS, "User validates correct name and surname on landing screen");
	   
	}

	@When("User adds a product to the cart")
	public void user_adds_a_product_to_the_cart() throws InterruptedException {
		driver.navigate().refresh();
		actionMouseHover(driver, HomePage.FRAGRANCE);
		click(HomePage.PRODUCT_MEN,20,driver);
		click(HomePage.ONE_SHOCK,20,driver);
		click(HomePage.ADD_TO_CART,10,driver);
		RegRunner.test.log(Status.PASS, "User adds a product to the cart");
	}

	@When("User proceeds to checkout")
	public void user_proceeds_to_checkout() {
		
		click(HomePage.CHECKOUT,10,driver);
		RegRunner.test.log(Status.PASS, "User proceeds to checkout");
		
	    
	}

	@When("User continues till payments")
	public void user_continues_till_payments() {
		
		Assert.assertEquals(getTextFromElement(driver, HomePage.PAYMENT_PAGE_TITLE, 10).trim(),"CHECKOUT CONFIRMATION");
		RegRunner.test.log(Status.PASS, "User continues till payments");
		
	
	}

	@Then("User validates product details on payments page")
	public void user_validates_product_details_on_payments_page() {
		
		Assert.assertEquals(getTextFromElement(driver, HomePage.VALIDATE_PRODUCT,10),userDetails.get("PRODUCT"));
		click(HomePage.ProductConfirm,10,driver);
		RegRunner.test.log(Status.PASS, "User validates product details on payments page");
	    
	}


}
