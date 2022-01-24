import models.Cities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static helpers.RequestSpec.getRequest;
import static helpers.ResponseSpec.getResponse;
import static helpers.ResponseSpec.getResponseFromEnum;
import static io.restassured.RestAssured.given;


public class Homework extends TestBase {

    @Test
    public void getWeather() {

        given()
                .spec(getRequest("q", "Miami, USA")).
                when()
                .get().
                then()
                .spec(getResponse());
    }

    @ParameterizedTest
    @EnumSource(Cities.class)
    public void practiceWithEnum(Cities cities) {
        given()
                .spec(getRequest("q",
                        cities.getCityName() + "," + cities.getCountry())).
                when()
                .get().
                then()
                .spec(getResponseFromEnum(cities));
    }
}
