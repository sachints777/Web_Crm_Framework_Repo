package com.webcrm.objectrepositoryutility;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webcrm.generic.webdriverutility.WebDriverUtility;

public class SupportPage {
public WebDriver driver;
	
	//Object Initialization
	
	public SupportPage(WebDriver driver) {
		
	PageFactory.initElements(driver, this);
	this.driver = driver;

	}
	WebDriverUtility wlib = new WebDriverUtility();
	@FindBy(id="SupportCaseListSearchName")
	private WebElement supportSearchEdt;
	
	@FindBy(xpath="//i[@class='webcrm-plus-white-button-icon']")
	private WebElement createNewSupportBtn;
	
	@FindBy(xpath="//div[@class='entity-list-empty-main-text']")
	private WebElement supportSearchResult;
	
	
	
	//Business Library
	

	public void searchCreatedSupport(String orgName) throws InterruptedException {
		
		String SupportSearchTextField = "SupportCaseListSearchName";
		wlib.waitForElementClickableById(driver, SupportSearchTextField);
		
		supportSearchEdt.click();
		Thread.sleep(2000);
		supportSearchEdt.clear();
		supportSearchEdt.sendKeys(orgName,Keys.ENTER);
		String SearchResultLocator = "//div[@class='entity-list-empty-main-text']";
		wlib.waitForElementPresent(driver,SearchResultLocator);
		
		
		
	
		
		
		
		
		
	
	}
	
	
	
	
	
	//getters
	

	public WebElement getSupportSearchEdt() {
		return supportSearchEdt;
	}

	public WebElement getCreateNewSupportBtn() {
		return createNewSupportBtn;
	}

	public WebElement getSupportSearchResult() {
		return supportSearchResult;
	}

	
	
}
