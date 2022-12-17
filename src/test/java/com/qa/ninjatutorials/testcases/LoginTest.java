package com.qa.ninjatutorials.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.ninjatutorials.base.TestBase;
import com.qa.ninjatutorials.utilities.Utilities;

public class LoginTest extends TestBase {
	
	public LoginTest() throws Exception {
		super();
		
	}

	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowser(prop.getProperty("BrowserName"));
		driver.findElement(By.xpath("//span[text() = 'My Account']")).click();
		driver.findElement(By.linkText("Login")).click();	
	}
	
	@Test(priority = 1, dataProvider = "ValidCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), "The link showing Edit your account information does not exist");
	   
	}
	
	/*
	 * @DataProvider public Object[][] supplyData() { Object[][] data =
	 * {{"seleniumpanda@gmail.com", "Selenium@123"},
	 * {"seleniumpanda@rediffmail.com", "Selenium@123"},
	 * {"automationhubsarthak@gmail.com", "Selenium@123"}}; return data; }
	 */
	
	@DataProvider(name = "ValidCredentialsSupplier")
	public Object[][] supplyExcelTestData() throws Exception {
		Object[][] data = Utilities.getDataFromExcel("Login");
		return data;
	}
	
	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() {
	
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateDateTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = dataprop.getProperty("emailPasswordMismatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning Message is not displayed");
	    
	}
	
	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailidValidPassword() {
	
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateDateTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();	
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning Message is not displayed");
	   
	}
	
	@Test(priority = 4)
	public void verifyLoginWithValidEmailidInValidPassword() {
	
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateDateTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123456789");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();	
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning Message is not displayed");
	   
	}
	
	@Test(priority = 5)
	public void verifyLoginWithNoCredentials() {
	
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();	
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning Message is not displayed");
	   
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	

}
