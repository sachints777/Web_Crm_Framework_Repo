package com.webcrm.objectrepositoryutility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webcrm.generic.webdriverutility.WebDriverUtility;

public class CreateNewOrganisationPage {


	public WebDriver driver;

	//Object Initialization
	
	public CreateNewOrganisationPage(WebDriver driver) {
		
	PageFactory.initElements(driver, this);
	
	this.driver = driver;

	}
	WebDriverUtility wlib = new WebDriverUtility();
	@FindBy(id="o_organisation")
	private WebElement orgNameEdt;
	
	@FindBy(xpath="//button[@class='el-button webcrm-primary-button el-button--default']")
	
	private WebElement createBtn;
	
	//Business methods
	

	public void createOrg(String orgName) {
		wlib.waitForElementClickable(driver, orgNameEdt);
		orgNameEdt.click();
		orgNameEdt.clear();
		orgNameEdt.sendKeys(orgName);
		
		createBtn.click();
		
	}
	
	//Getters

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getCreateBtn() {
		return createBtn;
	}
	
		
}
