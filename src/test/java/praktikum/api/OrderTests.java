package praktikum.api;

import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import praktikum.api.config.TestBase;
import praktikum.api.model.OrderRequest;
import praktikum.api.steps.OrderSteps;
import praktikum.api.util.TestData;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class OrderTests extends TestBase {

    private OrderSteps orderSteps;

    @Before
    public void setUp() {
        orderSteps = new OrderSteps(api);

        needUser = true;
        createAndLoginUser(); // создаем пользователя и получаем token
    }

    @Test
    @DisplayName("Создание заказа с авторизацией возвращает 200")
    @Description("Создаем заказ с валидными ингредиентами под авторизованным пользователем. Проверяем статус 200 и наличие номера заказа.")
    public void createOrderWithAuthReturns200() {
        orderSteps.createOrder(new OrderRequest(TestData.realIngredients().subList(0, 2)), token)
                .then().statusCode(200)
                .body("success", equalTo(true))
                .body("order.number", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа без авторизации возвращает 401")
    @Description("Попытка создать заказ без токена авторизации. Проверяем, что возвращается статус 401 и сообщение о необходимости авторизации.")
    public void createOrderWithoutAuthReturns401() {
        orderSteps.createOrder(new OrderRequest(TestData.realIngredients().subList(0, 1)), null)
                .then().statusCode(401)
                .body("success", equalTo(false))
                .body("message", containsString("You should be authorised"));
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов возвращает 400")
    @Description("Попытка создать заказ с пустым списком ингредиентов. Проверяем, что возвращается статус 400 и корректное сообщение об ошибке.")
    public void createOrderWithoutIngredientsReturns400() {
        orderSteps.createOrder(new OrderRequest(new ArrayList<>()), token)
                .then().statusCode(400)
                .body("success", equalTo(false))
                .body("message", containsString("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Создание заказа с некорректным хешем ингредиента возвращает 500")
    @Description("Попытка создать заказ с неверным хешем ингредиента. Проверяем, что возвращается статус 500.")
    public void createOrderWithInvalidIngredientHashReturns500() {
        List<String> invalidIngredients = new ArrayList<>(TestData.realIngredients());
        invalidIngredients.set(0, invalidIngredients.get(0) + "ZZ");

        orderSteps.createOrder(new OrderRequest(invalidIngredients), token)
                .then().statusCode(500);
    }
}
