package web;
import org.json.JSONArray;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;


public class BaseTestWeb {
    public WebDriver driver;
    public static final String AUTOMATE_USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    public static final String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
    public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    @BeforeMethod(alwaysRun = true)
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("start-maximized");
//        driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //driver = new ChromeDriver(options);
        driver= new RemoteWebDriver(new URL(URL),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
