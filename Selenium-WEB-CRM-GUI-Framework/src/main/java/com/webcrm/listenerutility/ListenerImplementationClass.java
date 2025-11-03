package com.webcrm.listenerutility;

import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.webcrm.generic.webdriverutility.UtilityClassObject;

public class ListenerImplementationClass implements ITestListener, ISuiteListener {

    private static ExtentReports report;
    private static ExtentSparkReporter spark;

    @Override
    public void onStart(ISuite suite) {
        System.out.println("=== Report Configuration Started ===");
        String time = new Date().toString().replace(" ", "_").replace(":", "_");

        spark = new ExtentSparkReporter("./AdvanceReport/report_" + time + ".html");
        spark.config().setDocumentTitle("CRM Test Suite Results");
        spark.config().setReportName("CRM Automation Report");
        spark.config().setTheme(Theme.DARK);

        report = new ExtentReports();
        report.attachReporter(spark);
        report.setSystemInfo("OS", "Windows 11");
        report.setSystemInfo("Browser", "Chrome");
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("=== Report Backup Completed ===");
        if (report != null) {
            report.flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = report.createTest(result.getMethod().getMethodName());
        UtilityClassObject.setTest(test);
        UtilityClassObject.getTest().log(Status.INFO, result.getMethod().getMethodName() + " === STARTED ===");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        UtilityClassObject.getTest().log(Status.PASS, result.getMethod().getMethodName() + " === PASSED ===");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        UtilityClassObject.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " === FAILED ===");
        UtilityClassObject.getTest().log(Status.FAIL, result.getThrowable());

        try {
            String time = new Date().toString().replace(" ", "_").replace(":", "_");
            TakesScreenshot ts = (TakesScreenshot) UtilityClassObject.getDriver();
            String base64Screenshot = ts.getScreenshotAs(OutputType.BASE64);
            UtilityClassObject.getTest().addScreenCaptureFromBase64String(base64Screenshot, result.getMethod().getMethodName() + "_" + time);
        } catch (Exception e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    	
        UtilityClassObject.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " === SKIPPED ===");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

    @Override
    public void onStart(ITestContext context) { }

    @Override
    public void onFinish(ITestContext context) { }
}
