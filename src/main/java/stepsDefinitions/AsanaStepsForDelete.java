package stepsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import models.ProjectData;
import models.ProjectUpdateName;
import testBase.TestBase;
import utils.JsonConnector;
import utils.RequestBuilder;

import java.io.File;

@Slf4j
public class AsanaStepsForDelete extends TestBase {
    private static final String TOKEN = data.getToken();

    @Given("I have created two projects")
    public void i_have_created_two_projects_with() {
        requestBuilder = new RequestBuilder();
        log.info("Request builder implemented successfully");
    }

    @When("User performs DELETE request on projects with {string}")
    public void user_performs_DELETE_request(String project_gid) {
        response = requestBuilder.sendDELETE_project(TOKEN, new File(PROJECT_PATH), project_gid);
        log.info("DELETE request sent properly");
    }
    @Then("User is able to see that projects no longer exists")
    public void user_is_able_to_see_that_projects_no_longer_exists() {
        response.then()
                .log()
                .all()
                .statusCode(200);
        response = null;
        log.info("Projects deleted successfully");
    }
}
