package test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Basics4 {
	@Parameters({"URL"})
	@Test
	public void WebloginhomeLoan(String uname) {
		// selenium
		System.out.println("WebloinghomePersonalloan");
		System.out.println(uname);
	}

	@Test(groups={"Smoke"})
	public void MobileloginhomeLoan() {

		// Appium
		System.out.println("Mobileloinghome");
	}

	@Test
	public void LoginApihomeLoan() {
		// Rest API automation
		System.out.println("APIloginhome");
	}
}
