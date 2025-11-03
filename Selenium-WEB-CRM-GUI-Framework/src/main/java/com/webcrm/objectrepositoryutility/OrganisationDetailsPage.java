package com.webcrm.objectrepositoryutility;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webcrm.generic.webdriverutility.WebDriverUtility;

public class OrganisationDetailsPage {
public WebDriver driver;
	
	//Object Initialization
	
	public OrganisationDetailsPage(WebDriver driver) {
		
	PageFactory.initElements(driver, this);
	this.driver = driver;
	

	}
	
	
	WebDriverUtility wlib = new WebDriverUtility();
	
	@FindBy(xpath="//div[@class='title']")
	private WebElement organisationHeaderInfo;
	
	@FindBy(xpath="//button[@class='el-button el-button--default webcrm-third-button webcrm-split-button flex']")
	private WebElement deleteButtonDropdown;
	
	@FindBy(xpath="//li[@class='el-dropdown-menu__item with-icon delete-action']")
	private WebElement deleteButton;
	
	@FindBy(xpath="//input[@name='userNameConfirmation']")
	private WebElement deleteConfirmationEdt;
	
	@FindBy(xpath="//button[@class='el-button webcrm-danger-primary-button el-button--default']")
	private WebElement finalDeleteButton;
	
	
	
	//business library
	

	public void deleteOrganisation() {

		String deleteOptionLocator = "//button[@class='el-button el-button--default webcrm-third-button webcrm-split-button flex']";
		wlib.waitForElementPresent(driver, deleteOptionLocator);
		
		deleteButtonDropdown.click();
		deleteButton.click();
		deleteConfirmationEdt.sendKeys("Anju Radhakrishnan");
		finalDeleteButton.click();
	
	}
	
	
	//getters

	public WebElement getOrganisationHeaderInfo() {
		return organisationHeaderInfo;
	}


	public WebElement getDeleteButtonDropdown() {
		return deleteButtonDropdown;
	}


	public WebElement getDeleteButton() {
		return deleteButton;
	}


	public WebElement getDeleteConfirmationEdt() {
		return deleteConfirmationEdt;
	}


	public WebElement getFinalDeleteButton() {
		return finalDeleteButton;
	}
	
	
	
	
	
	
	
	
	
}
