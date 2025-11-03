package com.webcrm.System.Test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.webcrm.basetest.BaseClass;
import com.webcrm.generic.webdriverutility.UtilityClassObject;
import com.webcrm.objectrepositoryutility.ActivitiesPage;
import com.webcrm.objectrepositoryutility.CreateNewActivitiesPage;
import com.webcrm.objectrepositoryutility.CreateNewOpportunitiesPage;
import com.webcrm.objectrepositoryutility.CreateNewOrganisationPage;
import com.webcrm.objectrepositoryutility.HomePage;
import com.webcrm.objectrepositoryutility.OpportunitiesPage;
import com.webcrm.objectrepositoryutility.OpportunityDetailsPage;
import com.webcrm.objectrepositoryutility.OrganisationDetailsPage;
import com.webcrm.objectrepositoryutility.OrganisationsPage;
@Listeners(com.webcrm.listenerutility.ListenerImplementationClass.class)
public class System_Tests extends BaseClass {

	@Test(groups = "System")
	public void createAndDeleteOrganizationTest() throws IOException, InterruptedException {
		
		try {
			// Random Number
			
			int randomNo = jlib.getRandomNumber();
			HomePage hp = new HomePage(UtilityClassObject.getDriver());
	
			
	
			String orgName = elib.getDataFromExcel("system", 1, 2) + randomNo;
			UtilityClassObject.getTest().log(Status.INFO, "Read Data From Excel");
			// create organization
	
			OrganisationsPage op = new OrganisationsPage(UtilityClassObject.getDriver());
			CreateNewOrganisationPage cnop = new CreateNewOrganisationPage(UtilityClassObject.getDriver());
			wlib.waitForElementClickable(UtilityClassObject.getDriver(), hp.getOrganisationsLink());
			hp.getOrganisationsLink().click();
			wlib.waitForElementClickable(UtilityClassObject.getDriver(), op.getCreateNewOrgBtn());
			op.getCreateNewOrgBtn().click();
			UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create New Organization Page");
			cnop.createOrg(orgName);
			UtilityClassObject.getTest().log(Status.INFO, "Create a new Organization");
			// Verify Header Organization Name
	
			OrganisationDetailsPage odp = new OrganisationDetailsPage(UtilityClassObject.getDriver());
			String HeaderInfo = odp.getOrganisationHeaderInfo().getText();
	
			System.out.println(HeaderInfo);
			
			Assert.assertEquals(HeaderInfo, orgName,orgName+"is not created==Fail");
			
	
			
			// Search for created organisation
			wlib.waitForPageToLoad(UtilityClassObject.getDriver());
		
			hp.navigateToOrganisationsLink();
			wlib.waitForPageToLoad(UtilityClassObject.getDriver());
			op.searchCreatedOrganisation(orgName);
			
			
			String SearchResultOrgText =op.getSearchResultOrgText(orgName).getText();
	
		
			Assert.assertEquals(SearchResultOrgText, orgName, orgName + " does not exist in the organization list ==> FAIL");
	
			// Click and delete
			op.getSearchResultOrgText(orgName).click();
			odp.deleteOrganisation();
			
			System.out.println(orgName + " deleted successfully ==> PASS");
	
			op.searchCreatedOrganisationWithStaleElementExceptionHandling(orgName);
	
			WebElement SearchResult = op.getOrganisationSearchResult();
	
			String SearchResultText = SearchResult.getText();
	
			// Verify organization deleted successfully
			
			
			Assert.assertTrue(SearchResultText.contains("No results found"),orgName + " not deleted ==> FAIL");
	
			System.out.println(orgName + " deleted successfully ==> PASS");
	
	
	
			WebElement Notification = op.getNotification();
	
			wlib.waitForInvisibilityOfElement(driver, Notification);
		}
		catch (Exception e) {
		    
		    e.printStackTrace();
		    
		}


	}

	@Test(groups = "System")
	public void createAndDeleteOpportunityTest() throws InterruptedException, IOException {
		try {
			// Random Number
	
			int randomNo = jlib.getRandomNumber();
			HomePage hp = new HomePage(UtilityClassObject.getDriver());
	
	
	
			String orgName = elib.getDataFromExcel("system", 4, 2) + randomNo;
			String pipeline = elib.getDataFromExcel("system", 4, 3);
			String description = elib.getDataFromExcel("system", 4, 4);
			String orgHistroryHeader1 = elib.getDataFromExcel("system", 4, 5);
			String orgHistroryHeader2 = description;
	
			UtilityClassObject.getTest().log(Status.INFO, "Read Data From Excel");
	
			// Create Opportunity with new organization
			CreateNewOpportunitiesPage cnopp = new CreateNewOpportunitiesPage(UtilityClassObject.getDriver());
			OpportunitiesPage op = new OpportunitiesPage(UtilityClassObject.getDriver());
			wlib.waitForElementClickable(UtilityClassObject.getDriver(), hp.getOpportunitiesLink());
			hp.getOpportunitiesLink().click();
			wlib.waitForElementClickable(UtilityClassObject.getDriver(), op.getCreateNewOpportunityBtn());
			op.getCreateNewOpportunityBtn().click();
			
	
			UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create New Opportunity Page");
			
			cnopp.createNewOpportunityWithNewOrganisation(orgName, pipeline, description);
			
			UtilityClassObject.getTest().log(Status.INFO, "Create a new Opportunity With New Organisation");
	
			// Verify Created Opportunity
			OpportunityDetailsPage odp = new OpportunityDetailsPage(UtilityClassObject.getDriver());
			odp.verifyCreatedOpportunity(description);
	
			String ActorgHistroryHeader1 = odp.getActorgHistroryHeader1();
	
			String ActorgHistroryHeader2 = odp.getActorgHistroryHeader2(description);
			
			// Verify opportunity is reflected under organization history
			Assert.assertTrue(
			    ActorgHistroryHeader1.equalsIgnoreCase(orgHistroryHeader1) &&
			    orgHistroryHeader2.equalsIgnoreCase(ActorgHistroryHeader2),
			    "Created opportunity " + orgHistroryHeader2 + " is not reflected on Opportunity Details Page under organization history tab ==> FAIL"
			);
	
			System.out.println("Created opportunity " + orgHistroryHeader2 + " is reflected on Opportunity Details Page under organization history tab ==> PASS");
	
	
	
			// Delete Opportunity
	
			odp.deleteOpportunity();
			UtilityClassObject.getTest().log(Status.INFO, "Delete Opportunity");
	
			// Verify opportunity deleted
			op.getOpportunitySearchEdt().sendKeys(orgName);
	
			WebElement SearchResult = op.getOpportunitySearchResult();
	
			String SearchResultText = SearchResult.getText();
			
			// Verify organization deletion is reflected in the opportunity list
			Assert.assertTrue(
			    SearchResultText.contains("No results found"),
			    orgName + " not deleted and still showing in the opportunity list ==> FAIL"
			);
	
			System.out.println(orgName + " deleted successfully and reflected in the opportunity list ==> PASS");
	
	
	
		}
		catch (Exception e) {
		    
		    e.printStackTrace();
		    
		}


	}

	@Test(groups = "System")
	public void createAndEditActivityTest() throws EncryptedDocumentException, IOException, InterruptedException {
		try {
			
			int randomNo = jlib.getRandomNumber();
			HomePage hp = new HomePage(UtilityClassObject.getDriver());
			String orgName = elib.getDataFromExcel("system", 7, 2) + randomNo;
			String activityActionType = elib.getDataFromExcel("system", 7, 5);
			String activityActionTypeForEdit = elib.getDataFromExcel("system", 7, 6);
			UtilityClassObject.getTest().log(Status.INFO, "Read Data From Excel");
	
			// Create Activity With New Organization
			CreateNewActivitiesPage cnap = new CreateNewActivitiesPage(UtilityClassObject.getDriver());
			ActivitiesPage ap = new ActivitiesPage(UtilityClassObject.getDriver());
	
			hp.getActivitiesLink().click();
			wlib.waitForElementClickable(UtilityClassObject.getDriver(), ap.getCreateNewActivityBtn());
			wlib.waitForElementClickable(UtilityClassObject.getDriver(), ap.getCreateNewActivityBtn());
			ap.getCreateNewActivityBtn().click();
			UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create New Activity Page");
	
			cnap.createActivityWithNewOrganisation(orgName);
			UtilityClassObject.getTest().log(Status.INFO, "Create Activity With New Organisation");
	
			// Select Activity Action Type
	
			cnap.selectActivityActionType(activityActionType);
	
			// Verify created activity with given action type
	
			String ActualActivityActionType = cnap.getActualActivityActionType(activityActionType);
			
			
			// Verify activity is reflected with correct action type
			Assert.assertEquals(
			    ActualActivityActionType,
			    activityActionType,
			    "Activity Created is not reflected on activity details page with given action type: " 
			    + activityActionType + " ==> FAIL"
			);
	
			System.out.println("Activity Created is reflected on activity details page with given action type: "
			    + activityActionType + " ==> PASS");
	
	
	
	
			// Edit activity with given action type
	
			cnap.editActivityActionType(activityActionTypeForEdit);
	
			UtilityClassObject.getTest().log(Status.INFO, "Edit Activity with given Action Type");
	
			// Verify edited Activity with given action type
	
			String ActualActivityActionTypeForEdit = cnap.ActualActivityActionTypeForEdit(activityActionTypeForEdit);
			
			// Verify edited activity is reflected with correct action type
			Assert.assertEquals(
			    ActualActivityActionTypeForEdit,
			    activityActionTypeForEdit,
			    "Activity edited is not reflected on activity details page with given action type: " 
			    + activityActionTypeForEdit + " ==> FAIL"
			);
	
			System.out.println("Activity edited is reflected on activity details page with given action type: "
			    + activityActionTypeForEdit + " ==> PASS");
	
			
	
		}
		catch (Exception e) {
		    
		    e.printStackTrace();
		    
		}


	}

}
