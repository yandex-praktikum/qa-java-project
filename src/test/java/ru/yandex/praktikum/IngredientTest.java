package ru.yandex.praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @Test
    @DisplayName("Получение имени ингридиента")
    public void getIngredientName() {

        Ingredient ingredient = new Ingredient(
                IngredientType.SAUCE, "Соус Spicy-X", 55f);

        assertEquals("Соус Spicy-X", ingredient.getName());
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    @DisplayName("Получение типа ингридиента")
    public void getTypeIngredient(IngredientType ingredientType) {

        Ingredient ingredient = new Ingredient(ingredientType, "Test", 105f);

        assertEquals(ingredientType, ingredient.getType());
    }

    @Test
    @DisplayName("Получение цены ингридиента")
    public void getPriceForIngredient() {

        Ingredient ingredient = new Ingredient(
                IngredientType.FILLING, "cutlet", 999f);

        assertEquals(999f, ingredient.getPrice(), 0.001);
    }
}