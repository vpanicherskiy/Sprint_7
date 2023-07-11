package steps;

import data.request.courier.CreateCourierRequest;
import data.request.courier.LogInCourierRequest;
import data.response.courier.CreateCourierResponse;
import data.response.courier.DeleteCourierResponse;
import data.response.courier.LogInCourierResponse;

public interface CourierStep {
    CreateCourierResponse createCourier(CreateCourierRequest createCourierRequest, int statusCode);

    LogInCourierResponse logInCourier(LogInCourierRequest logInCourierRequest, int statusCode);

    DeleteCourierResponse deleteCourier(int courierId, int statusCode);
}
