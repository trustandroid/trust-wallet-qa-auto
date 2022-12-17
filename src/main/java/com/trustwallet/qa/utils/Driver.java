package com.trustwallet.qa.utils;

import com.trustwallet.qa.base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.time.Duration;

public class Driver {
    public static void initialise(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.0");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        //For running the tests on physical device
        capabilities.setCapability(MobileCapabilityType.UDID, "9B111FFAZ006ZH");
        capabilities.setCapability("avd", "Pixel_4");
        //Downloads the apk from the url instead of the already downloaded apk
//        capabilities.setCapability(MobileCapabilityType.APP, "https://trustwallet.com/dl/apk");
        capabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"v6.55_release.apk");
        capabilities.setCapability("autoLaunch", false);
        capabilities.setCapability("appWaitActivity", "*");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 360);
        BaseTest.driver = new AndroidDriver(Server.server.getUrl(), capabilities);
        BaseTest.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
}
