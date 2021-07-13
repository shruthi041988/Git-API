package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",plugin = {"json:target/jsonReports/cucumber-report.json", "html:target/cucumber-reports/cucumber.html"}, glue= {"stepDefinations"},stepNotifications=true,monochrome=true,publish=true)
public class TestRunner {
	//tags="@AddPlace"
	//plugin = {"json:target/jsonReports/cucumber-reports.json"},
}
