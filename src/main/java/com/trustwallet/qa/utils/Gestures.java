package com.trustwallet.qa.utils;

import com.google.common.collect.ImmutableMap;
import com.trustwallet.qa.base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class Gestures {
    public static void swipeElement(WebElement element, String direction) {
        ((JavascriptExecutor) BaseTest.driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "direction", direction,
                "percent", 0.75
        ));
    }

}
