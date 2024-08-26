package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/cucumberFeatureFiles",
		glue = "stepDefinitionFiles",
		tags = "@validateUserInfo",
		monochrome = true,
		plugin = {"html:target/cucumber.html"}
		
		)


public class RunnerClass extends AbstractTestNGCucumberTests {


}
