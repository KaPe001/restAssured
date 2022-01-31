package utils;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import testBase.TestBase;

import java.io.File;

import static io.restassured.RestAssured.given;

@Slf4j
public class RequestBuilder extends TestBase {

    public Response sendGET_workspace(String token) {
        return given().auth()
                .oauth2(token)
                .log()
                .all().
        when()
                .get("/workspaces");
    }

    public Response sendPOST_project(String token, File file) {
        return given().auth()
                .oauth2(token)
                .log()
                .all()
                .body(file).
        when()
                .post("workspaces/" + workspace.getGid() + "/projects");
    }

    public Response sendPUT_project(String token) {
        return given().auth()
                .oauth2(token)
                .log()
                .all()
                .body(new File(PROJECT_UPDATE_PATH)).
        when()
                .put("/projects/" + projectData.getGid());
    }
}
