package helpers;

import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;

public class RequestSpec {

    public static RequestSpecification getRequest(String paramName, String paramValue) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setBaseUri("https://samples.openweathermap.org/data/2.5")
                .setBasePath("/weather")
                .addParam(paramName, paramValue);

        return requestSpecBuilder.build();
    }
}
