import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.HomePage;
import pageObjects.Preferences;


public class basics extends Hybridbase {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		AndroidDriver <AndroidElement> driver =Capabilities("emulator");
		// or if you want to execute in emulator - AndroidDriver <AndroidElement> driver =Capabilities("emulator");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   //Xpath id, classname,androidUIautomator
//		xpath syntax
		//tagname[@attribute='value']
		HomePage h = new HomePage(driver);
		//constructor of class will be invoked when you create object for the class
		
		//if no constructor is defined then default constructor will be called
		
		//constructor can be defined with arguments
		
		//driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
		
		//You can call the methods or variables of the class with class object
		
		h.Preferences.click();
		Preferences p = new Preferences(driver);
		//driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
		p.dependencies.click();
		//driver.findElementById("android:id/checkbox").click();
		p.checkbox.click();
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		driver.findElementByClassName("android.widget.EditText").sendKeys("keshavjha");
		//driver.findElementsByClassName("android.widget.Button").get(1).click();
		p.buttons.get(1).click();
		//driver.findElementById("android:id/button1").click();
		//android.widget.RelativeLayout)[2];
		
	}

}
