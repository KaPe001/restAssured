package helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {

    public static RequestSpecification getRequest(String paramName, String paramValue) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .addParam("appid", "ca3971e3d9a9b7b9f19b601ff387cb38")
                .addParam(paramName, paramValue);

        return requestSpecBuilder.build();
    }
}
