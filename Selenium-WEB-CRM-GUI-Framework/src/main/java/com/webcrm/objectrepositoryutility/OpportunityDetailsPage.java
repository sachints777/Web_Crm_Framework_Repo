package com.webcrm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webcrm.generic.webdriverutility.WebDriverUtility;

public class OpportunityDetailsPage {
public WebDriver driver;
	
	
	public OpportunityDetailsPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		this.driver=driver;

		}
		
		
		WebDriverUtility wlib = new WebDriverUtility();
		
		@FindBy(xpath="//span[text()='created organisation']")
		private WebElement opportunityDetailsPageOrgHistory1;
		

		@FindBy(id="tab-organisation")
		private WebElement OrgHistoryTab;
		
		@FindBy(xpath="//button[@class='el-button el-button--default webcrm-outlined-button webcrm-split-button flex']")
		private WebElement opportunityDetailsPageDeleteButtonDropdown;
		
		@FindBy(xpath="//li[@class='el-dropdown-menu__item with-icon delete-action']")
		private WebElement opportunityDetailsPageDeleteButton;
		
		@FindBy(xpath="//button[@class='el-button webcrm-danger-primary-button el-button--default']")
		private WebElement opportunityDetailsPageFinalDeleteButton;
		
		
		

		@FindBy(xpath="//span[text()='created opportunity']")
		private WebElement opportunityDetailsPageOpportunityHeader1;
		
		
		
		
		//Business Library
		
		public void verifyCreatedOpportunity(String description) throws InterruptedException {
			
			Actions action = new Actions(driver);
			String orgHistoryLocator = "tab-organisation";
			
			
			WebElement orgHistory =OrgHistoryTab;
			action.scrollByAmount(0, 650).perform();
			Thread.sleep(2000);
			wlib.waitForPageToLoad(driver);
			orgHistory.click();
			Thread.sleep(2000);

			String ActorgHistroryHeader1Locator = "//span[text()='created opportunity']";
			
			wlib.waitForElementClickableByXpath(driver, ActorgHistroryHeader1Locator);
			String ActorgHistroryHeader1 =opportunityDetailsPageOpportunityHeader1.getText();
			String ActorgHistroryHeader2Locator = "//a[text()='"+description+"']";
			wlib.waitForElementClickableByXpath(driver, ActorgHistroryHeader2Locator);
			String ActorgHistroryHeader2 =driver.findElement(By.xpath("//a[text()='"+description+"']")).getText();
			
			Thread.sleep(3000);
			
			
	}
		
		public void deleteOpportunity() throws InterruptedException  {
			Actions action = new Actions(driver);
			Thread.sleep(2000);

			String deleteButtonLocator = "//button[@class='el-button el-button--default webcrm-outlined-button webcrm-split-button flex']";
			
			wlib.waitForElementClickableByXpath(driver, deleteButtonLocator);
			WebElement delete =opportunityDetailsPageDeleteButtonDropdown;
			action.moveToElement(delete).perform();
		
			wlib.waitForElementClickable(driver, delete);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			js.executeScript("arguments[0].scrollIntoView(true);", delete);

			
			js.executeScript("arguments[0].click();", delete);
			
			// delete.click();
			
			 String deleteActionLocator = "//li[@class='el-dropdown-menu__item with-icon delete-action']";
				
			wlib.waitForElementClickableByXpath(driver, deleteActionLocator);
			opportunityDetailsPageDeleteButton.click();
			
			//
			String defaultButtonLocator = "//button[@class='el-button webcrm-danger-primary-button el-button--default']";
		
			
			
			wlib.waitForElementClickable(driver, opportunityDetailsPageFinalDeleteButton);
			
	
			
			opportunityDetailsPageFinalDeleteButton.click();
			
			
			
	
			
			
		}
		
		public void navigateToOrgHistoryTab() {
			Actions action = new Actions(driver);
			String OrgHistorylocator= "tab-organisation";
			wlib.waitForElementClickableById(driver, OrgHistorylocator);
			WebElement OrgHistory = OrgHistoryTab;
			action.scrollByAmount(0, 650).perform();
			
			OrgHistory.click();
			
			
	
			
			
		}
		
		
		
		public String getOrgHistoryHeaderText(String orgHistoryHeader2) {
		 
			
		String 	orgHistoryHeaderText = driver.findElement(By.xpath("//a[text()='"+orgHistoryHeader2+"']")).getText();
		return	orgHistoryHeaderText;
		
		
		    
		}
		
		public String getActorgHistroryHeader1() {
			 
			String ActorgHistroryHeader1 =opportunityDetailsPageOpportunityHeader1.getText();
			
			return ActorgHistroryHeader1;
			
			    
			}
		
		public String getActorgHistroryHeader2(String description ) {
			 
			
			String ActorgHistroryHeader2Locator = "//a[text()='"+description+"']";
			wlib.waitForElementClickableByXpath(driver, ActorgHistroryHeader2Locator);
			String ActorgHistroryHeader2 =driver.findElement(By.xpath("//a[text()='"+description+"']")).getText();
			return ActorgHistroryHeader2;
			
			
			
			    
			}

		
		


		//Getters
		
		public WebElement getOpportunityDetailsPageOrgHistory1() {
			return opportunityDetailsPageOrgHistory1;
		}



		public WebElement getOpportunityDetailsPageOpportunityHeader1() {
			return opportunityDetailsPageOpportunityHeader1;
		}



		public WebElement getOrgHistoryTab() {
			return OrgHistoryTab;
		}

		public WebElement getOpportunityDetailsPageDeleteButtonDropdown() {
			return opportunityDetailsPageDeleteButtonDropdown;
		}

		public WebElement getOpportunityDetailsPageDeleteButton() {
			return opportunityDetailsPageDeleteButton;
		}

		public WebElement getOpportunityDetailsPageFinalDeleteButton() {
			return opportunityDetailsPageFinalDeleteButton;
		}
	
	
	
	
	
	
}
