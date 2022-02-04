package stepsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import models.Workspace;
import testBase.TestBase;
import utils.JsonConnector;
import utils.RequestBuilder;

import java.io.File;

import static org.hamcrest.core.Is.is;

@Slf4j
public class AsanaStepsGetWorkspace extends TestBase {
    Workspace workspace;

    private static String token = data.getToken();

    @Given("i have workspace object")
    public void i_have_workspace_object() {
        workspace = JsonConnector.fillTheData(new File(WORKSPACE_PATH), Workspace.class);
        requestBuilder = new RequestBuilder();
        log.info("Data filled for workspace object");
    }

    @When("user performs GET workspace operation")
    public void user_performs_GET_workspace_operation() {
        response = requestBuilder.sendGET_workspace(token);
        log.info("GET request sent");
    }

    @Then("user is able to see valid response with workspace details")
    public void user_is_able_to_see_valid_response_with_workspace_details() {
        response.then()
                .log()
                .all()
                .body("data[0].gid", is(workspace.getGid()))
                .body("data[0].name", is(workspace.getName()))
                .body("data[0].resource_type", is(workspace.getResource_type()))
                .statusCode(200);
        response = null;
        log.info("Validation pass");
    }
}
