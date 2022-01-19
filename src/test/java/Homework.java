import org.junit.jupiter.api.Test;

import static helpers.RequestSpec.getRequest;
import static helpers.ResponseSpec.getResponse;
import static io.restassured.RestAssured.given;


public class Homework extends TestBase{

    @Test
    public void getWeather() {

        given()
                .spec(getRequest()).
        when()
                .get().
        then()
                .spec(getResponse());
    }
}
