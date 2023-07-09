package steps.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseRequestSpecification {
    public static final String BASE_URL = "http://qa-scooter.praktikum-services.ru";
    public static final String CREATE_COURIER_PATH = "/api/v1/courier";
    public static final String LOGIN_COURIER_PATH = "/api/v1/courier/login";
    public static final String DELETE_COURIER_PATH = "/api/v1/courier/";
    public static final String ORDER_PATH = "/api/v1/orders";
    public static final String CANCEL_ORDER = "/api/v1/orders/cancel";

    public static RequestSpecification baseRequestSpecification(String url, ContentType contentType) {
        LogDetail all = LogDetail.ALL;
        LogDetail uri = LogDetail.URI;
        LogDetail method = LogDetail.METHOD;

        return new RequestSpecBuilder()
                .setConfig(RestAssuredConfig.config().logConfig(LogConfig
                        .logConfig()
                        .enableLoggingOfRequestAndResponseIfValidationFails(all)))
                .setBaseUri(url)
                .setRelaxedHTTPSValidation()
                .setContentType(contentType)
                .log(uri)
                .log(method)
                .build();
    }
}
