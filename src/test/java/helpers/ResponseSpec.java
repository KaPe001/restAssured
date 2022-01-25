package helpers;

import configuration.Property;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import models.Cities;

import static org.hamcrest.Matchers.*;

public class ResponseSpec {
    private static Property property;

    public static ResponseSpecification getResponseFromEnum(Cities cities) {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder
                .expectStatusCode(property.getExpectedSuccessCode())
                .expectBody("sys", hasValue(cities.getCountry()))
                .expectBody("name", equalTo(cities.getCityName()))
                .expectBody("id", is(cities.getCityId()))
                .expectBody("coord.lon", is(cities.getLongitude()))
                .expectBody("coord.lat", is(cities.getLatitude()));

        return responseSpecBuilder.build();
    }
}
