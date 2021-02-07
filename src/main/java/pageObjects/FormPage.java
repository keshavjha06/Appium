package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {
	public FormPage(AppiumDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	//driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Hello");
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	//driver.findElementByXPath("//*[@text='Female']").click();
	@AndroidFindBy(xpath="//*[@text='Female']")
	public WebElement femaleOption;
	//driver.findElementById("android:id/text1").click();
	@AndroidFindBy(id="android:id/text1")
	public WebElement dropDown;
	//driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
	@AndroidFindBy(xpath="//*[@text='Argentina']")
	public WebElement text;
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement LetsShop;
	
	public WebElement getNameField()
	{
		System.out.println("trying to find the Name field");
		return nameField;
	}
}

