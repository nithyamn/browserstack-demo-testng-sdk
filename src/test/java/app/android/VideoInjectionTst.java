package app.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class VideoInjectionTst {
    public static String username = "";
    public static String accessKey = "";
    @Test
    public void videoInjectionTest() throws MalformedURLException, InterruptedException {
        //String mediaUrl = "";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> bstackOptions = new HashMap<String, Object>();

        bstackOptions.put("sessionName", "videoInjection - 2.12.1");
        bstackOptions.put("buildName", "Device Features - Android");
        bstackOptions.put("enableCameraVideoInjection", "true");
        bstackOptions.put("appiumVersion", "2.12.1");
        capabilities.setCapability("appium:platformVersion", "12.0");
        capabilities.setCapability("appium:deviceName", "Samsung Galaxy S22");
        //capabilities.setCapability("enableCameraVideoInjection", "true");
        capabilities.setCapability("autoGrantPermissions","true");
        capabilities.setCapability("interactiveDebugging","true");
        capabilities.setCapability("appium:app", "");
        capabilities.setCapability("bstack:options", bstackOptions);


        AndroidDriver driver = new AndroidDriver(new URL("https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"),capabilities);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.example.all_in_one:id/camera1Bar"))).click();
        //driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
        jse.executeScript("browserstack_executor: {\"action\":\"cameraVideoInjection\", \"arguments\": {\"videoUrl\" : \"\"}}");
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.example.all_in_one:id/camera1Bar"))).click();
        Thread.sleep(30000);
//        Thread.sleep(2000);
        driver.quit();
    }
}
