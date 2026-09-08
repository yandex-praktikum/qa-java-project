package praktikum;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @DisplayName("Получение типа ингредиента")
    @Description("Проверяем, что метод getType() возвращает верное значение")
    @ParameterizedTest
    @CsvSource({
            "SAUCE",
            "FILLING"
    })
    void getTypeReturnsConstructorValue(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "ingredient", 100.0f);

        assertEquals(type, ingredient.getType());
    }

    @DisplayName("Получение названия ингредиента")
    @Description("Проверяем, что метод getName() возвращает верное значение")
    @ParameterizedTest
    @CsvSource({
            "hot sauce",
            "cutlet"
    })
    void getNameReturnsConstructorValue(String name) {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, name, 100.0f);

        assertEquals(name, ingredient.getName());
    }

    @DisplayName("Получение цены ингредиента")
    @Description("Проверяем, что метод getPrice() возвращает верное значение")
    @ParameterizedTest
    @CsvSource({
            "100.0",
            "200.5"
    })
    void getPriceReturnsConstructorValue(float price) {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "ingredient", price);

        assertEquals(price, ingredient.getPrice());
    }
}
