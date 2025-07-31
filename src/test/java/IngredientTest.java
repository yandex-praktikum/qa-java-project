import org.junit.jupiter.api.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @Test
    void constructorShouldSetAllFieldsCorrectly() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Ketchup", 0.5f);

        assertEquals(IngredientType.SAUCE, ingredient.getType());
        assertEquals("Ketchup", ingredient.getName());
        assertEquals(0.5f, ingredient.getPrice(), 0.0001f);
    }

    @Test
    void getTypeShouldReturnCorrectType() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Beef", 3.0f);
        assertEquals(IngredientType.FILLING, ingredient.getType());
    }

    @Test
    void getNameShouldReturnCorrectName() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Mayonnaise", 0.7f);
        assertEquals("Mayonnaise", ingredient.getName());
    }

    @Test
    void getPriceShouldReturnCorrectPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Mustard", 0.4f);
        assertEquals(0.4f, ingredient.getPrice(), 0.0001f);
    }
}
