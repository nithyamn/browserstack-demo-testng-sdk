package web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ApplePayTest {

    public static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    public static final String ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub.browserstack.com/wd/hub";

    public static void main(String[] args) throws Exception {
        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("deviceName", "iPhone 14 Pro");
        bstackOptions.put("osVersion", "16");
        bstackOptions.put("browserName", "safari");
        bstackOptions.put("deviceOrientation", "portrait");
        bstackOptions.put("buildName", "Apple Pay Build");
        bstackOptions.put("enableApplePay", true);
        bstackOptions.put("sessionName", "Apple Pay Test");
        bstackOptions.put("interactiveDebugging", true);
        bstackOptions.put("idleTimeout", 300);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", bstackOptions.get("browserName"));
        capabilities.setCapability("bstack:options", bstackOptions);

        WebDriver driver = new RemoteWebDriver(new URL(URL), capabilities);
        try {

            String script = "browserstack_executor: {" +
                    "\"action\": \"applePayDetails\"," +
                    "\"arguments\": {" +
                    "\"billingDetails\": {" +
                    "\"firstName\": \"John\"," +
                    "\"lastName\": \"Doe\"," +
                    "\"state\": \"Alabama\"," +
                    "\"city\": \"Crane Hill\"," +
                    "\"street\": \"242 County Rd\"," +
                    "\"postCode\": \"35053\"," +
                    "\"country\": \"United States\"" +
                    "}," +
                    "\"shippingDetails\": {" +
                    "\"firstName\": \"John\"," +
                    "\"lastName\": \"Doe\"," +
                    "\"state\": \"Alabama\"," +
                    "\"city\": \"Crane Hill\"," +
                    "\"street\": \"242 County Rd\"," +
                    "\"postCode\": \"35053\"," +
                    "\"country\": \"United States\"" +
                    "}," +
                    "\"contact\": {" +
                    "\"email\": \"nithya.demo@browserstack.com\"," +
                    "\"phone\": \"+14155550101\"" +
                    "}" +
                    "}" +
                    "}";

            ((JavascriptExecutor) driver).executeScript(script);

            driver.get("https://applepaydemo.apple.com");
            Thread.sleep(2000);
            //driver.findElement(By.cssSelector("button[value='Apple Pay']")).click();

            ((JavascriptExecutor) driver).executeScript("browserstack_executor: {\"action\":\"applePay\",\"arguments\": {\"confirmPayment\" : \"true\"}}");
            Actions actions = new Actions(driver);
            actions.sendKeys("123456").perform();

        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript(
                    "browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Some elements failed to load\"}}");
        } finally {
            driver.quit();
        }
    }
}
