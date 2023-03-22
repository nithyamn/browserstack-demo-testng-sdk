package web;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LocalWeb extends BaseTestWeb {
    @Test
    public void localTest() throws Exception {
        /*** Fetch Session ID***/
        SessionId sessionId = ((RemoteWebDriver)driver).getSessionId();
        driver.get("http://localhost:3000/");
        Assert.assertEquals(driver.findElement(By.cssSelector("body")).getText(),"HELLO!");
    }
}
