package appium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSSwipeTest extends IOSBaseTest {


    @Test
    public void iOSSwipeTestDemo() {
        //Bundle ID
        Map<String, String> params = new HashMap<String, String>();
        params.put("bundleId", "com.apple.mobileslideshow");
        driver.executeScript("mobile:launchApp", params);
        // First launch of Photos can show a system notifications permission alert
        List<WebElement> dontAllow = driver.findElements(AppiumBy.accessibilityId("Don’t Allow"));
        if (!dontAllow.isEmpty()) {
            dontAllow.get(0).click();
        }
        // Newer Photos app shows a one-time "What's New" screen; dismiss it if present
        List<WebElement> continueButton = driver.findElements(AppiumBy.accessibilityId("Continue"));
        if (!continueButton.isEmpty()) {
            continueButton.get(0).click();
        }
        // Photos is now split into "Library" and "Collections" tabs (no "All Photos"/"Albums")
        driver.findElement(AppiumBy.accessibilityId("LibraryTab")).click();
        By photo = AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeImage' AND label BEGINSWITH 'Photo,'");
        List<WebElement> allPhotos = driver.findElements(photo);
        System.out.println(allPhotos.size());
        allPhotos.get(0).click();
        for (int i = 0; i < allPhotos.size(); i++) {
            System.out.println(driver.findElement(By.xpath("//XCUIElementTypeNavigationBar")).getAttribute("name"));
            Map<String, Object> params1 = new HashMap<String, Object>();
            params1.put("direction", "left");
            //params1.put("element", ((AppiumWebElement)ele).getId());
            driver.executeScript("mobile:swipe", params1);
        }
        driver.navigate().back();
        driver.findElement(AppiumBy.accessibilityId("LibraryTab")).click();  //cleanup


    }
}
