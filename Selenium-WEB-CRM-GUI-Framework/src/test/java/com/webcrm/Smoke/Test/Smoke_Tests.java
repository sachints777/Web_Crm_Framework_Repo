package com.webcrm.Smoke.Test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.webcrm.basetest.BaseClass;

import com.webcrm.generic.webdriverutility.UtilityClassObject;
import com.webcrm.objectrepositoryutility.CreateNewOrganisationPage;
import com.webcrm.objectrepositoryutility.CreateNewSupportPage;
import com.webcrm.objectrepositoryutility.HomePage;
import com.webcrm.objectrepositoryutility.OrganisationDetailsPage;
import com.webcrm.objectrepositoryutility.OrganisationsPage;
import com.webcrm.objectrepositoryutility.SupportDetailsPage;
import com.webcrm.objectrepositoryutility.SupportPage;

@Listeners(com.webcrm.listenerutility.ListenerImplementationClass.class)
public class Smoke_Tests extends BaseClass {

@Test(groups = {"Smoke","Integration","System"})
public void createOrganisationTest() throws EncryptedDocumentException, IOException {	
	
	try {
		//Random Number
		int randomNo = jlib.getRandomNumber();
	
		HomePage hp = new HomePage(UtilityClassObject.getDriver());
	
		//Test Script Data
	
		String	orgName= elib.getDataFromExcel("smoke",1 , 2)+randomNo;
		UtilityClassObject.getTest().log(Status.INFO,"Read Data From Excel");	
		//create organization
		
		OrganisationsPage op = new OrganisationsPage(UtilityClassObject.getDriver());
		CreateNewOrganisationPage cnop= new CreateNewOrganisationPage(UtilityClassObject.getDriver());
		wlib.waitForPageToLoad(UtilityClassObject.getDriver());
		wlib.waitForElementClickable(UtilityClassObject.getDriver(), hp.getOrganisationsLink());
		hp.getOrganisationsLink().click();
		UtilityClassObject.getTest().log(Status.INFO,"Navigate to Create New Organization Page");
		wlib.waitForElementClickable(UtilityClassObject.getDriver(), op.getCreateNewOrgBtn());
		op.getCreateNewOrgBtn().click();
		
		//Create New Organization
		
		cnop.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO,"Create a new Organization");
		
		//Verify Header Organization Name
		OrganisationDetailsPage odp = new OrganisationDetailsPage(UtilityClassObject.getDriver());
		String HeaderInfo =odp.getOrganisationHeaderInfo().getText();
		
		
		System.out.println(HeaderInfo);
		Assert.assertEquals(HeaderInfo, orgName,orgName+"is not created==Fail");
	
	}
	catch (Exception e) {
	    
	    e.printStackTrace();
	    
	}


}
@Test(groups = {"Smoke","Integration","System"})
public void deleteOrganisationTest() throws EncryptedDocumentException, IOException, InterruptedException {

	try {
	
		//Random Number
		
	
		int randomNo = jlib.getRandomNumber();
		HomePage hp = new HomePage(UtilityClassObject.getDriver());
		
		String	orgName= elib.getDataFromExcel("smoke",4 , 2)+randomNo;
		UtilityClassObject.getTest().log(Status.INFO,"Read Data From Excel");
		
		//create organization
		OrganisationsPage op = new OrganisationsPage(UtilityClassObject.getDriver());
		CreateNewOrganisationPage cnop= new CreateNewOrganisationPage(UtilityClassObject.getDriver());
		wlib.waitForPageToLoad(UtilityClassObject.getDriver());
		wlib.waitForElementClickable(UtilityClassObject.getDriver(), hp.getOrganisationsLink());
		hp.getOrganisationsLink().click();
		UtilityClassObject.getTest().log(Status.INFO,"Navigate to Create New Organization Page");
		wlib.waitForElementClickable(UtilityClassObject.getDriver(), op.getCreateNewOrgBtn());
		op.getCreateNewOrgBtn().click();
		
		cnop.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO,"Create a new Organization");
		
		//Verify Header Organization Name
		OrganisationDetailsPage odp = new OrganisationDetailsPage(UtilityClassObject.getDriver());
		String HeaderInfo =odp.getOrganisationHeaderInfo().getText();
	
		System.out.println(HeaderInfo);
		
		Assert.assertEquals(HeaderInfo, orgName,orgName+"is not created==Fail");
	
		
		//search created organization
		
		hp.getOrganisationsLink().click();
		
		op.searchCreatedOrganisation(orgName);
		
		String SearchResultOrgText =op.getSearchResultOrgText(orgName).getText();
		
			
		System.out.println(SearchResultOrgText);
		
		
		//delete organization
		Assert.assertTrue(SearchResultOrgText.equals(orgName), orgName + " does not exist in the organization list==Fail");
		op.getSearchResultOrgText(orgName).click();
		odp.deleteOrganisation();
	
		op.searchCreatedOrganisationWithStaleElementExceptionHandling(orgName);
			
			WebElement SearchResult = op.getOrganisationSearchResult();
			
		
			 
			String SearchResultText= SearchResult.getText();
			
			//Verify organization deleted successfully
			Assert.assertTrue(SearchResultText.contains("No results found"), "not deleted==Fail");
			
			 
		
		
		WebElement Notification	=op.getNotification();
		wlib.waitForInvisibilityOfElement(UtilityClassObject.getDriver(), Notification);
	}
catch (Exception e) {
	    
	    e.printStackTrace();
	    
	}

	
}	

@Test(groups = {"Smoke","Integration","System"})
public void createSupportTicketWithExistingOrganizationTest() throws EncryptedDocumentException, IOException, InterruptedException {

	try {
		//Random Number
		
		int randomNo = jlib.getRandomNumber();
		HomePage hp = new HomePage(UtilityClassObject.getDriver());
		
	
		//wlib.windowMaximize(driver);
	
		wlib.waitForPageToLoad(UtilityClassObject.getDriver());
			
		
		String orgName= elib.getDataFromExcel("smoke",7 , 2)+randomNo;
		UtilityClassObject.getTest().log(Status.INFO,"Read Data From Excel");
		//create organization
		
		OrganisationsPage op = new OrganisationsPage(UtilityClassObject.getDriver());
		CreateNewOrganisationPage cnop = new CreateNewOrganisationPage(UtilityClassObject.getDriver());
		wlib.waitForPageToLoad(UtilityClassObject.getDriver());
		wlib.waitForElementClickable(UtilityClassObject.getDriver(), hp.getOrganisationsLink());
		hp.getOrganisationsLink().click();
		wlib.waitForElementClickable(UtilityClassObject.getDriver(), op.getCreateNewOrgBtn());
		op.getCreateNewOrgBtn().click();
		UtilityClassObject.getTest().log(Status.INFO,"Navigate to Create New Organization Page");
		
		
		cnop.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO,"Create a new Organization");
		
		//Verify Header Organization Name
				
		String HeaderInfo= UtilityClassObject.getDriver().findElement(By.xpath("//div[@class='title']")).getText();
		System.out.println(HeaderInfo);
		
		Assert.assertEquals(HeaderInfo, orgName,orgName+"is not created==Fail");
		
	
		
		//Create Support Ticket with existing organization
		CreateNewSupportPage cnsp = new CreateNewSupportPage(UtilityClassObject.getDriver());
		SupportDetailsPage sdp = new SupportDetailsPage(UtilityClassObject.getDriver());
		SupportPage sp = new SupportPage(UtilityClassObject.getDriver());
		wlib.waitForElementClickable(UtilityClassObject.getDriver(), hp.getSupportLink());
		hp.getSupportLink().click();
		wlib.waitForElementClickable(UtilityClassObject.getDriver(), sp.getCreateNewSupportBtn());
		sp.getCreateNewSupportBtn().click();
		cnsp.createSupportTicketWithExistingOrganisation(orgName);
		
		
		String HeaderInfo2 =sdp.getSupportDetailsPageOrgHeader().getText();
	
		System.out.println(HeaderInfo2);
		
		// Verify support ticket created with existing organization
		Assert.assertTrue(HeaderInfo2.equalsIgnoreCase(orgName),"Support Ticket failed to create with existing organization name: " + orgName);
		
		System.out.println("Support Ticket is created with existing organization name: " + orgName + " ==> Pass");
	
		
	
		
	
	}
catch (Exception e) {
	    
	    e.printStackTrace();
	    
	}

	
	
}

























}