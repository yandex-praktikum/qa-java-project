package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @Test
    void getPriceReturnsPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "AnyIngredient", 15);
        assertEquals(15, ingredient.getPrice());
    }

    @Test
    void getNameReturnsName() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "AnyIngredient", 15);
        assertEquals("AnyIngredient", ingredient.getName());
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void getTypeReturnsCorrectType(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "test", 10.0f);

        IngredientType actualType = ingredient.getType();

        assertEquals(type, actualType);
    }


}
