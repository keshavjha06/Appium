import java.net.MalformedURLException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class browse extends baseChrome {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		AndroidDriver<AndroidElement> driver = capabilities();
		/*
		 * driver.get("http://facebook.com");
		 * driver.findElementByXPath("//*[@id=\'m_login_email\']").sendKeys("qwerty");
		 * //driver.findElementById("m_login_password").sendKeys("12345");
		 * driver.findElementByXPath("//*[@id=\'m_login_password\']").sendKeys("123456")
		 * ; driver.findElementByXPath("//button[@value='Log In']").click();
		 */
		
		driver.get("http://cricbuzz.com");
		driver.findElementByXPath("//a[@href='#menu']").click();
		driver.findElementByCssSelector("a[title='Cricbuzz Home']").click();
		System.out.println(driver.getCurrentUrl());
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,480)", "");//minimum scroll is 0,250
		Assert.assertTrue(driver.findElementByXPath("//h4[text()='Top Stories']").getAttribute("class").contains("header"));
		
		//if we get adb devices unauthorized
		//adb kill-server
		//adb start-server
		//adb devices
	}

}
