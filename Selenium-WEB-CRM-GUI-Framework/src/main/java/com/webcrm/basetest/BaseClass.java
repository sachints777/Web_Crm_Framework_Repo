package com.webcrm.basetest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.webcrm.generic.databaseutility.DataBaseUtility;
import com.webcrm.generic.fileutility.ExcelUtility;
import com.webcrm.generic.fileutility.FileUtility;
import com.webcrm.generic.webdriverutility.JavaUtility;
import com.webcrm.generic.webdriverutility.UtilityClassObject;
import com.webcrm.generic.webdriverutility.WebDriverUtility;
import com.webcrm.objectrepositoryutility.HomePage;
import com.webcrm.objectrepositoryutility.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriverUtility wlib = new WebDriverUtility();

	public static WebDriver driver ;
	

	@BeforeSuite()
	public void configBS() {
		
		

	}

	@AfterSuite()
	public void configAS() {
		
	}

	
//@Parameters("BROWSER")
	@BeforeClass(alwaysRun = true)
	public void configBC() throws IOException {	
		System.out.println("---Execute BC----");
		System.out.println("Launch Browser");
		// String BROWSER=browser;
		String BROWSER =  flib.getDataFromPropertiesFile("browser"); //browser; 
		WebDriverManager.chromedriver().setup();
	

		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();
			}
			else if(BROWSER.equals("firefox")) {
				driver= new FirefoxDriver();
			}
			else if(BROWSER.equals("edge")) {
				driver=new EdgeDriver();
			}
			else {
				driver=new ChromeDriver();
			}
		
		 UtilityClassObject.setDriver(driver);
	
		wlib.waitForPageToLoad(UtilityClassObject.getDriver());
		wlib.windowMaximize(UtilityClassObject.getDriver());

	}

	@AfterClass(alwaysRun = true)
	public void configAC() {
		

		System.out.println("---Execute AC----");
		System.out.println("Close Browser");
		UtilityClassObject.getDriver().quit();

	}

	@BeforeMethod(alwaysRun = true)
	public void configBM() throws IOException {
		

		System.out.println("---Execute BM----");
		System.out.println("Login to App");
		
		
		String URL =flib.getDataFromPropertiesFile("url");
		String ACCOUNTNAME=flib.getDataFromPropertiesFile("accountname");
		String USERNAME =flib.getDataFromPropertiesFile("username");
		String PASSWORD =flib.getDataFromPropertiesFile("password");
		
		LoginPage lp = new LoginPage(UtilityClassObject.getDriver());
		wlib.waitForPageToLoad(UtilityClassObject.getDriver());
		lp.loginToApp(ACCOUNTNAME, USERNAME, PASSWORD, URL);
		wlib.waitForPageToLoad(UtilityClassObject.getDriver());
	}

	@AfterMethod(alwaysRun = true)
	public void configAM() {
		

		System.out.println("---Execute AM----");
		System.out.println("LogOut");
		try {
			HomePage hp = new HomePage(UtilityClassObject.getDriver());
			hp.logout();
		} catch (Exception e) {
			System.out.println("Browser already closed, skipping logout.");
		}
	}

}
