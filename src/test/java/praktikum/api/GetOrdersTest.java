package praktikum.api;

import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import praktikum.api.config.TestBase;
import praktikum.api.model.OrderRequest;
import praktikum.api.steps.OrderSteps;
import praktikum.api.util.TestData;

import static org.hamcrest.Matchers.*;

public class GetOrdersTest extends TestBase {

    private OrderSteps orderSteps;

    @Before
    public void setUp() {
        orderSteps = new OrderSteps(api);

        needUser = true;
        createAndLoginUser(); // создаем пользователя и получаем token
    }

    @Test
    @DisplayName("Получение заказов с авторизацией")
    @Description("Создаем заказ и получаем список заказов пользователя. Проверяем, что список не пустой и первый заказ имеет номер.")
    public void getOrdersWithAuthReturnsOrders() {
        orderSteps.createOrder(new OrderRequest(TestData.realIngredients().subList(0, 2)), token)
                .then().statusCode(200);

        orderSteps.getUserOrders(token)
                .then().statusCode(200)
                .body("orders", not(empty()))
                .body("orders[0].number", notNullValue());
    }

    @Test
    @DisplayName("Получение заказов без авторизации возвращает 401")
    @Description("Попытка получить заказы без токена авторизации. Проверяем, что возвращается статус 401 и сообщение о необходимости авторизации.")
    public void getOrdersWithoutAuthReturns401() {
        orderSteps.getUserOrders(null)
                .then().statusCode(401)
                .body("success", equalTo(false))
                .body("message", containsString("You should be authorised"));
    }
}
