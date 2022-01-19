import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    public static void setUp(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
//        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.authentication = RestAssured.oauth2("1/1201629892164949:d726687d7214eb9fe9e023fb2acd6309");
    }
}
