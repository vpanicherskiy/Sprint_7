package tests;

import data.request.courier.CreateCourierRequest;
import data.request.courier.LogInCourierRequest;
import data.response.courier.CreateCourierResponse;
import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.CourierStep;
import steps.CourierStepImpl;
import steps.specifications.BaseResponseSpecification;

@RunWith(JUnitParamsRunner.class)
public class CreateCourierTest {
    private CreateCourierRequest createCourierRequest;
    private CourierStep courierStep = new CourierStepImpl();
    private CreateCourierResponse createCourierResponse;
    private static final String LOGIN = "javaQa_7";
    private static final String PASSWORD = "12345";
    private static final String NAME = "Vladimir";

    @Test
    @DisplayName("Успешное создание курьера и проверка ответа запроса")
    public void createCourierSuccessTest() {
        createCourierRequest = new CreateCourierRequest(LOGIN, PASSWORD, NAME);
        createCourierResponse = courierStep.createCourier(createCourierRequest, BaseResponseSpecification.SC_CREATED);

        MatcherAssert.assertThat("Курьер не создан",
                createCourierResponse.isOk(),
                Matchers.equalTo(true));
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void createDoubleCourierTest() {
        createCourierRequest = new CreateCourierRequest(LOGIN, PASSWORD, NAME);
        createCourierResponse = courierStep.createCourier(createCourierRequest, BaseResponseSpecification.SC_CREATED);
        CreateCourierResponse createCourierResponse1 = courierStep.createCourier(createCourierRequest, BaseResponseSpecification.SC_CONFLICT);

        MatcherAssert.assertThat("Удалось создать двух одинаковых курьеров",
                createCourierResponse1.isOk(),
                Matchers.equalTo(false));

        MatcherAssert.assertThat("Ошибка отличается от ожидаемой",
                createCourierResponse1.getMessage(),
                Matchers.equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Проверка на обязательность полей")
    public void checkRequiredFields() {
        createCourierRequest = new CreateCourierRequest(NAME);
        createCourierResponse = courierStep.createCourier(createCourierRequest, BaseResponseSpecification.SC_BAD_REQUEST);

        MatcherAssert.assertThat("Удалось создать курьера без логина и пароля",
                createCourierResponse.isOk(),
                Matchers.equalTo(false));
    }

    @Test
    @Parameters({
            ", , ",
            "javaQa_7, , Vladimir",
            ",12345, Vladimir"}
    )
    @DisplayName("Создание исполнителя без данных")
    public void checkSuccessResponseBody(String login, String password, String name) {
        createCourierRequest = new CreateCourierRequest(login, password, name);
        createCourierResponse = courierStep.createCourier(createCourierRequest, BaseResponseSpecification.SC_BAD_REQUEST);

        MatcherAssert.assertThat("Удалось создать курьера без данных",
                createCourierResponse.getMessage(),
                Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void clearData() {
        if (createCourierResponse.isOk()) {
            courierStep.deleteCourier(
                    courierStep.logInCourier(new LogInCourierRequest(LOGIN, PASSWORD),
                            BaseResponseSpecification.SC_OK).getId(),
                    BaseResponseSpecification.SC_OK
            );
        }
    }
}
