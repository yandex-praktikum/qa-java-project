package org.example.clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.config.Configuration;
import org.example.models.Order;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OrderClient {
    private static final String INGREDIENTS_ENDPOINT = "/api/ingredients";
    private static final String ORDERS_ENDPOINT = "/api/orders";

    @Step("Получение списка ингредиентов")
    public Response getIngredients() {
        return given()
                .header("Content-type", "application/json")
                .baseUri(Configuration.BASE_URL)
                .when()
                .get(INGREDIENTS_ENDPOINT);
    }

    @Step("Создание заказа с авторизацией")
    public Response createOrder(Order order, String accessToken) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .baseUri(Configuration.BASE_URL)
                .body(order)
                .when()
                .post(ORDERS_ENDPOINT);
    }

    @Step("Создание заказа без авторизации")
    public Response createOrderWithoutAuth(Order order) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(Configuration.BASE_URL)
                .body(order)
                .when()
                .post(ORDERS_ENDPOINT);
    }

    @Step("Получение ID ингредиентов")
    public List<String> getIngredientIds() {
        Response response = getIngredients();
        response.then().assertThat().statusCode(200); // Проверка успешности
        return response.then().extract().path("data._id");
    }
}