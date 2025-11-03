package com.webcrm.objectrepositoryutility;

//import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.webcrm.generic.webdriverutility.WebDriverUtility;

public class OrganisationsPage {
	
	public WebDriver driver;
	
	//Object Initialization
	
	public OrganisationsPage(WebDriver driver) {
		
	PageFactory.initElements(driver, this);
	this.driver = driver;

	}
	
	
	WebDriverUtility wlib = new WebDriverUtility();
	
	@FindBy(id="OrganisationListSearchName")
	private WebElement organisationSearchEdt;
	
	@FindBy(xpath="//h2[@class='el-notification__title']")
	private WebElement notification;
	
	@FindBy(xpath="//div[@class='entity-list-empty-main-text']")
	private WebElement organisationSearchResult;
	
	@FindBy(xpath="//i[@class='webcrm-plus-white-button-icon']")
	private WebElement createNewOrgBtn;
	
//	@FindBy(xpath="//a[@class='el-tooltip list-organisation-link-text']")
//	private WebElement searchResultOrg;
	

	//Business Library
	
	public void searchCreatedOrganisation(String orgName) throws InterruptedException {
	//organisationSearchEdt.click();
	//organisationSearchEdt.clear();
//	Thread.sleep(2000);
	wlib.waitForElementClickable(driver, organisationSearchEdt);
	organisationSearchEdt.click();
	organisationSearchEdt.clear();
	organisationSearchEdt.sendKeys(orgName,Keys.ENTER);
	/*JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("arguments[0].value=''", organisationSearchEdt);*/
	String SearchResultOrgTextLocator="//a[@class='el-tooltip list-organisation-link-text']";
	wlib.waitForElementPresent(driver, SearchResultOrgTextLocator);
	
	}
	
	
	public WebElement getSearchResultOrgText(String orgName) {
	 
		 WebElement SearchResultOrgText = driver.findElement(By.xpath("//a[text()='"+orgName+"']"));
	
		return SearchResultOrgText;
	
	    
	}
	
	
	public void searchCreatedOrganisationWithStaleElementExceptionHandling(String orgName) throws InterruptedException {
	
		try {
			
			
			WebElement element = organisationSearchEdt; 
			wlib.waitForElementClickable(driver, element);
			element.sendKeys(orgName,Keys.ENTER);
			String SearchResultLocator = "//div[@class='entity-list-empty-main-text']";
			
			wlib.waitForElementPresent(driver, SearchResultLocator); 
			
			
		} catch (StaleElementReferenceException e) {
		    System.out.println("Element went stale, reinitializing OrganisationsPage...");
		    OrganisationsPage op = new OrganisationsPage(driver); 
		    Thread.sleep(1000);
			op.organisationSearchEdt.sendKeys(orgName, Keys.ENTER);
			String SearchResultLocator = "//div[@class='entity-list-empty-main-text']";
			
			wlib.waitForElementPresent(driver, SearchResultLocator); 
			
	    }
			
		    
	}
		

		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
	

	
	
	//getters

	
	
	public WebElement getOrganisationSearchEdt() {
		return organisationSearchEdt;
	}

	public WebElement getNotification() {
		return notification;
	}

	public WebElement getOrganisationSearchResult() {
		return organisationSearchResult;
	}

//	public WebElement getSearchResultOrg() {
//		return searchResultOrg;
//	}




	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}
	
}
		
	
	
	
	
	
	
	
	
	
	
	


