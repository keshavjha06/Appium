package practise.AppiumFramework;

import org.testng.annotations.DataProvider;

public class TestData {
	@DataProvider(name="InputData")
	public Object[][] getDataForEditField()
	{
		//2 sets of data, "keshavjha" , "Pass"
		Object[][] obj = new Object[][]
				{
			{"keshavjha"} , {"$%&*"}
				};
				return obj;
}
}
