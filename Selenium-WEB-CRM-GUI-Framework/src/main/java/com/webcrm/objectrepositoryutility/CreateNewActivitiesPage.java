package com.webcrm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webcrm.generic.webdriverutility.WebDriverUtility;

public class CreateNewActivitiesPage  {

public WebDriver driver;
	
	//Object Initialization
	
	public CreateNewActivitiesPage(WebDriver driver) {
		
	PageFactory.initElements(driver, this);
	this.driver = driver;

	}
	
	String AddNewOrgButtonLocator = "//div[@class='blue-14-size-font']";
	
	WebDriverUtility wlib = new WebDriverUtility();
	
	@FindBy(id="organisationField")
	private WebElement OrgNameEdt;
	
	@FindBy(xpath="//div[@class='blue-14-size-font']")
	private WebElement addNewOrgBtn;
	

	@FindBy(id="actionField")
	private WebElement actionDropdown;
	
	

	@FindBy(id="descriptionField")
	private WebElement descriptionEdt;
	
	
	@FindBy(xpath="//button[@class='el-button el-button--default webcrm-primary-button']")
	private WebElement createBtn;
	
	@FindBy(xpath="//button[@class='el-button webcrm-primary-button el-button--default']")
	
	private WebElement createNewOrgPagecreateBtn;
	

	@FindBy(xpath="//div[@class='back']")
	private WebElement backToActivitiesLink;
	
	
	@FindBy(xpath="//span[text()='Anju Radhakrishnan']")
	
	private WebElement activityEditEnableField;
	
	@FindBy(xpath="//div[@class='el-tooltip table-row-button with-icon edit-action']")
	private WebElement editActivityBtn;
	
	@FindBy(id="activityActionField")
	private WebElement editActivityPageActionField;
	

	@FindBy(xpath="//button[@class='el-button el-button--default webcrm-primary-button']")
	private WebElement editActivitySaveBtn;
	
	

	@FindBy(xpath="//a[contains(text(),'Overview')]")
	private WebElement editActivityOverviewTab;
	

	
	
	//Business Library
	
	// dynamic method for selecting an action type
	
	
	
	public void createActivityWithNewOrganisation(String orgName,String description) throws InterruptedException {
		wlib.waitForElementClickable(driver, OrgNameEdt);
		OrgNameEdt.sendKeys(orgName);
		String AddNewOrgButtonLocator ="//div[@class='blue-14-size-font']";
		wlib.waitForElementPresent(driver, AddNewOrgButtonLocator);
		  WebElement AddNewOrgButton = addNewOrgBtn;
		  wlib.waitForElementClickable(driver, AddNewOrgButton);
		   AddNewOrgButton.click();
		   wlib.waitForElementClickable(driver, createNewOrgPagecreateBtn);
		  createNewOrgPagecreateBtn.click();
		  wlib.waitForElementClickable(driver, descriptionEdt);
		  descriptionEdt.sendKeys(description);
		  wlib.waitForElementClickable(driver, createBtn);
		  createBtn.click();
	}
	

	public void createActivityWithNewOrganisation(String orgName ) {
		wlib.waitForElementClickable(driver, OrgNameEdt);
		OrgNameEdt.sendKeys(orgName);
		 String AddNewOrgButtonLocator = "//div[@class='blue-14-size-font']";
		 wlib.waitForElementPresent(driver, AddNewOrgButtonLocator);
		 WebElement AddNewOrgButton = addNewOrgBtn;
		 wlib.waitForElementClickable(driver, AddNewOrgButton);
		 AddNewOrgButton.click();
		 wlib.waitForElementClickable(driver, createNewOrgPagecreateBtn);
		 createNewOrgPagecreateBtn.click();
	}
	
	 public void editActivityActionType(String activityActionTypeForEdit) throws InterruptedException {
		 
		 Actions action = new Actions(driver);
		 wlib.waitForElementClickable(driver, backToActivitiesLink);
		 backToActivitiesLink.click();
		 WebElement name = activityEditEnableField;
		 action.moveToElement(name).perform();
		 Thread.sleep(2000);
		 wlib.waitForElementClickable(driver, editActivityBtn);
		 
		 editActivityBtn.click();
		 
		 String activityActionFieldLocator = "activityActionField";
		 
		 wlib.waitForElementClickableById(driver, activityActionFieldLocator);
		 wlib.waitForElementClickable(driver, editActivityPageActionField);
		 editActivityPageActionField.click();
		 
		 
		 String activityActionTypeForEditLocator = "//li[@class='el-select-dropdown__item']/span[contains(text(),'"+activityActionTypeForEdit+"')]";
		
		 wlib.waitForVisibilityOfAllElements(driver, activityActionTypeForEditLocator);
		 Thread.sleep(2000);
		 WebElement activityActionTypeForEditLocatorElement = driver.findElement(By.xpath("//li[@class='el-select-dropdown__item']/span[contains(text(),'"+activityActionTypeForEdit+"')]"));
		 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", activityActionTypeForEditLocatorElement);

		// driver.findElement(By.xpath("//li[@class='el-select-dropdown__item']/span[contains(text(),'"+activityActionTypeForEdit+"')]")).click();
		 
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].click();", activityActionTypeForEditLocatorElement);
		 Thread.sleep(3000);
		 Actions act = new Actions(driver);
		 act.click(editActivitySaveBtn).perform();
		 
//		 wlib.waitForElementClickable(driver, editActivityBtn);
//		 js.executeScript("arguments[0].click();", editActivitySaveBtn);
		 
		
		 
		 
		//editActivitySaveBtn.click();
		 wlib.waitForElementClickable(driver, editActivityOverviewTab);
		 editActivityOverviewTab.click();
		 
		 
	 }
	
    public void selectActivityActionType(String activityActionType) {
    	wlib.waitForElementClickable(driver, actionDropdown);
    	actionDropdown.click();
    	
    	String activityActionTypeLocator = "//li[@class='el-select-dropdown__item']/span[contains(text(),'"+activityActionType+"')]";
        
        
        wlib.waitForElementClickableByXpath(driver, activityActionTypeLocator);
        
        driver.findElement(By.xpath("//li[@class='el-select-dropdown__item']/span[contains(text(),'"+activityActionType+"')]")).click();
        wlib.waitForElementClickable(driver, createBtn);
        createBtn.click();
        
        
       
 }
    
    
    
    public void verifyEditedActivityWithActionType(String activityActionTypeForEdit) {
    	
    	Actions action = new Actions(driver);
    	String ActualActivityActionTypeForEditelementLocator = "//span[text()='"+activityActionTypeForEdit+"']";
    	wlib.waitForElementClickableByXpath(driver, ActualActivityActionTypeForEditelementLocator);
    	 WebElement ActualActivityActionTypeForEditelement = driver.findElement(By.xpath("//span[text()='"+activityActionTypeForEdit+"']"));
		  action.moveToElement(ActualActivityActionTypeForEditelement).perform();
		  String ActualActivityActionTypeForEdit = driver.findElement(By.xpath("//span[text()='"+activityActionTypeForEdit+"']")).getText();
       
 }
 public String ActualActivityActionTypeForEdit(String activityActionTypeForEdit) {
	 String ActualActivityActionTypeForEdit = driver.findElement(By.xpath("//span[text()='"+activityActionTypeForEdit+"']")).getText();
    	return ActualActivityActionTypeForEdit;
 } 

    
    
    
   
    public String getActualActivityActionType(String activityActionType) {
    	
    	
    	String ActualActivityActionType = driver.findElement(By.xpath("//span[text()='"+activityActionType+"']")).getText();
        return ActualActivityActionType;
       
 }


	public String getAddNewOrgButtonLocator() {
		return AddNewOrgButtonLocator;
	}


	public WebElement getOrgNameEdt() {
		return OrgNameEdt;
	}


	public WebElement getAddNewOrgBtn() {
		return addNewOrgBtn;
	}


	public WebElement getActionDropdown() {
		return actionDropdown;
	}


	public WebElement getDescriptionEdt() {
		return descriptionEdt;
	}


	public WebElement getCreateBtn() {
		return createBtn;
	}


	public WebElement getCreateNewOrgPagecreateBtn() {
		return createNewOrgPagecreateBtn;
	}


	public WebElement getBackToActivitiesLink() {
		return backToActivitiesLink;
	}


	public WebElement getActivityEditEnableField() {
		return activityEditEnableField;
	}


	public WebElement getEditActivityBtn() {
		return editActivityBtn;
	}


	public WebElement getEditActivityPageActionField() {
		return editActivityPageActionField;
	}


	public WebElement getEditActivitySaveBtn() {
		return editActivitySaveBtn;
	}


	public WebElement getEditActivityOverviewTab() {
		return editActivityOverviewTab;
	}
	
	
}
