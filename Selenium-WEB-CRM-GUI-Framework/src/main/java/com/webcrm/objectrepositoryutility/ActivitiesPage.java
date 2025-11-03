package com.webcrm.objectrepositoryutility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActivitiesPage {

public WebDriver driver;
	
	//Object Initialization
	
	public ActivitiesPage(WebDriver driver) {
		
	PageFactory.initElements(driver, this);
	this.driver = driver;

	}
	
	@FindBy(id="ActivityListSearchName")
	private WebElement activitySearchEdt;
	
	@FindBy(xpath="//i[@class='webcrm-plus-white-button-icon']")
	private WebElement createNewActivityBtn;
	
	
	
	//getters

	public WebElement getActivitySearchEdt() {
		return activitySearchEdt;
	}

	public WebElement getCreateNewActivityBtn() {
		return createNewActivityBtn;
	}
	
	
}
