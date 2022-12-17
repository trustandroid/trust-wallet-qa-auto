package com.trustwallet.qa.base;

import com.google.common.collect.ImmutableMap;
import com.trustwallet.qa.screens.*;
import com.trustwallet.qa.utils.Driver;
import com.trustwallet.qa.utils.Reporter;
import com.trustwallet.qa.utils.Server;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

public class BaseTest {
    public static AppiumDriver driver;
    protected static String appID = "com.wallet.crypto.trustapp";
    protected Welcome welcome;
    protected Legal legal;
    protected Passcode passcode;
    protected Consent consent;
    protected Recovery recovery;

    @BeforeSuite
    public static void beforeSuite() {
        Server.start();
    }

    @BeforeTest
    public void beforeTest() {
        Reporter.start();
        Driver.initialise();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        driver.executeScript("mobile: installApp", ImmutableMap.of("appPath", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "v6.55_release.apk"));
        driver.executeScript("mobile: activateApp", ImmutableMap.of("appId", appID));
        String testName= method.getName();
        Reporter.logTest(testName);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws Exception {
        Reporter.addResult(result);
        driver.executeScript("mobile: removeApp", ImmutableMap.of("appId", appID));
    }

    @AfterTest
    public void afterTest() {
        if (null != driver) {
            driver.quit();
        }
        Reporter.end();
    }

    @AfterSuite
    public void afterSuite() {
        Server.stop();
    }

    public void restartApp() {
        driver.executeScript("mobile: terminateApp", ImmutableMap.of("appId", appID));
        driver.executeScript("mobile: activateApp", ImmutableMap.of("appId", appID));
    }

}
