
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.DataStore;
import models.Student;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.regex.Matcher;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AsanaTest {

    float speedWind;

    @Test
    public void shouldGetWorkspaces() {
        when()
                .get("https://app.asana.com/api/1.0/workspaces").
        then()
                .statusCode(200);
    }



    @Test
    public void requestSpec() {
        given()
                .param("q","London,uk")
                .param("appid", "b1b15e88fa797225412429c1c50c122a1")
                .log()
                .all().
        when()
                .get("https://samples.openweathermap.org/data/2.5/weather")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void requestSpecWithObject() {

        RequestSpecification requestSpec =
                given()
                        .header("name", "Kinga")
                        .param("q","London,uk")
                        .param("appid", "b1b15e88fa797225412429c1c50c122a1")
                        .log()
                        .all();

        given()
                .spec(requestSpec).
        when()
                .get("https://samples.openweathermap.org/data/2.5/weather")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void responseSpecWithObject() {

        ResponseSpecification responseSpec = RestAssured.expect();

        responseSpec
                .log()
                .all()
                .time(Matchers.lessThan(5000L))
                .contentType(ContentType.JSON)
                .statusCode(200);

        given()
                .param("q","London,uk")
                .param("appid", "b1b15e88fa797225412429c1c50c122a1")
                .log()
                .all().
        when()
                .get("https://samples.openweathermap.org/data/2.5/weather").
        then()
                .spec(responseSpec);
    }

    @Test
    public void getWindSpeed() {
        RequestSpecification requestSpec =
                given()
                        .header("name", "Kinga")
                        .param("q","London,uk")
                        .param("appid", "b1b15e88fa797225412429c1c50c122a1")
                        .log()
                        .all();


        DataStore.WINDSPEED = given()
                .param("q","London,uk")
                .param("appid", "b1b15e88fa797225412429c1c50c122a1").
        when()
                .get("https://samples.openweathermap.org/data/2.5/weather").
        then()
                .log()
                .all()
                .extract()
                .path("wind.speed");

        System.out.println("Speed wind = " + DataStore.WINDSPEED);
    }

    @Test
    public void getCountry() {
        RequestSpecification requestSpec =
                given()
                        .header("name", "Kinga")
                        .param("q","London,uk")
                        .param("appid", "b1b15e88fa797225412429c1c50c122a1")
                        .log()
                        .all();


        DataStore.COUNTRY = given()
                .spec(requestSpec).
                when()
                    .get("https://samples.openweathermap.org/data/2.5/weather").
//                  .post("https://samples.openweathermap.org/data/2.5/weather" + DataStore.COUNTRY + "/country")
                then()
                    .log()
                    .all()
                    .extract()
                    .path("sys.country");

        System.out.println("Country is:  " + DataStore.COUNTRY);
    }

    @Test
    public void isWindSpeedCorrect() {
        RequestSpecification requestSpec = given()
                .header("name", "Kinga")
                .param("q","London,uk")
                .param("appid", "b1b15e88fa797225412429c1c50c122a1")
                .log()
                .all();


        given()
                .spec(requestSpec).
                when()
                .get("https://samples.openweathermap.org/data/2.5/weather").
        then()
                .log()
                .all()
                .body("wind.speed", is(4.1f))
                .statusCode(200);

        System.out.println("Wind speed is:  " + DataStore.WINDSPEED);
    }

    @Test
    public void isNameCorrect() {
        RequestSpecification requestSpec = given()
                .header("name", "Kinga")
                .param("q","London,uk")
                .param("appid", "b1b15e88fa797225412429c1c50c122a1")
                .log()
                .all();


        given()
                .spec(requestSpec).
                when()
                .get("https://samples.openweathermap.org/data/2.5/weather").
                then()
                .log()
                .all()
                .body("name", equalTo("London"))
                .statusCode(200);
    }

    @Test
    public void isWindSpeedCorrectNotKnowingValue() {
        RequestSpecification requestSpec = given()
                .header("name", "Kinga")
                .param("q","London,uk")
                .param("appid", "b1b15e88fa797225412429c1c50c122a1")
                .log()
                .all();


        given()
                .spec(requestSpec).
        when()
                .get("https://samples.openweathermap.org/data/2.5/weather").
        then()
                .log()
                .all()
                .body("wind.speed", greaterThan(4.0f))
                .statusCode(200);
    }

    @Test
    public void isCountryCorrectInMap() {
        RequestSpecification requestSpec = given()
                .header("name", "Kinga")
                .param("q","London,uk")
                .param("appid", "b1b15e88fa797225412429c1c50c122a1")
                .log()
                .all();


        given()
                .spec(requestSpec).
        when()
                .get("https://samples.openweathermap.org/data/2.5/weather").
        then()
                .log()
                .all()
//                .body("sys", hasEntry("country", "GB"))
//                .body("sys", empty()) when map is empty
                .body("sys", hasValue("GB")) // it is possible to validate more than one options (ca be 2 bodies)
                .body("name", equalTo("London"))
                .statusCode(200);
    }
}
