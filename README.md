# trust-wallet-qa-auto
A Test repository for [Trust Wallet Android](https://trustwallet.com/) application

> The scope of this test repository is limited to `Create Wallet` functionality.

# Test Plan

`Create Wallet` test plan can be viewed [here](https://drive.google.com/file/d/1ZaDxYm6UQMF_QuTLAegZMEsT36EvNNhl/view)

# Test cases

`Create Wallet` test cases can be viewed [here](https://docs.google.com/spreadsheets/d/17kSCSyKbI_g8Xt99q60dt1pqvzeeYMhnu5dMq_jZRr4/edit#gid=0)

> This guide has been setup using a Windows 10 environment. Follow platform specific installation steps for below prerequisites.

# Setup Prerequisites

- Download and install [node](https://nodejs.org/en/).

  :heavy_check_mark: `node -v` should give something like `v18.12.1` 

- Download and install [appium](https://appium.io) and its dependencies using the below commands.
         `npm install -g appium@next`
         `appium driver install uiautomator2`

  :heavy_check_mark: Running `appium -v` should return something like `2.0.0-beta.47`

  :heavy_check_mark: Running `appium driver list --installed` should return something like `√ Listing installed drivers - uiautomator2@2.12.0 [installed (NPM)]`

- Download [Maven](https://maven.apache.org/download.cgi) and add Maven's `bin` folder to `PATH`

  :heavy_check_mark: `mvn -v` should return something like `Apache Maven 3.8.6 (84538c9988a25aec085021c365c560670ad80f63)`

- Download and install [Java](https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html). Add `JAVA_HOME` environment variable and add `JAVA_HOME/bin` to `PATH` variale.

  :heavy_check_mark: `java --version` should return something like `java 11.0.17 2022-10-18 LTS`

- Download and install [Android Studio](https://developer.android.com/studio/). 
  Set `ANDROID_HOME` = `%HOMEPATH%\AppData\Local\Android\Sdk` and add `%ANDROID_HOME%\tools`, `%ANDROID_HOME%\emulator`, `%ANDROID_HOME%\platform-tools` to `PATH` variable.
  (Update the Paths accordingly for Linux or Mac machines.) 

  Create an emulator using the AVD manager in Android studio and set it up to Quick boot (Follow the guide [here](https://wix.github.io/Detox/docs/next/guide/android-dev-env/#setting-up-a-quick-boot-snapshot-from-the-emulator))

  :heavy_check_mark: `adb --version` should return something like `Android Debug Bridge version 1.0.41`

  :heavy_check_mark: `emulator -list-avds` should return something like `Pixel_4` `<Whatever name that was given while creating the emulator in Android studio>`

  :heavy_check_mark: `emulator -avd <your_emulator_name>` should launch a virtual device. Wait for it to launch completely until you see the Android home screen.

# Test Execution Details

Update the `PLATFORM_VERSION`, `avd`(emulator name created in prior steps) OR `UDID` (In case you are using a real android device connected to your machine. `adb devices`)  in [Driver.java](src/main/java/com/trustwallet/qa/utils/Driver.java) [initialise()](https://github.com/trustandroid/trust-wallet-qa-auto/blob/main/src/main/java/com/trustwallet/qa/utils/Driver.java#L11) method accordingly.

Run `mvn clean test` to run the tests and generate `testNG` and `extent report`

The Test Execution Reports will be available at 
1. Extent - `extent/TrustWalletAndroidE2e.html` Live Report can be found [here](https://trustandroid.github.io)
2. Test - `target/surefire-reports/index.html`

Below are the Full Suite run result screenshots from `testNG` console and `extent report`

![Full Suite](https://lh3.googleusercontent.com/drive-viewer/AFDK6gOHiQQfNpEtSRDA-EZ49_5VeXuBfp0DDS5PueAO4PT4QMibne5Md0PwgEzq4DV5X04Iq3YpBpe1lR8gYLQH_ZSwMe5r_Q=w1920-h852)
![Extent Full suite](https://lh3.googleusercontent.com/drive-viewer/AFDK6gMYtOO8jXTiuZwjhaNZkXYM_3A1HA-WG-YcgNbaDURszeuxvJtvl9b4WScV7f8uRKDyY0C5IpDkIhQ-iwWSXjF_lOpODQ=w1920-h852)
![Extent Summary](https://lh3.googleusercontent.com/drive-viewer/AFDK6gPFxT3e6Yc87sMX2Jq6GkFXu6dpPSWPV22Wh2Kdxn5KxQ3soOVkd1gLOPDvohm2Nm2jJMtXiEBbh07JedvZvjNlnAS2=w1920-h852)

# Future Improvements
- Group tests into `smoke` `feature_name` etc. and selectively pick by parameterizing the `mvn` command
- Parameterize the tests to run the same tests on different types of Android devices using a cloud farm(Parallel Execution) or cloning multiple emulators plus selenium grid.
- Integrate the repo with any CI/CD tool, schedule the job and send automated Test Execution Report link to slack groups etc.
- Integrate with [Applitools Eyes](https://eyes.applitools.com/) to automate visual testing.
- Add Test video recording to the report.