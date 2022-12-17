package com.trustwallet.qa;

import com.trustwallet.qa.base.BaseTest;
import com.trustwallet.qa.screens.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TrustWalletTest extends BaseTest {
    @BeforeTest
    public void setup() {
        welcome = new Welcome(driver);
        legal = new Legal(driver);
        passcode = new Passcode(driver);
        consent = new Consent(driver);
        recovery = new Recovery(driver);
    }

    @Test(testName = "User is able to create a new wallet")
    public void verify_create_Wallet() {
        welcome.clickCreateNewWallet();
        legal.agreetoLegal();
        passcode.enterPasscode(913423);
        passcode.enterPasscode(913423);
        consent.accept();
        ArrayList<String> passPhrase = recovery.getpassphrase();
        recovery.continueToConfirmRecoveryScreen();
        recovery.confirmRecoveryPhrase(passPhrase);
    }

    @Test(testName = "Verify Welcome Screen")
    public void verify_welcome_screen() {
        welcome.verifyElements();
    }

    @Test(testName = "Verify Legal Screen")
    public void verify_legal_screen() {
        welcome.clickCreateNewWallet();
        legal.verifyElements();
    }

    @Test(testName = "Verify Create Passcode Screen")
    public void verify_create_passcode_screen() {
        welcome.clickCreateNewWallet();
        legal.agreetoLegal();
        passcode.verifyElements("Create");
    }

    @Test(testName = "Verify Confirm Passcode Screen")
    public void verify_confirm_passcode_screen() {
        welcome.clickCreateNewWallet();
        legal.agreetoLegal();
        passcode.enterPasscode(984567);
        passcode.verifyElements("Confirm");
    }

    @Test(testName = "Verify Consent Screen")
    public void verify_consent_screen() {
        welcome.clickCreateNewWallet();
        legal.agreetoLegal();
        passcode.enterPasscode(984567);
        passcode.enterPasscode(984567);
        consent.verifyElements();
    }

    @Test(testName = "Verify Secret Phrase Screen")
    public void verify_secret_phrase_screen() {
        welcome.clickCreateNewWallet();
        legal.agreetoLegal();
        passcode.enterPasscode(984567);
        passcode.enterPasscode(984567);
        consent.accept();
        recovery.verifyElements();
    }

    @Test(testName = "Verify \"Verify Secret Phrase\" Screen")
    public void verify_verify_secret_phrase_screen() {
        welcome.clickCreateNewWallet();
        legal.agreetoLegal();
        passcode.enterPasscode(984567);
        passcode.enterPasscode(984567);
        consent.accept();
        recovery.continueToConfirmRecoveryScreen();
        recovery.verify_verifysecretPhraseElements();
    }

    @Test(testName = "Verify Passcodes mismatch error")
    public void verify_error_passcodes_mismatch() {
        welcome.clickCreateNewWallet();
        legal.agreetoLegal();
        passcode.enterPasscode(984567);
        passcode.enterPasscode(765489);
        passcode.verifyErrorMessage();
    }

    @Test(testName = "This is a dummy test for failed screenshots")
    public void verify_a_failing_test() {
        Assert.fail("This test will always fail");
    }

    @Test(testName = "Verify Passphrases mismatch error")
    public void verify_error_passphrase_mismatch() {
        welcome.clickCreateNewWallet();
        legal.agreetoLegal();
        passcode.enterPasscode(913423);
        passcode.enterPasscode(913423);
        consent.accept();
        ArrayList<String> passPhrase = recovery.getpassphrase();
        recovery.continueToConfirmRecoveryScreen();
        recovery.verifyErrorPassphrase(passPhrase);
    }

    @Test(testName = "Verify unauthorized application access")
    public void verify_app_security() {
        welcome.clickCreateNewWallet();
        legal.agreetoLegal();
        passcode.enterPasscode(913423);
        passcode.enterPasscode(913423);
        consent.accept();
        ArrayList<String> passPhrase = recovery.getpassphrase();
        recovery.continueToConfirmRecoveryScreen();
        recovery.confirmRecoveryPhrase(passPhrase);
        restartApp();
        passcode.verifyWrongPasscodeEntry(111111);
    }
}
