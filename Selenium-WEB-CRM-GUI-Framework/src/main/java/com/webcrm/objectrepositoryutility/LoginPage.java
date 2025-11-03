package com.webcrm.objectrepositoryutility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webcrm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility{

WebDriver driver;
	
	//Object Initialization
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;

	}

	
	WebDriverUtility wlib = new WebDriverUtility();
	@FindBy(id = "AccountName")
	private WebElement accountnameEdt;
	
	@FindBy(id = "Username")
	private WebElement usernameEdt;

	@FindBy(id = "Password")
	private WebElement passwordEdt;

	@FindBy(xpath = "//button[text()='Login']")
	private WebElement loginBtn;
	
	//business library
	
	public void loginToApp(String accountname,String username,String password,String URL) {
		driver.get(URL);
		// Wait until accountnameEdt is clickable
		// Clear any pre-filled text safely
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		wait.until(ExpectedConditions.elementToBeClickable(accountnameEdt));
		// Scroll into view to avoid overlays
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='';", accountnameEdt);

		// Focus field
		js.executeScript("arguments[0].focus();", accountnameEdt);
		
		
		js.executeScript("arguments[0].scrollIntoView(true);", accountnameEdt);

		// Clear any prefilled text
		accountnameEdt.clear();

		// Now send keys
		accountnameEdt.sendKeys(accountname);
		
		
		
	

		

		
	
		wlib.waitForElementClickable(driver, usernameEdt);
		usernameEdt.sendKeys(username);
		
		wlib.waitForElementClickable(driver, passwordEdt);
		passwordEdt.sendKeys(password);
	
		loginBtn.click();
		waitForPageToLoad(driver);
		
	}
	
	//getters

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getAccountnameEdt() {
		return accountnameEdt;
	}

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
