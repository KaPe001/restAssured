package stepsDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import testBase.TestBase;
import utils.JsonConnector;
import utils.MakeCopy;
import utils.RequestBuilder;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;

@Slf4j
public class AsanaStepsForPost extends TestBase {
    private static final String TOKEN = data.getToken();
    private File copyFile;

    @Given("I save name data {string}")
    public void iSaveNameData(String feature) throws IOException {
        copyFile = MakeCopy.getCopyOfProjectFile();
        JsonConnector.updateJsonFile(copyFile, "nazwa", feature);
    }

    @And("I save title data {string}")
    public void iSaveTitleData(String feature) throws IOException {
        JsonConnector.updateJsonFile(copyFile, "my title", feature);
    }

    @When("User performs asana POST project")
    public void userPerformsAsanaPOSTProject() {
        requestBuilder = new RequestBuilder();
        response = requestBuilder.sendPOST_project(TOKEN, copyFile);
    }

    @Then("User is able to see response with new project name {string}")
    public void userIsAbleToSeeResponseWithNewProjectName(String name) {
        response.then()
                .log()
                .all()
                .body("data.name", is(name))
                .statusCode(201);

        copyFile.delete();
        response = null;

        log.info("Name validation success");
    }
}
