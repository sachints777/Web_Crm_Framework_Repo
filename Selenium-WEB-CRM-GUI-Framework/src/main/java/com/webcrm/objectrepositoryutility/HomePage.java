package com.webcrm.objectrepositoryutility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
public WebDriver driver;
	
	//Object Initialization
	
	public HomePage(WebDriver driver) {
		
	PageFactory.initElements(driver, this);
	this.driver = driver;

	}
	
	@FindBy(xpath="//span[contains(text(),'Organisations')]")
	private WebElement organisationsLink;
	
	@FindBy(xpath="//span[@class='top-menu-link-text' and contains(text(),'Activities')]")
	private WebElement activitiesLink;
	
	@FindBy(xpath="//span[@class='top-menu-link-text' and contains(text(),'Opportunities')]")
	private WebElement opportunitiesLink;
	
	@FindBy(xpath="//span[@class='top-menu-link-text' and contains(text(),'Support')]")
	private WebElement supportLink;
	
	
	
	@FindBy(xpath="//div[@class='user-circle-container user-top-circle']")
	private WebElement logoutIcon;
	
	@FindBy(xpath="//div[text()='Log out']")
	private WebElement logoutLink;

	//Business methods
	public void logout() {
		
	logoutIcon.click();
	logoutLink.click();
		
	}
	
	
	public void navigateToOrganisationsLink() {
		
		try {
		   getOrganisationsLink().click();
		} catch (StaleElementReferenceException e) {
		    System.out.println("Element went stale, reinitializing HomePage...");
		    HomePage hp = new HomePage(driver); 
		    hp.getOrganisationsLink().click();
		}
		
			
		}
	
	
	public void navigateToSupportTabUsingJavascriptExecutor() throws InterruptedException {
	

		WebElement supporttab = supportLink;
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",supporttab);
	
	}
	public void clickOnOrganisationsLink(WebDriver driver) {
	    By orgLink = By.xpath("//span[contains(text(),'Organisations')]");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(05));
	    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(orgLink));
	    element.click();
	}

	
	
	//getters
	public WebElement getOrganisationsLink() {
		return organisationsLink;
	}

	public WebElement getActivitiesLink() {
		return activitiesLink;
	}

	public WebElement getOpportunitiesLink() {
		return opportunitiesLink;
	}

	public WebElement getSupportLink() {
		return supportLink;
	}

	public WebElement getLogoutIcon() {
		return logoutIcon;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}
	
	
	
	
	
	
}
