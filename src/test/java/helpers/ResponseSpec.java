package helpers;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import models.Cities;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.*;

public class ResponseSpec {

    public static ResponseSpecification getResponse() {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder
                .expectStatusCode(200)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectContentType(ContentType.JSON)
                .expectBody("sys", hasValue("US"))
                .expectBody("name", equalTo("Miami"))
                .expectBody("id", is(4164138))
                .expectBody("coord.lon", lessThanOrEqualTo(-80.0f))
                .expectBody("coord.lat", greaterThanOrEqualTo(25.0f));

        return responseSpecBuilder.build();
    }

    public static ResponseSpecification getResponseFromEnum(Cities cities) {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder
                .expectStatusCode(200)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectContentType(ContentType.JSON)
                .expectBody("sys", hasValue(cities.getCountry()))
                .expectBody("name", equalTo(cities.getCityName()))
                .expectBody("id", is(cities.getCityId()))
                .expectBody("coord.lon", is(cities.getLongitude()))
                .expectBody("coord.lat", is(cities.getLatitude()));

        return responseSpecBuilder.build();
    }
}
