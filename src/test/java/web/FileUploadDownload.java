package web;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;

public class FileUploadDownload extends BaseTestWeb {

    @Test
    public void fileUploadTest() throws Exception {
        SessionId sessionId = ((RemoteWebDriver)driver).getSessionId();
        ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
        driver.get("https://the-internet.herokuapp.com/upload");
        driver.findElement(By.id("file-upload")).sendKeys("/Users/nithyamani/Desktop/sample.jpg");
        driver.findElement(By.id("file-submit")).click();
        Thread.sleep(2000);
        GetSessionDetails.sessionData(sessionId);
        Assert.assertTrue(driver.findElement(By.cssSelector("h3")).getText().contains("File Uploaded!"));
    }

    @Test
    public void fileDownloadTest() throws Exception {
        //Download file
        SessionId sessionId = ((RemoteWebDriver)driver).getSessionId();
        driver.get("https://the-internet.herokuapp.com/download");
        driver.findElement(By.linkText("sample.jpg")).click();
        Thread.sleep(2000);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        System.out.println(jse.executeScript("browserstack_executor: {\"action\": \"fileExists\", \"arguments\": {\"fileName\": \"sample.jpg\"}}"));
        System.out.println(jse.executeScript("browserstack_executor: {\"action\": \"getFileProperties\", \"arguments\": {\"fileName\": \"sample.jpg\"}}"));
        String base64EncodedFile = (String) jse.executeScript("browserstack_executor: {\"action\": \"getFileContent\", \"arguments\": {\"fileName\": \"sample.jpg\"}}");

        // Decode the content to Base64 and write to a file
        byte[] data = Base64.getDecoder().decode(base64EncodedFile);
        try{
            OutputStream stream = new FileOutputStream("res/sample.jpg");
            stream.write(data);
            stream.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        GetSessionDetails.sessionData(sessionId);
    }
}
