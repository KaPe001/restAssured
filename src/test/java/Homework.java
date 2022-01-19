import org.junit.jupiter.api.Test;

import static helpers.RequestSpec.getRequest;
import static helpers.ResponseSpec.getResponse;
import static io.restassured.RestAssured.given;


public class Homework extends TestBase{

    @Test
    public void getWeather() {

        given()
                .spec(getRequest("q", "London, gb"))
                .spec(getRequest("appid", "b1b15e88fa797225412429c1c50c122a1")).
        when()
                .get().
        then()
                .spec(getResponse());
    }
}
