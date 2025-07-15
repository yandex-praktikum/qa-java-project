package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientType.FILLING;

class IngredientParameterizedTest {

    Ingredient ingredient; // Тестируемый объект Ingredient
    private static final IngredientType INGREDIENT_TYPE = FILLING; // Константа типа ингредиента
    private static final String INGREDIENT_NAME = "cutlet"; // Константа имени ингредиента
    private static final float INGREDIENT_PRICE = 100; // Константа цены ингредиента

    @ParameterizedTest
    @ValueSource(strings = {"SAUCE", "FILLING"}) // Тестируем на двух типах ингредиентов
    @DisplayName("Получение типа ингредиента")
    void getType(String type) {
        IngredientType ingredientType = IngredientType.valueOf(type); // Преобразуем строку в enum
        ingredient = new Ingredient(ingredientType, INGREDIENT_NAME, INGREDIENT_PRICE); // Создаем ингредиент с данным типом
        assertEquals(ingredientType, ingredient.getType()); // Проверяем, что getType возвращает правильный тип
    }

    @Test
    @DisplayName("Получение имени ингредиента")
    void getName() {
        ingredient = new Ingredient(INGREDIENT_TYPE, INGREDIENT_NAME, INGREDIENT_PRICE); // Создаем ингредиент
        assertEquals(INGREDIENT_NAME, ingredient.getName()); // Проверяем, что getName возвращает правильное имя
    }

    @Test
    @DisplayName("Получение цены ингредиента")
    void getPrice() {
        ingredient = new Ingredient(INGREDIENT_TYPE, INGREDIENT_NAME, INGREDIENT_PRICE); // Создаем ингредиент
        assertEquals(INGREDIENT_PRICE, ingredient.getPrice()); // Проверяем, что getPrice возвращает правильную цену
    }
}