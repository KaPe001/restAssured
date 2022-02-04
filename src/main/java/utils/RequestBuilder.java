package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import testBase.TestBase;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;

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

    public Response sendGET_project(String token) {
         return  given().auth()
                .oauth2(token)
                .log()
                .all().
                when()
                .get("/projects");
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

    public Response sendDELETE_project(String token, File file) {
        return given().auth()
                .oauth2(token)
                .log()
                .all()
                .body(file).
                when()
                .delete("/projects/" + responseBody.getData().get(0).getGid());
    }

    //-----------------------------------------------------------------------------------------------------//
    //when you want to return value as a big decimal instead of float (f) or double (d), you can use
    //JsonConfig:
    //given()
    //        .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL)))
}
