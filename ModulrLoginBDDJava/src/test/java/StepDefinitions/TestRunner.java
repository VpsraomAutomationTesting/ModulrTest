package StepDefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features", 
glue = {"StepDefinitions"},
monochrome=true,
plugin = {"pretty","html:target/HtmlReports/results.html"},
tags= "@Test5 or @Tests1to4"
)

public class TestRunner {

}
