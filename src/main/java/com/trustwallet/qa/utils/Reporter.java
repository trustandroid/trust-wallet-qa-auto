package com.trustwallet.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.trustwallet.qa.base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Reporter {
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;

    public static void start() {
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator+"extent"+File.separator+"TrustWalletAndroidE2e.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        sparkReporter.config().setDocumentTitle("Trust wallet android test suite");
        sparkReporter.config().setReportName("Trust wallet android test suite");
        sparkReporter.config().setTheme(Theme.STANDARD);
    }

    public static String getScreenShot(String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) BaseTest.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") +File.separator+"extent"+File.separator+"screenshots"+File.separator+ screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        byte[] file = FileUtils.readFileToByteArray(finalDestination);
        return  Base64.getEncoder().encodeToString(file);
    }

    public static void addResult(ITestResult result) throws Exception {

        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println(result.getName() +" - FAILED***");
            logger.log(Status.FAIL, result.getThrowable());
            String base64Screenshot = getScreenShot(result.getName());

            logger.fail("click here for failed screenshot",MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        } else if (result.getStatus() == ITestResult.SKIP) {
            System.out.println(result.getName() +" - SKIPPED***");
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case SKIPPED", ExtentColor.ORANGE));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println(result.getName() +" - PASSED***");
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
        }
    }

    public static void end() {
        extent.flush();
    }
    public static void log(final String message){
        System.out.println("- "+message);
        org.testng.Reporter.log("- "+message+"\n");
        logger.info(message);
    }
    public static void logTest(final String testDescription){
        logger = extent.createTest(testDescription);
        org.testng.Reporter.log("***Started - "+testDescription+"\n");
        System.out.println("***Started - "+testDescription);
    }
}