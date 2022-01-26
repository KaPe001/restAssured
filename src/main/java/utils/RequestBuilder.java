package utils;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testBase.TestBase;

import static io.restassured.RestAssured.given;

public class RequestBuilder extends TestBase {
    private static Logger logger = LoggerFactory.getLogger(RequestBuilder.class);

    //work with workspace
    public Response sendGET_workspace(String token) {
        Response response =
                given().auth()
                        .oauth2(token)
                        .log()
                        .all().
                when()
                        .get("/workspaces");
        return response;
    }
}
