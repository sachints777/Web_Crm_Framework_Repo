package com.webcrm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunitiesPage {

public WebDriver driver;
	
	//Object Initialization
	
	public OpportunitiesPage(WebDriver driver) {
		
	PageFactory.initElements(driver, this);
	this.driver = driver;

	}
	
	@FindBy(id="OpportunityListSearchName")
	private WebElement opportunitySearchEdt;
	
	@FindBy(xpath="//div[@class='entity-list-empty-main-text']")
	private WebElement opportunitySearchResult;
	
	@FindBy(xpath="//i[@class='webcrm-plus-white-button-icon']")
	private WebElement createNewOpportunityBtn;

	//getters
	
	public WebElement getOpportunitySearchEdt() {
		return opportunitySearchEdt;
	}

	public WebElement getCreateNewOpportunityBtn() {
		return createNewOpportunityBtn;
	}

	public WebElement getOpportunitySearchResult() {
		return opportunitySearchResult;
	}
		
	
	
	}
