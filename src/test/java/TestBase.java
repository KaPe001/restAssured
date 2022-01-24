import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    public static void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.authentication = RestAssured.oauth2("1/1201629892164949:d726687d7214eb9fe9e023fb2acd6309");

        RestAssured.requestSpecification = setCommonRequest();
        RestAssured.responseSpecification = setCommonResponse();
    }

    public static RequestSpecification setCommonRequest() {
        return new RequestSpecBuilder()
                .setBaseUri("https://api.openweathermap.org/data/2.5")
                .setBasePath("/weather")
                .build();
    }

    public static ResponseSpecification setCommonResponse() {
        Long expectedResponseTime = 5000L;
        return new ResponseSpecBuilder()
                .expectResponseTime(Matchers.lessThan(expectedResponseTime))
                .expectContentType(ContentType.JSON)
                .build();
    }

}
