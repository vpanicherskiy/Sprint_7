package steps;

import data.request.courier.CreateCourierRequest;
import data.request.courier.LogInCourierRequest;
import data.response.courier.CreateCourierResponse;
import data.response.courier.DeleteCourierResponse;
import data.response.courier.LogInCourierResponse;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import steps.specifications.BaseRequestSpecification;
import steps.specifications.BaseResponseSpecification;
import steps.specifications.JsonSpecification;

import static steps.specifications.BaseRequestSpecification.*;

public class CourierStepImpl implements CourierStep {
    @Step("Создание курьера")
    public CreateCourierResponse createCourier(CreateCourierRequest createCourierRequest, int statusCode) {

        String json = JsonSpecification.setGsonBuilder().toJson(createCourierRequest);
        return RestAssured.given()
                .spec(BaseRequestSpecification.baseRequestSpecification(BASE_URL, ContentType.JSON))
                .body(json)
                .post(CREATE_COURIER_PATH)
                .then()
                .spec(BaseResponseSpecification.baseResponseSpecification(statusCode, ""))
                .extract()
                .body()
                .as(CreateCourierResponse.class);
    }

    @Step("Авторизация курьера")
    public LogInCourierResponse logInCourier(LogInCourierRequest logInCourierRequest, int statusCode) {
        String json = JsonSpecification.setGsonBuilder().toJson(logInCourierRequest);
        return RestAssured.given()
                .spec(BaseRequestSpecification.baseRequestSpecification(BASE_URL, ContentType.JSON))
                .body(json)
                .post(LOGIN_COURIER_PATH)
                .then()
                .spec(BaseResponseSpecification.baseResponseSpecification(statusCode, ""))
                .extract()
                .body()
                .as(LogInCourierResponse.class);
    }

    @Step("Удвление курьера")
    public DeleteCourierResponse deleteCourier(int courierId, int statusCode) {
        return RestAssured.given()
                .spec(BaseRequestSpecification.baseRequestSpecification(BASE_URL, ContentType.JSON))
                .delete(DELETE_COURIER_PATH + courierId)
                .then()
                .spec(BaseResponseSpecification.baseResponseSpecification(statusCode, ""))
                .extract()
                .body()
                .as(DeleteCourierResponse.class);
    }
}
