package Test_Scripts;

import org.testng.TestNG;

public class Mainclass {
	public static void main(String[] args) {
		TestNG testngrunner = new TestNG();
		testngrunner.setTestClasses(new Class[] { Hybrid_Framework.class });
		testngrunner.run();
	}
}
