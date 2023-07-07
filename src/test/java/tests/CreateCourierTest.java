package tests;

import data.request.courier.CreateCourierRequest;
import data.response.courier.CreateCourierResponse;
import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.CourierStep;
import steps.CourierStepImpl;
import steps.specifications.BaseResponseSpecification;

@RunWith(JUnitParamsRunner.class)
public class CreateCourierTest {
    private CreateCourierRequest createCourierRequest;
    private CourierStep courierStep = new CourierStepImpl();
    ;
    private CreateCourierResponse createCourierResponse;
    private boolean isSuccess;

    @Test
    @DisplayName("Успешное создание курьера и проверка ответа завпроса")
    public void createCourierSuccessTest() {
        createCourierRequest = new CreateCourierRequest("zevs8", "12341", "saske1");
        createCourierResponse = courierStep.createCourier(createCourierRequest, BaseResponseSpecification.SC_CREATED);

        MatcherAssert.assertThat("Курьер не создан",
                createCourierResponse.isOk(),
                Matchers.equalTo(true));
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void createDoubleCourierTest() {
        createCourierRequest = new CreateCourierRequest("zevs28", "12341", "saske1");
        courierStep.createCourier(createCourierRequest, BaseResponseSpecification.SC_CREATED);
        createCourierResponse = courierStep.createCourier(createCourierRequest, BaseResponseSpecification.SC_CONFLICT);

        MatcherAssert.assertThat("Удалось создать двух одинаковых курьеров",
                createCourierResponse.isOk(),
                Matchers.equalTo(false));

        MatcherAssert.assertThat("Ошибка отличается от ожидаемой",
                createCourierResponse.getMessage(),
                Matchers.equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Проверка на обязательность полей")
    public void checkRequiredFields() {
        createCourierRequest = new CreateCourierRequest("Vovca");
        isSuccess = courierStep.createCourier(createCourierRequest, BaseResponseSpecification.SC_BAD_REQUEST).isOk();

        MatcherAssert.assertThat("Удалось создать курьера без логина и пароля",
                isSuccess,
                Matchers.equalTo(false));
    }

    @Test
    @Parameters({
            ", , ",
            "login, , name",
            ",password, name"}
    )
    @DisplayName("Создание исполнителя без данных")
    public void checkSuccessResponseBody(String login, String password, String name) {
        createCourierRequest = new CreateCourierRequest(login, password, name);
        createCourierResponse = courierStep.createCourier(createCourierRequest, BaseResponseSpecification.SC_BAD_REQUEST);

        MatcherAssert.assertThat("Удалось создать курьера без данных",
                createCourierResponse.getMessage(),
                Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }
}
