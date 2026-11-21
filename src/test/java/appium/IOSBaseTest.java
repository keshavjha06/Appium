package appium;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class IOSBaseTest {

    public IOSDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void configureAppium() throws MalformedURLException, URISyntaxException {

        URL serverUrl = new URI("http://127.0.0.1:4723").toURL();
        // Reuse an Appium server that is already running; otherwise start one locally
        if (!isServerRunning(serverUrl)) {
            service = new AppiumServiceBuilder().withIPAddress("127.0.0.1").usingPort(4723).build();
            service.start();
        }

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 18 Pro");
        // Pick the app with -Dapp=<name>.app (defaults to UIKitCatalog.app)
        String app = System.getProperty("app", "UIKitCatalog.app");
        options.setApp(System.getProperty("user.dir") + "/src/test/java/resources/" + app);
        options.setPlatformVersion("27.2");
        //Appium- Webdriver Agent -> IOS Apps.
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));

        driver = new IOSDriver(serverUrl, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
