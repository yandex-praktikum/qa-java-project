import org.junit.jupiter.api.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void testIngredientGetters() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Cheese", 1.5f);
        assertEquals("Cheese", ingredient.getName());
        assertEquals(1.5f, ingredient.getPrice());
        assertEquals(IngredientType.FILLING, ingredient.getType());
    }

    @Test
    void testIngredientSauce() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Ketchup", 0.5f);
        assertEquals("Ketchup", ingredient.getName());
        assertEquals(0.5f, ingredient.getPrice());
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }
}
