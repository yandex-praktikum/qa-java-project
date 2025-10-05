import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование класса Ingredient")
class IngredientTest {

    @ParameterizedTest(name = "Создание ингредиента: {1} ({0}), цена {2}")
    @CsvSource({
            "SAUCE, Барбекю, 100.0",
            "FILLING, Котлета, 200.5",
            "SAUCE, Острый соус, 50.0"
    })
    @DisplayName("Проверка корректного создания ингредиента и работы геттеров")
    @Description("Проверяем, что при создании ингредиента его тип, имя и цена корректно сохраняются и возвращаются методами getType(), getName(), getPrice()")
    void testIngredientFieldsAndGetters(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType(), "Тип ингредиента должен совпадать с переданным");
        assertEquals(name, ingredient.getName(), "Имя ингредиента должно совпадать с переданным");
        assertEquals(price, ingredient.getPrice(), 0.001, "Цена ингредиента должна совпадать с переданным значением");
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    @DisplayName("Проверка всех возможных типов ингредиентов")
    @Description("Проверяем, что перечисление IngredientType содержит корректные значения SAUCE и FILLING")
    void testIngredientTypeValues(IngredientType type) {
        assertTrue(type == IngredientType.SAUCE || type == IngredientType.FILLING,
                "Перечисление IngredientType должно содержать только SAUCE и FILLING");
    }
}
