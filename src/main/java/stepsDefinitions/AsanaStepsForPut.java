package stepsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import testBase.TestBase;
import utils.RequestBuilder;

import static org.hamcrest.core.Is.is;

@Slf4j
public class AsanaStepsForPut extends TestBase {
    private static final String TOKEN = data.getToken();

    @Given("I have new project named DemoRestAssured")
    public void iHaveNewProjectName() {
        requestBuilder = new RequestBuilder();
        log.info("Request builder implemented properly");
    }

    @When("User performs PUT request to update project")
    public void userPerformsPUTRequestToUpdateProject() {
        response = requestBuilder.sendPUT_project(TOKEN);
        log.info("PUT request sent");
    }

    @Then("User is able to see the change in a project named RestAssured")
    public void userIsAbleToSeeTheChangeInAProjectName() {
        response.then()
                .log()
                .all()
                .body("data.name", is(projectUpdateName.getData().getName()))
                .statusCode(200);
        response = null;

        log.info("Validation success");
    }
}
