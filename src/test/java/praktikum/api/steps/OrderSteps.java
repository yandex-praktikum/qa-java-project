package praktikum.api.steps;

import io.qameta.allure.Step;
import praktikum.api.client.ApiClient;
import praktikum.api.model.OrderRequest;
import io.restassured.response.Response;

public class OrderSteps {

    private final ApiClient api;

    public OrderSteps(ApiClient api) {
        this.api = api;
    }

    @Step("Создание заказа с ингредиентами: {order.ingredients}")
    public Response createOrder(OrderRequest order, String token) {
        return api.createOrder(order, token);
    }

    @Step("Получение заказов пользователя")
    public Response getUserOrders(String token) {
        return api.getUserOrders(token);
    }
}
