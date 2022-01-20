package helpers;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.*;

public class ResponseSpec {

    public static ResponseSpecification getResponse() {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder
                .expectStatusCode(200)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectContentType(ContentType.JSON)
                .expectBody("sys", hasValue("GB"))
                .expectBody("name", equalTo("London"))
                .expectBody("wind.speed", greaterThan(4.0f))
                .expectBody("coord.lon", lessThan(0.0f))
                .expectBody("coord.lat", lessThan(52.0f));

        return responseSpecBuilder.build();
    }
}
