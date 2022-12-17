package com.trustwallet.qa.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import java.time.Duration;

public class Server {
    protected static AppiumDriverLocalService server;
    public static void start(){
        AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
        serviceBuilder.usingAnyFreePort();
        serviceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "error").withTimeout(Duration.ofSeconds(120));
        server = AppiumDriverLocalService.buildService(serviceBuilder);
        server.start();
    }
    public static void stop(){
        server.stop();
    }
}
