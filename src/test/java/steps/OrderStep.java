package steps;

import data.request.order.CreateOrderRequest;
import data.response.order.CreateOrderResponse;
import data.response.order.GetOrdersResponse;

public interface OrderStep {
    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest, int statusCode);

    GetOrdersResponse getOrders(int statusCode);
}
