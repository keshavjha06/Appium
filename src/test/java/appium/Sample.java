package appium;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AppiumWebElement;
import io.github.cdimascio.dotenv.Dotenv;

public class Sample {

    // ignoreIfMissing: don't fail if there is no .env file; get() also checks System.getenv()
    private static final Dotenv DOTENV = Dotenv.configure().ignoreIfMissing().load();

    public AndroidDriver driver;

    @BeforeClass
    public void configureAppium() throws MalformedURLException, URISyntaxException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setApp(requireEnv("BROWSERSTACK_APP_ID"));

        // BrowserStack-specific capabilities (W3C format)
        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", requireEnv("BROWSERSTACK_USERNAME"));
        bstackOptions.put("accessKey", requireEnv("BROWSERSTACK_ACCESS_KEY"));
        bstackOptions.put("deviceName", "Google Pixel 3");
        bstackOptions.put("osVersion", "9.0");
        bstackOptions.put("projectName", "First Java Project");
        bstackOptions.put("buildName", "browserstack-build-1");
        bstackOptions.put("sessionName", "first_test");
        options.setCapability("bstack:options", bstackOptions);

        driver = new AndroidDriver(new URI("https://hub-cloud.browserstack.com/wd/hub").toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private static String requireEnv(String name) {
        String value = DOTENV.get(name);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(name + " is not set in .env or the environment");
        }
        return value;
    }

    public void longPressAction(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((AppiumWebElement) ele).getId(),
                        "duration", 2000));
    }

    public void scrollToEndAction() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0

            ));
        } while (canScrollMore);
    }


    public void swipeAction(WebElement ele, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((AppiumWebElement) ele).getId(),

                "direction", direction,
                "percent", 0.75
        ));


    }


    public Double getFormattedAmount(String amount) {
        Double price = Double.parseDouble(amount.substring(1));
        return price;

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
