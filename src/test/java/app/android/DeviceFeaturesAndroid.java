package app.android;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DeviceFeaturesAndroid {
    public static String username = System.getenv("BROWSERSTACK_USERNAME");
    public static String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    @Test
    public void cameraInjectionTest() throws MalformedURLException, InterruptedException {
        String mediaUrl = "media://054dce0c24c5fa14ba748cd024b03e5357415b9b";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("device","Google Pixel 7");
        caps.setCapability("build","Device Features - Android");
        caps.setCapability("name","cameraInjectionTest");
        caps.setCapability("autoGrantPermissions","true");
        caps.setCapability("interactiveDebugging","true");
        caps.setCapability("app","bs://b721c8d8c1ce1194339ad189a494b3164ff52468"); //regression.apk
        caps.setCapability("browserstack.enableCameraImageInjection", "true");

        AndroidDriver driver = new AndroidDriver(new URL("https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Thread.sleep(3000);
        driver.executeScript("browserstack_executor: {\"action\":\"cameraImageInjection\", \"arguments\": {\"imageUrl\" : \""+mediaUrl+"\"}}");
        MobileElement el1 = (MobileElement) driver.findElementById("com.example.all_in_one:id/camintent");
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElementById("com.example.all_in_one:id/camera_button");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementById("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
        el3.click();
        try{
            MobileElement el4 = (MobileElement) driver.findElementById("com.android.permissioncontroller:id/permission_allow_button");
            el4.click();
        }catch (Exception e){
            System.out.println(e);
        }
        try{
            MobileElement el5 = (MobileElement) driver.findElementById("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
            el5.click();
        }catch (Exception e){
            System.out.println(e);
        }
        MobileElement el6 = (MobileElement) driver.findElementById("com.google.android.GoogleCamera:id/center_placeholder");
        el6.click();
        MobileElement el7 = (MobileElement) driver.findElementById("com.google.android.GoogleCamera:id/center_placeholder");
        el7.click();
        driver.quit();
    }

    @Test
    public void qrScanTest() throws MalformedURLException, InterruptedException {
        String mediaUrl = "media://5edba1ec1851a40cefcfef081415e9b26ef6af9e";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("device","Google Pixel 7");
        caps.setCapability("build","Device Features - Android");
        caps.setCapability("name","qrScanTest");
        caps.setCapability("autoGrantPermissions","true");
        caps.setCapability("interactiveDebugging","true");
        caps.setCapability("app","bs://b721c8d8c1ce1194339ad189a494b3164ff52468"); //regression.apk
        caps.setCapability("browserstack.enableCameraImageInjection", "true");

        AndroidDriver driver = new AndroidDriver(new URL("https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Thread.sleep(3000);
        driver.executeScript("browserstack_executor: {\"action\":\"cameraImageInjection\", \"arguments\": {\"imageUrl\" : \""+mediaUrl+"\"}}");
        driver.findElementById("com.example.all_in_one:id/camera1Bar").click();
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void biometricTest() throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("device","Google Pixel 7");
        caps.setCapability("build","Device Features - Android");
        caps.setCapability("name","biometricTest");
        caps.setCapability("autoGrantPermissions","true");
        caps.setCapability("interactiveDebugging","true");
        caps.setCapability("app","bs://b721c8d8c1ce1194339ad189a494b3164ff52468"); //regression.apk
        caps.setCapability("browserstack.enableBiometric", "true");

        AndroidDriver driver = new AndroidDriver(new URL("https://"+username+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Thread.sleep(3000);
        driver.findElementById("com.example.all_in_one:id/bio_prompt").click();
        driver.findElementById("com.example.all_in_one:id/prompt").click();
        driver.executeScript("browserstack_executor: {\"action\":\"biometric\", \"arguments\": {\"biometricMatch\" : \"pass\"}}");
        driver.findElementById("com.example.all_in_one:id/prompt").click();
        driver.executeScript("browserstack_executor: {\"action\":\"biometric\", \"arguments\": {\"biometricMatch\" : \"fail\"}}");
        Thread.sleep(5000);
        driver.quit();
    }
}
