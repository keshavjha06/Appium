package practise.AppiumFramework;

import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

import java.net.MalformedURLException;

import java.net.ServerSocket;

import java.net.URL;

import java.sql.Driver;

import java.util.Properties;

import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.AndroidElement;

import io.appium.java_client.remote.MobileCapabilityType;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class base {

	public static AppiumDriverLocalService service;

	public static AndroidDriver<AndroidElement> driver;

	public AppiumDriverLocalService startServer()

	{

		boolean flag = checkIfServerIsRunnning(4723);

		if (!flag)

		{

			service = AppiumDriverLocalService.buildDefaultService();

			service.start();

		}

		return service;

	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;

		ServerSocket serverSocket;

		try {

			serverSocket = new ServerSocket(port);

			serverSocket.close();

		} catch (IOException e) 
		{

//If control comes here, then it means that the port is in use

			isServerRunning = true;

		} finally {

			serverSocket = null;

		}

		return isServerRunning;

	}

	public static void startEmulator() throws IOException, InterruptedException

	{

		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\startEmulator.bat");

		Thread.sleep(6000);

	}

	public static AndroidDriver<AndroidElement> Capabilities(String app) throws IOException, InterruptedException {

// TODO Auto-generated method stub

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\global.properties");

		Properties prop = new Properties();

		prop.load(fis);

//AndroidDriver<AndroidElement> driver;

		File f = new File("src");

//File fs = new File(f, "ApiDemos-debug.apk");

		File fs = new File(f, (String) prop.get(app));

		DesiredCapabilities cap = new DesiredCapabilities();

		String device = (String) prop.get("device");
//String device=System.getProperty("deviceName");
		if (device.contains("Keshav"))

		{

			startEmulator();

		}

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, device);

		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

//cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 5); - To add time

		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());

		AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;

	}

//public static void getScreenshot(String s) throws IOException
//
//{
//
//File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
//
//FileUtils.copyFile(scrfile, new File(System.getProperty("user.dir")+"\\"+s+".png"));
//
//}
		public static void getScreenshot(String s) throws IOException {
	try{
		// To create reference of TakesScreenshot
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		// Call method to capture screenshot
		File src=screenshot.getScreenshotAs(OutputType.FILE);
		// Copy files to specific location
		// result.getName() will return name of test case so that screenshot name will be same as test case name
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\"+s+".png"));
		System.out.println("Successfully captured a screenshot");
		}catch (Exception e)
	{
		System.out.println("Exception while taking screenshot "+e.getMessage());
		}
		}
}