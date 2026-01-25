package steps;

import io.restassured.response.Response;
import models.User;
import io.qameta.allure.Step;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class UserSteps {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru/api/auth";

    @Step("Регистрация пользователя через API")
    public Response register(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .post(BASE_URL + "/register");
    }

    @Step("Получение accessToken по объекту User")
    public String getAccessToken(User user) {
        return getAccessToken(user.getEmail(), user.getPassword());
    }

    @Step("Получение accessToken по email и паролю")
    public String getAccessToken(String email, String password) {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        return given()
                .header("Content-Type", "application/json")
                .body(credentials)
                .post(BASE_URL + "/login")
                .then()
                .statusCode(SC_OK)
                .extract()
                .path("accessToken");
    }

    @Step("Удаление пользователя по accessToken")
    public void delete(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .delete(BASE_URL + "/user")
                .then()
                .statusCode(SC_ACCEPTED);
    }
}