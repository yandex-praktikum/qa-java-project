package praktikum;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class IngredientTest {

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void getTypeReturnsCorrectType(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "Test ingredient", 50.0f);
        assertEquals(type, ingredient.getType());
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void getNameReturnsCorrectName(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "Test ingredient", 50.0f);
        assertEquals("Test ingredient", ingredient.getName());
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void getPriceReturnsCorrectPrice(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "Test ingredient", 50.0f);
        assertEquals(50.0f, ingredient.getPrice());
    }

}