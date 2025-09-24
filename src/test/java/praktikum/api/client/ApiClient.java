package praktikum.api.client;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ApiClient {

    private RequestSpecification spec() {
        return given().spec(io.restassured.RestAssured.requestSpecification);
    }

    // --- AUTH / USER ---
    @Step("Регистрация пользователя с телом {body}")
    public Response registerUser(Object body) {
        return spec()
                .body(body)
                .when()
                .post("/auth/register");
    }
    // --- DELETE USER ---
    @Step("Удаление пользователя с токеном {authorizationHeaderValue}")
    public Response deleteUser(String authorizationHeaderValue) {
        RequestSpecification req = spec();
        if (authorizationHeaderValue != null && !authorizationHeaderValue.isBlank()) {
            req.header("Authorization", authorizationHeaderValue);
        }
        return req.when().delete("/auth/user");
    }

    @Step("Авторизация пользователя с телом {body}")
    public Response loginUser(Object body) {
        return spec()
                .body(body)
                .when()
                .post("/auth/login");
    }

    @Step("Выход пользователя с телом {body}")
    public Response logout(Map<String, String> body) {
        return spec()
                .body(body)
                .when()
                .post("/auth/logout");
    }

    // --- INGREDIENTS ---
    @Step("Получение всех ингредиентов")
    public Response getIngredients() {
        return spec()
                .when()
                .get("/ingredients");
    }

    @Step("Получение списка всех ID ингредиентов")
    public List<String> getIngredientIds() {
        Response r = getIngredients().then().extract().response();
        JsonPath jp = r.jsonPath();

        List<String> ids = jp.getList("data._id");
        if (ids != null && !ids.isEmpty()) return ids;

        ids = jp.getList("ingredients._id");
        if (ids != null && !ids.isEmpty()) return ids;

        List<Map<String, Object>> items = jp.getList("data");
        if (items != null && !items.isEmpty()) {
            return items.stream()
                    .map(m -> (String) m.get("_id"))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    // --- ORDERS ---
    @Step("Создание заказа с телом {body} и токеном {authorizationHeaderValue}")
    public Response createOrder(Object body, String authorizationHeaderValue) {
        RequestSpecification req = spec().body(body);
        if (authorizationHeaderValue != null && !authorizationHeaderValue.isBlank()) {
            req.header("Authorization", authorizationHeaderValue);
        }
        return req.when().post("/orders");
    }

    @Step("Получение заказов пользователя с токеном {authorizationHeaderValue}")
    public Response getUserOrders(String authorizationHeaderValue) {
        RequestSpecification req = spec();
        if (authorizationHeaderValue != null && !authorizationHeaderValue.isBlank()) {
            req.header("Authorization", authorizationHeaderValue);
        }
        return req.when().get("/orders");
    }
}
