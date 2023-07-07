package tests;

import data.response.order.GetOrdersResponse;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import steps.OrderStep;
import steps.OrderStepImpl;
import steps.specifications.BaseResponseSpecification;

public class GetOrdersTest {
    @Test
    @DisplayName("Получение списка заказов")
    public void getOrders() {
        OrderStep orderStep = new OrderStepImpl();
        GetOrdersResponse getOrdersResponse = orderStep.getOrders(BaseResponseSpecification.SC_OK);
        MatcherAssert.assertThat("Нет заказов",
                getOrdersResponse.getOrders(),
                Matchers.not(Matchers.empty()));
    }
}
