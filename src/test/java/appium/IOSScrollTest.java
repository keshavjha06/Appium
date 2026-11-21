package appium;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.remote.AppiumWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSScrollTest extends IOSBaseTest {


    @Test
    public void iOSScrollTestDemo() throws InterruptedException {
        WebElement ele = driver.findElement(AppiumBy.accessibilityId("Web View"));
        Map<String, Object> params = new HashMap<>();
        // Scroll the list until "Web View" is fully on screen (direction-based scroll on a label doesn't move the list)
        params.put("elementId", ((AppiumWebElement) ele).getId());
        params.put("toVisible", true);

        driver.executeScript("mobile:scroll", params);
        driver.findElement(AppiumBy.accessibilityId("Web View")).click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND label == 'UIKitCatalog'")).click();
        driver.findElement(AppiumBy.accessibilityId("Picker View")).click();
        driver.findElement(AppiumBy.accessibilityId("Red color component value")).sendKeys("80");
        driver.findElement(AppiumBy.accessibilityId("Green color component value")).sendKeys("220");
        //label == "Blue color component value"
        driver.findElement(AppiumBy.iOSNsPredicateString("label == 'Blue color component value'")).sendKeys("105");
        String number = driver.findElement(AppiumBy.iOSNsPredicateString("label == 'Blue color component value'")).getText();
        Assert.assertEquals(number, "105");


    }
}
