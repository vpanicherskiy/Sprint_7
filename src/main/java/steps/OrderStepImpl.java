package steps;

import data.request.order.CreateOrderRequest;
import data.response.order.CancelOrderResponse;
import data.response.order.CreateOrderResponse;
import data.response.order.GetOrdersResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import steps.specifications.BaseRequestSpecification;
import steps.specifications.BaseResponseSpecification;
import steps.specifications.JsonSpecification;

import static steps.specifications.BaseRequestSpecification.CANCEL_ORDER;
import static steps.specifications.BaseRequestSpecification.ORDER_PATH;

public class OrderStepImpl implements OrderStep {
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest, int statusCode) {
        String json = JsonSpecification.setGsonBuilder().toJson(createOrderRequest);
        return RestAssured.given()
                .spec(BaseRequestSpecification.baseRequestSpecification(BaseRequestSpecification.BASE_URL, ContentType.JSON))
                .body(json)
                .post(ORDER_PATH)
                .then()
                .spec(BaseResponseSpecification.baseResponseSpecification(statusCode, ""))
                .extract()
                .body()
                .as(CreateOrderResponse.class);
    }

    public GetOrdersResponse getOrders(int statusCode) {
        return RestAssured.given()
                .spec(BaseRequestSpecification.baseRequestSpecification(BaseRequestSpecification.BASE_URL, ContentType.JSON))
                .get(ORDER_PATH)
                .then()
                .spec(BaseResponseSpecification.baseResponseSpecification(statusCode, ""))
                .extract()
                .body()
                .as(GetOrdersResponse.class);
    }

    public CancelOrderResponse cancelOrder(int track, int statusCode) {
        return RestAssured.given()
                .spec(BaseRequestSpecification.baseRequestSpecification(BaseRequestSpecification.BASE_URL, ContentType.JSON))
                .queryParam("track", track)
                .put(CANCEL_ORDER)
                .then()
                .spec(BaseResponseSpecification.baseResponseSpecification(BaseResponseSpecification.SC_OK, ""))
                .extract()
                .body()
                .as(CancelOrderResponse.class);
    }
}
