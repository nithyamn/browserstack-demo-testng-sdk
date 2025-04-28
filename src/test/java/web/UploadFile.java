package web;
import org.json.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.URL;

public class UploadFile extends BaseTestWeb{
    @Test
   public void uploadFileTest() throws Exception{
       driver.get("https://www.fileconvoy.com");
       WebElement uploadElement = driver.findElement(By.id("upfile_0"));
//       uploadElement.sendKeys("C:\\Users\\hello\\Documents\\images\\sample.jpg");
        uploadElement.sendKeys("/Users/test1/Documents/images/sample.jpg");
       ((JavascriptExecutor) driver).executeScript("document.getElementById('readTermsOfUse').click();");
       driver.findElement(By.name("upload_button")).submit();
       WebElement topMessage = driver.findElement(By.id("TopMessage"));
       if (topMessage.getText().contains("successfully uploaded")) {
           ((JavascriptExecutor) driver).executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"passed\",\"reason\": \"File upload successful\"}}");
       } else {
           ((JavascriptExecutor) driver).executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\",\"reason\": \"File upload failed\"}}");
       }
   }
}
