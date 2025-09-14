package praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import praktikum.config.RestConfig;
import praktikum.model.Order;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("Получение списка ингредиентов")
    public ValidatableResponse ingredientsList(Order order) {
        return given()
                .body(order)
                .when()
                .get(RestConfig.INGREDIENTS_PATH)
                .then();
    }

    @Step("Получение случайных ID ингредиентов")
    public List<String> getRandomIngredientIds(int count) {
        Response response = given()
                .when()
                .get(RestConfig.INGREDIENTS_PATH);

        List<String> allIds = response.jsonPath()
                .getList("data._id")
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());

        count = Math.min(count, allIds.size());

        Random random = new Random();
        return allIds.stream()
                .distinct()
                .limit(count)
                .collect(Collectors.toList());
    }

    @Step("Создание заказа")
    public ValidatableResponse createOrder(Order order) {
            return given()
                    .body(order)
                    .when()
                    .post(RestConfig.ORDER_PATH)
                    .then();


    }
}
