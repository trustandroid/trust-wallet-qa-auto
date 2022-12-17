package com.trustwallet.qa.screens;

import com.trustwallet.qa.base.BaseScreen;
import com.trustwallet.qa.utils.Reporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class Passcode extends BaseScreen {
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Create Passcode']")
    private WebElement createPasscodeTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Confirm Passcode']")
    private WebElement confirmPasscodeTitle;
    @AndroidFindBy(xpath = "//*[contains(@text, \"Passcode adds an extra layer of security\")]")
    private WebElement disclaimer;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, \"Those passwords\")]")
    private WebElement passcodesNoMatch;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter Passcode']")
    private WebElement enterPasscodeTitle;

    public Passcode(AppiumDriver driver) {
        super(driver);
    }

    public void enterPasscode(Integer passcode) {
        char[] passcodeDigits = passcode.toString().toCharArray();
        for (char passcodeDigit : passcodeDigits) {
            WebElement digit = driver.findElement(By.xpath("//android.widget.TextView[@text='" + passcodeDigit + "']"));
            wait.until(ExpectedConditions.visibilityOf(digit));
            digit.click();
        }
        Reporter.log("Entered Passcode xxxxxx");
    }

    public void verifyElements(String type) {
        if (type.equals("Create")) {
            Assert.assertEquals(createPasscodeTitle.getText(), "Create Passcode", "Create Passcode title is not displayed.");
            Reporter.log("Create Passcode title is displayed.");
            Assert.assertEquals(disclaimer.getText(), "Passcode adds an extra layer of security\nwhen using the app", "Passcode Disclaimer is not displayed.");
            Reporter.log("Passcode Disclaimer is displayed.");
        } else if (type.equals("Confirm")) {
            Assert.assertEquals(confirmPasscodeTitle.getText(), "Confirm Passcode", "Confirm Passcode title is not displayed.");
            Reporter.log("Confirm Passcode title is displayed.");
            Assert.assertEquals(disclaimer.getText(), "Passcode adds an extra layer of security\nwhen using the app", "Passcode Disclaimer is not displayed.");
            Reporter.log("Passcode Disclaimer is displayed.");
        }
    }

    public void verifyErrorMessage() {
        //There is an issue with this locator due to the way this element is constructed on Android side. So, added contains() for now.
        Assert.assertTrue(passcodesNoMatch.getText().contains("Those passwords didn"), "The Passcodes didn't match error is not displayed when the passcodes doesn't match");
    }

    public void verifyWrongPasscodeEntry(Integer passcode) {
        enterPasscode(passcode);
        Assert.assertTrue(enterPasscodeTitle.isDisplayed(),"Enter Passcode Screen is not displayed.");
        Reporter.log("User is not allowed to enter into the app with a wrong passcode");
    }
}
