package praktikum;


import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class IngredientTest {

    @Test
    public void testGetPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Ketchup", 0.5f);
        assertEquals(0.5f, ingredient.getPrice(), 0.00001f);
    }

    @Test
    public void testGetName() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Meat Patty", 2.0f);
        assertEquals("Meat Patty", ingredient.getName());
    }

    @Test
    public void testGetType() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Mustard", 1.0f);
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }
}