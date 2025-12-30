import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static constants.ingredient.IngredientInt.PRICE_1000;
import static constants.ingredient.IngredientInt.PRICE_500;
import static constants.ingredient.IngredientString.BBQ_SAUCE;
import static constants.ingredient.IngredientString.FISH_FILLING;
import static org.junit.jupiter.api.Assertions.*;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

public class IngredientTests {

    private Ingredient ingredient;

    public static Object[][] data() {
        return new Object[][] {
                {SAUCE, BBQ_SAUCE, PRICE_500},
                {FILLING, FISH_FILLING, PRICE_1000},
        };
    }

    @ParameterizedTest
    @Tag("Positive")
    @MethodSource("data")
    @DisplayName("Проверка возврата значений функции получения названия ингредиентов")
    void checkGetNameOfIngredients(IngredientType ingredientType, String name, float price){
        ingredient = new Ingredient(ingredientType, name, price);

        String actualName = ingredient.getName();

        assertAll("Проверки для наименования ингредиентов",
                () -> assertEquals(name, actualName, "У каждого ингредиента должно быть наименование"),
                () -> assertNotNull(actualName, "Наименование ингредиента не может быть пустым")
        );
    }

    @ParameterizedTest
    @Tag("Positive")
    @MethodSource("data")
    @DisplayName("Проверка возврата значений функции получения цены ингредиентов")
    void checkGetPriceOfIngredients(IngredientType ingredientType, String name, float price){
        ingredient = new Ingredient(ingredientType, name, price);

        float actualPrice = ingredient.getPrice();

        assertAll("Проверки для цены ингредиентов",
                () -> assertEquals(price, actualPrice, "У каждого ингредиента должна быть цена"),
                () -> assertNotNull(actualPrice, "Цена ингредиента не может быть пустой")
        );
    }

    @ParameterizedTest
    @Tag("Positive")
    @MethodSource("data")
    @DisplayName("Проверка возврата значений функции получения типа ингредиента")
    void checkGetTypeOfIngredients(IngredientType ingredientType, String name, float price){
        ingredient = new Ingredient(ingredientType, name, price);

        IngredientType actualType = ingredient.getType();

        assertAll("Проверки для типов ингредиентов",
                () -> assertEquals(ingredientType, actualType, "У каждого ингредиента должен быть тип"),
                () -> assertNotNull(actualType, "Тип ингредиента не может быть пустой")
        );
    }
}
