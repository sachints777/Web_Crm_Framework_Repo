package com.webcrm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webcrm.generic.webdriverutility.WebDriverUtility;

public class CreateNewOpportunitiesPage {
	

public WebDriver driver;
	
	//Object Initialization
	
	public CreateNewOpportunitiesPage(WebDriver driver) {
		
	PageFactory.initElements(driver, this);
	this.driver = driver;

	}
	
		
	WebDriverUtility wlib = new WebDriverUtility();
	
	@FindBy(id="opportunityOrganisationField")
	
	private WebElement OrgNameEdt;
	
	@FindBy(xpath="//div[@class='create-list-quick-create-option']")
	
	private WebElement createNewOpportunityAddNewOrganisationBtn;
	
	@FindBy(xpath="//div[@class='blue-14-size-font']")
	private WebElement addNewOrgBtn;
	
	@FindBy(xpath="//button[@class='el-button el-button--default webcrm-primary-button']")
	private WebElement createBtn;
	
	@FindBy(xpath="//button[@class='el-button webcrm-primary-button el-button--default']")
	private WebElement createNewOrgBtn;
	
	@FindBy(id="descriptionField")
	private WebElement descriptionField;
	

	@FindBy(xpath="//input[@placeholder='Pipeline level']")
	private WebElement pipelineLevelDropdown;
	
	
	
	
	//Business Library
	
	public void createNewOpportunityWithExistingOrganisation(String orgName,String pipeline) throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		js.executeScript("arguments[0].click();", OrgNameEdt);

		OrgNameEdt.clear();
		
		js.executeScript("arguments[0].value=''", OrgNameEdt);
		
		js.executeScript("arguments[0].click();", OrgNameEdt);
		
		OrgNameEdt.sendKeys(orgName);
		
		
		
		Thread.sleep(3000);
		
	
	driver.findElement(By.xpath("//span[text()='"+orgName+" ']")).click();
		 
		Thread.sleep(2000);
		
		pipelineLevelDropdown.click();
		
		String pipelinelocator= "//li[@class='el-select-dropdown__item']/span[contains(text(),'" + pipeline + "')]";
		
		wlib.waitForElementClickableByXpath(driver, pipelinelocator);
		
		driver.findElement(By.xpath("//li[@class='el-select-dropdown__item']/span[contains(text(),'" + pipeline + "')]")).click();
		js.executeScript("arguments[0].click();", createBtn);
		
	//	createBtn.click();
		
	}
	public void createNewOpportunityWithNewOrganisation(String orgName,String pipeline,String description) {
		//OrgNameEdt.click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		js.executeScript("arguments[0].click();", OrgNameEdt);

		OrgNameEdt.clear();
		
		js.executeScript("arguments[0].value=''", OrgNameEdt);
		
		js.executeScript("arguments[0].click();", OrgNameEdt);
		


		OrgNameEdt.sendKeys(orgName);
		wlib.waitForElementClickable(driver, createNewOpportunityAddNewOrganisationBtn);
		createNewOpportunityAddNewOrganisationBtn.click();
		createNewOrgBtn.click();
		pipelineLevelDropdown.click();
		String pipelineLocator = "//li[@class='el-select-dropdown__item']/span[contains(text(),'" + pipeline + "')]";
		wlib.waitForElementClickableByXpath(driver, pipelineLocator);
		driver.findElement(By.xpath("//li[@class='el-select-dropdown__item']/span[contains(text(),'" + pipeline + "')]")).click();
		descriptionField.sendKeys(description);
		String createButtonLocator = "//button[@class='el-button el-button--default webcrm-primary-button']";
		wlib.waitForElementClickableByXpath(driver, createButtonLocator);
		createBtn.click();
		
		
		
	}


	public WebDriverUtility getWlib() {
		return wlib;
	}




	public WebElement getOrgNameEdt() {
		return OrgNameEdt;
	}




	public WebElement getAddNewOrgBtn() {
		return addNewOrgBtn;
	}




	public WebElement getCreateBtn() {
		return createBtn;
	}




	public WebElement getPipelineLevelDropdown() {
		return pipelineLevelDropdown;
	}
	public WebElement getCreateNewOpportunityAddNewOrganisationBtn() {
		return createNewOpportunityAddNewOrganisationBtn;
	}
	
	
}
