package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
//monochrome-it will remove unnessary character from window console give the console in proper format
@RunWith(Cucumber.class)
@CucumberOptions(
features=".//Features/",
glue="StepDefinitions",
dryRun=false,
monochrome=true,
plugin= {"pretty","html:Results/test-output.html","json:target/Myreports/report.json","junit:target/Myreports/report.xml"}
		)
 
public class TestRun {

	
	
}
