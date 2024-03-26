package pageObjects;

import org.openqa.selenium.By;


/**
 * class to interact with page
 * @author Girish Kumar
 *
 * This class has web elements of Register or Login which are used by respective step definitions
 */


public class RegistrationAndLogin_PageObjects {
	
	public static final By LOGIN_LINK=By.xpath("//div[@id='customernav']/ul/li/a");
	
	public static final By REGISTER_RADIO_BTN=By.id("accountFrm_accountregister");
	
	public static final By CONTINUE_BTN=By.xpath("//button[@type='submit' and @title='Continue']");
	
	public static final By REG_COMPLETE_CONTINUE=By.xpath("//a[@title='Continue']");
	
	public static final By FIRST_NAME=By.id("AccountFrm_firstname");
	
	public static final By LAST_NAME=By.id("AccountFrm_lastname");
	
	public static final By EMAIL=By.id("AccountFrm_email");
	
	public static final By TELEPHONE=By.id("AccountFrm_telephone");
	
	public static final By FAX=By.id("AccountFrm_fax"); 
	
	public static final By COMPANY=By.id("AccountFrm_company");
	
	public static final By ADDRESS1=By.id("AccountFrm_address_1");
	
	public static final By ADDRESS2=By.id("AccountFrm_address_2");
	
	public static final By CITY=By.id("AccountFrm_city");
	
	public static final By REGION_OR_STATE=By.id("AccountFrm_zone_id");
	
	public static final By ZIPCODE=By.id("AccountFrm_postcode");
	
	public static final By COUNTRY=By.id("AccountFrm_country_id");
	
	public static final By LOGIN_NAME=By.id("AccountFrm_loginname");
	
	public static final By PASSWORD=By.id("AccountFrm_password");
	
	public static final By PASSWORD_CONFIRM=By.id("AccountFrm_confirm");
	
	public static final By SUBSCRIBE_RADIO_BTN_YES=By.id("AccountFrm_newsletter1");
	
	public static final By SUBSCRIBE_RADIO_BTN_NO=By.id("AccountFrm_newsletter0");
	
	public static final By PRIVACY_POLICY_CHK_BOX=By.id("AccountFrm_agree");
	
	
	
	
	

}
