package test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Basics3 {
	@BeforeClass
	public void beforeclass()
	{
		System.out.println("before executing any method in the class");
	}
	
	@AfterClass
	public void afterclass()
	{
		System.out.println("after executing all method in the class");
	}
	@Parameters({"URL","APIKey/username"})
	@Test
	public void WeblogincarLoan(String urlname,String key) {
		// selenium
		System.out.println("Webloingcar");
		System.out.println(urlname);
		System.out.println(key);
	}
	@BeforeMethod
	public void beforeevery()
	{
		System.out.println("I will execute before every test method in basics3 class");
	}
	@AfterMethod
	public void afterevery()
	{
		System.out.println("I will execute after every test method in basics3 class");
	}
	@Test(groups={"Smoke"})
	public void MobilelogincarLoan() {

		// Appium
		System.out.println("Mobileloingcar");
	}
		@BeforeSuite
		public void Bfsuite()
		{
System.out.println("I am no 1");
	}
	@Test(enabled=false)
	public void MobilesignincarLoan() {

		// Appium
		System.out.println("Mobilesignincar");
	}@Test(dataProvider="getData")
	public void MobilesignoutcarLoan(String username, String password) {

		// Appium
		System.out.println("Mobilesignoutcar");
		System.out.println(username);
		System.out.println(password);
	}
	@Test(dependsOnMethods={"WeblogincarLoan","MobilesignoutcarLoan"})

	public void ApicarLoan() {
		// Rest API automation
		System.out.println("APIloingcar");
	}
	@DataProvider
	public Object[][] getData()
	{
		//1st combinatione - username password - good credit history
		//2nd - username password - no credit history
		//3rd - fraudelent credit history
		Object[][] data = new Object[3][2];
		data[0][0] = "firstsetusername";
		data[0][1] = "firstpassword";
		//columns in the row are nothing but values for that particular combination(row)
		
		//2nd set
		data[1][0] = "secondsetusername";
		data[1][1] = "secondpassword";
		//3rd set
		data[2][0] = "thirdsetusername";
		data[2][1] = "thirdpassword";
		return data;
	}
}
