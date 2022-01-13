import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonPlaceholder extends TestBase {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private final String USERS = "users";
    @Test
    public void shouldGetUsers() {
        when()
                .get(BASE_URL + USERS).
        then()
                .statusCode(200);
    }

    @Test
    public void shouldGetFirstUser() {
        when()
                .get(BASE_URL + USERS + "/1").
        then()
                .statusCode(200)
                .extract()
                .response();
    }

    @Test
    public void shouldGetFirstUserUsingQueryParam() {
        Response response =
                given()
                        .queryParam("username", "Bret").
                        when()
                        .get(BASE_URL + USERS).
                        then()
                        .statusCode(200)
                        .extract()
                        .response();


        JsonPath json = response.jsonPath();

        assertEquals("Bret", json.getList("username").get(0));
        assertEquals("Leanne Graham", json.getList("name").get(0));
        assertEquals("Sincere@april.biz", json.getList("email").get(0));
        assertEquals("Kulas Light", json.getList("address.street").get(0));
    }

    @Test
    public void shouldGetFirstUserUsingPathParam() {
        Response response =
                given()
                        .pathParam("userId", 1)
                        .when()
                        .get(BASE_URL + USERS + "/{userId}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        JsonPath json = response.jsonPath();

        assertEquals("Bret", json.get("username"));
        assertEquals("Leanne Graham", json.get("name"));
        assertEquals("Sincere@april.biz", json.get("email"));
    }
}
