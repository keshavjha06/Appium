package test;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Basics1 {

	
		// TODO Auto-generated method stub
	@AfterTest
	public void lastexecution()
	{
		System.out.println("I will execute last");
		
	}
	@Test
	public void Demo()
	{
	System.out.println("Hello");//automation
	Assert.assertTrue(false);
	}
	@AfterSuite
	public void afsuite()
	{
		System.out.println("I am the no 1 from last");
	}
	@Test
	public void SecondTest()
	{
		System.out.println("bye");
	}
}


