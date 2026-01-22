package org.example.clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.config.Configuration;
import org.example.models.LoginRequest;

import static io.restassured.RestAssured.given;

public class AuthClient {
    private static final String LOGIN_ENDPOINT = "/api/auth/login";

    @Step("Авторизация пользователя")
    public Response login(String email, String password) {
        LoginRequest loginRequest = new LoginRequest(email, password);

        return given()
                .header("Content-type", "application/json")
                .baseUri(Configuration.BASE_URL)
                .body(loginRequest)
                .when()
                .post(LOGIN_ENDPOINT);
    }

    @Step("Получение access токена")
    public String getAccessToken(String email, String password) {
        Response response = login(email, password);
        return response.then().extract().path("accessToken");
    }
}