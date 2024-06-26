package pageObjects;

import org.openqa.selenium.By;

/**
 * class to interact with page
 * @author Girish Kumar
 *
 * This class has web elements of Home Page which are used by respective step definitions
 */

public class HomePage{
	
	public static final String USER="//div[@class='menu_text' and contains(text(), '%s')]";
	
	public static final By FRAGRANCE=By.xpath("//section[@id='categorymenu']/nav/ul/li/a[contains(text(),'Fragrance')]");
	
	public static final By PRODUCT_MEN=By.xpath("//div[@class='subcategories']/ul/li/a[contains(text(),'Men')]");
	
	public static final By ONE_SHOCK=By.xpath("//a[@title='ck one shock for him Deodorant']");
	
	public static final By ADD_TO_CART=By.xpath("//a[@class='cart']");
	
	public static final By CHECKOUT=By.xpath("//a[@id='cart_checkout1']");
	
	public static final By VALIDATE_PRODUCT=By.xpath("//table[@class='table confirm_products']/tbody/tr/td/a[text()='ck one shock for him Deodorant']");
	
	public static final By PAYMENT_PAGE_TITLE=By.xpath("//span[contains(text(),' Checkout Confirmation')]");
	
	public static final By ProductConfirm=By.xpath("//button[@title='Confirm Order']");

}
