package appium;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BrowserBaseTest {

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
        options.setDeviceName("KeshavPhone");
        // Only set a chromedriver path if one exists locally; otherwise rely on Appium's chromedriver autodownload
        String chromedriver = System.getProperty("chromedriver", System.getProperty("user.home") + "/Downloads/chromedriver");
        if (new File(chromedriver).exists()) {
            options.setChromedriverExecutable(chromedriver);
        }
        options.setCapability("browserName", "Chrome");

        driver = new AndroidDriver(serverUrl, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
