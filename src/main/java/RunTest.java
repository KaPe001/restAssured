import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = {"src/main/java/features"},
                                    strict = true, glue = {"stepsDefinitions"})
public class RunTest {

}
