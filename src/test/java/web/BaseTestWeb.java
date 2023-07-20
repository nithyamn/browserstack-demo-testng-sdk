package web;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;


public class BaseTestWeb {
    public WebDriver driver;
    public String username = System.getenv("BROWSERSTACK_USERNAME");
    public String accesskey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    @BeforeMethod(alwaysRun = true)
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        /*MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        LocalDate date = LocalDate.now();
        browserstackOptions.put("projectName", "Demo Testng SDK 1");
        browserstackOptions.put("buildName", "sample");
        browserstackOptions.put("buildIdentifier", "s");
        browserstackOptions.put("sessionName", "name");
        capabilities.setCapability("buildIdentifier",date);
        browserstackOptions.put("buildTag","1122");
        capabilities.setCapability("bstack:options", browserstackOptions);*/

        driver = new ChromeDriver(options);
        //driver = new RemoteWebDriver(new URL("https://"+username+":"+accesskey+"@hub-clud.browserstack.com/wd/hub"),capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
