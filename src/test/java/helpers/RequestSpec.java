package helpers;

import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;

public class RequestSpec {

    public static RequestSpecification getRequest() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setBaseUri("https://samples.openweathermap.org/data/2.5")
                .setBasePath("/weather")
                .addParam("q", "London, uk")
                .addParam("appid", "b1b15e88fa797225412429c1c50c122a1");

        return requestSpecBuilder.build();
    }
}
