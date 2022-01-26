import configuration.Property;
import configuration.PropertyConfig;
import configuration.yamlReader.YamlReader;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {
    public static Logger logger = LoggerFactory.getLogger(YamlReader.class);
    public static PropertyConfig property;

    @BeforeAll
    public static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.authentication = RestAssured.oauth2("1/1201629892164949:d726687d7214eb9fe9e023fb2acd6309");

        RestAssured.requestSpecification = setCommonRequest();
        logger.info("Common request used");
        RestAssured.responseSpecification = setCommonResponse();
        logger.info("Common response used");
    }

    public static RequestSpecification setCommonRequest() {
        return new RequestSpecBuilder()
                .setBaseUri(property.getProperties().get("baseUri")) //null
                .setBasePath(property.getProperties().get("basePath"))
//                .setBaseUri("https://api.openweathermap.org/data/2.5")
//                .setBasePath("/weather")
                .build()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public static ResponseSpecification setCommonResponse() {
        String expectedResponseTime = property.getProperties().get("expectedRespTime");
        Long responseTime = Long.parseLong(expectedResponseTime);
        return new ResponseSpecBuilder()
                .expectResponseTime(Matchers.lessThan(responseTime))
                .expectContentType(ContentType.JSON)
                .build();
    }
}
