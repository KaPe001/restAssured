import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import testBase.TestBase;

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

    @Test
    public void shouldDeleteFirstUser() {
        given()
                .when()
                .delete(BASE_URL + USERS + "/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldCreateNewUser() {
        String user = "{\n"  +
                "    \"name\": \"Kinga Petko\",\n" +
                "    \"username\": \"xxxxxxx\",\n" +
                "    \"email\": \"newEmail@april.biz\",\n" +
                "    \"address\": {\n" +
                "      \"street\": \"Kulas Light\",\n" +
                "      \"suite\": \"Apt. 556\",\n" +
                "      \"city\": \"Gwenborough\",\n" +
                "      \"zipcode\": \"92998-3874\",\n" +
                "      \"geo\": {\n" +
                "        \"lat\": \"-37.3159\",\n" +
                "        \"lng\": \"81.1496\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"phone\": \"1-770-736-8031 x56442\",\n" +
                "    \"website\": \"hildegard.org\",\n" +
                "    \"company\": {\n" +
                "      \"name\": \"Romaguera-Crona\",\n" +
                "      \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                "      \"bs\": \"harness real-time e-markets\"\n" +
                "    }\n" +
                "  }";

        Response response =
        given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(BASE_URL + USERS)
                .then()
                .statusCode(201)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals("xxxxxxx", json.get("username"));
        assertEquals("Kinga Petko", json.get("name"));
    }

    @Test
    public void shouldUpdateUser() {
        String user = "{\n" +
                "    \"name\": \"Kinga Petko\",\n" +
                "    \"username\": \"xxxxxxx022\",\n" +
                "    \"email\": \"newestEmail@april.biz\",\n" +
                "    \"address\": {\n" +
                "      \"street\": \"Kulas Light\",\n" +
                "      \"suite\": \"Apt. 556\",\n" +
                "      \"city\": \"Gwenborough\",\n" +
                "      \"zipcode\": \"92998-3874\",\n" +
                "      \"geo\": {\n" +
                "        \"lat\": \"-37.3159\",\n" +
                "        \"lng\": \"81.1496\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"phone\": \"1-770-736-8031 x56442\",\n" +
                "    \"website\": \"hildegard.org\",\n" +
                "    \"company\": {\n" +
                "      \"name\": \"Romaguera-Crona\",\n" +
                "      \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                "      \"bs\": \"harness real-time e-markets\"\n" +
                "    }\n" +
                "  }";

        Response response =
        given()
                .contentType("application/json")
                .body(user)
                .when()
                .put(BASE_URL + USERS + "/1")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertEquals("xxxxxxx022", json.get("username"));
        assertEquals("newestEmail@april.biz", json.get("email"));
    }

    @Test
    public void shouldPatchUser() {
//        String user = "{\n" +
//                "    \"email\": \"Sincere@april.biz\"\n" + "}";

        JSONObject user = new JSONObject();
        user.put("email", "entirelyNewEmail@april.biz");

        Response response =
                given()
                        .contentType("application/json")
                        .body(user.toString())
                        .when()
                        .patch(BASE_URL + USERS + "/1")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        JsonPath json = response.jsonPath();

        assertEquals("entirelyNewEmail@april.biz" , json.get("email"));
    }
}
