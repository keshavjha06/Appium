package appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import io.appium.java_client.remote.AppiumWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class BaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void configureAppium() throws MalformedURLException, URISyntaxException {

        URL serverUrl = new URI("http://127.0.0.1:4723").toURL();
        // Reuse an Appium server that is already running; otherwise start one locally
        if (!isServerRunning(serverUrl)) {
            service = new AppiumServiceBuilder().withIPAddress("127.0.0.1").usingPort(4723).build();
            service.start();
        }

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("KeshavPhone"); //emulator
       // options.setDeviceName("Android Device");// real device

        // Only set a chromedriver path if one exists locally (needed for hybrid/webview tests)
        String chromedriver = System.getProperty("chromedriver", System.getProperty("user.home") + "/Downloads/chromedriver");
        if (new File(chromedriver).exists()) {
            options.setChromedriverExecutable(chromedriver);
        }

        // Pick the app with -Dapp=ApiDemos-debug.apk (defaults to General-Store.apk)
        String app = System.getProperty("app", "General-Store.apk");
        options.setApp(System.getProperty("user.dir") + "/src/test/java/resources/" + app);

        driver = new AndroidDriver(serverUrl, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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

    private boolean isServerRunning(URL serverUrl) {
        try {
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) serverUrl.toURI().resolve("/status").toURL().openConnection();
            conn.setConnectTimeout(2000);
            return conn.getResponseCode() == 200;
        } catch (Exception e) {
            return false;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }

}
