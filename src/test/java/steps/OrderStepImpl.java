package steps;

import data.request.order.CreateOrderRequest;
import data.response.order.CreateOrderResponse;
import data.response.order.GetOrdersResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import steps.specifications.BaseRequestSpecification;
import steps.specifications.BaseResponseSpecification;
import steps.specifications.JsonSpecification;

public class OrderStepImpl implements OrderStep {
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest, int statusCode) {
        String json = JsonSpecification.setGsonBuilder().toJson(createOrderRequest);
        return RestAssured.given()
                .spec(BaseRequestSpecification.baseRequestSpecification(BaseRequestSpecification.BASE_URL, ContentType.JSON))
                .body(json)
                .post("/api/v1/orders")
                .then()
                .spec(BaseResponseSpecification.baseResponseSpecification(statusCode, ""))
                .extract()
                .body()
                .as(CreateOrderResponse.class);
    }

    public GetOrdersResponse getOrders(int statusCode) {
        return RestAssured.given()
                .spec(BaseRequestSpecification.baseRequestSpecification(BaseRequestSpecification.BASE_URL, ContentType.JSON))
                .get("/api/v1/orders")
                .then()
                .spec(BaseResponseSpecification.baseResponseSpecification(statusCode, ""))
                .extract()
                .body()
                .as(GetOrdersResponse.class);
    }
}
