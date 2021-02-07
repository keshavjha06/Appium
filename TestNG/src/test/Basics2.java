package test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Basics2 {

	
		// TODO Auto-generated method stub
	@Test(groups={"Smoke"})
	public void ploan()
	{
	System.out.println("good");//automation

	}
	@BeforeTest
	public void prerequisite()
	{
		System.out.println("I will execute first");
	}
}


