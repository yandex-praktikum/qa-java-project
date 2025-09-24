package praktikum.api.steps;

import io.qameta.allure.Step;
import praktikum.api.client.ApiClient;
import praktikum.api.model.LoginRequest;
import praktikum.api.model.RegisterRequest;
import io.restassured.response.Response;

public class UserSteps {

    private final ApiClient api;

    public UserSteps(ApiClient api) {
        this.api = api;
    }

    @Step("Регистрация пользователя с email: {request.email}")
    public Response registerUser(RegisterRequest request) {
        return api.registerUser(request);
    }

    @Step("Логин пользователя с email: {request.email}")
    public Response loginUser(LoginRequest request) {
        return api.loginUser(request);
    }

    @Step("Удаление пользователя с токеном")
    public Response deleteUser(String token) {
        return api.deleteUser(token);
    }
}
