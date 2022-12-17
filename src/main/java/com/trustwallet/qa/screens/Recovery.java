package com.trustwallet.qa.screens;

import com.trustwallet.qa.base.BaseScreen;
import com.trustwallet.qa.utils.Reporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class Recovery extends BaseScreen {
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/title")
    private WebElement yourSecretPhraseTitle;
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/subtitle")
    private WebElement writeDownSecretPhraseSubtitle;
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/value")
    private List<WebElement> passPhraseElements;
    @AndroidFindBy(id="com.wallet.crypto.trustapp:id/action_copy")
    private WebElement copy;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='DO NOT share your phrase to anyone as this gives full access to your wallet!']")
    private WebElement cautionTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Trust Wallet support will NEVER reach out to ask for it']")
    private WebElement cautionSubTitle;
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/action_verify")
    private WebElement continueButton;
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/action_done")
    private WebElement doneButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Well done! ']")
    private WebElement message;
    @AndroidFindBy(xpath = "//*[contains(@text, \"new wallet\")]")
    private WebElement letsExplore;

    public Recovery(AppiumDriver driver) {
        super(driver);
    }

    public ArrayList<String> getpassphrase() {
        ArrayList<String> passPhrase = new ArrayList<>();
        for (int counter = 0; counter < 12; counter++) {
            passPhrase.add(passPhraseElements.get(counter).getText());
        }
        copy.click();
        return passPhrase;
    }

    public void continueToConfirmRecoveryScreen() {
        continueButton.click();
        Reporter.log("Continue button is clicked");
    }

    public void confirmRecoveryPhrase(ArrayList<String> passPhrase) {
        for (String s : passPhrase) {
            WebElement passCodeDigit = driver.findElement(By.xpath("//android.widget.TextView[@text='" + s + "']"));
            passCodeDigit.click();
        }
        Reporter.log("Entered Passphrase in right order");
        wait.until(ExpectedConditions.visibilityOf(message));
        Assert.assertEquals(message.getText().trim(), "Well done!", "Well done! message is not displayed.");
        Reporter.log("Well done! message is displayed");
        doneButton.click();
        Reporter.log("DONE button is clicked");
        wait.until(ExpectedConditions.visibilityOf(letsExplore));
        Assert.assertEquals(letsExplore.getText(), "Let's explore your \n" + "new wallet!", "Let's explore your new wallet! message is not displayed.");
        Reporter.log("The Introductory \"Set to disappear\"  slides are displayed.");
    }

    public void verifyElements(){
        wait.until(ExpectedConditions.textToBePresentInElement(yourSecretPhraseTitle,"Your Secret Phrase"));
        Assert.assertEquals(yourSecretPhraseTitle.getText(),"Your Secret Phrase","Your Secret Phrase title is not displayed.");
        Reporter.log("Your Secret Phrase title is displayed");
        Assert.assertEquals(writeDownSecretPhraseSubtitle.getText(),"Write down or copy these words in the right order and save them somewhere safe.","Sub title is not displayed.");
        Reporter.log("Your Secret Phrase subtitle is displayed");
        Assert.assertEquals(passPhraseElements.size(),12,"12 Passphrase words are not displayed");
        Reporter.log("12 Passphrase words are displayed");
        Assert.assertTrue(cautionTitle.isDisplayed(),"Caution Title is not displayed.");
        Reporter.log("Caution Title is displayed.");
        Assert.assertTrue(cautionSubTitle.isDisplayed(),"Caution SubTitle is not displayed.");
        Reporter.log("Caution SubTitle is displayed.");
        Assert.assertEquals(continueButton.getText(),"Continue");
        Assert.assertTrue(continueButton.isEnabled(),"Continue button is not enabled.");
        Reporter.log("Continue button is displayed.");

    }
    public void verify_verifysecretPhraseElements(){
        Assert.assertEquals(yourSecretPhraseTitle.getText(),"Verify Secret Phrase","Your Secret Phrase title is not displayed.");
        Reporter.log("Verify Secret Phrase title is displayed");
        Assert.assertEquals(writeDownSecretPhraseSubtitle.getText(),"Tap the words to put them next to each other in the correct order.","Sub title is not displayed.");
        Reporter.log("Verify Secret Phrase subtitle is displayed");
        Assert.assertEquals(passPhraseElements.size(),12,"12 Passphrase words are not displayed");
        Reporter.log("12 Passphrase words are displayed");
        Assert.assertEquals(doneButton.getText(),"Done");
        Assert.assertFalse(doneButton.isEnabled(),"Done button is not disabled.");
        Reporter.log("Done button is displayed.");

    }

    public void verifyErrorPassphrase(ArrayList<String> passPhrase) {
        ArrayList<String> reversePasphrase = new ArrayList<>();
        for (int counter = passPhrase.size() - 1; counter >= 0; counter--) {
            reversePasphrase.add(passPhrase.get(counter));
        }
        for (String s : reversePasphrase) {
            WebElement passCodeDigit = driver.findElement(By.xpath("//android.widget.TextView[@text='" + s + "']"));
            passCodeDigit.click();
        }
        WebElement message = driver.findElement(By.id("com.wallet.crypto.trustapp:id/message"));
        Assert.assertEquals(message.getText(),"Invalid order. Try again!","Invalid Order message is not displayed.");
    }

}
