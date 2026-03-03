package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {
    @ParameterizedTest
    @ValueSource(floats = {50, 80, 5.2598f})
    @DisplayName("Проверка getPrice")
    public void testGetPrice(float price) {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, null, price);
        assertEquals(price, ingredient.getPrice());
    }

    @ParameterizedTest
    @ValueSource(strings = {"котлета","сыр","помидор"})
    @DisplayName("Проверка getName")
    public void testGetName(String name) {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, name, 50);
        assertEquals(name, ingredient.getName());
    }

    @ParameterizedTest
    @EnumSource(value = IngredientType.class, names = {"SAUCE", "FILLING"})
    @DisplayName("Проверка getType")
    public void testGetType(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, null, 100);
        assertEquals(type, ingredient.getType());
    }
}