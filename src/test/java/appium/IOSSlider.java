package appium;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.remote.AppiumWebElement;

public class IOSSlider extends IOSBaseTest {

    @Test
    public void iOSSliderTest() {
        // Open UIKitCatalog > Sliders (scroll the list until the row is fully on screen)
        WebElement slidersRow = driver.findElement(AppiumBy.accessibilityId("Sliders"));
        Map<String, Object> params = new HashMap<>();
        params.put("elementId", ((AppiumWebElement) slidersRow).getId());
        params.put("toVisible", true);
        driver.executeScript("mobile:scroll", params);
        driver.findElement(AppiumBy.accessibilityId("Sliders")).click();

        // The screen has 4 sliders under the sections Default, Tinted, Custom, Min and Max Images.
        // They have no accessibility labels, so pick the first one (under "Default") by position.
        // Unlabelled elements are tracked partly by their value, which goes stale as soon as the slider moves;
        // binding elements by index keeps the reference valid.
        driver.setSetting("boundElementsByIndex", true);
        WebElement slider = driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeSlider[1]"));

        // sendKeys sets the slider position as a fraction between 0 and 1
        slider.sendKeys("0");
        Assert.assertEquals(slider.getAttribute("value"), "0%");

        slider.sendKeys("1");
        Assert.assertEquals(slider.getAttribute("value"), "100%");

        // Middle positions snap to the nearest point the thumb can reach (0.5 -> ~47%), so allow a margin
        slider.sendKeys("0.5");
        int percent = Integer.parseInt(slider.getAttribute("value").replace("%", ""));
        Assert.assertTrue(percent >= 40 && percent <= 60, "Expected slider near 50% but was " + percent + "%");
    }
}
