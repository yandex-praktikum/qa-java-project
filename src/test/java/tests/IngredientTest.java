package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @Test
    @DisplayName("Конструктор Ingredient корректно устанавливает поля")
    void constructorIngredientSetCorrectionFields() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", 100.0f);

        assertEquals(IngredientType.FILLING, ingredient.getType(), "Тип должен быть FILLING");
        assertEquals("cutlet", ingredient.getName(), "Имядолжно быть 100,0");
        assertEquals(100.0f, ingredient.getPrice(), "Цена должна быть 100,0");
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    @DisplayName("Ingredient возвращает корректный тип")
    void shouldReturnCorrectType(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "test", 50.0f);
        assertEquals(type, ingredient.getType());
    }

    @ParameterizedTest
    @ValueSource(strings = {"sauce", "cheese", "lettuce"})
    @DisplayName("Ingredient возвращает корректное имя")
    void shouldReturnCorrectName(String name) {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, name, 30.0f);
        assertEquals(name, ingredient.getName());
    }

    @ParameterizedTest
    @ValueSource(floats = {0.0f, 99.9f, 500.5f})
    @DisplayName("Ingredient возвращает корректную цену")
    void shouldReturnCorrectPrice(float price) {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "test", price);
        assertEquals(price, ingredient.getPrice());
    }
}
