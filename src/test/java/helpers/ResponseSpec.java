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
                .expectBody("sys", hasValue("PL"))
                .expectBody("name", equalTo("Rzeszów"))
                .expectBody("wind.speed", greaterThan(1.0f))
                .expectBody("coord.lon", lessThan(22.0f))
                .expectBody("coord.lat", lessThan(52.0f));

        return responseSpecBuilder.build();
    }
}
