package com.trustwallet.qa.screens;

import com.trustwallet.qa.base.BaseScreen;
import com.trustwallet.qa.utils.Reporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.testng.Assert;

public class Consent extends BaseScreen {
    public Consent(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Back up your wallet now!']")
    private WebElement consentTitle;
    @CacheLookup
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/subtitle")
    private WebElement consentSubtitle;
    @CacheLookup
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/icon")
    private WebElement consentImage;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.wallet.crypto.trustapp:id/concent1']/android.view.ViewGroup/android.widget.CheckBox")
    private WebElement consent1Checkbox;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.wallet.crypto.trustapp:id/concent2']/android.view.ViewGroup/android.widget.CheckBox")
    private WebElement consent2Checkbox;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.wallet.crypto.trustapp:id/concent3']/android.view.ViewGroup/android.widget.CheckBox")
    private WebElement consent3Checkbox;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.wallet.crypto.trustapp:id/concent1']/android.view.ViewGroup/android.widget.TextView")
    private WebElement consent1text;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.wallet.crypto.trustapp:id/concent2']/android.view.ViewGroup/android.widget.TextView")
    private WebElement consent2text;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.wallet.crypto.trustapp:id/concent3']/android.view.ViewGroup/android.widget.TextView")
    private WebElement consent3text;
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/next")
    private WebElement next;

    public void accept() {
        consent1Checkbox.click();
        Reporter.log("Consent 1 Checked");
        consent2Checkbox.click();
        Reporter.log("Consent 2 Checked");
        consent3Checkbox.click();
        Reporter.log("Consent 3 Checked");
        next.click();
        Reporter.log("NEXT button is clicked");
    }

    public void verifyElements(){
        Assert.assertEquals(consentTitle.getText(),"Back up your wallet now!","Consent screen title is not displayed.");
        Reporter.log("Consent Screen title is displayed.");
        Assert.assertEquals(consentSubtitle.getText(),"In the next step you will see Secret Phrase (12 words) that allows you to recover a wallet.","Consent screen subtitle is not displayed.");
        Reporter.log("Consent Screen subtitle is displayed.");
        Assert.assertTrue(consentImage.isDisplayed(),"Consent screen image is not displayed.");
        Reporter.log("Consent Screen image is displayed.");

        Assert.assertEquals(consent1text.getText(),"If I lose my secret phrase, my funds will be lost forever.","Consent text1 is not displayed.");
        Reporter.log("Consent text 1 is displayed.");
        Assert.assertEquals(consent2text.getText(),"If I expose or share my secret phrase to anybody, my funds can get stolen.","Consent text2 is not displayed.");
        Reporter.log("Consent text 2 is displayed.");
        Assert.assertEquals(consent3text.getText(),"Trust Wallet support will NEVER reach out to ask for it","Consent text3 is not displayed.");
        Reporter.log("Consent text 3 is displayed.");
        Assert.assertEquals(consent1Checkbox.getAttribute("checked"),"false","Consent 1 is checked");
        Assert.assertEquals(consent2Checkbox.getAttribute("checked"),"false","Consent 2 is checked");
        Assert.assertEquals(consent3Checkbox.getAttribute("checked"),"false","Consent 3 is checked");
        Reporter.log("All Consent Checkboxes are unchecked by default.");
        Assert.assertEquals(next.getText(),"CONTINUE","CONTINUE button is not displayed.");
        Reporter.log("CONTINUE button is disabled by default");
    }
}
