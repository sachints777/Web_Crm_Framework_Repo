package com.webcrm.objectrepositoryutility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webcrm.generic.webdriverutility.WebDriverUtility;

public class SupportDetailsPage {

	public WebDriver driver;
	
	
	public SupportDetailsPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		this.driver=driver;

		}
		
		
		WebDriverUtility wlib = new WebDriverUtility();
		
		@FindBy(xpath="//a[@class='el-tooltip organisation-name organisation-link']")
		private WebElement supportDetailsPageOrgHeader;
		

		@FindBy(id="tab-organisation")
		private WebElement supportDetailsPageOrgHistoryTab;
		

		@FindBy(xpath="//span[text()='created activity']")
		private WebElement supportDetailsPageActivityHeader1;
		
		
		
		//Business Library
		

		public String getOrgHistoryActivityHeader2(String description) {
		 
		String OrgHistoryActivityHeader2 = driver.findElement(By.xpath("//a[text()='"+description+"']")).getText();
		return OrgHistoryActivityHeader2;
		    
		}
		
		
		

		//Getters
		
		
		
		
		public WebElement getSupportDetailsPageOrgHeader() {
			return supportDetailsPageOrgHeader;
		}


		public WebElement getSupportDetailsPageActivityHeader1() {
			return supportDetailsPageActivityHeader1;
		}


		public WebElement getSupportDetailsPageOrgHistoryTab() {
			return supportDetailsPageOrgHistoryTab;
		}
		
		
		
		
		
		
		
		
		
	}	