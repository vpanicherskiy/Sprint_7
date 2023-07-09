package tests;

import data.request.order.CreateOrderRequest;
import data.response.order.CreateOrderResponse;
import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.OrderStep;
import steps.OrderStepImpl;
import steps.specifications.BaseResponseSpecification;

@RunWith(JUnitParamsRunner.class)
public class CreateOrderTest {

    private CreateOrderRequest createOrderRequest;
    private OrderStep orderStep = new OrderStepImpl();
    private CreateOrderResponse createOrderResponse;

    @Test
    @DisplayName("Создание заказа")
    @Parameters({
            "BLACK",
            "GREY",
            "BLACK, GREY",
            ""
    })
    public void createOrder(String[] color) {
        createOrderRequest = new CreateOrderRequest(
                "name",
                "lastName",
                "address",
                4,
                "+7 800 355 35 35",
                5,
                "2020-06-06",
                "Saske, come back to Konoha",
                color);
        createOrderResponse = orderStep.createOrder(createOrderRequest, BaseResponseSpecification.SC_CREATED);

        MatcherAssert.assertThat("Не удалось создать заказ",
                createOrderResponse.getTrack(),
                Matchers.notNullValue());
    }

    @After
    public void cancelOrder() {
        orderStep.cancelOrder(createOrderResponse.getTrack(), BaseResponseSpecification.SC_OK);
    }
}
