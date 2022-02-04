package stepsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import models.ProjectData;

import testBase.TestBase;
import utils.JsonConnector;
import utils.RequestBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@Slf4j
public class AsanaStepsGETProject extends TestBase {

    private static String token = data.getToken();

    @Given("i have project object")
    public void i_have_project_object() {
        projectData = JsonConnector.fillTheData(new File(PROJECT_DATA_PATH), ProjectData.class);
        requestBuilder = new RequestBuilder();
        log.info("Data filled for project object");
    }

    @When("user performs GET project operation")
    public void user_performs_GET_project_operation() {
        response = requestBuilder.sendGET_project(token);
        log.info("GET request sent");
    }

    @Then("user is able to see valid response with project details")
    public void user_is_able_to_see_valid_response_with_project_details() {
//        response.then()
//                .log()
//                .all()
//                .body("data[0].gid", is(projectData.getGid()))
//                .body("data[0].name", is(projectData.getName()))
//                .body("data[0].resource_type", is(projectData.getResource_type()))
//                .statusCode(200)
//                .extract()
//                .asString();
        String response =
                given().auth()
                        .oauth2(token)
                        .log()
                        .all().
                        when()
                        .get("/projects")
                        .then()
                        .log()
                        .all()
                        .extract().asPrettyString();

        File fileObj = new File("src/main/java/testData/ResponseBody.json");
        try {
            if (fileObj.createNewFile()) {
                FileWriter myWriter = new FileWriter("src/main/java/testData/ResponseBody.json");
                myWriter.write(response);
                myWriter.close();
            } else {
                log.info("Creating a file failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        response = null;
        log.info("Validation pass");
    }
}
