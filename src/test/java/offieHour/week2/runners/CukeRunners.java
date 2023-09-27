package offieHour.week2.runners;


import io.cucumber.junit.*;
import org.junit.runner.*;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/cucumber-report.html"
        },
        features = "src/test/resources/features",
        glue = "offieHour/week2/steps",
        dryRun = false,
        tags = "@wip"

)
public class CukeRunners {
}
