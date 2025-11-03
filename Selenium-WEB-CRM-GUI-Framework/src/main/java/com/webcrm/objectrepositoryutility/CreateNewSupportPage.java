package com.webcrm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webcrm.generic.webdriverutility.WebDriverUtility;

public class CreateNewSupportPage {
	
	
	public WebDriver driver;
	
	//Object Initialization
	
	public CreateNewSupportPage(WebDriver driver) {
		
	PageFactory.initElements(driver, this);
	this.driver = driver;

	}
	
	
	WebDriverUtility wlib = new WebDriverUtility();
	
	@FindBy(id="supportCaseOrganisationField")
	
	private WebElement OrgNameEdt;
	

	@FindBy(xpath="//div[@class='blue-14-size-font']")
	
	private WebElement addNewOrgBtn;
	
	@FindBy(xpath="//button[@class='el-button el-button--default webcrm-primary-button']")
	private WebElement createBtn;
	
	
	
	
	//Business Library
	
	public void createSupportTicketWithExistingOrganisation(String orgName) throws InterruptedException {
		wlib.waitForElementClickable(driver, OrgNameEdt);
		wlib.waitForElementClickable(driver, OrgNameEdt);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].value=''", OrgNameEdt);
		js.executeScript("arguments[0].scrollIntoView(true);", OrgNameEdt);
		Thread.sleep(300);  // tiny delay to allow rendering/animation
		js.executeScript("arguments[0].click();", OrgNameEdt);  // replaces normal click
		//OrgNameEdt.click();
		//OrgNameEdt.clear();
		OrgNameEdt.sendKeys(orgName);
		String elementlocator ="//span[text()='"+orgName+" ']";
		wlib.waitForElementPresent(driver, elementlocator);
		
		WebElement element = driver.findElement(By.xpath("//span[text()='"+orgName+" ']"));
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", element);

		//element.click();
		js.executeScript("arguments[0].click();", createBtn);

	//	createBtn.click();
		
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

	
}


