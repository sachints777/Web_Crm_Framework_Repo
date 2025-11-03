package com.webcrm.Integration.Test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.webcrm.basetest.BaseClass;
import com.webcrm.generic.fileutility.ExcelUtility;
import com.webcrm.generic.webdriverutility.UtilityClassObject;
import com.webcrm.objectrepositoryutility.ActivitiesPage;
import com.webcrm.objectrepositoryutility.CreateNewActivitiesPage;
import com.webcrm.objectrepositoryutility.CreateNewOpportunitiesPage;
import com.webcrm.objectrepositoryutility.CreateNewOrganisationPage;
import com.webcrm.objectrepositoryutility.CreateNewSupportPage;
import com.webcrm.objectrepositoryutility.HomePage;
import com.webcrm.objectrepositoryutility.OpportunitiesPage;
import com.webcrm.objectrepositoryutility.OpportunityDetailsPage;
import com.webcrm.objectrepositoryutility.OrganisationDetailsPage;
import com.webcrm.objectrepositoryutility.OrganisationsPage;
import com.webcrm.objectrepositoryutility.SupportDetailsPage;
import com.webcrm.objectrepositoryutility.SupportPage;

@Listeners(com.webcrm.listenerutility.ListenerImplementationClass.class)
public class Integration_Tests extends BaseClass {
	@Test(groups = {"Smoke","Integration","System"})
	public void organizationOpportunityLink() throws EncryptedDocumentException, IOException, InterruptedException {

		try
		{
		// Random Number
	
			int randomNo = jlib.getRandomNumber();
			HomePage hp = new HomePage(UtilityClassObject.getDriver());
			
			// Test Script Data
			
			String orgName = elib.getDataFromExcel("integration", 1, 2) + randomNo;
	
			String pipeline = elib.getDataFromExcel("integration", 1, 3);
			String orgHistroryHeader1 = elib.getDataFromExcel("integration", 1, 4);
			String orgHistroryHeader2 = orgName;
	
			UtilityClassObject.getTest().log(Status.INFO, "Read Data From Excel");
	
			// create organization
			OrganisationsPage op = new OrganisationsPage(UtilityClassObject.getDriver());
			CreateNewOrganisationPage cnop = new CreateNewOrganisationPage(UtilityClassObject.getDriver());
			wlib.waitForElementClickable(UtilityClassObject.getDriver(), hp.getOrganisationsLink());
			hp.getOrganisationsLink().click();
			op.getCreateNewOrgBtn().click();
			UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create New Organization Page");
			cnop.createOrg(orgName);
			UtilityClassObject.getTest().log(Status.INFO, "Create a new Organization");
	
			// Verify Header Organization Name
			OrganisationDetailsPage odp = new OrganisationDetailsPage(UtilityClassObject.getDriver());
			String HeaderInfo = odp.getOrganisationHeaderInfo().getText();
	
			System.out.println(HeaderInfo);
			Assert.assertEquals(HeaderInfo, orgName,orgName+"is not created==Fail");
	
	
			// Create Opportunity with alreday created organization
	
			OpportunitiesPage opp = new OpportunitiesPage(UtilityClassObject.getDriver());
			CreateNewOpportunitiesPage cnopp = new CreateNewOpportunitiesPage(UtilityClassObject.getDriver());
			wlib.waitForElementClickable(UtilityClassObject.getDriver(), hp.getOpportunitiesLink());
			hp.getOpportunitiesLink().click();
			opp.getCreateNewOpportunityBtn().click();
			UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create New Opportunitiy Page");
	
			cnopp.createNewOpportunityWithExistingOrganisation(orgName, pipeline);
	
			UtilityClassObject.getTest().log(Status.INFO, "Create a new Opportunitiy with existing organisation");
	
			// navigate to orghistorytab
			OpportunityDetailsPage oppdp = new OpportunityDetailsPage(UtilityClassObject.getDriver());
	
			oppdp.navigateToOrgHistoryTab();
			UtilityClassObject.getTest().log(Status.INFO, "Navigate to OrganisationHistoryTab");
	
			// verify Organization from orghistory under opportunity details page
	
			String ActorgHistroryHeader1 = oppdp.getOpportunityDetailsPageOrgHistory1().getText();
	
			String ActorgHistroryHeader2 = oppdp.getOrgHistoryHeaderText(orgHistroryHeader2);
			// Verify organization is reflected on Opportunity Details Page
			boolean isOrgReflected = ActorgHistroryHeader1.equalsIgnoreCase(orgHistroryHeader1)
			        && orgHistroryHeader2.equalsIgnoreCase(ActorgHistroryHeader2);
	
			Assert.assertTrue(isOrgReflected,
			        "Created organization " + orgHistroryHeader2 + " is NOT reflected on Opportunity Details Page ==> FAIL");
	
			System.out.println("Created organization " + orgHistroryHeader2
			        + " is reflected on Opportunity Details Page ==> PASS");
	
	
	
		}
		catch (Exception e) {
		    
		    e.printStackTrace();
		    
		}

	}

	
	
	@Test(groups = {"Smoke","Integration","System"})
	public void activitySupportLink() throws InterruptedException, IOException {

		// Random Number
try {
		int randomNo = jlib.getRandomNumber();
		HomePage hp = new HomePage(UtilityClassObject.getDriver());

		String orgName = elib.getDataFromExcel("integration", 4, 2) + randomNo;
		String ActivityHeader1 = elib.getDataFromExcel("integration", 4, 3);
		String description = elib.getDataFromExcel("integration", 4, 4);
		String ActivityHeader2 = description;
		UtilityClassObject.getTest().log(Status.INFO, "Read Data From Excel");

		// Create Activity With New Organization

		ActivitiesPage ap = new ActivitiesPage(UtilityClassObject.getDriver());
		CreateNewActivitiesPage cnap = new CreateNewActivitiesPage(UtilityClassObject.getDriver());
		wlib.waitForElementClickable(UtilityClassObject.getDriver(), hp.getActivitiesLink());
		hp.getActivitiesLink().click();

		ap.getCreateNewActivityBtn().click();

		cnap.createActivityWithNewOrganisation(orgName, description);

		// Create Support Case with description
		SupportDetailsPage sdp = new SupportDetailsPage(UtilityClassObject.getDriver());

		SupportPage sp = new SupportPage(UtilityClassObject.getDriver());
		CreateNewSupportPage cnsp = new CreateNewSupportPage(UtilityClassObject.getDriver());

		hp.getSupportLink().click();

		sp.getCreateNewSupportBtn().click();

		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create New Support Page");

		cnsp.createSupportTicketWithExistingOrganisation(orgName);

		UtilityClassObject.getTest().log(Status.INFO, "Create a new Support Ticket With existing Organisation");
		WebElement organisationTab = sdp.getSupportDetailsPageOrgHistoryTab();

		wlib.waitForElementClickable(UtilityClassObject.getDriver(), organisationTab);
		wlib.scrollIntoView(UtilityClassObject.getDriver(), organisationTab);
		
		organisationTab.click();
		Thread.sleep(2000);

		String ActualActivityHeader1 = sdp.getSupportDetailsPageActivityHeader1().getText();

		String ActualActivityHeader2 = sdp.getOrgHistoryActivityHeader2(description);
		
		// Verify activity is reflected on Support Details Page
		boolean isActivityReflected = ActualActivityHeader1.equalsIgnoreCase(ActivityHeader1)
		        && ActualActivityHeader2.equalsIgnoreCase(ActivityHeader2);

		Assert.assertTrue(isActivityReflected,
		        "Created activity " + ActivityHeader2 + " is NOT reflected on Support Details Page ==> FAIL");

		System.out.println("Created activity " + ActivityHeader2
		        + " is reflected on Support Details Page ==> PASS");



}
catch (Exception e) {
    
    e.printStackTrace();
    
}

	}

	@Test(groups = {"Smoke","Integration","System"})
	public void organizationDeleteSupportTicketLink() throws InterruptedException, IOException {
	try {
		int randomNo = jlib.getRandomNumber();
		HomePage hp = new HomePage(driver);



		// Test Script Data
		ExcelUtility elib = new ExcelUtility();
		String orgName = elib.getDataFromExcel("integration", 7, 2) + randomNo;
		UtilityClassObject.getTest().log(Status.INFO, "Read Data From Excel");
		// create organization
		OrganisationsPage op = new OrganisationsPage(UtilityClassObject.getDriver());
		CreateNewOrganisationPage cnop = new CreateNewOrganisationPage(UtilityClassObject.getDriver());
		wlib.waitForElementClickable(UtilityClassObject.getDriver(), hp.getOrganisationsLink());
		hp.clickOnOrganisationsLink(driver);
		//hp.getOrganisationsLink().click();

		op.getCreateNewOrgBtn().click();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create New Organization Page");
		cnop.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO, "Create a new Organization");

		// Verify Header Organization Name
		OrganisationDetailsPage odp = new OrganisationDetailsPage(UtilityClassObject.getDriver());
		String HeaderInfo = odp.getOrganisationHeaderInfo().getText();
		Assert.assertEquals(HeaderInfo, orgName,orgName+"is not created==Fail");
		


		// Create Support Ticket
		CreateNewSupportPage cnsp = new CreateNewSupportPage(UtilityClassObject.getDriver());
		SupportPage sp = new SupportPage(UtilityClassObject.getDriver());
		hp.getSupportLink().click();
		sp.getCreateNewSupportBtn().click();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create New Support Ticket Page");

		cnsp.createSupportTicketWithExistingOrganisation(orgName);
		UtilityClassObject.getTest().log(Status.INFO, "Create a new Support Ticket With Existing Organisation");

		hp.navigateToOrganisationsLink();

		// Search for created organisation

		op.searchCreatedOrganisation(orgName);
		String SearchResultOrgText =op.getSearchResultOrgText(orgName).getText();

	
		Assert.assertTrue(SearchResultOrgText.equals(orgName),
		        orgName + " does not exist in the organization list ==> FAIL");

		// If assertion passes, delete organization
		op.getSearchResultOrgText(orgName).click();
		//SearchResultOrg.click();
		odp.deleteOrganisation();
		System.out.println(orgName + " deleted successfully ==> PASS");
		
		hp.navigateToSupportTabUsingJavascriptExecutor();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Support Tab");

		sp.searchCreatedSupport(orgName);

		WebElement SearchResult = sp.getSupportSearchResult();

		String SearchResultText = SearchResult.getText();
		// Verify organization deletion reflected in support list
		Assert.assertTrue(SearchResultText.contains("No results found"),orgName + " not deleted and still showing in the support list ==> FAIL");

		System.out.println(orgName + " deleted successfully and reflected in the support list ==> PASS");
	}
catch (Exception e) {
	    
	    e.printStackTrace();
	    
	}



	}

}
