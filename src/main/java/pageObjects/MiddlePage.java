package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//All the objects belonging to one page will be defined in one java class
public class MiddlePage {
//1.first is to tcall the driver object from testcase to Pageobject file
	
	//concatenate driver
	
	
	//for a constructor the method name should be classname & constructor has capibility of handling arguments
	public MiddlePage(AppiumDriver<AndroidElement> driver) //public HomePage(AppiumDriver driver) try this if gets any error
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//*[@text='ADD TO CART']")
	public List<WebElement> Add;
	@AndroidFindBy(xpath="//*[@text='ADD TO CART']")
	public List<WebElement> Add1;
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement Cart;
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	public List<WebElement> Product;
	


}
