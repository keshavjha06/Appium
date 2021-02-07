package practise.AppiumFramework;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.CheckoutPage;
import pageObjects.FormPage;
import pageObjects.MiddlePage;

public class Ecommerce_tc_4 extends base {

	@Test
	public void totalValidation() throws InterruptedException, IOException {

		// TODO Auto-generated method stub
		// General-Store .apk 4.0
		service = startServer();
		AndroidDriver<AndroidElement> driver = Capabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		FormPage fp = new FormPage(driver);
		// Thread.sleep(5000);
		// fp.nameField.sendKeys("Hello");
		fp.getNameField().sendKeys("Hello");
		driver.hideKeyboard();
		fp.femaleOption.click();
		fp.dropDown.click();
		Utilities u = new Utilities(driver);
		u.scrollToText("Argentina");

		// driver.findElementByAndroidUIAutomator("new UiScrollable(new
		// UiSelector()).scrollIntoView(text(\"Argentina\"));");
		// driver.findElement(MobileBy.AndroidUIAutomator("new
		// UiScrollable(newUiSelector().scrollable(true).instance(0)).scrollIntoView(newUiSelector().textMatches(\""
		// + Argentina + "\").instance(0))"));
		fp.text.click();
		fp.LetsShop.click();
		MiddlePage mp = new MiddlePage(driver);
		mp.Add.get(0).click();
		mp.Add1.get(0).click();
		mp.Cart.click();
		int count = mp.Product.size();
		double sum = 0;
		CheckoutPage cp = new CheckoutPage(driver);

		for (int i = 0; i < count; i++) {
			String amount1 = cp.productPrice.get(i).getText();
			double amount = getAmount(amount1);
			sum = sum + amount;// 160.97 - for 2nd loop 160.97+120=280.97+116.97 = total value
		}
		System.out.println(sum + "sum of Products");

		String total = cp.totalAmount.getText();
		total = total.substring(1);
		double totalValue = Double.parseDouble(total);
		System.out.println(totalValue + "Total value of products");
		Assert.assertEquals(sum, totalValue);
		service.stop();

	}
	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException
	{
		//taskkill /F /IM node.exe
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}

	public static double getAmount(String value) {
		value = value.substring(1);
		double amount2value = Double.parseDouble(value);
		return amount2value;
	}
}
