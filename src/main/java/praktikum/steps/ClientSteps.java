package praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import praktikum.config.RestConfig;
import praktikum.model.Client;


import static io.restassured.RestAssured.given;

public class ClientSteps {

    @Step("Регистрация пользователя")
    public ValidatableResponse createClient(Client client) {
        return given()
                .contentType(ContentType.JSON)
                .body(client)
                .when()
                .post(RestConfig.REGISTER_PATH)
                .then();
    }

    @Step("Вход пользователя")
    public ValidatableResponse loginClient(Client client) {
        return given()
                .contentType(ContentType.JSON)
                .body(client)
                .when()
                .post(RestConfig.LOGIN_PATH)
                .then();
    }
    public ValidatableResponse deleteClient(String accessToken) {
    return given()
            // некоторым API нужен префикс "Bearer ", некоторым - просто токен
            .header("Authorization", accessToken)
            .when()
            .delete("/api/auth/user")
            .then();
 }

}
