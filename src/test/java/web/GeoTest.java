package web;
import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.Test;


public class GeoTest extends BaseTestWeb{

    @Test
    public void geoTest(){
        driver.get("http://ip-api.com/json");
        System.out.println(driver.findElement(By.cssSelector("body")).getText());
        Assert.assertTrue(driver.findElement(By.cssSelector("body")).getText().contains("Belgium"));
    }
}


