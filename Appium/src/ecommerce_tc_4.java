import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;
public class ecommerce_tc_4 extends base {

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
		int count = driver.findElementsById("com.androidsample.generalstore:id/productPrice").size();
		double sum = 0;
		for (int i = 0; i < count; i++) {
			String amount1 = driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(i).getText();
			double amount = getAmount(amount1);
			sum = sum + amount;// 160.97 - for 2nd loop 160.97+120=280.97+116.97 = total value
		}

		String amount1 = driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(0).getText();
// $120.0 - cannot do sum of two numbers because of string in amount- cannot convert into integer 
		double amount1value = getAmount(amount1);
		// 120.0
		String amount2 = driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(1).getText();
		double amount2value = getAmount(amount2); // 160.97
		double sumOfProducts = amount1value + amount2value;
		System.out.println(sum + "sum of Products");

		String total = driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText();
		total = total.substring(1);
		double totalValue = Double.parseDouble(total);
		System.out.println(totalValue + "total value of products");
		Assert.assertEquals(sum, totalValue);
		
		//Mobile Gestures
		WebElement checkbox = driver.findElementByClassName("android.widget.CheckBox");
		TouchAction t = new TouchAction(driver);
		t.tap(tapOptions().withElement(element(checkbox))).perform();
		WebElement tc = driver.findElementByXPath("//*[@text='Please read our terms of conditions']");
		t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();
		driver.findElementById("android:id/button1").click();
		driver.findElementById("com.androidsample.generalstore:id/btnProceed").click();

	}

	public static double getAmount(String value) {
		value = value.substring(1);
		double amount2value = Double.parseDouble(value);
		return amount2value;
	}
}
