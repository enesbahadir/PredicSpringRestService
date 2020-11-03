package com.preschool;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber.json"},
        glue = {"com.preschool.stepdef"},
        features = "src/test/resources/"
)
public class CucumberIntegrationTest {
}
