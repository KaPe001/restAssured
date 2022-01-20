package helpers;

import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;

public class RequestSpec {

    public static RequestSpecification getRequest(String paramName, String paramValue) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setBaseUri("https://api.openweathermap.org/data/2.5")
                .setBasePath("/weather")
                .addParam("appid", "89a2ed8a594cc497a6273490e7ca59dd")
                .addParam(paramName, paramValue);

        return requestSpecBuilder.build();
    }
}
