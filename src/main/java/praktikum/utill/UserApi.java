package praktikum.utill;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import praktikum.models.User;
import praktikum.utill.Config;

import static io.restassured.RestAssured.given;

public class UserApi {

    @Step("POST {endpoint} — регистрация пользователя: {user.email}")
    public static Response registerRaw(User user) {
        return given()
                .baseUri(Config.BASE_URL)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(Config.REGISTER)
                .andReturn();
    }
    @Step("Регистрация пользователя через API и получение accessToken: {user.email}")
    public static String registerAndGetAccessToken(User user) {
        Response resp = registerRaw(user);
        // НЕ жёсткое требование на код — адаптируйте, если у вас другой код
        resp.then().statusCode(200);
        String token = resp.jsonPath().getString("accessToken");
        return normalizeToken(token);
    }
    @Step("Авторизация пользователя через API: {email}")
    public static String loginAndGetAccessToken(String email, String password) {
        Response resp = given()
                .baseUri(Config.BASE_URL)
                .contentType(ContentType.JSON)
                .body(new User(email, password, null))
                .when()
                .post(Config.LOGIN)
                .andReturn();

        resp.then().statusCode(200);
        String token = resp.jsonPath().getString("accessToken");
        return normalizeToken(token);
    }
    @Step("Удаление пользователя через API по accessToken")
    public static void deleteUser(String accessToken) {
        if (accessToken == null || accessToken.isEmpty()) return;
        String headerToken = accessToken.startsWith("Bearer ") ? accessToken : "Bearer " + accessToken;
        given()
                .baseUri(Config.BASE_URL)
                .header("Authorization", headerToken)
                .when()
                .delete(Config.USER)
                .then()
                // статус может быть 202 / 200 / 204 — подставьте ваш
                .statusCode(202);
    }

    private static String normalizeToken(String token) {
        if (token == null) return null;
        return token.startsWith("Bearer ") ? token : "Bearer " + token;
    }
}