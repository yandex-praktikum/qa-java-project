package praktikum;

import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;

public class IngredientTest {

    @Test
    public void testGettersCorrectlyReturnValues() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Tomato", 50.0f);
        assertEquals("Tomato", ingredient.getName());
        assertEquals(50.0f, ingredient.getPrice(), 0.001);
        assertEquals(IngredientType.FILLING, ingredient.getType());
    }

    @Test
    public void testMaxLengthIngredientName() {
        String maxLengthName = "A".repeat(255);
        Ingredient ing = new Ingredient(IngredientType.SAUCE, maxLengthName, 100.0f);
        assertEquals(maxLengthName, ing.getName());
    }

    @Test
    public void testMinPositiveIngredientPrice() {
        Ingredient ing = new Ingredient(IngredientType.FILLING, "Minimal Price Ing", Float.MIN_VALUE);
        assertEquals(Float.MIN_VALUE, ing.getPrice(), 0.001);
    }

    @Test
    public void testDirectFieldChange() {
        Ingredient ing = new Ingredient(IngredientType.SAUCE, "Ketchup", 100.0f);
        ing.name = "Mustard";
        ing.price = 150.0f;
        assertEquals("Mustard", ing.getName());
        assertEquals(150.0f, ing.getPrice(), 0.001);
    }

    @Test
    public void testExtremeIngredientPrices() {
        Ingredient extremePriceIng = new Ingredient(IngredientType.SAUCE, "Expensive Sauce", Float.MAX_VALUE);
        assertEquals(Float.MAX_VALUE, extremePriceIng.getPrice(), 0.001);
    }

    @Test
    public void testRenamingIngredient() {
        Ingredient ing = new Ingredient(IngredientType.SAUCE, "Initial Name", 100.0f);
        ing.name = "Updated Name";
        assertEquals("Updated Name", ing.getName());
    }
}