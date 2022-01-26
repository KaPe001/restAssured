import models.Cities;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import testBase.TestBase;

import static helpers.RequestSpec.getRequest;
import static helpers.ResponseSpec.getResponseFromEnum;
import static io.restassured.RestAssured.given;


public class Homework extends TestBase {

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
