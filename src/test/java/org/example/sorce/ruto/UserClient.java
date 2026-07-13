package org.example.sorce.ruto;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String BASE_URI = "https://stellarburgers.education-services.ru/api";
    private final RequestSpecification baseSpec;

    public UserClient() {
        this.baseSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }

    private String formatToken(String token) {
        if (token != null && !token.startsWith("Bearer ")) {
            return "Bearer " + token;
        }
        return token;
    }

    // 1 Создание пользователя (POST /api/auth/register)
    @Step("Регистрация нового пользователя")
    public ValidatableResponse register(User user) {
        return given()
                .spec(baseSpec)
                .body(user)
                .when()
                .post("/auth/register")
                .then();
    }

    // 2 Логин пользователя (POST /api/auth/login)
    @Step("Авторизация (логин) пользователя")
    public ValidatableResponse login(User user) {
        return given()
                .spec(baseSpec)
                .body(user)
                .when()
                .post("/auth/login")
                .then();
    }

    // 3 Удаление пользователя (DELETE /api/auth/user)
    @Step("Удаление пользователя по токену")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .spec(baseSpec)
                .header("Authorization", formatToken(accessToken))
                .when()
                .delete("/auth/user")
                .then();
    }
}