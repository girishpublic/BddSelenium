package StepDefinitions;

import static org.testng.Assert.*;

import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.Assert;

import Utils.CommonUtility;
import assignment.HomePage;
import assignment.RegistrationAndLogin_PageObjects;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RegisterAndLoginSteps extends CommonUtility {
	WebDriver driver;
	  HashMap<String, String> userDetails;
	
	
	@Given("I read excel file {string} and sheet {string}")
	public void i_read_excel_file_and_sheet(String excelPath, String sheetName) {
	    // Write code here that turns the phrase above into concrete actions
	  userDetails = excelFileToMap(excelPath, sheetName);
	  System.out.println(userDetails);
	}

	@When("User is on the landing page")
	public void user_is_on_the_landing_page() {
	    WebDriverManager.chromedriver().setup();
	    ChromeOptions options=new ChromeOptions();
	    options.addArguments("--remote-allow-origins=*");
	    options.addArguments("--start-maximized");
	    options.addArguments("--disable-dev-shm-usage");
	    options.addArguments("--disable-extensions");
	    options.addArguments("--disable-infobars");
	    options.addArguments("--no-sandbox");
	    options.addArguments("--disable-popup-blocking");
	    options.addArguments("--disable-notifications");
	    options.addArguments("incognito");
	    options.setExperimentalOption("prefs", 
	      Collections.singletonMap("profile.default_content_setting_values.autofill", 1));
        driver = new ChromeDriver(options);
        driver.get("https://automationteststore.com");
        driver.manage().window().maximize();
	}

	@And("User clicks Sign In")
	public void user_clicks_sign_in() {
	        click(RegistrationAndLogin_PageObjects.LOGIN_LINK , 10, driver);
	    
	}

	@And("User select Register and click Continue")
	public void user_creates_account_with() {
		
		click(RegistrationAndLogin_PageObjects.REGISTER_RADIO_BTN,10,driver);
		click(RegistrationAndLogin_PageObjects.CONTINUE_BTN,10,driver);
		
	    
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
		
	}

	@When("User validates correct name and surname on landing screen")
	public void user_validates_correct_name_and_surname_on_landing_screen() {
		
		String xpath=HomePage.USER;
		String formattedXpath=String.format(xpath,userDetails.get("FirstName"));
		System.out.println(" After format String " +formattedXpath);
		assertTrue(validateElementPresent(formattedXpath, driver));
		
	   
	}

	@When("User adds a product to the cart")
	public void user_adds_a_product_to_the_cart() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		actionMouseHover(driver, HomePage.FRAGRANCE);
		Thread.sleep(2000);
		click(HomePage.PRODUCT_MEN,10,driver);
		Thread.sleep(2000);
		click(HomePage.ONE_SHOCK,10,driver);	
	    
	}

	@When("User proceeds to checkout")
	public void user_proceeds_to_checkout() {
		
		click(HomePage.ADD_TO_CART,10,driver);
		
	    
	}

	@When("User continues till payments")
	public void user_continues_till_payments() {
		
		click(HomePage.CHECKOUT,10,driver);
		
	
	}

	@Then("User validates product details on payments page")
	public void user_validates_product_details_on_payments_page() {
		
		Assert.assertEquals(getTextFromElement(driver, HomePage.VALIDATE_PRODUCT,10),userDetails.get("PRODUCT"));
		click(HomePage.ProductConfirm,10,driver);
	    
	}


}
