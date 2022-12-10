package com.qa.ninjatutorials.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.ninjatutorials.base.TestBase;

public class SearchTest extends TestBase {

	public SearchTest() throws Exception {
		super();

	}


	@BeforeMethod
	public void startup() {
		driver = initializeBrowser(prop.getProperty("BrowserName"));

	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		driver.findElement(By.name("search")).sendKeys("Samsung");
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Samsung SyncMaster 941BW")).isDisplayed());

	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		driver.findElement(By.name("search")).sendKeys("Dell");
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		String actualInvalidSearchMessage = driver
				.findElement(By.xpath("//p[contains(text(), 'There is no product that matches the search criteria.')]"))
				.getText();
		Assert.assertEquals(actualInvalidSearchMessage, "There is no product that matches the search criteria.",
				"The search message is not displayed");
	}

	@Test(priority = 3)
	public void verifySearchWithoutAnyProduct() {
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		String actualNoProductSearchMessage = driver
				.findElement(By.xpath("//p[contains(text(), 'There is no product that matches the search criteria.')]"))
				.getText();
		Assert.assertEquals(actualNoProductSearchMessage, "There is no product that matches the search criteria.",
				"The search message is not displayed");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
