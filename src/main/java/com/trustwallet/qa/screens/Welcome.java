package com.trustwallet.qa.screens;

import com.trustwallet.qa.base.BaseScreen;
import com.trustwallet.qa.utils.Gestures;
import com.trustwallet.qa.utils.Reporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Welcome extends BaseScreen {
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/pager")
    private static WebElement carousel;
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/new_account_action")
    private static WebElement createNewWallet;
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/img")
    private static WebElement heroImage;
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/title")
    private static WebElement title;
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/message")
    private static WebElement message;
    @AndroidFindBy(id = "com.wallet.crypto.trustapp:id/import_account_action")
    private static WebElement alreadyHaveAWallet;

    public Welcome(AppiumDriver driver) {
        super(driver);
    }

    public void clickCreateNewWallet() {
        createNewWallet.click();
        Reporter.log("Create a New Wallet button is clicked");
    }

    public void verifyElements() {
        Assert.assertEquals(createNewWallet.getText(), "CREATE A NEW WALLET", "CREATE A NEW WALLET button is not displayed.");
        Reporter.log("CREATE A NEW WALLET button is displayed");

        Assert.assertEquals(alreadyHaveAWallet.getText(), "I already have a wallet", "I already have a wallet button is not displayed.");
        Reporter.log("I already have a wallet button is displayed");

        Assert.assertTrue(heroImage.isDisplayed(), "The Intro Banner 1 is not displayed.");
        Assert.assertEquals(title.getText(), "Private and secure", "Intro Title 1 is not displayed as expected.");
        Assert.assertEquals(message.getText(), "Private keys never leave your device.", "Intro Message 1 is not displayed as expected.");
        Reporter.log("Intro Carousel item 1 is displayed");
        for (int counter = 2; counter < 5; counter++) {
            Gestures.swipeElement(carousel, "left");
            if (counter == 2) {
                Assert.assertTrue(heroImage.isDisplayed(), "The Intro Banner " + counter + " is not displayed.");
                Assert.assertEquals(title.getText(), "All assets in one place", "Intro Title " + counter + " is not displayed as expected.");
                Assert.assertEquals(message.getText(), "View and store your assets seamlessly.", "Intro Message " + counter + " is not displayed as expected.");
                Reporter.log("Intro Carousel item " + counter + " is displayed");
            } else if (counter == 3) {
                Assert.assertTrue(heroImage.isDisplayed(), "The Intro Banner " + counter + " is not displayed.");
                Assert.assertEquals(title.getText(), "Trade assets", "Intro Title " + counter + " is not displayed as expected.");
                Assert.assertEquals(message.getText(), "Trade your assets anonymously.", "Intro Message " + counter + " is not displayed as expected.");
                Reporter.log("Intro Carousel item " + counter + " is displayed");
            } else if (counter == 4) {
                Assert.assertTrue(heroImage.isDisplayed(), "The Intro Banner " + counter + " is not displayed.");
                Assert.assertEquals(title.getText(), "Explore decentralized apps", "Intro Title " + counter + " is not displayed as expected.");
                Assert.assertEquals(message.getText(), "Earn, explore, utilize, spend, trade, and more.", "Intro Message " + counter + " is not displayed as expected.");
                Reporter.log("Intro Carousel item " + counter + " is displayed");
            }
        }
    }

}
