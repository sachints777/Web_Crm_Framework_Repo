package com.webcrm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	public void waitForPageToLoad(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

	}


	public WebDriverWait  explicitWait(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		
		return wait;
		

	}

	
	
	public void waitForElementPresent(WebDriver driver, String xpathlocator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathlocator)));

	}
	
	public void waitForElementClickableByXpath(WebDriver driver, String xpathlocator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathlocator)));

	}
	
	public void waitForElementClickableById(WebDriver driver, String idlocator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		wait.until(ExpectedConditions.elementToBeClickable(By.id(idlocator)));

	}
	public void waitForElementClickable(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}
	
	public void waitForInvisibilityOfElement(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		wait.until(ExpectedConditions.invisibilityOf(element));

	}
	
	public void waitForVisibilityOfAllElements(WebDriver driver, String xpathlocator) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathlocator)));

	}
	


	public void switchToTabOnTitle(WebDriver driver, String partialTitle) {

		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while (it.hasNext())

		{
			String windowID = it.next();
			driver.switchTo().window(windowID);
			String title = driver.getTitle();
			if (title.contains(partialTitle)) {
				break;

			}

		}
	}

	public void switchToFrame(WebDriver driver, int index) {

		driver.switchTo().frame(index);

	}
	
	public void windowMaximize(WebDriver driver) {

		driver.manage().window().maximize();

	}
	
	

	public void switchToFrame(WebDriver driver, String nameorID) {

		driver.switchTo().frame(nameorID);

	}

	public void switchToFrame(WebDriver driver, WebElement element) {

		driver.switchTo().frame(element);

	}

	public void switchToAlertAndAccept(WebDriver driver) {

		driver.switchTo().alert().accept();
	}

	public void switchToAlertAndCancel(WebDriver driver) {

		driver.switchTo().alert().dismiss();
	}
	public void switchToParentWindow(WebDriver driver) {
		driver.switchTo().parentFrame();
	}
	public void selectdropdown(WebElement element,String text) {
	
	Select select= new Select(element);
	select.selectByVisibleText(text);
		
	}
	
	public void selectdropdown(WebElement element,int index) {
		
		Select select= new Select(element);
		select.selectByIndex(index);
			
	}
	
	public void mouseHoverOnElement(WebDriver driver,WebElement element) {
	
	Actions action = new Actions(driver);	
	
	action.moveToElement(element).perform();
		
		
	}
	
	public void doubleClick(WebDriver driver,WebElement element) {
		Actions action = new Actions(driver);	
		
		action.doubleClick(element).perform();
	
	}
	
	public void scrollIntoView(WebDriver driver,WebElement organisationTab) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		js.executeScript("arguments[0].scrollIntoView(true);",organisationTab);	
	
	}
	
	
	
	public void acceptAlertIfPresent(WebDriver driver) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().accept();
	        System.out.println("Alert handled.");
	    } catch (Exception e) {
	        System.out.println("No alert present.");
	    }
	}

	public void dismissAlertIfPresent(WebDriver driver) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().dismiss();
	        System.out.println("Alert dismissed.");
	    } catch (Exception e) {
	        System.out.println("No alert present.");
	    }
	}

		
	


}
