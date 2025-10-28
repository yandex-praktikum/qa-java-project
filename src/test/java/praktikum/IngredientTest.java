package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTest {

    @Test
    public void testGetName() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "ketchup", 50);
        assertEquals("ketchup", ingredient.getName());
    }

    @Test
    public void testGetPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", 150);
        assertEquals(150, ingredient.getPrice(), 0.0);
    }

    @Test
    public void testGetType() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "mustard", 70);
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }
}