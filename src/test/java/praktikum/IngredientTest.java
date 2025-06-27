package praktikum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    @Test
    public void testGetType() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Hot Sauce", 99.9f);
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }

    @Test
    public void testGetName() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Hot Sauce", 99.9f);
        assertEquals("Hot Sauce", ingredient.getName());
    }

    @Test
    public void testGetPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Hot Sauce", 99.9f);
        assertEquals(99.9f, ingredient.getPrice());
    }
}
