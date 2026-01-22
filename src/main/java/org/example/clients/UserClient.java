package org.example.clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.config.Configuration;
import org.example.models.User;

import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String REGISTER_ENDPOINT = "/api/auth/register";
    private static final String USER_ENDPOINT = "/api/auth/user";

    @Step("Создание нового пользователя")
    public Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(Configuration.BASE_URL)
                .body(user)
                .when()
                .post(REGISTER_ENDPOINT);
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .baseUri(Configuration.BASE_URL)
                .when()
                .delete(USER_ENDPOINT);
    }
}