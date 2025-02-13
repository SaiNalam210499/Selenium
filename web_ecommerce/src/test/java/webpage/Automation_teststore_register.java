package webpage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameters;

public class Automation_teststore_register {
	
	WebDriver driver;

	@BeforeTest
	public void setup(){
		
		System.setProperty("WebDriver.chrome.driver","C:\\Users\\LENOVO\\git\\repository\\web_ecommerce\\src\\test\\resources\\chrome.exe");
		driver = new ChromeDriver();
		
		driver.get("https://automationteststore.com/");
		driver.manage().window().maximize();	
	}
    @Test(dataProvider="data")
    public void newregistration(String firstname, String lastname, String email, String phone, String address,String city, String statename, 
    		String zipcode, String countryname, String loginname, String password) {
    	//clicks the register option on account login page
    	driver.findElement(By.xpath("//a[contains(text(),'Login or register')]")).click();
    	Assert.assertEquals(driver.getTitle(), "Account Login");
    	//clicks the continue button on create account page
    	driver.findElement(By.xpath("//button[@title='Continue']")).click();
    	Assert.assertEquals(driver.getTitle(), "Create Account");
    	// fills the details in form
    	
    	driver.findElement(By.id("AccountFrm_firstname")).sendKeys(firstname);
    	driver.findElement(By.id("AccountFrm_lastname")).sendKeys(lastname);
    	driver.findElement(By.id("AccountFrm_email")).sendKeys(email);
    	driver.findElement(By.id("AccountFrm_telephone")).sendKeys(phone);
    	driver.findElement(By.id("AccountFrm_address_1")).sendKeys(address);
    	driver.findElement(By.id("AccountFrm_city")).sendKeys(city);
    	
    	// selects the country
    	WebElement dropdown1= driver.findElement(By.id("AccountFrm_country_id"));
    	Select country= new Select(dropdown1);
    	country.selectByVisibleText(countryname);
    	
    	// to select previous fields
    	Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).build().perform();  // Move back to the last field
        actions.sendKeys(Keys.TAB).build().perform();  // Moves back to the second last field
    	
    	// selects the state
    	WebElement dropdown= driver.findElement(By.id("AccountFrm_zone_id"));
    	Select state= new Select(dropdown);
    	state.selectByVisibleText(statename);
    	
    	driver.findElement(By.id("AccountFrm_postcode")).sendKeys(zipcode);
    	driver.findElement(By.id("AccountFrm_loginname")).sendKeys(loginname);
    	driver.findElement(By.id("AccountFrm_password")).sendKeys(password);
    	driver.findElement(By.id("AccountFrm_confirm")).sendKeys(password);
    	
    	driver.findElement(By.id("AccountFrm_newsletter1")).click();
    	driver.findElement(By.id("AccountFrm_agree")).click();
    	
    	driver.findElement(By.xpath("//button[@title='Continue']")).click();
    	
    	Assert.assertEquals(driver.getTitle(), "Your Account Has Been Created!");
    	
    }
    
    
    @DataProvider
    public Object[][] data() {
    	return new Object[][]{
    	{"ken","ben","kenben3@gmail.com","3654127893","New codn NY 2325", "New Mark", "Tamil Nadu", "22039", "India", "name14", "pass123"}
    };
    }
    
    @AfterTest
    public void close() {
    	   	driver.quit();
    }
   
}
