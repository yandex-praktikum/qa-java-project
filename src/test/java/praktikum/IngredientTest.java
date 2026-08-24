package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    public void getTypeReturnsCorrectType(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "ingredient", 100.0f);

        IngredientType actualType = ingredient.getType();

        assertEquals(type, actualType);
    }

    @Test
    public void getNameReturnsCorrectName() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100.0f);

        String actualName = ingredient.getName();

        assertEquals("hot sauce", actualName);
    }

    @Test
    public void getPriceReturnsCorrectPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100.0f);

        float actualPrice = ingredient.getPrice();

        assertEquals(100.0f, actualPrice, 0.001f);
    }
}