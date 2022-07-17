package appium;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class ScrollDemo extends BaseTest {

    @Test
    public void ScrollDemoTest() {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        //where to scroll is known prior
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"))"));

        //No prior idea
        scrollToEndAction();

    }

}
