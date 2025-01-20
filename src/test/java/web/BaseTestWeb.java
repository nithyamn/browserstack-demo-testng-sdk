package web;

import com.browserstack.BrowserStackSdk;
import com.google.common.net.MediaType;
import io.github.bonigarcia.wdm.WebDriverManager;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.sql.Timestamp;



public class BaseTestWeb {
    public WebDriver driver;
    public String username = System.getenv("BROWSERSTACK_USERNAME");
    public String accesskey = System.getenv("BROWSERSTACK_ACCESS_KEY");
    public HashMap<String, Object> currentPlatform;
    public String platformDetails;
    @BeforeMethod(alwaysRun = true)
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--remote-allow-origins=*");
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
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        currentPlatform = BrowserStackSdk.getCurrentPlatform();

        if(currentPlatform.get("deviceName")!=null) {
            platformDetails = currentPlatform.get("deviceName")+"-"+ currentPlatform.get("osVersion") +" "+currentPlatform.get("browserName");
        }else{
            platformDetails = currentPlatform.get("os") +" "+ currentPlatform.get("osVersion") +" "+ currentPlatform.get("browserName")+" "+currentPlatform.get("browserVersion");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
    @AfterSuite
    public void afterSuite(){
        uploadResultsToTM();
    }

    public void uploadResultsToTM(){
        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(formatter.format(ts));

        HttpResponse<String> response = Unirest.post("https://test-management.browserstack.com/api/v1/import/results/xml/junit")
                .header("Authorization", basicAuthHeaderGeneration())
                .field("project_name", "BrowserStack E2E Platform Story")
                .field("file_path", new File("target/surefire-reports/junitreports/TEST-web.SingleWeb.xml"))
                .field("test_run_name", "TR: "+formatter.format(ts))
                .asString();

        System.out.println("print:"+response.getBody());
    }
    public static String basicAuthHeaderGeneration(){
        String authCreds = System.getenv("BROWSERSTACK_USERNAME")+":"+System.getenv("BROWSERSTACK_ACCESS_KEY");
        return "Basic " + Base64.getEncoder().encodeToString(authCreds.getBytes());
    }
}
