package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//cucumber -> TestNG(testng assertions), junit
@CucumberOptions(features = "src/test/java/cucumber", glue = "academy.stepDefinitions", monochrome = true,
tags = "@SubmitOrder", plugin = {"html:target/cucumber.html" })
public class TestNGTestRunner extends AbstractTestNGCucumberTests { // cucumber has not inbuilt capabilty to run testNG
																	// tests

}
