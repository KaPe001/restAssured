package stepsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.Workspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testBase.Data;
import testBase.TestBase;
import utils.JsonConnector;
import utils.RequestBuilder;

import java.io.File;

import static org.hamcrest.core.Is.is;

public class AsanaSteps extends TestBase {
    private static Logger logger = LoggerFactory.getLogger(AsanaSteps.class);
    Workspace workspace;

    private static String token = data.getToken();

    @Given("i have workspace object")
    public void i_have_workspace_object() {
        workspace = JsonConnector.fillWorkspaceData(new File(WORKSPACE_PATH));
        requestBuilder = new RequestBuilder();
    }

    @When("user performs GET workspace operation")
    public void user_performs_GET_workspace_operation() {
        response = requestBuilder.sendGET_workspace(token);
    }

    @Then("user is able to see valid response with workspace details")
    public void user_is_able_to_see_valid_response_with_workspace_details() {
        response.then()
                .log()
                .all()
                .body("data[0].name", is(workspace.getName()))
                .body("data[0].gid", is(workspace.getGid()))
                .body("data[0].resource_type", is(workspace.getResource_type()))
                .statusCode(200);
        response = null;
        logger.info("Validation pass");
    }

}
