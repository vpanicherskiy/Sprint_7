package tests;

import data.request.courier.LogInCourierRequest;
import data.response.courier.LogInCourierResponse;
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
public class LogInCourierTest {
    private LogInCourierRequest logInCourierRequest;
    private LogInCourierResponse logInCourierResponse;
    private CourierStep courierStep = new CourierStepImpl();

    @Test
    @DisplayName("Авторизацция курьера")
    public void logIn() {
        logInCourierRequest = new LogInCourierRequest("vovc", "12341");
        logInCourierResponse = courierStep.logInCourier(logInCourierRequest, BaseResponseSpecification.SC_OK);

        MatcherAssert.assertThat("Не удалось авторизоваться",
                logInCourierResponse.getId(),
                Matchers.notNullValue());
    }

    @Test
    @Parameters({
            ",",
            "login, ",
            ",password"
    })
    @DisplayName("Проверка на обязательность полей")
    public void checkRequiredFields(String login, String password) {
        logInCourierRequest = new LogInCourierRequest(login, password);
        logInCourierResponse = courierStep.logInCourier(logInCourierRequest, BaseResponseSpecification.SC_BAD_REQUEST);

        MatcherAssert.assertThat("Удалось авторизваться без обязательных полей",
                logInCourierResponse.getMessage(),
                Matchers.equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Ошибка при неверном логине или пароле")
    @Parameters({
            "vovc12, 1234",
            "vovc, jkssv"
    })
    public void failLogIn(String login, String password) {
        logInCourierRequest = new LogInCourierRequest(login, password);
        logInCourierResponse = courierStep.logInCourier(logInCourierRequest, BaseResponseSpecification.SC_NOT_FOUND);

        MatcherAssert.assertThat("Удалось авторизоваться с неверными данными",
                logInCourierResponse.getMessage(),
                Matchers.equalTo("Учетная запись не найдена"));
    }
}
