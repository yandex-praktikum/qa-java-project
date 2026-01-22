package stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.models.Order;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

@DisplayName("Тесты на создание заказа")
public class OrderTests extends BaseTest {

    private List<String> validIngredients;

    @Before
    public void setUpOrderTests() {
        // Дополнительная настройка для тестов заказов
        super.setUp();

        // Создаем пользователя и получаем токен
        Response createResponse = userClient.createUser(testUser);
        createResponse.then().assertThat().statusCode(200);

        accessToken = authClient.getAccessToken(testUser.getEmail(), testUser.getPassword());

        // Получаем список валидных ингредиентов
        validIngredients = orderClient.getIngredientIds();
        assertTrue("Должен быть хотя бы один ингредиент", validIngredients != null && validIngredients.size() > 0);
    }

    @Test
    @DisplayName("Создание заказа с авторизацией")
    @Description("Проверка успешного создания заказа авторизованным пользователем")
    public void createOrderWithAuthTest() {
        // Берем первые два ингредиента из списка
        List<String> ingredients = validIngredients.subList(0, Math.min(2, validIngredients.size()));
        Order order = new Order(ingredients);

        Response response = orderClient.createOrder(order, accessToken);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("order.number", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    @Description("Проверка создания заказа без авторизации")
    public void createOrderWithoutAuthTest() {
        List<String> ingredients = validIngredients.subList(0, Math.min(2, validIngredients.size()));
        Order order = new Order(ingredients);

        Response response = orderClient.createOrderWithoutAuth(order);

        // Согласно документации, должен быть 401
        response.then()
                .assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }

    @Test
    @DisplayName("Создание заказа с ингредиентами")
    @Description("Проверка успешного создания заказа с валидными ингредиентами")
    public void createOrderWithIngredientsTest() {
        // Используем несколько ингредиентов
        List<String> ingredients = Arrays.asList(
                validIngredients.get(0),
                validIngredients.get(1 % validIngredients.size())
        );
        Order order = new Order(ingredients);

        Response response = orderClient.createOrder(order, accessToken);

        response.then()
                .assertThat()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("order.number", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    @Description("Проверка ошибки при создании заказа без ингредиентов")
    public void createOrderWithoutIngredientsTest() {
        Order order = new Order(Collections.emptyList());

        Response response = orderClient.createOrder(order, accessToken);

        response.then()
                .assertThat()
                .statusCode(400)
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Создание заказа с неверным хешем ингредиентов")
    @Description("Проверка ошибки при создании заказа с невалидным хешем ингредиентов")
    public void createOrderWithInvalidIngredientHashTest() {
        List<String> invalidIngredients = Arrays.asList("invalid_hash_1", "invalid_hash_2");
        Order order = new Order(invalidIngredients);

        Response response = orderClient.createOrder(order, accessToken);

        response.then()
                .assertThat()
                .statusCode(500); // Согласно документации
    }
}