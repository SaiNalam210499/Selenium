package webpage;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Amazon_login {
	WebDriver driver;

    @BeforeTest
	public void setup() {
    	
    	 driver = new ChromeDriver();
    	driver.get("https://practicetestautomation.com/practice-test-login/");
    	driver.manage().window().maximize();

	}
    @Test 
    public void login() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     	
        String Expectedtitle = driver.getTitle();
      	wait.until(ExpectedConditions.titleIs(Expectedtitle));
      
      	Assert.assertEquals(Expectedtitle, "Test Login | Practice Test Automation");
    	
    	driver.findElement(By.id("username")).sendKeys("student");
    	driver.findElement(By.id("password")).sendKeys("Password123");
    	driver.findElement(By.id("submit")).click();
    	
    	Assert.assertEquals(driver.getTitle(), "Logged In Successfully | Practice Test Automation");
    }
    
    @AfterTest
    public void close() {
    	driver.close();
    }

}
