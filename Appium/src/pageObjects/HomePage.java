package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//All the objects belonging to one page will be defined in one java class
public class HomePage {
//1.first is to tcall the driver object from testcase to Pageobject file
	
	//concatenate driver
	
	
	//for a constructor the method name should be classname & constructor has capibility of handling arguments
	public HomePage(AppiumDriver driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Preference']")
	public WebElement Preferences;
	
	
	
	//driver.findElementByXpath("//android.widget.TextView[@text='Preference']");
	

}
