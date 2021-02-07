import java.net.MalformedURLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;
//import io.appium.java_client.android.nativekey.AndroidKey;
//import io.appium.java_client.android.nativekey.KeyEvent;

public class ecommerce_tc_5 extends base {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		AndroidDriver<AndroidElement> driver = Capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Hello");
		driver.findElementByXPath("//*[@text='Female']").click();
		driver.findElementById("android:id/text1").click();

		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
		// driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new
		// UiSelector().scrollable(true).instance(0)).scrollIntoView(new
		// UiSelector().textMatches(\"" + Argentina + "\").instance(0))"));
		driver.findElementByXPath("//*[@text='Argentina']").click();
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		driver.findElementsByXPath("//*[@text='ADD TO CART']").get(0).click();
		driver.findElementsByXPath("//*[@text='ADD TO CART']").get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

		Thread.sleep(4000);

		// Mobile Gestures
		WebElement checkbox = driver.findElementByClassName("android.widget.CheckBox");
		TouchAction t = new TouchAction(driver);
		t.tap(tapOptions().withElement(element(checkbox))).perform();
		driver.findElementById("com.androidsample.generalstore:id/btnProceed").click();
		Thread.sleep(7000);
		Set<String> contexts = driver.getContextHandles();//set is a collection of strings
		
		for(String contextName : contexts)//enhanced for loop - in every loop one of the context will be retrieved and stored in contextname
		{
			System.out.println(contextName);//print whatever got stored in contextName
		}
driver.context("WEBVIEW_com.androidsample.generalstore");
driver.findElementByName("q").sendKeys("Keshav");
driver.findElementByName("q").sendKeys(Keys.ENTER);
//driver.pressKey(new KeyEvent(AndroidKey.BACK)); or
	((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
	}

}
