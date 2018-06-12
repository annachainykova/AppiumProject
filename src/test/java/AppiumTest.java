
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.aspectj.lang.annotation.After;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumTest {
    private static AndroidDriver<MobileElement> driver;

    public static final String EMAIL = "echornobai@intersog.com";
    public static final String CORRECT_PWD = "testpass";
    public static final String CREATE_ACCOUNT_MESSAGE = "You will be taken to ted.com to create an account.";
    public static final String USERNAME = "Elena Chornobai ";

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Google");
        File app = new File("bin/TED_v3.1.20_apkpure.com.apk");
        caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        //caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");

        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.ted.android");
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.ted.android.view.home.HomeActivity");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }




    @Test
    public void  successLoginTest() {
        MobileElement profile = driver.findElementByAccessibilityId("My talks");
        profile.click();
        MobileElement loginSection = driver.findElementById("myTalksLoggedOutActionLogin");
        loginSection.click();
        MobileElement emailInput = driver.findElementById("user_email");
        emailInput.sendKeys(EMAIL);
        MobileElement pwdInput = driver.findElementById("user_password");
        pwdInput.sendKeys(CORRECT_PWD);
        MobileElement logInBtn = driver.findElementByAccessibilityId("Log in");
        logInBtn.click();
        MobileElement OKButton = driver.findElementById("button2");
        OKButton.click();
        MobileElement loggedInTitle = driver.findElementById("myTalksLoggedInTitle");
        Assert.assertEquals(loggedInTitle.getText(), "Elena Chornobai ");
    }

    @Test
    public void registerText() throws InterruptedException {
        MobileElement profileBtn = driver.findElementByAccessibilityId("My talks");
        profileBtn.click();
        MobileElement loginBtn = driver.findElementById("com.ted.android:id/myTalksLoggedOutActionLogin");
        loginBtn.click();
        Thread.sleep(5000);
        try {
            driver.hideKeyboard();
        } catch (Exception e) {

        }

        MobileElement signUpBtn = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[5]/android.view.View[2]");
        signUpBtn.click();
        Assert.assertTrue(driver.findElementById("android:id/message").getText().equals(CREATE_ACCOUNT_MESSAGE));
        MobileElement cancelBtn = driver.findElementById("android:id/button2");
        cancelBtn.click();
    }

    @Test
    public void logIn() throws InterruptedException {
        MobileElement profileBtn = driver.findElementByAccessibilityId("My talks");
        profileBtn.click();
        MobileElement loginBtn = driver.findElementById("com.ted.android:id/myTalksLoggedOutActionLogin");
        loginBtn.click();
        MobileElement usernameField = driver.findElementById("user_email");
        usernameField.sendKeys(EMAIL);
        MobileElement passwordField = driver.findElementById("user_password");
        passwordField.sendKeys(CORRECT_PWD);
        try {
            driver.hideKeyboard();
        } catch (Exception e) {

        }
        MobileElement logInBtn = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[4]/android.widget.Button");
        logInBtn.click();
        MobileElement alertSuccess = driver.findElementById("com.ted.android:id/alertTitle");
        Assert.assertTrue(alertSuccess.getText().equals("Success!"));
        MobileElement okBtn = driver.findElementById("android:id/button2");
        okBtn.click();
        MobileElement userName = driver.findElementById("com.ted.android:id/myTalksLoggedInTitle");
        Assert.assertTrue(userName.getText().equals(USERNAME));

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}


