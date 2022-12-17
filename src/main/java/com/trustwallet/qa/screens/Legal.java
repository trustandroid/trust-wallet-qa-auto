package com.trustwallet.qa.screens;

import com.trustwallet.qa.base.BaseScreen;
import com.trustwallet.qa.utils.Reporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Legal extends BaseScreen {
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/acceptCheckBox")
    private static WebElement acceptCheckbox;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CONTINUE']")
    private static WebElement continueButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Legal']")
    private WebElement headerTitle;
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/disclaimer")
    private WebElement disclaimer;
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/privacy")
    private WebElement privacyPolicy;
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/terms")
    private WebElement termsofService;

    public Legal(AppiumDriver driver) {
        super(driver);
    }

    public void verifyElements() {
        Assert.assertEquals(headerTitle.getText(), "Legal", "Legal Title is not displayed.");
        Reporter.log("Legal Title is displayed.");
        Assert.assertEquals(disclaimer.getText(), "Please review the Trust Wallet Terms of Service and Privacy Policy.", "Disclaimer is not displayed.");
        Reporter.log("Disclaimer is displayed.");
        Assert.assertEquals(privacyPolicy.getText(),"Privacy Policy", "Privacy Policy button is not displayed");
        Reporter.log("Privacy Policy is displayed.");
        Assert.assertEquals(termsofService.getText(),"Terms of Service", "Privacy Policy button is not displayed");
        Reporter.log("Terms of Service is displayed.");
        Assert.assertEquals(acceptCheckbox.getText(),"I've read and accept the Terms of Service and Privacy Policy.", "T&C Accept text is not displayed");
        Reporter.log("Accept the Terms of Service and Privacy Policy is displayed.");
        Assert.assertEquals(continueButton.getText(),"CONTINUE", "CONTINUE button is displayed");
        Reporter.log("CONTINUE button is displayed.");
        acceptCheckbox.click();
        Assert.assertEquals(acceptCheckbox.getAttribute("checked"),"true","Accept checkbox is not checked");
        Assert.assertTrue(continueButton.isEnabled(),"CONTINUE button is not enabled");
        Reporter.log("CONTINUE button is enabled after accepting Terms of Service and Privacy Policy");
    }

    public void agreetoLegal() {
        acceptCheckbox.click();
        Reporter.log("Accept T&C Checkbox is clicked");
        continueButton.click();
        Reporter.log("CONTINUE button is clicked");
    }
}
